package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.StoppingCondition;
import org.apache.commons.math4.legacy.genetics.Population;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipleConditions implements StoppingCondition {

    private List<StoppingCondition> conditions = new ArrayList<>();

    public MultipleConditions(StoppingCondition... conditions) {
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
