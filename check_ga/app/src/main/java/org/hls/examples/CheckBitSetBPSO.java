package org.hls.examples;

import jext.optim.domain.Solution;
import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.bitset.BitSetFactory;
import jext.optim.domain.bitset.BitSetFitnessFunction;
import jext.optim.heuristics.bpso.BinaryParticleSwarmOptimization;
import jext.optim.heuristics.bpso.Population;
import jext.optim.heuristics.bpso.operators.bitset.BitSetUpdater;
import jext.optim.heuristics.bpso.stopping.Conditions;
import jext.optim.heuristics.bpso.stopping.FixedGenerationCount;
import jext.optim.heuristics.bpso.stopping.Patience;

public class CheckBitSetBPSO {

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

        Population<BitSet> pop = new Population<>(
            1000,.001, .001, .01,
            bitsetFactory,
            fitnessFunction
        );

        // crossover
        // mutation
        // selection
        BinaryParticleSwarmOptimization<BitSet> solver = new BinaryParticleSwarmOptimization<>(
            new BitSetUpdater()
        );

        Solution<BitSet> sol = solver.solve(true, pop,
            new Conditions(
                new FixedGenerationCount(10000),
                new Patience(1000)
            )
        );

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        System.out.println(fitnessFunction.getWorstSolution());

        sol = solver.solve(false, pop,
            new Conditions(
                new FixedGenerationCount(10000),
                new Patience(1000)
            )
        );

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

    }
}
