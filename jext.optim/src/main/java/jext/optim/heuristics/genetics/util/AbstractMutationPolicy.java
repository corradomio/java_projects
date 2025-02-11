package jext.optim.heuristics.genetics.util;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.MutationPolicy;

import java.util.random.RandomGenerator;

public abstract class AbstractMutationPolicy<T> implements MutationPolicy<T> {

    public AbstractMutationPolicy() {

    }

    public Chromosome<T> mutate(Chromosome<T> original) {
        return mutate(original, GeneticAlgorithm.getRandomGenerator());
    }

    public abstract Chromosome<T> mutate(Chromosome<T> original, RandomGenerator rng);

}
