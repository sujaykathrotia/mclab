package natlab.backends.JavaScript.codegen.casehandlers;

import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.TIRForStmt;

public class JSHandleCaseTIRForStmt {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg,
			TIRForStmt node) {
		String indent = node.getIndent();
		String inc = "1";
		if (node.getIncName() != null)
			inc = node.getIncName().getVarName();
		fcg.buf.append(indent + "for(" + node.getLoopVarName().getVarName()
				+ " = " + node.getLowerName().getVarName() + "; "
				+ node.getLoopVarName().getVarName() + " <= "
				+ node.getUpperName().getVarName() + "; "
				+ node.getLoopVarName().getVarName() + "+=" + inc + ") {\n");
		fcg.indentFW = true;
		fcg.printStatements(node.getStatements());
		fcg.indentFW = false;
		fcg.buf.append(indent + "}");
		fcg.forStmtParameter.add(node.getLoopVarName().getVarName());
		fcg.forStmtParameter.add(node.getLowerName().getVarName());
		fcg.forStmtParameter.add(node.getUpperName().getVarName());
		return fcg;
	}
}
