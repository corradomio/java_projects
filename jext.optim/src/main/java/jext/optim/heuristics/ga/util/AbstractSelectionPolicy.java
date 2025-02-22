package jext.optim.heuristics.ga.util;

import jext.math.random.UniformRandomGenerator;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.SelectionPolicy;

import java.util.random.RandomGenerator;

public abstract class AbstractSelectionPolicy<T> implements SelectionPolicy<T> {

    public ChromosomePair<T> select(Population<T> population) {
        return select(population, UniformRandomGenerator.getRandomGenerator());
    }

    public abstract ChromosomePair<T> select(Population<T> population, RandomGenerator rng);

}
