package org.hls.examples;

import jext.optim.domain.Solution;
import jext.optim.heuristics.ga.GeneticAlgorithm;
import jext.optim.heuristics.ga.Population;
import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.bitset.BitSetFactory;
import jext.optim.domain.bitset.BitSetFitnessFunction;
import jext.optim.heuristics.ga.operator.bitset.OneBitMutation;
import jext.optim.heuristics.ga.operator.bitset.OnePointCrossover;
import jext.optim.heuristics.ga.selection.TournamentSelection;
import jext.optim.heuristics.ga.stopping.FixedElapsedTime;
import jext.optim.heuristics.ga.stopping.FixedGenerationCount;
import jext.optim.heuristics.ga.stopping.Conditions;
import jext.optim.heuristics.ga.stopping.NeverStop;
import jext.optim.heuristics.ga.stopping.Patience;


public class CheckBitSetGA {

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
            100,.10, .10,
            bitsetFactory,
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio
        GeneticAlgorithm<BitSet> solver = new GeneticAlgorithm<BitSet>(
            new OnePointCrossover(), .25,
            new OneBitMutation(), .01,
            new TournamentSelection<>(TOURNAMENT_ARITY)
        );

        // Population<BitSet> bestPop = ga.evolve(true,
        //     pop,
        //     new MultipleConditions(new NeverStop()
        //         , new FixedGenerationCount(NUM_GENERATIONS)
        //         , new Patience(PATIENCE)
        //         , new FixedElapsedTime(30)
        //     )
        // );
        //
        // Chromosome<BitSet> bestChromosome = bestPop.getFittestChromosome();
        //
        // System.out.println(bestChromosome);
        // System.out.println(bestChromosome.candidate().symdiff(fitnessFunction.getBestChromosome().candidate()));

        Solution<BitSet> sol = solver.solve(true, pop,
            new Conditions(new NeverStop()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        System.out.println(fitnessFunction.getWorstSolution());

        // Population<BitSet> worstPop = ga.evolve(false,
        //     pop,
        //     new MultipleConditions(new NeverStop()
        //         , new LogGeneration()
        //         , new FixedGenerationCount(NUM_GENERATIONS)
        //         , new Patience(PATIENCE)
        //         , new FixedElapsedTime(30)
        //     )
        // );
        //
        // Chromosome<BitSet> worstChromosome = worstPop.getFittestChromosome();
        //
        // System.out.println(worstChromosome);
        // System.out.println(worstChromosome.candidate().symdiff(fitnessFunction.getWorstChromosome().candidate()));

        sol = solver.solve(false, pop,
                new Conditions(new NeverStop()
                    , new FixedGenerationCount(NUM_GENERATIONS)
                    , new Patience(PATIENCE)
                    , new FixedElapsedTime(30)
                )
        );

        System.out.println(sol.fitness());
        System.out.println(sol.candidate());

    }
}
