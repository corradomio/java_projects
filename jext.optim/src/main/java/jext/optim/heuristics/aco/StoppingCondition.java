package jext.optim.heuristics.aco;

public interface StoppingCondition {

    boolean isSatisfied(AntColony antColony);
}
