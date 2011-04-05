package natlab.Static.callgraph;

import java.util.*;

import natlab.Static.ir.transform.ThreeAddressToIR;
import natlab.Static.mc4.Mc4;
import natlab.toolkits.BiMap;
import natlab.toolkits.analysis.varorfun.*;
import natlab.toolkits.rewrite.*;
import natlab.toolkits.rewrite.inline.*;
import natlab.toolkits.rewrite.simplification.AbstractSimplification;

import ast.*;

/**
 * 
 * This represents a static function, as stored in a FunctionCollection Object.
 * It stores the AST of a function in IR form, it's location (via a FunctionReference),
 * and all the functions called (sort of a basic symbol table).
 * and other useful information.
 * 
 */
public class StaticFunction implements Cloneable {
    Function function;
    FunctionReference reference; //reference to this function
    String name;
    HashSet<String> siblings; //TODO - make these a hashmap<string,StaticFunction>
    BiMap<String,FunctionReference> calledFunctions;
    
    /**
     * Creates a Mc4 Function, given an ast and the source file.
     * This transforms the function to 3 address code and builds the symbol table.
     * 
     * @param function the AST of the function, will get copied
     * @param source the file where it is from
     * @param a function finder, to resolve IDs into Function references
     */
    public StaticFunction(Function function, FunctionReference reference) {
        this.function = function.fullCopy();
        this.reference = reference;
        this.calledFunctions = new BiMap<String,FunctionReference>();
        this.name = function.getName();
        
        
        //set siblings
        siblings = new HashSet<String>(function.getSiblings().keySet());
        

        for (Stmt s : this.function.getStmtList()){
            System.out.println(s.getPrettyPrinted()+"| "+s.getClass().getName()+
                    " "+s.getPosString());
        }
        System.out.println(this.function.getPrettyPrinted()); //print result

        System.exit(0);
        
        
        //transform to IR
        transformToIR();
        
        System.out.println(this.function.getPrettyPrinted()); //print result
        System.exit(0);
        
        //create first symbol table
        findAndResolveFunctions();
    }
    
    public StaticFunction clone(){
        StaticFunction f = null;
        try {
            f = (StaticFunction)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
        f.calledFunctions = new BiMap<String,FunctionReference>(getCalledFunctions());
        f.function = function.fullCopy();
        f.siblings = new HashSet<String>(siblings);
        return f;
    }
    
    //creates and puts first order approximation of the symbol table - variable or function
    private void findAndResolveFunctions(){
        //perform variable or function analysis on function and get result
        VFPreorderAnalysis functionAnalysis = new VFPreorderAnalysis(this.function,
                Mc4.functionFinder.getFunctionOrScriptQuery());
        functionAnalysis.analyze();        
        VFFlowset flowset; 
        flowset = functionAnalysis.getFlowSets().get(function);
        Mc4.debug("create symbol table - function "+name+" variable or function analysis result:\n"+flowset);
        
        //go through all symbols, and put them in the symbol table
        for (ValueDatumPair<String, ? extends VFDatum> pair : flowset.toList()){
            VFDatum vf = pair.getDatum();
            String name = pair.getValue();
            
            if (vf.isFunction()){
                calledFunctions.put(name, null); 
                //TODO actually resolve names
            } else if (vf.isVariable()){
            } else {
                System.err.println("symbol table: "+name+" in "+this.name+" cannot be resolved as a function or variable. vf:"+vf);
                calledFunctions.put(name, null); 
            }
        }
        //TODO are there any other symbols?
        
        Mc4.debug(this.toString());
        Mc4.debug("first symbol table for "+name+":"+calledFunctions);
        //TODO deal with errors
    }
    
    
    //apply simplification on the thing
    public void applySimplification(Class<? extends AbstractSimplification> simplification){
        //TODO
        
    }
    
    
    //transforms the underlying AST to 3 address code
    private void transformToIR(){
        FunctionList fList = new FunctionList();
        fList.addFunction(function);
        Simplifier simplifier =
            new Simplifier(fList, ThreeAddressToIR.class);
        fList = ((FunctionList)simplifier.simplify());
    	this.function = fList.getFunction(0);
    }
    
    
    //getter methods
    public HashMap<String,FunctionReference> getCalledFunctions(){ return calledFunctions; }
    public String getName(){ return name; }
    public Function getAst(){ return function; }
    public FunctionReference getReference(){ return reference; }
    public Set<String> getSiblings(){ return siblings; }
    
    
    
    /**
     * inlines a copy of the given function into this, if it is called from this function.
     * Note: in order to inline multiple functions, use inline(Map<FunctionReference, StaticFunction>)
     */
    public void inline(StaticFunction other){
        HashMap<FunctionReference,StaticFunction> map = 
            new HashMap<FunctionReference,StaticFunction>();
        map.put(other.reference, other);
        inline(map);
    }
    
    
    /**
     * inlines a copy of all functions that are in the given map,
     * and which are called from this function
     */
    public void inline(Map<FunctionReference, StaticFunction> map){
    	HashMap<String, Function> inlinerMap = new HashMap<String,Function>();
    	
    	//build inliner map
    	for (FunctionReference ref : map.keySet()){
    	    if (calledFunctions.containsValue(ref)){ //we need to inline ref
    	        //ref is in the map and is called from this - we need to inline it
    	        StaticFunction otherFunction = map.get(ref);

    	        //create a copy of the function
    	        otherFunction = otherFunction.clone();

    	        //merge symbol tables
    	        mergeSymbols(otherFunction, otherFunction.name.substring(0,4)+"_");

    	        //for any name that maps to ref, we need to inline it
    	        for (String name : calledFunctions.getKeys(ref)){
    	            //put it in the inline list
    	            inlinerMap.put(name, otherFunction.function);
    	        }
    		}
    	}
    	
    	//actually inline
    	Inliner<Function,Function> inliner = new Inliner<Function,Function>(this.function,inlinerMap);
    	this.function = (Function)inliner.transform();
    }
    
        
    @Override
    public String toString() {
        return 
            "Function: "+name+
            "\ncalled functions:\n"+calledFunctions+
            "\ncode:\n"+function.getPrettyPrinted();
    }
    
    
    
    /**
     * merges the symbols of this Function with the given other Function
     * This will rename all symbols of the other function that are
     * already present in this symbol table - unless they refer to the same function,
     * or if they refer to a function in the other function but not in this, in which
     * case the variable in this will be renamed. Unless it is a builtin, which
     * won't get renamed.
     * 
     * adds the called functions of the other map to this.
     * 
     * Variables get renamed to <prefix><original name><postfix>,
     * where the prefix is a given argument, and the postfix are
     * some set of characters (usually numbers) that ensure uniqueness.
     * To rename variables in this, the prefix will be empty.
     * 
     */
    public void mergeSymbols(StaticFunction otherFunction,String prefix){
        //the rename maps for both functions
        HashMap<String,String> renameOther = new HashMap<String,String>();
        HashMap<String,String> renameThis = new HashMap<String,String>();
        
        Set<String> symbols = function.getSymbols();
        Set<String> otherSymbols = otherFunction.function.getSymbols();
        
        //build rename maps - find name conflicts
        for (String name : otherSymbols){
            if (symbols.contains(name)){ //name conflict
                if (name.equals("abs")){
                    System.err.println(calledFunctions.get(name));
                    System.err.println(otherFunction.calledFunctions.get(name));
                    System.err.println(otherFunction);
                }
                
                boolean doRenameOther = true; //we always rename other except for the following cases               
                //case: name is fun in other only - rename in this
                if (otherFunction.calledFunctions.containsKey(name)
                        && !calledFunctions.containsKey(name)){
                    doRenameOther = false;
                }
                
                if (otherFunction.calledFunctions.containsKey(name)
                        && calledFunctions.containsKey(name)){
                    //case: name is fun in both and they refer to the same - don't rename 
                    if(calledFunctions.get(name).equals(otherFunction.calledFunctions.get(name))){
                        continue; // don't rename
                    } else {
                    //case: name is fun in both, refer to different functions, and other is builtin - rename this
                        if(otherFunction.calledFunctions.get(name).isBuiltin()) doRenameOther = false;
                    }
                }
                
                //generate new name and put in correct rename map
                String newName = RenameSymbols.getNewName(prefix + name,
                        symbols,otherSymbols,renameThis.values(),renameOther.values());
                if (doRenameOther){
                    renameOther.put(name, newName);
                }else{
                    renameThis.put(name, newName);
                }
            }
        }
        
        //actually rename
        rename(renameThis);
        otherFunction.rename(renameOther);
        
        //add the others' calls
        calledFunctions.putAll(otherFunction.calledFunctions);
        
    }
 
    /**
     * renames all symbols in this function according to the given map. That
     * is, all symbols that are keys in the map are renamed to the corresponding value.
     * This will also update the calledFunctions set.
     * @param renameMap
     */
    public void rename(Map<String,String> renameMap){
        //update AST
        RenameSymbols rename = new RenameSymbols(function, renameMap);
        
        //update called Functions map
        for (String key : renameMap.keySet()){
            if (calledFunctions.containsKey(key)){
                calledFunctions.put(renameMap.get(key),calledFunctions.remove(key));
            }
        }
        
        function = (Function)rename.transform();        
    }
}


