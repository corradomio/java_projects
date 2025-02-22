package jext.optim.heuristics.ga.util;

import jext.math.random.UniformRandomGenerator;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.CrossoverPolicy;

import java.util.random.RandomGenerator;

public abstract class AbstractCrossoverPolicy<T> implements CrossoverPolicy<T> {

    public AbstractCrossoverPolicy() {

    }

    public ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second) {
        return crossover(first, second, UniformRandomGenerator.getRandomGenerator());
    }

    public abstract ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second, RandomGenerator rng);

}
