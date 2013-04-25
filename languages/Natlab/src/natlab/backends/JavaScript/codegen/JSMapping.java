package natlab.backends.JavaScript.codegen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class JSMapping {
	private static HashMap<String, String> JSBinOperatorMap = new HashMap<String, String>();
	private static HashMap<String, String> JSCondOperatorMap = new HashMap<String, String>();
	private static HashMap<String, String> JSUnOperatorMap = new HashMap<String, String>();
	private static HashMap<String, String> JSDirectBuiltinMap = new HashMap<String, String>();
	private static Set<String> JSNoDirectBuiltinSet = new HashSet<String>();
	private static HashMap<String, String> JSBuiltinConstMap = new HashMap<String, String>();
	private static HashMap<String, String> JSIOOperationMap = new HashMap<String, String>();

	public JSMapping() {
		makeJSBinOperatorMap();
		makeJSUnOperatorMap();
		makeJSDirectBuiltinMap();
		makeJSNoDirectBuiltinSet();
		makeJSBuiltinConstMap();
		makeJSIOOperationMap();
		makeJSCondOperatorMap();
	}

	private static void makeJSBinOperatorMap() {
		JSBinOperatorMap.put("mldivide", "\\");
		JSBinOperatorMap.put("ldivide", "\\");
		JSBinOperatorMap.put("rdivide", "/");
	}
	
	private static void makeJSCondOperatorMap() {
		JSCondOperatorMap.put("and", "&&");
		JSCondOperatorMap.put("or", "||");
		JSCondOperatorMap.put("lt", "<");
		JSCondOperatorMap.put("gt", ">");
		JSCondOperatorMap.put("le", "<=");
		JSCondOperatorMap.put("ge", ">=");
		JSCondOperatorMap.put("eq", "==");
		JSCondOperatorMap.put("ne", "!=");
	}

	private static void makeJSUnOperatorMap() {
		JSUnOperatorMap.put("uplus", "+");
	}

	private static void makeJSDirectBuiltinMap() {
		// create a categorical map here
		JSDirectBuiltinMap.put("times", "_McLib.dot");
		JSDirectBuiltinMap.put("plus", "_McLib.add");
		JSDirectBuiltinMap.put("minus", "_McLib.sub");
		JSDirectBuiltinMap.put("mtimes", "_McLib.mul");
		JSDirectBuiltinMap.put("mrdivide", "_McLib.div");
		JSDirectBuiltinMap.put("uminus", "_McLib.neg");
		JSDirectBuiltinMap.put("abs", "_McLib.abs");
		JSDirectBuiltinMap.put("mean", "_McLib.average");
		JSDirectBuiltinMap.put("sqrt", "_McLib.sqrt");
		JSDirectBuiltinMap.put("sin", "_McLib.sin");
		JSDirectBuiltinMap.put("cos", "_McLib.cos");
		JSDirectBuiltinMap.put("sum", "_McLib.sum");
		JSDirectBuiltinMap.put("length", "_McLib.length");
		JSDirectBuiltinMap.put("size", "_McLib.length");
		JSDirectBuiltinMap.put("exp", "_McLib.exp");
		JSDirectBuiltinMap.put("transpose", "_McLib.transpose");
		JSDirectBuiltinMap.put("ceil", "_McLib.ceil");
		JSDirectBuiltinMap.put("power", "_McLib.pow");
		JSDirectBuiltinMap.put("mpower", "_McLib.pow");
		JSDirectBuiltinMap.put("disp", "disp");
		JSDirectBuiltinMap.put("rand", "_McLib.random");
		JSDirectBuiltinMap.put("norm", "_McLib.norm2");
		JSDirectBuiltinMap.put("colon", "_McLib.range");
		JSDirectBuiltinMap.put("zeros", "_McLib.zeros");
		JSDirectBuiltinMap.put("ones", "_McLib.ones");
		JSDirectBuiltinMap.put("horzcat", "_McLib.horzcat");
		JSDirectBuiltinMap.put("vertcat", "_McLib.vertcat");
		JSDirectBuiltinMap.put("linspace", "_McLib.linspace");
	}

	private static void makeJSNoDirectBuiltinSet() {
		JSNoDirectBuiltinSet.add("randperm");
	}

	private static void makeJSBuiltinConstMap() {
		JSBuiltinConstMap.put("pi", "Math.PI");
	}

	private static void makeJSIOOperationMap() {
	}

	public static Boolean isJSBinOperator(String expType) {
		if (true == JSBinOperatorMap.containsKey(expType))
			return true;
		else
			return false;
	}

	public static String getJSBinOpMapping(String Operator) {
		return JSBinOperatorMap.get(Operator);
	}
	
	public static Boolean isJSCondOperator(String expType) {
		if (true == JSCondOperatorMap.containsKey(expType))
			return true;
		else
			return false;
	}

	public static String getJSCondOpMapping(String Operator) {
		return JSCondOperatorMap.get(Operator);
	}

	public static Boolean isJSUnOperator(String expType) {
		if (true == JSUnOperatorMap.containsKey(expType))
			return true;
		else
			return false;
	}

	public static String getJSUnOpMapping(String Operator) {
		return JSUnOperatorMap.get(Operator);
	}

	public static Boolean isJSDirectBuiltin(String expType) {
		if (true == JSDirectBuiltinMap.containsKey(expType))
			return true;
		else
			return false;
	}

	public static String getJSDirectBuiltinMapping(String BuiltinName) {
		return JSDirectBuiltinMap.get(BuiltinName);
	}

	public static Boolean isJSNoDirectBuiltin(String BuiltinName) {
		return JSNoDirectBuiltinSet.contains(BuiltinName);
	}

	public static Boolean isBuiltinConst(String expType) {
		if (true == JSBuiltinConstMap.containsKey(expType))
			return true;
		else
			return false;
	}

	public static String getJSBuiltinConstMapping(String BuiltinName) {
		return JSBuiltinConstMap.get(BuiltinName);
	}

	public static Boolean isJSIOOperation(String expType) {
		if (true == JSIOOperationMap.containsKey(expType))
			return true;
		else
			return false;
	}

	public static String getJSIOOperationMapping(String Operator) {
		return JSIOOperationMap.get(Operator);
	}
}