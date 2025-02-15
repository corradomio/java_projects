package jext.optim.heuristics.ants.stopping;


import jext.optim.heuristics.ants.AntColony;
import jext.optim.heuristics.ants.StoppingCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipleConditions implements StoppingCondition {

    private List<StoppingCondition> conditions = new ArrayList<>();

    public MultipleConditions(StoppingCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean isSatisfied(final AntColony population) {
        for (StoppingCondition condition : conditions)
            if (condition.isSatisfied(population))
                return true;
        return false;
    }
}
