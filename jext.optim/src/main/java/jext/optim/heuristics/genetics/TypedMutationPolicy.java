package jext.optim.heuristics.genetics;

import java.util.random.RandomGenerator;

public interface TypedMutationPolicy<T> extends org.apache.commons.math4.legacy.genetics.MutationPolicy {

    Chromosome<T> mutate(Chromosome<T> original, RandomGenerator rng);
}
