package natlab.cs621.analysis;

import java.io.IOException;

import mclint.util.Parsing;
import ast.CompilationUnits;
import ast.Program;


public class Main {

  public static void main(String[] args) throws IOException {
    // Parse the input files into an AST.
    CompilationUnits program = Parsing.files(args);
    
    // Run the analysis here.
    // Here we run the example reaching defs analysis:
    
    // Report the analysis results.
    for (Program unit : program.getPrograms()) {
      
    }
  }
}
