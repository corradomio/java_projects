package jext.optim.heuristics.ga;

/**
 * Used only to have all reference classes in this package
 */
public interface StoppingCondition {

    boolean isSatisfied(Population population);
}
