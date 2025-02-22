package jext.optim.heuristics.ga;

public interface MutationPolicy<T> {

    Chromosome<T> mutate(Chromosome<T> original);
}
