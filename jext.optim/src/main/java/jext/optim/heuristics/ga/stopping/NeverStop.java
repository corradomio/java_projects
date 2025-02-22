package jext.optim.heuristics.ga.stopping;

import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.StoppingCondition;

public class NeverStop implements StoppingCondition {

    @Override
    public boolean isSatisfied(Population population) {
        return false;
    }
}
