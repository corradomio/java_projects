package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.StoppingCondition;

public class NeverStop implements StoppingCondition {

    @Override
    public boolean isSatisfied(Population population) {
        return false;
    }
}
