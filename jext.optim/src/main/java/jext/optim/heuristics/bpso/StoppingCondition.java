package jext.optim.heuristics.bpso;

public interface StoppingCondition {
    boolean isSatisfied(Population population);
}
