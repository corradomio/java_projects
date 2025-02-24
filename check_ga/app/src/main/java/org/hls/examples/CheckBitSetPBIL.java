package org.hls.examples;

import jext.optim.domain.Solution;
import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.bitset.BitSetFactory;
import jext.optim.domain.bitset.BitSetFitnessFunction;
import jext.optim.heuristics.pbil.Population;
import jext.optim.heuristics.pbil.PopulationBasedIncrementalLearning;
import jext.optim.heuristics.pbil.operator.bitset.BitSetUpdater;
import jext.optim.heuristics.pbil.stopping.Conditions;
import jext.optim.heuristics.pbil.stopping.FixedGenerationCount;
import jext.optim.heuristics.pbil.stopping.Patience;
import jext.util.concurrent.Parallel;


public class CheckBitSetPBIL {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 1000;
    static int SET_SIZE = 64;

    public static void main(String[] args) {

        BitSetFitnessFunction fitnessFunction = BitSetFitnessFunction.random(SET_SIZE);
        BitSetFactory bitsetFactory = new BitSetFactory(SET_SIZE);

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");
        System.out.println(fitnessFunction.getBestSolution());

        Solution<BitSet> sol;

        Population<BitSet> pop = new Population<>(
            1000,
            bitsetFactory,
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio
        PopulationBasedIncrementalLearning<BitSet> solver = new PopulationBasedIncrementalLearning<>(
            0.1, 0.075, 0.2, 0.05,
            // 0.33, 0.1, 0.2, 0.1,
            new BitSetUpdater()
        );

        sol = solver.solve(true, pop, new Conditions(
            new FixedGenerationCount(10000)
            , new Patience(500)
        ));

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

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
