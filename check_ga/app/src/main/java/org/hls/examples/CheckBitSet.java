package org.hls.examples;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.domain.bitset.BitSetFactory;
import jext.optim.heuristics.genetics.domain.bitset.Mutation;
import jext.optim.heuristics.genetics.domain.bitset.OnePointCrossover;
import jext.optim.heuristics.genetics.domain.bitset.BitSetFitnessFunction;
import jext.optim.heuristics.genetics.selection.TournamentSelection;
import jext.optim.heuristics.genetics.stopping.ElapsedTime;
import jext.optim.heuristics.genetics.stopping.GenerationCount;
import jext.optim.heuristics.genetics.stopping.MultipleConditions;
import jext.optim.heuristics.genetics.stopping.NeverStop;
import jext.optim.heuristics.genetics.stopping.Patience;
import jext.optim.heuristics.genetics.util.TPrint;


public class CheckBitSet {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 100000;
    static int SET_SIZE = 30;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        BitSetFitnessFunction fitnessFunction = BitSetFitnessFunction.random(SET_SIZE);

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");

        System.out.println(fitnessFunction.getBestChromosome());

        Population<BitSet> pop = new Population<>(
            100,
            .5,
            new BitSetFactory(SET_SIZE),
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio
        GeneticAlgorithm<BitSet> ga = new GeneticAlgorithm<BitSet>(
            new OnePointCrossover(), .25,
            new Mutation(), .01,
            new TournamentSelection<>(TOURNAMENT_ARITY)
        ) {
            @Override
            protected void onGeneration(int g, Population<BitSet> population, boolean isEnd) {
                if (isEnd)
                    TPrint.printf("[%7d]: %.6f\n", g, population.getFittestChromosome().getFitness());
            }
        };

        Population<BitSet> bestPop = ga.maximize(pop,
            new MultipleConditions(new NeverStop()
                , new GenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new ElapsedTime(30)
            )
        );

        Chromosome<BitSet> bestChromosome = bestPop.getFittestChromosome();

        System.out.println(bestChromosome);

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        System.out.println(fitnessFunction.getWorstChromosome());

        Population<BitSet> worstPop = ga.minimize(pop,
            new MultipleConditions(new NeverStop()
                , new GenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new ElapsedTime(30)
            )
        );

        Chromosome<BitSet> worstChromosome = worstPop.getFittestChromosome();

        System.out.println(worstChromosome);

    }
}
