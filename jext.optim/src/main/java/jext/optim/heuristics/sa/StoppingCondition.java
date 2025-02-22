package jext.optim.heuristics.sa;

public interface StoppingCondition {
    boolean isSatisfied(Population swarm);
}
