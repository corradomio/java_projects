package org.hls.examples;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.domain.partition.NElementsMutation;
import jext.optim.heuristics.genetics.domain.partition.OnePointCrossover;
import jext.optim.domain.partition.Partition;
import jext.optim.domain.partition.PartitionFactory;
import jext.optim.domain.partition.PartitionFitnessFunction;
import jext.optim.heuristics.genetics.filter.DropDuplicates;
import jext.optim.heuristics.genetics.selection.TournamentSelection;
import jext.optim.heuristics.genetics.stopping.FixedElapsedTime;
import jext.optim.heuristics.genetics.stopping.FixedGenerationCount;
import jext.optim.heuristics.genetics.stopping.LogGeneration;
import jext.optim.heuristics.genetics.stopping.MultipleConditions;
import jext.optim.heuristics.genetics.stopping.NeverStop;
import jext.optim.heuristics.genetics.stopping.Patience;
import jext.util.ArrayOps;

public class CheckPartition {

    static int TOURNAMENT_ARITY = 2;
    static int NUM_GENERATIONS = 1000000;
    static int PATIENCE = 100000;
    static int SET_SIZE = 30;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        PartitionFitnessFunction fitnessFunction = PartitionFitnessFunction.random(SET_SIZE);

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");

        Population<Partition> pop = new Population<>(
            100, .10, .10,
            new PartitionFactory(SET_SIZE, 3),
            fitnessFunction
        );

        // crossover
        // mutation
        // selection

        GeneticAlgorithm<Partition> ga = new GeneticAlgorithm<>(
            new OnePointCrossover(), .25,
            new NElementsMutation(3), .01,
            new TournamentSelection<>(TOURNAMENT_ARITY),
            new DropDuplicates<>()
        );

        Population<Partition> bestPop = ga.evolve(true,
            pop,
            new MultipleConditions(new NeverStop()
                , new LogGeneration()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        Chromosome<Partition> bestChromosome = bestPop.getFittestChromosome();
        float[] pvalues = fitnessFunction.values(bestChromosome.candidate());

        System.out.println(bestChromosome);
        System.out.println(ArrayOps.asList(pvalues));

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        pop = new Population<>(
            100, .10, .10,
            new PartitionFactory(SET_SIZE, 3),
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio

        ga = new GeneticAlgorithm<>(
            new OnePointCrossover(), .25,
            new NElementsMutation(3), .01,
            new TournamentSelection<>(TOURNAMENT_ARITY),
            new DropDuplicates<>()
        );

        bestPop = ga.evolve(false,
            pop,
            new MultipleConditions(new NeverStop()
                , new LogGeneration()
                , new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
                , new FixedElapsedTime(30)
            )
        );

        bestChromosome = bestPop.getFittestChromosome();
        pvalues = fitnessFunction.values(bestChromosome.candidate());

        System.out.println(bestChromosome);
        System.out.println(ArrayOps.asList(pvalues));
    }
}
