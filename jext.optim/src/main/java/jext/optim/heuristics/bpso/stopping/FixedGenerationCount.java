package jext.optim.heuristics.bpso.stopping;


import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.bpso.Swarm;
import jext.optim.heuristics.bpso.StoppingCondition;
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
    public boolean isSatisfied(final Swarm swarm) {
        TPrint.tprintf("[%7d]: %s\n", numGenerations, swarm.getGlobalBest());

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
