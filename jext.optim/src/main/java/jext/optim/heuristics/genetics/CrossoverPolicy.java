package jext.optim.heuristics.genetics;

import java.util.random.RandomGenerator;

public interface CrossoverPolicy<T> {

    ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second);

}
