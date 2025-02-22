package jext.optim.heuristics.sa;

import jext.optim.domain.FitnessFunction;
import jext.optim.domain.Solution;

public class Particle<T> implements Comparable<Particle<T>>, Solution<T>  {

    /** Value assigned when no fitness has been computed yet. */
    private static final double NO_FITNESS = Double.NEGATIVE_INFINITY;

    private final T candidate;
    private final FitnessFunction<T> fitnessFunction;
    private final boolean decreasingOrder;
    private double fitness = NO_FITNESS;

    public Particle(T candidate, FitnessFunction<T> fitnessFunction, boolean decreasingOrder) {
        this.candidate = candidate;
        this.fitnessFunction = fitnessFunction;
        this.decreasingOrder = decreasingOrder;
    }

    public Particle(final T candidate, final Particle<T> template) {
        this.candidate = candidate;
        this.fitnessFunction = template.fitnessFunction;
        this.decreasingOrder = template.decreasingOrder;
    }

    @Override
    public T candidate() {
        return candidate;
    }

    @Override
    public double fitness() {
        if (fitness == NO_FITNESS) {
            fitness = fitnessFunction.fitness(candidate);
        }
        return fitness;
    }

    @Override
    public int compareTo(Particle<T> that) {
        int cmp = Double.compare(this.fitness(), that.fitness());
        return decreasingOrder ? -cmp : cmp;
    }

    @Override
    public String toString() {
        return String.format("%s: %f", candidate, fitness());
    }
}
