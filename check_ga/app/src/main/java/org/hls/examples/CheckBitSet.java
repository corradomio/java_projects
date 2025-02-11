package org.hls.examples;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.domain.bitset.BitSetFactory;
import jext.optim.heuristics.genetics.domain.bitset.OneBitMutation;
import jext.optim.heuristics.genetics.domain.bitset.OnePointCrossover;
import jext.optim.heuristics.genetics.domain.bitset.BitSetFitnessFunction;
import jext.optim.heuristics.genetics.selection.TournamentSelection;
import jext.optim.heuristics.genetics.stopping.FixedElapsedTime;
import jext.optim.heuristics.genetics.stopping.FixedGenerationCount;
import jext.optim.heuristics.genetics.stopping.MultipleConditions;
import jext.optim.heuristics.genetics.stopping.NeverStop;
import jext.optim.heuristics.genetics.stopping.Patience;
import jext.optim.heuristics.genetics.util.TPrint;


public class CheckBitSet {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 1000;
    static int SET_SIZE = 64;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        BitSetFitnessFunction fitnessFunction = BitSetFitnessFunction.random(SET_SIZE);
        BitSetFactory bitsetFactory = new BitSetFactory(SET_SIZE);

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");

        System.out.println(fitnessFunction.getBestChromosome());

        Population<BitSet> pop = new Population<>(
            100,.10, .10,
            bitsetFactory,
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio
        GeneticAlgorithm<BitSet> ga = new GeneticAlgorithm<BitSet>(
            new OnePointCrossover(), .25,
            new OneBitMutation(), .01,
            new TournamentSelection<>(TOURNAMENT_ARITY)
        ) {
            @Override
            protected void onGeneration(int g, Population<BitSet> population) {
                TPrint.printf("[%7d]: %.6f\n", g, population.getFittestChromosome().fitness());
            }
        };

        Population<BitSet> bestPop = ga.maximize(pop,
            new MultipleConditions(new NeverStop()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        Chromosome<BitSet> bestChromosome = bestPop.getFittestChromosome();

        System.out.println(bestChromosome);
        System.out.println(bestChromosome.candidate().symdiff(fitnessFunction.getBestChromosome().candidate()));

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        System.out.println(fitnessFunction.getWorstChromosome());

        Population<BitSet> worstPop = ga.minimize(pop,
            new MultipleConditions(new NeverStop()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        Chromosome<BitSet> worstChromosome = worstPop.getFittestChromosome();

        System.out.println(worstChromosome);
        System.out.println(worstChromosome.candidate().symdiff(fitnessFunction.getWorstChromosome().candidate()));

    }
}
