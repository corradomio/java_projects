package jext.optim.heuristics.rand;

import jext.optim.domain.Solution;

public class RandomCandidate<T> implements Solution<T>, Comparable<RandomCandidate<T>> {

    private final T candidate;
    private final double fitness;
    private final boolean decreasingOrder;

    RandomCandidate(T candidate, double fitness, boolean decreasingOrder) {
        this.candidate = candidate;
        this.fitness = fitness;
        this.decreasingOrder = decreasingOrder;
    }

    @Override
    public T candidate() {
        return candidate;
    }

    @Override
    public double fitness() {
        return fitness;
    }

    @Override
    public int compareTo(RandomCandidate<T> that) {
        int cmp = Double.compare(this.fitness, that.fitness);
        return decreasingOrder ? -cmp : cmp;
    }
}
