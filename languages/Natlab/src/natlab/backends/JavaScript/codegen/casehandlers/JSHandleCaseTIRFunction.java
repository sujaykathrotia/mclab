package natlab.backends.JavaScript.codegen.casehandlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ast.Name;
import natlab.backends.JavaScript.codegen.*;
import natlab.tame.tir.*;
import natlab.tame.valueanalysis.advancedMatrix.AdvancedMatrixValue;

/*
 * Main tasks are handling return variables and arguments.
 */
public class JSHandleCaseTIRFunction {
	public static JSCodePrettyPrinter getJS(JSCodePrettyPrinter fcg, TIRFunction node) {
		fcg.majorName = node.getName();
		for (Name param : node.getInputParams()) {
			fcg.inArgs.add(param.getVarName());
		}
		
		String indent = node.getIndent();
		boolean first = true;
		
		fcg.printStatements(node.getStmts());

		fcg.buf2.append(indent + "function ");
		fcg.buf2.append(fcg.majorName);
		fcg.buf2.append("(");
		first = true;
		for (Name param : node.getInputParams()) {
			if (!first) {
				fcg.buf2.append(", ");
			}
			fcg.buf2.append(param.getVarName());
			first = false;
		}
		first = true;
		fcg.buf2.append(") {");
		fcg.buf2.append("\n");
		fcg.buf.append(indent + "  return");
		for (Name result : node.getOutputParams()) {
			fcg.outRes.add(result.getVarName());
			if (!first) {
				fcg.buf.append(", ");
			} else {
				fcg.buf.append(" [");
			}
			fcg.buf.append(result.getVarName());
			first = false;
		}
		if(first == false) {
			fcg.buf.append("]");
		}
		fcg.buf.append(";\n");
		fcg.buf.append(indent + "}");
		fcg.buf2.append(fcg.buf);
		
		return fcg;
	}
}
