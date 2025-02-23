package jext.optim.heuristics.sa.stopping;


import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.sa.Population;
import jext.optim.heuristics.sa.StoppingCondition;
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
    public boolean isSatisfied(final Population swarm) {
        TPrint.tprintf("[%7d]: %s\n", numGenerations, swarm.getFittestParticle());

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
