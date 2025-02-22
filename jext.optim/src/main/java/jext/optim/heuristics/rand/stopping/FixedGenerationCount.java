package jext.optim.heuristics.rand.stopping;


import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.rand.RandomCandidate;
import jext.optim.heuristics.rand.StoppingCondition;

public class FixedGenerationCount implements StoppingCondition {

    /** Number of generations that have passed. */
    private int numGenerations;

    /** Maximum number of generations (stopping criteria). */
    private final int maxGenerations;

    /**
     * Create a new FixedGenerationCount instance.
     *
     * @param maxGenerations number of generations to evolve
     * @throws NumberIsTooSmallException if the number of generations is &lt; 1
     */
    public FixedGenerationCount(final int maxGenerations) throws NumberIsTooSmallException {
        if (maxGenerations <= 0) {
            throw new NumberIsTooSmallException(maxGenerations, 1, true);
        }
        this.maxGenerations = maxGenerations;
    }

    @Override
    public boolean isSatisfied(RandomCandidate randomCandidate) {
        if (this.numGenerations < this.maxGenerations) {
            numGenerations++;
            return false;
        }
        return true;
    }

    public int getNumGenerations() {
        return numGenerations;
    }
}
