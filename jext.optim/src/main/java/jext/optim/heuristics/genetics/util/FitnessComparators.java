package jext.optim.heuristics.genetics.util;

import jext.optim.heuristics.genetics.FitnessComparator;

public abstract class FitnessComparators {

    public static FitnessComparator INCREASING_ORDER = (first, second) -> Float.compare(first, second);

    public static FitnessComparator DECREASING_ORDER = (first, second) -> -Float.compare(first, second);
}
