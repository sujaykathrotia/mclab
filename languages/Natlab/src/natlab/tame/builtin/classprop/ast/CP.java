package natlab.tame.builtin.classprop.ast;

import java.util.*;

import beaver.Symbol;

import natlab.tame.builtin.classprop.ClassPropMatch;
import natlab.tame.builtin.classprop.PrecedenceInfo;
import natlab.tame.classes.reference.ClassReference;
import natlab.tame.valueanalysis.value.Value;


/**
 * This class is the parent class of all possible expressions in the 
 * domain specific language used to define class propagation information for
 * Matlab builtin functions. Note that the language just has expressions,
 * which can produce matches given input classes/values. The AST for this
 * language has a flat hierarchy.
 * 
 * The language is interpreted, tree based. Interpreting is done by the 
 * 'match' method, which will consume arguments and emit results.
 * 
 * See the classPropagationLanguage.txt in the ../builtin/gen folder.
 * 
 * @author adubra
 */
public abstract class CP extends Symbol{
    /**
     * computes the match result for the tree, given the input classes, and
     * (optional) values. The input values can be used to get extra information
     * about the arguments, but may be null. The input values should not be
     * used to query the argument class, because arguments may be coerced to
     * other classes.
     * previousMatchResult gives information about the matches so far, and
     * the current index in the arguments, and should not be altered.
     * This method returns null if the match failed.
     */
    abstract public ClassPropMatch match(boolean isLeft,ClassPropMatch previousMatchResult,
            List<ClassReference> inputClasses, List<? extends Value<?>> inputValues);
    
    
    /**
     * returns the string of the other. Places parentheses 
     */
    String print(CP other){
    	if (PrecedenceInfo.getPrecedence(other.getClass()) < PrecedenceInfo.getPrecedence(this.getClass())){
    		return "("+other.toString()+")";
    	}
    	return other.toString();    	
    }
    
    
    /**
     * returns an iterator over all the CP nodes contained in this ast.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<CP> getChildren(){
    	return Collections.EMPTY_LIST;
    }
    
    /**
     * sets values for the variables (see CPVar node). Returns this, for chaining.
     */
    public CP setVar(String name, CP value){
    	for (CP child : getChildren()){
    		child.setVar(name, value);    		
    	}
    	return this;
    }
}

