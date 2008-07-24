package fir.builtin.matlabBuiltins;
import fir.ast.Constant;
import fir.codegen.CodeGenInterfacer;
import fir.table.*;
import fir.type.*;

public abstract class Transcendental extends MatlabBuiltin {
	@Override
	public void generate(CodeGenInterfacer interfacer, Signature signature) {
		if (interfacer.haveToInsertExpression()){ //we only need to insert expression
			interfacer.insertExpression(getName()+"("+signature.getarg()[0].fortranName+")")
		} else {
			System.err.println("Transcendal (class Transcendental) cannot generate subrotine!"); ///TODO
		}
	}

	@Override
	public void generateWithConstants(CodeGenInterfacer interfacer,
			Signature signature, Constant[] constants) {
		generate(interfacer,signature);
	}

	@Override
	public Constant[] getResults(Constant[] inputs, int numberOfOutputs) {
		if (inputs.length > 1 || inputs.length < 1 || numberOfOutputs > 1){
			return null;
		}
		if (numberOfOutputs == 0){ return new Constant[0];}
		Constant[] outs = new Constant[1];
		outs[0] = getResult(inputs);
		return outs;
	}

	@Override
	public Type getReturnType(Type[] inputTypes) {
		if (inputTypes.length > 1 || inputTypes.length < 1) return null;
		if (inputTypes[0].getIntrinsic() instanceof Numeric){
			return inputTypes[0];
		}
		return null;
	}

	@Override
	public Type[] getReturnTypes(Type[] inputTypes, int numberOfOutputs) {
		if (inputTypes.length > 1 || inputTypes.length < 1 || numberOfOutputs > 1){
			return null;
		}
		if (numberOfOutputs == 0){ return new Type[0];}
		Type[] outs = new Type[1];
		outs[0] = getReturnType(inputTypes);
		return outs;
	}

	@Override
	public boolean isDefined(Type[] inputTypes) {
		return getReturnType(inputTypes)!=null; //
	}

	@Override
	public boolean isDefined(Type[] inputTypes, int numberOfOutputs) {
		return getReturnTypes(inputTypes,numberOfOutputs)!=null;
	}
	
	@Override
	public boolean isDefined(SignatureType signature) {
		// TODO Auto-generated method stub
		return false;
	}

}
