package jext.optim.heuristics.pbil;

import jext.optim.domain.FitnessFunction;
import jext.optim.domain.Solution;

public class Learner<T> implements Solution<T>, Comparable<Learner<T>> {

    private static final double NO_FITNESS = Double.NEGATIVE_INFINITY;

    private final T candidate;
    private final FitnessFunction<T> fitnessFunction;
    private final boolean decreasingOrder;
    private double fitness = NO_FITNESS;

    public Learner(T candidate, FitnessFunction<T> fitnessFunction, boolean decreasingOrder) {
        this.candidate = candidate;
        this.fitnessFunction = fitnessFunction;
        this.decreasingOrder = decreasingOrder;
    }

    public Learner(T candidate, Learner<T> template) {
        this.candidate = candidate;
        this.fitnessFunction = template.fitnessFunction;
        this.decreasingOrder = template.decreasingOrder;
    }

    @Override
    public int compareTo(Learner<T> o) {
        int cmp = Double.compare(fitness(), o.fitness());
        return decreasingOrder ? -cmp : cmp;
    }

    @Override
    public T candidate() {
        return candidate;
    }

    @Override
    public double fitness() {
        if (fitness == NO_FITNESS)
            fitness = fitnessFunction.fitness(candidate);
        return fitness;
    }

    @Override
    public String toString() {
        return String.format("%s: %f", candidate, fitness());
    }
}
