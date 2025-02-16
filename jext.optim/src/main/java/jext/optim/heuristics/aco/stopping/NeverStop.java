package jext.optim.heuristics.aco.stopping;


import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.StoppingCondition;

public class NeverStop implements StoppingCondition {

    @Override
    public boolean isSatisfied(final AntColony population) {
        return false;
    }
}
