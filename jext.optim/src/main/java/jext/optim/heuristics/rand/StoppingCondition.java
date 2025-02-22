package jext.optim.heuristics.rand;

public interface StoppingCondition {
    public boolean isSatisfied(RandomCandidate randomCandidate);
}
