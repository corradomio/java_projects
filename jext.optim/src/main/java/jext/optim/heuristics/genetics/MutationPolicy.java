package jext.optim.heuristics.genetics;

import org.apache.commons.math4.legacy.exception.MathIllegalArgumentException;

import java.util.random.RandomGenerator;

public abstract class MutationPolicy<T> implements TypedMutationPolicy<T> {

    public MutationPolicy() {

    }

    public abstract Chromosome<T> mutate(Chromosome<T> original, RandomGenerator rng);

    // ----------------------------------------------------------------------
    // compatibility

    @Override
    public org.apache.commons.math4.legacy.genetics.Chromosome mutate(org.apache.commons.math4.legacy.genetics.Chromosome original) throws MathIllegalArgumentException {
        return mutate((Chromosome<T>) original, GeneticAlgorithm.getRandomNumberGenerator());
    }

}
