package jext.optim.heuristics.genetics;

public interface MutationPolicy<T> {

    Chromosome<T> mutate(Chromosome<T> original);
}
