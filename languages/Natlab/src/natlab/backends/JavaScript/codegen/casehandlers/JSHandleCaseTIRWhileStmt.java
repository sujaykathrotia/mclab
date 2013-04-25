package natlab.backends.JavaScript.codegen.casehandlers;

import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.TIRWhileStmt;

public class JSHandleCaseTIRWhileStmt {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg, TIRWhileStmt node){
		String indent = node.getIndent();
		
		String rhsValue = node.getCondition().getVarName();
		fcg.buf.append(indent + "while (" + rhsValue + ") {\n");
		fcg.indentFW = true;
		fcg.printStatements(node.getStatements());
		fcg.indentFW = false;
		fcg.buf.append(indent + "}");
		return fcg;
	}
}
