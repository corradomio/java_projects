package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.CrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class EdgeCrossover extends CrossoverPolicy<Permutation> {

    @Override
    public ChromosomePair<Permutation> crossover(Chromosome<Permutation> first, Chromosome<Permutation> second, RandomGenerator rng) {

        return null;
    }
}
