package jext.optim.heuristics.genetics;

import java.util.random.RandomGenerator;

public interface TypedCrossoverPolicy<T> extends org.apache.commons.math4.legacy.genetics.CrossoverPolicy {

    ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second, RandomGenerator rng);

}
