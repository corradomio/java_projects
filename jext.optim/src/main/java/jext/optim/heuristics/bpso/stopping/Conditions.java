package jext.optim.heuristics.bpso.stopping;

import jext.optim.heuristics.bpso.StoppingCondition;
import jext.optim.heuristics.bpso.Swarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conditions implements StoppingCondition {

    private final List<StoppingCondition> conditions;

    public Conditions(StoppingCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean isSatisfied(Swarm population) {
        for (StoppingCondition condition : conditions)
            if (condition.isSatisfied(population))
                return true;
        return false;
    }
}
