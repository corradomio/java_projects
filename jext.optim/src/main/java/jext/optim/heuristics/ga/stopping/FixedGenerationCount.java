package jext.optim.heuristics.ga.stopping;

import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.StoppingCondition;
import jext.util.TPrint;


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

    /**
     * Determine whether or not the given number of generations have passed. Increments the number of generations
     * counter if the maximum has not been reached.
     *
     * @param population ignored (no impact on result)
     * @return <code>true</code> IFF the maximum number of generations has been exceeded
     */
    @Override
    public boolean isSatisfied(final Population population) {
        TPrint.tprintf("[%7d]: %s\n", numGenerations, population.getFittestChromosome());

        if (this.numGenerations < this.maxGenerations) {
            numGenerations++;
            return false;
        }
        return true;
    }

    /**
     * Returns the number of generations that have already passed.
     * @return the number of generations that have passed
     */
    public int getNumGenerations() {
        return numGenerations;
    }
}
