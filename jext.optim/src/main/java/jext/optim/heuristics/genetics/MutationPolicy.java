package jext.optim.heuristics.genetics;

import java.util.random.RandomGenerator;

public interface MutationPolicy<T> {

    Chromosome<T> mutate(Chromosome<T> original);
}
