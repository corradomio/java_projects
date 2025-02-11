package jext.optim.heuristics.genetics.filter;

import jext.optim.heuristics.genetics.FitnessComparator;

public abstract class FitnessComparators {

    public static FitnessComparator INCREASING_ORDER = (first, second) -> Double.compare(first, second);

    public static FitnessComparator DECREASING_ORDER = (first, second) -> -Double.compare(first, second);
}
