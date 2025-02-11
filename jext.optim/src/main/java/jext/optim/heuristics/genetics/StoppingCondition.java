package jext.optim.heuristics.genetics;

/**
 * Used only to have all reference classes in this package
 */
public interface StoppingCondition {

    boolean isSatisfied(Population population);
}
