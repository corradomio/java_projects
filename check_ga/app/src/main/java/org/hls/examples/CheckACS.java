package org.hls.examples;

import jext.optim.domain.permutation.PermutationFitnessFunction;
import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.AntColonyOptimization;
import jext.optim.heuristics.aco.Tour;
import jext.optim.heuristics.aco.stopping.FixedGenerationCount;
import jext.optim.heuristics.aco.stopping.LogGeneration;
import jext.optim.heuristics.aco.stopping.MultipleConditions;
import jext.optim.heuristics.aco.stopping.NeverStop;
import jext.optim.heuristics.aco.stopping.Patience;

import java.util.Arrays;

public class CheckACS {

    static int SIZE = 100;

    public static void main(String[] args) {
        PermutationFitnessFunction ff = PermutationFitnessFunction.random(SIZE);
        float[][] distanceMatrix = ff.getDistanceMatrix();

        AntColony ac = new AntColony(
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
        Tour tour = ac.getBestTour();

        System.out.printf("%s: %f\n", Arrays.toString(tour.tour), tour.length);
        System.out.println("done");
    }
}
