package org.hls.examples;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.domain.permutation.Permutation;
import jext.optim.heuristics.genetics.domain.permutation.PermutationFactory;
import jext.optim.heuristics.genetics.domain.permutation.PermutationFitnessFunction;
import jext.optim.heuristics.genetics.filter.AcceptAll;
import jext.optim.heuristics.genetics.operator.permutation.InversionMutation;
import jext.optim.heuristics.genetics.operator.permutation.OrderCrossover;
import jext.optim.heuristics.genetics.selection.TournamentSelection;
import jext.optim.heuristics.genetics.stopping.FixedElapsedTime;
import jext.optim.heuristics.genetics.stopping.FixedGenerationCount;
import jext.optim.heuristics.genetics.stopping.LogGeneration;
import jext.optim.heuristics.genetics.stopping.MultipleConditions;
import jext.optim.heuristics.genetics.stopping.NeverStop;
import jext.optim.heuristics.genetics.stopping.Patience;


public class CheckPermutation {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 100000;
    static int SET_SIZE = 30;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        PermutationFitnessFunction fitnessFunction = PermutationFitnessFunction.random(SET_SIZE);

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");

        System.out.println(fitnessFunction.getDefaultChromosome());

        Population<Permutation> pop = new Population<>(
            100, .10, .10,
            new PermutationFactory(SET_SIZE),
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio

        GeneticAlgorithm<Permutation> ga = new GeneticAlgorithm<Permutation>(
            new OrderCrossover(), .25,
            new InversionMutation(), .01,
            new TournamentSelection<>(TOURNAMENT_ARITY),
            new AcceptAll<>()
        );

        Population<Permutation> bestPop = ga.evolve(true,
            pop,
            new MultipleConditions(new NeverStop()
                , new LogGeneration()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
                , new LogGeneration()
            )
        );

        Chromosome<Permutation> bestChromosome = bestPop.getFittestChromosome();

        System.out.println(bestChromosome);

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        System.out.println(fitnessFunction.getDefaultChromosome());

        Population<Permutation> worstPop = ga.evolve(false,
            pop,
            new MultipleConditions(new NeverStop()
                , new LogGeneration()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        Chromosome<Permutation> worstChromosome = worstPop.getFittestChromosome();

        System.out.println(worstChromosome);

    }
}
