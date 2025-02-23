package org.hls.examples;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.GeneticAlgorithm;
import jext.optim.heuristics.ga.Population;
import jext.optim.domain.permutation.Permutation;
import jext.optim.domain.permutation.PermutationFactory;
import jext.optim.domain.permutation.PermutationFitnessFunction;
import jext.optim.heuristics.ga.filter.AcceptAll;
import jext.optim.heuristics.ga.operator.permutation.InversionMutation;
import jext.optim.heuristics.ga.operator.permutation.OrderCrossover;
import jext.optim.heuristics.ga.selection.TournamentSelection;
import jext.optim.heuristics.ga.stopping.FixedElapsedTime;
import jext.optim.heuristics.ga.stopping.FixedGenerationCount;
import jext.optim.heuristics.ga.stopping.Conditions;
import jext.optim.heuristics.ga.stopping.NeverStop;
import jext.optim.heuristics.ga.stopping.Patience;


public class CheckPermutationGA {

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

        GeneticAlgorithm<Permutation> solver = new GeneticAlgorithm<Permutation>(
            new OrderCrossover(), .25,
            new InversionMutation(), .01,
            new TournamentSelection<>(TOURNAMENT_ARITY),
            new AcceptAll<>()
        );

        Population<Permutation> bestPop = solver.evolve(true,
            pop,
            new Conditions(new NeverStop()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        Chromosome<Permutation> bestChromosome = bestPop.getFittestChromosome();

        System.out.println(bestChromosome);

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        System.out.println(fitnessFunction.getDefaultChromosome());

        Population<Permutation> worstPop = solver.evolve(false,
            pop,
            new Conditions(new NeverStop()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        Chromosome<Permutation> worstChromosome = worstPop.getFittestChromosome();

        System.out.println(worstChromosome);

    }
}
