package jext.optim.heuristics.genetics.util;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.CrossoverPolicy;
import jext.optim.heuristics.genetics.GeneticAlgorithm;

import java.util.random.RandomGenerator;

public abstract class AbstractCrossoverPolicy<T> implements CrossoverPolicy<T> {

    public AbstractCrossoverPolicy() {

    }

    public ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second) {
        return crossover(first, second, GeneticAlgorithm.getRandomGenerator());
    }

    public abstract ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second, RandomGenerator rng);

}
