package org.hls.examples;

import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.domain.bitset.BitSetFactory;
import jext.optim.heuristics.genetics.examples.bitset.BitSetFitnessFunction;

import java.util.Random;
import java.util.stream.Collectors;

public class Check2 {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 100000;
    static int SET_SIZE = 10;

    public static void main(String[] args) {

        BitSetFitnessFunction fitnessFunction = new BitSetFitnessFunction(SET_SIZE);

        Population<BitSet> pop = new Population<>(
            10,
            2,
            new BitSetFactory(SET_SIZE),
            fitnessFunction
        );

        pop.withDecreasingOrder(false).initialize();
        pop.sort();
        System.out.println("maximize");
        System.out.println(pop.getChromosomes().stream().map(c -> (float)c.fitness()).toList());

        pop.withDecreasingOrder(true).initialize();
        pop.sort();
        System.out.println("minimize");
        System.out.println(pop.getChromosomes().stream().map(c -> (float)c.fitness()).toList());
    }
}
