package org.hls.examples;

import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.domain.bitset.BitSetFactory;
import jext.optim.heuristics.genetics.domain.bitset.BitSetFitnessFunction;

public class Check2 {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 100000;
    static int SET_SIZE = 10;

    public static void main(String[] args) {

        BitSetFitnessFunction fitnessFunction = BitSetFitnessFunction.random(SET_SIZE);

        Population<BitSet> pop = new Population<>(
            10, .10, .10,
            new BitSetFactory(SET_SIZE),
            fitnessFunction
        );

        pop.setDecreasingOrder(false).initialize();
        System.out.println("maximize");
        System.out.println(pop.getChromosomes().stream().map(c -> (float)c.fitness()).toList());

        pop.setDecreasingOrder(true).initialize();
        System.out.println("minimize");
        System.out.println(pop.getChromosomes().stream().map(c -> (float)c.fitness()).toList());
    }
}
