package natlab.backends.JavaScript.codegen;

import java.util.ArrayList;
import java.util.HashMap;

import ast.ASTNode;

import natlab.backends.JavaScript.codegen.casehandlers.*;
import natlab.tame.tir.*;
import natlab.tame.tir.analysis.TIRAbstractNodeCaseHandler;
import natlab.tame.valueanalysis.ValueAnalysis;
import natlab.tame.valueanalysis.advancedMatrix.AdvancedMatrixValue;
import natlab.tame.valueanalysis.aggrvalue.AggrValue;

public class JSCodePrettyPrinter extends TIRAbstractNodeCaseHandler {

	public ValueAnalysis<AggrValue<AdvancedMatrixValue>> analysis;
	public StringBuffer buf;
	public StringBuffer buf2;
	public ArrayList<String> forStmtParameter;
	public ArrayList<String> arrayIndexParameter;
	public int callgraphSize;
	public int index;
	public String fileDir;
	public String majorName;
	public ArrayList<String> inArgs;
	public ArrayList<String> outRes;
	public HashMap<String, String> funcNameRep;// <function name, substitute variable name>
	public boolean indentIf;
	public boolean indentFW;
	public boolean isSubroutine; // distinguish subroutine with function.

	public JSCodePrettyPrinter(
			ValueAnalysis<AggrValue<AdvancedMatrixValue>> analysis,
			int callgraphSize, int index) {
		this.analysis = analysis;
		this.buf = new StringBuffer();
		this.buf2 = new StringBuffer();
		this.forStmtParameter = new ArrayList<String>();
		this.arrayIndexParameter = new ArrayList<String>();
		this.callgraphSize = callgraphSize;
		this.index = index;
		this.majorName = "";
		this.inArgs = new ArrayList<String>();
		this.outRes = new ArrayList<String>();
		this.funcNameRep = new HashMap<String, String>();
		this.indentIf = false;
		this.indentFW = false;
		this.isSubroutine = false;
		
		new JSMapping();
		((TIRNode) analysis.getNodeList().get(index).getAnalysis().getTree())
				.tirAnalyze(this);
	}

	public static String JSCodePrint(
			ValueAnalysis<AggrValue<AdvancedMatrixValue>> analysis,
			int callgraphSize, int index) {
		return new JSCodePrettyPrinter(analysis, callgraphSize, index).buf2
				.toString();
	}

	@Override
	public void caseASTNode(ASTNode node) {
	}

	@Override
	public void caseTIRFunction(TIRFunction node) {
		JSHandleCaseTIRFunction.getJS(this, node);
	}

	@Override
	public void caseTIRAssignLiteralStmt(TIRAssignLiteralStmt node) {
		JSHandleCaseTIRAssignLiteralStmt.getJS(this, node);
	}

	@Override
	public void caseTIRIfStmt(TIRIfStmt node) {
		JSHandleCaseTIRIfStmt.getJS(this, node);
	}
	
	@Override
	public void caseTIRWhileStmt(TIRWhileStmt node){
		JSHandleCaseTIRWhileStmt.getJS(this, node);
	}

	@Override
	public void caseTIRForStmt(TIRForStmt node) {
		JSHandleCaseTIRForStmt.getJS(this, node);
	}

	@Override
	public void caseTIRArrayGetStmt(TIRArrayGetStmt node) {
		JSHandleCaseTIRArrayGetStmt.getJS(this, node);
	}
	
	@Override
	public void caseTIRArraySetStmt(TIRArraySetStmt node) {
		 JSHandleCaseTIRArraySetStmt.getJS(this, node);
	}

	@Override
	public void caseTIRAbstractAssignToListStmt(TIRAbstractAssignToListStmt node) {
		 JSHandleCaseTIRAbstractAssignToListStmt.getJS(this, node);
	}

	@Override
	public void caseTIRAbstractAssignToVarStmt(TIRAbstractAssignToVarStmt node) {
		JSHandleCaseTIRAbstractAssignToVarStmt abstractAssignToVarStmt = new JSHandleCaseTIRAbstractAssignToVarStmt();
		abstractAssignToVarStmt.getJS(this, node);
	}

	public String makeExpression(TIRAbstractAssignStmt node) {
		return makeExpression(node, "");
	}

	public String makeExpression(TIRAbstractAssignStmt node, String LHSVarName) {
		int RHSCaseNumber;
		String RHSJSOperator;
		String Operand1, Operand2, prefix = "";
		String ArgsListasString;
		RHSCaseNumber = getRHSCaseNumber(node);
		RHSJSOperator = getRHSMappingJSOperator(node);
		Operand1 = getOperand1(node);
		Operand2 = getOperand2(node);
		StringBuffer RHSexpression = new StringBuffer();
		ArrayList<String> Args = new ArrayList<String>();
		if (Operand2 != "" && Operand2 != null) {
			prefix = ", ";
		}
		switch (RHSCaseNumber) {
		case 0:
			RHSexpression.append(Operand1 + " " + RHSJSOperator + " "
					+ Operand2 + " ? true : false;");
			break;
		case 1:
			RHSexpression.append(Operand1 + " " + RHSJSOperator + " "
					+ Operand2 + " ;");
			break;
		case 2:
			RHSexpression.append(RHSJSOperator + "" + Operand1 + " ;"); // test this
			break;
		case 3:
			Args = getArgsList(node);
			ArgsListasString = getArgsListAsString(Args);
			RHSexpression.append(RHSJSOperator + "(" + ArgsListasString + ");");
			break;
		case 4:
			break;
		case 5:
			RHSexpression.append(RHSJSOperator + ";");
			break;
		case 6:
			Args = getArgsList(node);
			ArgsListasString = getArgsListAsString(Args);
			RHSexpression.append("" + RHSJSOperator + ArgsListasString);
			break;
		case 7:
			/**
			 * deal with user defined functions, apparently, there is no
			 * corresponding JS function for this.
			 */
			String RHSName;
			RHSName = node.getRHS().getVarName();
			String LHSName;
			LHSName = node.getLHS().getNodeString().replace("[", "")
					.replace("]", "");
			// XU, a little bit trick, go back to IR to get a better solution
			funcNameRep.put(RHSName, LHSName);
			Args = getArgsList(node);
			ArgsListasString = getArgsListAsString(Args);
			RHSexpression.append(RHSName + "(" + ArgsListasString + ");");
			break;
		case 8:
			RHSexpression.append("!(" + Operand1 + ");");
			break;
		default:
			RHSexpression.append("//is it an error?");
			break;
		}
		return RHSexpression.toString();
	}

	/********************** HELPER METHODS ***********************************/
	public int getRHSCaseNumber(TIRAbstractAssignStmt node) {
		String RHSMatlabOperator;
		RHSMatlabOperator = node.getRHS().getVarName();
		if (true == JSMapping.isJSCondOperator(RHSMatlabOperator)) {
			return 0; // "Conditional OP";
		} else if (true == JSMapping.isJSBinOperator(RHSMatlabOperator)) {
			return 1; // "binop";
		} else if (true == JSMapping.isJSUnOperator(RHSMatlabOperator)) {
			return 2; // "unop";
		} else if (true == JSMapping.isJSDirectBuiltin(RHSMatlabOperator)) {
			return 3; // "builtin";
		} else if (true == JSMapping.isBuiltinConst(RHSMatlabOperator)) {
			return 5; // "builtin";
		} else if (true == JSMapping.isJSIOOperation(RHSMatlabOperator)) {
			return 6; // "IO OPeration";
		} else if (RHSMatlabOperator == "not") {
			return 8;
		} else {
			return 7; // "user defined function";
		}
	}

	public String getOperand1(TIRAbstractAssignStmt node) {
		if (node.getRHS().getChild(1).getNumChild() >= 1)
			return node.getRHS().getChild(1).getChild(0).getNodeString();
		else
			return "";
	}

	public String getOperand2(TIRAbstractAssignStmt node) {
		if (node.getRHS().getChild(1).getNumChild() >= 2)
			return node.getRHS().getChild(1).getChild(1).getNodeString();
		else
			return "";
	}

	public String getRHSMappingJSOperator(TIRAbstractAssignStmt node) {
		String RHSJSOperator;
		String RHSMatlabOperator;
		RHSMatlabOperator = node.getRHS().getVarName();
		if (true == JSMapping.isJSCondOperator(RHSMatlabOperator)) {
			RHSJSOperator = JSMapping.getJSCondOpMapping(RHSMatlabOperator);
		} else if (true == JSMapping.isJSBinOperator(RHSMatlabOperator)) {
			RHSJSOperator = JSMapping.getJSBinOpMapping(RHSMatlabOperator);
		} else if (true == JSMapping.isJSUnOperator(RHSMatlabOperator)) {
			RHSJSOperator = JSMapping.getJSUnOpMapping(RHSMatlabOperator);
		} else if (true == JSMapping.isJSDirectBuiltin(RHSMatlabOperator)) {
			RHSJSOperator = JSMapping.getJSDirectBuiltinMapping(RHSMatlabOperator);
		} else if (true == JSMapping.isBuiltinConst(RHSMatlabOperator)) {
			RHSJSOperator = JSMapping.getJSBuiltinConstMapping(RHSMatlabOperator);
		} else if (true == JSMapping.isJSIOOperation(RHSMatlabOperator)) {
			RHSJSOperator = JSMapping.getJSIOOperationMapping(RHSMatlabOperator);
		} else if ("not" == RHSMatlabOperator) {
			RHSJSOperator = "!";
		} else {
			RHSJSOperator = "//cannot process it yet";
		}
		return RHSJSOperator;
	}

	public ArrayList<String> getArgsList(TIRAbstractAssignStmt node) {
		ArrayList<String> Args = new ArrayList<String>();
		int numArgs = node.getRHS().getChild(1).getNumChild();
		for (int i = 0; i < numArgs; i++) {
			Args.add(node.getRHS().getChild(1).getChild(i).getNodeString());
		}
		return Args;
	}

	public String getArgsListAsString(ArrayList<String> Args) {
		String prefix = "";
		String argListasString = "";
		for (String arg : Args) {
			argListasString = argListasString + prefix + arg;
			prefix = ", ";
		}
		return argListasString;
	}

	public void printStatements(ast.List<ast.Stmt> stmts) {
		for (ast.Stmt stmt : stmts) {
			if (indentIf == true) {
				buf.append("  ");
			} else if (indentFW == true) {
				buf.append(" ");
			}
			int length = buf.length();
			((TIRNode) stmt).tirAnalyze(this);
			if (buf.length() > length)
				buf.append('\n');
		}
	}
}
