package natlab;

import junit.framework.Test;
import junit.framework.TestSuite;
import mclint.AllMcLintTests;
import natlab.refactoring.ExtractFunctionTest;
import natlab.refactoring.FEvalToCallTest;
import natlab.refactoring.FunctionInlinerTest;
import natlab.refactoring.MScriptInlinerTest;
import natlab.toolkits.analysis.core.ReachingDefsTest;

/** Top-level test suite.  Contains all tests. */
public class AllTests {
  public static Test suite() {
    TestSuite suite = new TestSuite();
    //$JUnit-BEGIN$
    suite.addTestSuite(NatlabScannerTests.class);
    suite.addTestSuite(NatlabParserPassTests.class);
    suite.addTestSuite(NatlabParserFailTests.class);

    suite.addTestSuite(TransformedNodeTests.class);

    suite.addTestSuite(MultiRewritePassTests.class);

    suite.addTestSuite(MScriptInlinerTest.class);
    suite.addTestSuite(FEvalToCallTest.class);
    suite.addTestSuite(FunctionInlinerTest.class);
    suite.addTestSuite(ExtractFunctionTest.class);
    
    suite.addTestSuite(ReachingDefsTest.class);
    
    suite.addTest(AllMcLintTests.suite());

    //$JUnit-END$
    return suite;
  }
}
