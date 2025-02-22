package jext.optim.heuristics.ga.util;

import jext.math.random.UniformRandomGenerator;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.MutationPolicy;

import java.util.random.RandomGenerator;

public abstract class AbstractMutationPolicy<T> implements MutationPolicy<T> {

    public AbstractMutationPolicy() {

    }

    public Chromosome<T> mutate(Chromosome<T> original) {
        return mutate(original, UniformRandomGenerator.getRandomGenerator());
    }

    public abstract Chromosome<T> mutate(Chromosome<T> original, RandomGenerator rng);

}
