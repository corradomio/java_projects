package jext.optim.heuristics.ants.stopping;


import jext.optim.heuristics.ants.AntColony;
import jext.optim.heuristics.ants.StoppingCondition;

import jext.util.TPrint;

public class LogGeneration implements StoppingCondition {

    private int g = 0;

    @Override
    public boolean isSatisfied(final AntColony population) {
        ++g;
        TPrint.printf("[%7d]: %.6f\n", g, population.getBestTour().getTourLength());
        return false;
    }
}
