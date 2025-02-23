package org.hls.examples;

import jext.optim.domain.Solution;
import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.bitset.BitSetFactory;
import jext.optim.domain.bitset.BitSetFitnessFunction;
import jext.optim.heuristics.sa.stopping.Conditions;
import jext.optim.heuristics.sa.stopping.FixedGenerationCount;
import jext.optim.heuristics.sa.DefaultTemperatureScheduler;
import jext.optim.heuristics.sa.Population;
import jext.optim.heuristics.sa.SimulatedAnnealing;
import jext.optim.heuristics.sa.operator.bitset.BitSetNeighbours;
import jext.optim.heuristics.sa.stopping.Patience;
import jext.util.concurrent.Parallel;


public class CheckBitSetSA {

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
        System.out.println(fitnessFunction.getBestSolution());

        Solution<BitSet> sol;

        Population<BitSet> pop = new Population<>(
            1000, 3,
            bitsetFactory,
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio
        SimulatedAnnealing<BitSet> solver = new SimulatedAnnealing<>(
            1000,
            new DefaultTemperatureScheduler(1000),
            new BitSetNeighbours()
        );

        // sol = sa.solve(true, pop, new Conditions(
        //     new FixedGenerationCount(10000),
        //     new Patience(100)
        // ));
        //
        // System.out.println(sol.fitness());
        // System.out.println(sol.candidate());

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");
        System.out.println(fitnessFunction.getWorstSolution());

        sol = solver.solve(false, pop, new Conditions(
            new FixedGenerationCount(10000)
            , new Patience(500)
        ));

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

        Parallel.shutdown();
    }
}
