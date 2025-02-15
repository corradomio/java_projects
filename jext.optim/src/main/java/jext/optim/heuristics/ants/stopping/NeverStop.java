package jext.optim.heuristics.ants.stopping;


import jext.optim.heuristics.ants.AntColony;
import jext.optim.heuristics.ants.StoppingCondition;

public class NeverStop implements StoppingCondition {

    @Override
    public boolean isSatisfied(final AntColony population) {
        return false;
    }
}
