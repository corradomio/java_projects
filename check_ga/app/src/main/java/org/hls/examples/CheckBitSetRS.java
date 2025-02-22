package org.hls.examples;

import jext.optim.domain.Solution;
import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.bitset.BitSetFactory;
import jext.optim.domain.bitset.BitSetFitnessFunction;
import jext.optim.heuristics.rand.RandomFactory;
import jext.optim.heuristics.rand.RandomSearch;
import jext.optim.heuristics.rand.stopping.FixedGenerationCount;


public class CheckBitSetRS {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 1000;
    static int SET_SIZE = 64;

    public static void main(String[] args) {
        // Random rng = new Random();
        // System.out.println("Hello World!");
        //
        // BitSet bs = new BitSet(10);
        //
        // for (int i=0; i<10; ++i)
        //     if (rng.nextBoolean())
        //         bs.set(i);
        //
        // System.out.println(bs);
        // System.out.println(bs.size());


        BitSetFitnessFunction fitnessFunction = BitSetFitnessFunction.random(SET_SIZE);
        BitSetFactory bitsetFactory = new BitSetFactory(SET_SIZE);

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");
        System.out.println(fitnessFunction.getBestChromosome());

        RandomFactory<BitSet> rf = new RandomFactory<>(
            bitsetFactory,
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio
        RandomSearch<BitSet> rs = new RandomSearch<>();

        Solution<BitSet> sol = rs.solve(true, rf, new FixedGenerationCount(100000));

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");
        System.out.println(fitnessFunction.getWorstChromosome());

        sol = rs.solve(false, rf, new FixedGenerationCount(100000));

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

    }
}
