package jext.optim.heuristics.ants;

public interface StoppingCondition {

    boolean isSatisfied(AntColony antColony);
}
