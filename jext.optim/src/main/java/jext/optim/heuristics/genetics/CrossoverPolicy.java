package jext.optim.heuristics.genetics;

import org.apache.commons.math4.legacy.exception.MathIllegalArgumentException;

import java.util.random.RandomGenerator;

public abstract class CrossoverPolicy<T> implements TypedCrossoverPolicy<T> {

    public CrossoverPolicy() {

    }

    public abstract ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second, RandomGenerator rng);

    // ----------------------------------------------------------------------
    // compatibility

    @Override
    public org.apache.commons.math4.legacy.genetics.ChromosomePair crossover(
        org.apache.commons.math4.legacy.genetics.Chromosome first,
        org.apache.commons.math4.legacy.genetics.Chromosome second) throws MathIllegalArgumentException {
        return crossover((Chromosome<T>)first, (Chromosome<T>)second, GeneticAlgorithm.getRandomNumberGenerator());
    }

}
