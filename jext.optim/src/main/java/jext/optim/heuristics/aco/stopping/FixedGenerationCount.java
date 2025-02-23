package jext.optim.heuristics.aco.stopping;

import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.StoppingCondition;
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
    public boolean isSatisfied(final AntColony population) {
        TPrint.tprintf("[%7d]: %s\n", numGenerations, population.getFittestSolution());

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
