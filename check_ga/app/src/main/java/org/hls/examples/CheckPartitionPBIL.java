package org.hls.examples;

import jext.optim.domain.Solution;
import jext.optim.domain.partition.Partition;
import jext.optim.domain.partition.PartitionFactory;
import jext.optim.domain.partition.PartitionFitnessFunction;
import jext.optim.heuristics.pbil.Population;
import jext.optim.heuristics.pbil.PopulationBasedIncrementalLearning;
import jext.optim.heuristics.pbil.operator.partition.PartitionUpdater;
import jext.optim.heuristics.pbil.stopping.Conditions;
import jext.optim.heuristics.pbil.stopping.FixedGenerationCount;
import jext.optim.heuristics.pbil.stopping.Patience;
import jext.util.ArrayUtils;

public class CheckPartitionPBIL {

    static int NUM_GENERATIONS = 10000;
    static int POPULATION_SIZE = 1000;
    static int PATIENCE = 1000;
    static int SET_SIZE = 30;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        PartitionFitnessFunction fitnessFunction = PartitionFitnessFunction.random(SET_SIZE);

        // ------------------------------------------------------------------

        System.out.println("-- Maximize --");

        Population<Partition> pop = new Population<>(
            POPULATION_SIZE,
            new PartitionFactory(SET_SIZE, 3),
            fitnessFunction
        );

        // crossover
        // mutation
        // selection

        PopulationBasedIncrementalLearning<Partition> solver = new PopulationBasedIncrementalLearning<>(
            0.1, 0.075, 0.2, 0.05,
            new PartitionUpdater()
        );

        Solution<Partition> bestSol = solver.solve(true,
            pop,
            new Conditions(new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
            )
        );


        System.out.println(bestSol);
        double[] pvalues = fitnessFunction.values(bestSol.candidate());
        System.out.println(ArrayUtils.asList(pvalues));

        // ------------------------------------------------------------------

        System.out.println("-- Minimize --");

        pop = new Population<>(
            POPULATION_SIZE,
            new PartitionFactory(SET_SIZE, 3),
            fitnessFunction
        );

        // crossover
        // mutation
        // selectio

        solver = new PopulationBasedIncrementalLearning<>(
            0.3, 0.1, 0.2, 0.05,
            new PartitionUpdater()
        );

        bestSol = solver.solve(false,
            pop,
            new Conditions(
                new FixedGenerationCount(NUM_GENERATIONS)
                , new Patience(PATIENCE)
            )
        );

        System.out.println(bestSol);
        pvalues = fitnessFunction.values(bestSol.candidate());
        System.out.println(ArrayUtils.asList(pvalues));

    }
}
