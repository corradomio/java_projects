package jext.optim.heuristics.genetics;

import org.apache.commons.math4.legacy.exception.MathIllegalArgumentException;

import java.util.random.RandomGenerator;

public abstract class SelectionPolicy<T> implements TypedSelectionPolicy<T> {

    public abstract ChromosomePair<T> select(Population<T> population, RandomGenerator rng);

    // ----------------------------------------------------------------------
    // compatibility

    @Override
    public org.apache.commons.math4.legacy.genetics.ChromosomePair select(org.apache.commons.math4.legacy.genetics.Population population) throws MathIllegalArgumentException {
        return select((Population<T>) population, GeneticAlgorithm.getRandomNumberGenerator());
    }

}
