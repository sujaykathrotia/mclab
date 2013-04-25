package natlab.backends.JavaScript.codegen.casehandlers;

import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.TIRIfStmt;
import natlab.tame.valueanalysis.advancedMatrix.AdvancedMatrixValue;

public class JSHandleCaseTIRIfStmt {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg, TIRIfStmt node) {
		String indent = node.getIndent();
		String rhsValue = node.getConditionVarName().getID();
		fcg.buf.append(indent + "if (" + rhsValue + ") {\n");
		fcg.indentIf = true;
		fcg.printStatements(node.getIfStameents());
		fcg.indentIf = false;
		fcg.buf.append(indent + "} else {\n");
		fcg.indentIf = true;
		fcg.printStatements(node.getElseStatements());
		fcg.indentIf = false;
		fcg.buf.append(indent + "}");
		return fcg;
	}
}