package jext.optim.heuristics.genetics;

import jext.optim.problems.Solution;
import jext.optim.heuristics.genetics.util.FitnessComparators;

public class Chromosome<T> implements Comparable<Chromosome<T>>, Solution<T> {

    /** Value assigned when no fitness has been computed yet. */
    private static final double NO_FITNESS = Double.NEGATIVE_INFINITY;

    private final T candidate;
    private final FitnessFunction<T> fitnessFunction;
    private final boolean decreasingOrder;
    private double fitness = NO_FITNESS;

    // ----------------------------------------------------------------------

    /**
     * Constructor to create a new chromosome using a new candiate
     * and the same other properties of ``template``
     */
    public Chromosome(final T candidate, final Chromosome<T> template) {
        this.candidate = candidate;
        this.fitnessFunction = template.fitnessFunction;
        this.decreasingOrder = template.decreasingOrder;
    }

    /** Constructor */
    public Chromosome(final T candidate, final FitnessFunction<T> fitnessFunction, boolean decreasingOrder) {
        this.candidate = candidate;
        this.fitnessFunction = fitnessFunction;
        this.decreasingOrder = decreasingOrder;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public double fitness() {
        if (this.fitness == NO_FITNESS) {
            this.fitness = fitnessFunction.fitness(candidate);
        }
        return fitness;
    }

    public T candidate() {
        return this.candidate;
    }

    public FitnessFunction<T> fitnessFunction() {
        return this.fitnessFunction;
    }

    public FitnessComparator fitnessComparator() {
        return (decreasingOrder) ? FitnessComparators.DECREASING_ORDER : FitnessComparators.INCREASING_ORDER;

    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int compareTo(final Chromosome<T> another) {
        int cmp = Double.compare(fitness(), another.fitness());
        return decreasingOrder ? -cmp : cmp;
    }

    @Override
    public String toString() {
        return String.format("%s: %f", candidate.toString(), fitness());
    }
}
