package jext.optim.heuristics.aco;

import jext.optim.heuristics.aco.AntColony;

public interface StoppingCondition {

    boolean isSatisfied(AntColony antColony);
}
