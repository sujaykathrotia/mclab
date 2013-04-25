package natlab.backends.JavaScript.codegen.casehandlers;

import java.util.ArrayList;

import ast.FPLiteralExpr;
import ast.IntLiteralExpr;
import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.*;
import natlab.tame.valueanalysis.advancedMatrix.AdvancedMatrixValue;

public class JSHandleCaseTIRAssignLiteralStmt {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg,
			TIRAssignLiteralStmt node) {
		String LHS;
		LHS = node.getTargetName().getVarName();
		String RHS;
		String indent = node.getIndent();
		if (node.getRHS() instanceof IntLiteralExpr) {
			RHS = ((IntLiteralExpr) node.getRHS()).getValue().getValue()
					.toString();
		} else {
			RHS = ((FPLiteralExpr) node.getRHS()).getValue().getValue()
					.toString();
		}
			ArrayList<Integer> dim = new ArrayList<Integer>(
					((AdvancedMatrixValue) (fcg.analysis.getNodeList()
							.get(fcg.index).getAnalysis().getCurrentOutSet()
							.get(LHS).getSingleton())).getShape()
							.getDimensions().size());
			try {
				for (Integer intgr : dim) {
					String test = intgr.toString();
				}
			} catch (Exception e) {
				fcg.buf.append("allocate(" + LHS + "(1, 1));\n  ");
			}
			fcg.buf.append(indent + LHS + " = " + RHS + ";");
		return fcg;
	}
}
