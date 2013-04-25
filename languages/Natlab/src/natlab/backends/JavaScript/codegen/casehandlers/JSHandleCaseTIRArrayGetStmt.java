package natlab.backends.JavaScript.codegen.casehandlers;

import java.util.ArrayList;

import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.TIRArrayGetStmt;

public class JSHandleCaseTIRArrayGetStmt {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg,
			TIRArrayGetStmt node) {
		String indent = node.getIndent();
		String indexList = node.getIndizes().toString();
		String[] tokens = indexList.replace("[", "").replace("]", "")
				.split("[,]");
		
		String indexString = "";
		for (String indexName : tokens) {
			indexString = indexString + "[" + indexName + "-1]";
		}

		fcg.buf.append(indent
				+ node.getLHS().getNodeString().replace("[", "")
						.replace("]", "") + " = "
				+ node.getArrayName().getVarName()
				+ indexString + ";");
		for (String indexName : tokens) {
			if (indexName.equals(":")) {
				// ignore this
			} else {
				fcg.arrayIndexParameter.add(indexName);
			}
		}
		return fcg;
	}
}
