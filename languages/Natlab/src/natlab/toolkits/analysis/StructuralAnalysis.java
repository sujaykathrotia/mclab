package natlab.toolkits.analysis;

import natlab.ast.*;
import java.util.*;

/**
 * Interface for structural analysis. Note: implementations should
 * supply a standard constructor that takes in an ASTNode as argument.
 */
public interface StructuralAnalysis<A extends FlowSet> extends NodeCaseHandler
{

    /**
     * Executes the analysis.
     */
    public void analyze();

    /**
     * Get the AST on which the analysis is being performed.
     *
     * @return ASTNode on which the analysis is performed.
     */
    public ASTNode getTree();

    /**
     * Returns a boolean signifying whether or not the analysis has
     * been performed on the given AST. This does not necessarily
     * take into account changes to the AST.
     *
     * @return Whether or not the analysis has been performed.
     */
    public boolean isAnalyzed();

    /**
     * Get the map of resulting out flow sets.
     *
     * @return Map of out flow sets.
     */
    public Map<ASTNode, A> getOutFlowSets();

    /**
     * Get the map of resulting in flow sets.
     *
     * @return Map of in flow sets.
     */
    public Map<ASTNode, A> getInFlowSets();

    /**
     * Merge two flowSets into a third one.
     *
     * @param in1  The first input set.
     * @param in2  The second input set.
     * @param out  The output set.
     */
    public void merge(A in1, A in2, A out); 

    /**
     * Copy a flow set into another.
     *
     * @param source  The set to be copied.
     * @param dest    The set to be copied into.
     */
    public void copy(A source, A dest);

    /**
     * Get the out set for the current node being operated on. This
     * should only be used by analysis helpers, and probably not even
     * then.
     *
     * @return The current out set.
     * @see AnalysisHelper
     */
    public A getCurrentOutSet();

    /**
     * Set the out set for the current node being operated on. This
     * should only be used by analysis helpers, and probably not even
     * then.
     *
     * @param outSet  The out set to set.
     * @see AnalysisHelper
     */
    public void setCurrentOutSet( A outSet );


    /**
     * Get the in set for the current node being operated on. This
     * should only be used by analysis helpers, and probably not even
     * then.
     *
     * @return The current in set.
     * @see AnalysisHelper
     */
    public A getCurrentInSet();

    /**
     * Set the in set for the current node being operated on. This
     * should only be used by analysis helpers, and probably not even
     * then.
     *
     * @param inSet  The in set to set.
     * @see AnalysisHelper
     */
    public void setCurrentInSet( A inSet );

    /**
     * Process a loop variable in the initialization part of a for
     * loop. An example of this is as follows:
     * for i=start:step:stop
     * treat loop var as i = start.
     *
     * @param loopVar  The loop variable assignment stmt to be treated
     * as an init case.
     */
    public void caseLoopVarAsInit( AssignStmt loopVar );

    /**
     * Process a loop variable in the update part of a for loop. An
     * example is as follows:
     * for i=start:step:stop
     * treat loopVar as i=i+step.
     * 
     * @param loopVar  The loop variable assignment stmt to be terated
     * as an update.
     */
    public void caseLoopVarAsUpdate( AssignStmt loopVar );

    /**
     * Process a loop variable in the condition part of a for loop. An
     * example of this is as follows:
     * for i=start:step:stop
     * treat loopVar as i<=stop or i*sign(step)<=stop*sign(step).
     *
     * @param loopVar  The loop variable assignment stmt to be treated
     * as a condition.
     */
    public void caseLoopVarAsCondition( AssignStmt loopVar );

    /**
     * Process the condition of a while loop or if stmt or any other
     * statement that includes a condition.
     *
     * @param condExpr  The condition expression to be handled.
     */
    public void caseCondition( Expr condExpr );
    
    /**
     * Create a new initial flow set.
     *
     * @return A new initial flow set. This is used as starting value
     * for flow sets.
     */
    public A newInitialFlow();
}
