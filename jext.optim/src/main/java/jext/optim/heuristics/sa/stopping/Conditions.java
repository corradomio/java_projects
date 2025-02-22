package jext.optim.heuristics.sa.stopping;

import jext.optim.heuristics.sa.StoppingCondition;
import jext.optim.heuristics.sa.Population;

import java.util.Arrays;
import java.util.List;

public class Conditions implements StoppingCondition {

    private final List<StoppingCondition> conditions;

    public Conditions(StoppingCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean isSatisfied(Population population) {
        for (StoppingCondition condition : conditions)
            if (condition.isSatisfied(population))
                return true;
        return false;
    }
}
