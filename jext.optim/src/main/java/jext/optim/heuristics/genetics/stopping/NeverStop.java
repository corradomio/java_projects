package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.StoppingCondition;
import org.apache.commons.math4.legacy.genetics.Population;

public class NeverStop implements StoppingCondition {
    @Override
    public boolean isSatisfied(Population population) {
        return false;
    }
}
