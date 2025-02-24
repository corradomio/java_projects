package jext.optim.heuristics.dpso;

public interface StoppingCondition {
    boolean isSatisfied(Population population);
}
