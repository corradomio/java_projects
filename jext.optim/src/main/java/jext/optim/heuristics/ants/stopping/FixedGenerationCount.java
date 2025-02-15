package jext.optim.heuristics.ants.stopping;

import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.ants.AntColony;
import jext.optim.heuristics.ants.StoppingCondition;

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
