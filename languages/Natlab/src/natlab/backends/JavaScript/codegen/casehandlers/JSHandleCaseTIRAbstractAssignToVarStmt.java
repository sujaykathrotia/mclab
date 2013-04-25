package natlab.backends.JavaScript.codegen.casehandlers;

import java.util.ArrayList;

import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.TIRAbstractAssignToVarStmt;
import natlab.tame.valueanalysis.advancedMatrix.AdvancedMatrixValue;

public class JSHandleCaseTIRAbstractAssignToVarStmt {

	public JSHandleCaseTIRAbstractAssignToVarStmt() {

	}

	public JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg,
			TIRAbstractAssignToVarStmt node) {
		String indent = node.getIndent();

		fcg.buf.append(indent + node.getTargetName().getID() + " = "
				+ node.getRHS().getNodeString() + ";");
		// TODO check for expression on RHS
		// TODO check for built-ins
		// TODO check for operators
		return fcg;
	}
}