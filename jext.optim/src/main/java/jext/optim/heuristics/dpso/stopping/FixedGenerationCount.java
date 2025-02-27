package jext.optim.heuristics.dpso.stopping;


import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.dpso.Population;
import jext.optim.heuristics.dpso.StoppingCondition;
import jext.util.TPrint;

public class FixedGenerationCount implements StoppingCondition {

    private int numGenerations;

    private final int maxGenerations;

    public FixedGenerationCount(final int maxGenerations) throws NumberIsTooSmallException {
        if (maxGenerations <= 0) {
            throw new NumberIsTooSmallException(maxGenerations, 1, true);
        }
        this.maxGenerations = maxGenerations;
    }

    @Override
    public boolean isSatisfied(final Population population) {
        TPrint.tprintf("[%7d]: %s\n", numGenerations, population.getGlobalBest());

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
