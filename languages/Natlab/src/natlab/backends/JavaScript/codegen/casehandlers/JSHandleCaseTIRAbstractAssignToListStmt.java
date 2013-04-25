package natlab.backends.JavaScript.codegen.casehandlers;

import java.util.ArrayList;

import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.TIRAbstractAssignToListStmt;

public class JSHandleCaseTIRAbstractAssignToListStmt {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg,
			TIRAbstractAssignToListStmt node) {
		if (JSMapping.isJSNoDirectBuiltin(node.getRHS().getVarName())) {
			// no direct mapping SKIP it for now.
		} else {
			String indent = node.getIndent();
			String LHS;
			ArrayList<String> vars = new ArrayList<String>();
			// All output variables
			for (ast.Name name : node.getTargets().asNameList()) {
				vars.add(name.getID());
			}
			/* dealing with difference number of output. */
			if (vars.size() > 1) {				
				LHS = vars.get(vars.size()-1);
				String exp = fcg.makeExpression(node, LHS);
				if (exp != null && !exp.trim().equals("")) {
					fcg.buf.append(indent + LHS + " = " + fcg.buf.append(exp));
					for (int i = 0; i < vars.size(); i++) {
						fcg.buf.append(indent + vars.get(i) + " = " + LHS + "[" + i + "];");
					}
				}
			} else if (1 == vars.size()) {
				LHS = vars.get(0);
				String exp = fcg.makeExpression(node, LHS);
				if (exp != null && !exp.trim().equals("")) {
					fcg.buf.append(indent + LHS + " = ");
					fcg.buf.append(exp);
				}
			} else if (0 == vars.size()) {
				fcg.buf.append(indent + fcg.makeExpression(node));
			}
		}
		return fcg;
	}
}