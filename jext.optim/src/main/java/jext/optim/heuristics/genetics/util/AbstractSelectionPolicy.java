package jext.optim.heuristics.genetics.util;

import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.SelectionPolicy;

import java.util.random.RandomGenerator;

public abstract class AbstractSelectionPolicy<T> implements SelectionPolicy<T> {

    public ChromosomePair<T> select(Population<T> population) {
        return select(population, GeneticAlgorithm.getRandomGenerator());
    }

    public abstract ChromosomePair<T> select(Population<T> population, RandomGenerator rng);

}
