package org.hls.examples;

import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.AntColonyOptimization;
import jext.optim.heuristics.aco.stopping.FixedGenerationCount;
import jext.optim.heuristics.aco.stopping.LogGeneration;
import jext.optim.heuristics.aco.stopping.MultipleConditions;
import jext.optim.heuristics.aco.stopping.NeverStop;
import jext.optim.heuristics.aco.stopping.Patience;
import jext.optim.heuristics.aco.tsp.TSPAntColony;
import jext.optim.domain.permutation.PermutationFitnessFunction;
import jext.optim.domain.Solution;

import java.util.Arrays;

public class CheckACS {

    static int SIZE = 100;

    public static void main(String[] args) {
        PermutationFitnessFunction ff = PermutationFitnessFunction.random(SIZE);
        double[][] distanceMatrix = ff.getDistanceMatrix();

        AntColony ac = new TSPAntColony(
            1000, .005,
            .5, .5, .5,
            distanceMatrix
        );

        AntColonyOptimization acs = new AntColonyOptimization();

        ac = acs.evolve(ac, new MultipleConditions(new NeverStop()
            , new LogGeneration()
            , new FixedGenerationCount(1000)
            , new Patience(100)
        ));
        Solution<int[]> tour = ac.getFittestSolution();

        System.out.printf("%s: %f\n", Arrays.toString(tour.candidate()), tour.fitness());
        System.out.println("done");
    }
}
