package natlab.backends.JavaScript;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import natlab.tame.valueanalysis.*;
import natlab.tame.valueanalysis.advancedMatrix.AdvancedMatrixValue;
import natlab.tame.valueanalysis.aggrvalue.*;
import natlab.tame.AdvancedTamerTool;
import natlab.toolkits.filehandling.GenericFile;
import natlab.toolkits.path.FileEnvironment;
import natlab.backends.JavaScript.codegen.*;

public class Main {
	public static void main(String[] args) {
		/* Error if parameters doesn't match */
		if(args.length < 2) {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("                                 ERROR                             ");
			System.out.println("-------------------------------------------------------------------\n");
			System.out.println("USE: java natlab.backends.JavaScript.Main <filepath> <inputshape>");
			System.out.println("e.g.: java natlab.backends.JavaScript.Main sample.m DOUBLE&1*1&REAL");
			System.out.println("-------------------------------------------------------------------");
		}
		
		/* File  */
		String[] inputShape = Arrays.copyOfRange(args, 1, args.length);
		
		/* File Handling */
		String fileIn = args[0];
		GenericFile gFile = GenericFile.create(fileIn);

		FileEnvironment env = new FileEnvironment(gFile);
		String dir = 
				env.getPwd().getPath() == "" ? "" : env.getPwd().getPath() + "/";
		// Get Input File Directory
		
		String file = gFile.getNameWithoutExtension();		// Get File name without Extention
		String fileOut = dir + file + ".js";				// Set Output file with extention
		String fileOutTame = dir + file + "_tame.m";		// Set TameIR output file
		
		/* Start Analysis */
		AdvancedTamerTool tool = new AdvancedTamerTool();
		System.out.println("Analyzing program...");
		
		ValueAnalysis<AggrValue<AdvancedMatrixValue>> analysis = null;
		
		try {
			analysis = tool.analyze(inputShape, env);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("\n\nERROR!! Analysis Failed!!");
			System.exit(0);
		}
		

		int size = analysis.getNodeList().size();

		/* Writing Tame IR */
		System.out.println("\nCreating file for TAME IR...");
		try {
			StringBuffer tamedCode = new StringBuffer();
			BufferedWriter out = new BufferedWriter(new FileWriter(fileOutTame));
			for (int i = 0; i < size; i++) {
				tamedCode.append(ValueAnalysisPrinter.prettyPrint(analysis
						.getNodeList().get(i).getAnalysis()));
			}
			out.write(tamedCode.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n\nERROR!! Cannot Create Tame IR file!!");
			System.exit(0);
		}
		System.out.println("Tame IR generated at: " + fileOutTame);

		/* pretty print the generated code. */
		String jsCode;
		System.out.println("\nGenerating JavaScript Code...");
		for (int i = 0; i < size; i++) {
			jsCode = JSCodePrettyPrinter.JSCodePrint(analysis, size, i);

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(fileOut));
				out.write(jsCode);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("\n\nERROR!! Cannot Create Output file!!");
				System.exit(0);
			}
		}
		System.out.println("JavaScript Code generated at: " + fileOut);
		
		/* Copy McLib Library */
		System.out.println("\nCopying McLib Library...");
		Path source = Paths
				.get("languages/Natlab/src/natlab/backends/JavaScript/mclib.js");
		Path target = Paths.get(dir + "mclib.js");

		try {
			File f = new File(target.toString());
			if(f.exists()) {
				f.delete();
			}
			Files.copy(source, target);
			System.out.println("McLib Library copied at: " + dir + "mclib.js");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n\nERROR!! Cannot Copy McLib Library!!");
			System.exit(0);
		}
		System.out.println("\n\nSUCCESS!!");
	}

}
