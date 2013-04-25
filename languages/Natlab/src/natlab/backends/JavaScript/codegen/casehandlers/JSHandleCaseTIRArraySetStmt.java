package natlab.backends.JavaScript.codegen.casehandlers;

import java.util.ArrayList;

import ast.Name;
import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.TIRArraySetStmt;

public class JSHandleCaseTIRArraySetStmt {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg,
			TIRArraySetStmt node) {
		String indent = node.getIndent();
		
		String indexList = node.getIndizes().toString();
		String[] tokens = indexList.replace("[", "").replace("]", "")
				.split("[,]");
		
		String indexString = "";
		for (String indexName : tokens) {
			indexString = indexString + "[" + indexName + "-1]";
		}
		
		fcg.buf.append(indent + node.getArrayName().getVarName()
				+ indexString + " = "
				+ node.getValueName().getVarName() + ";");
		for (Name index : node.getIndizes().asNameList()) {
			fcg.arrayIndexParameter.add(index.getVarName());
		}
		return fcg;
	}
}
