package jext.optim.heuristics.aco.stopping;


import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.StoppingCondition;
import jext.util.TPrint;

public class LogGeneration implements StoppingCondition {

    private int g = 0;

    @Override
    public boolean isSatisfied(final AntColony population) {
        ++g;
        TPrint.tprintf("[%7d]: %.6f\n", g, population.getFittestSolution().fitness());
        return false;
    }
}
