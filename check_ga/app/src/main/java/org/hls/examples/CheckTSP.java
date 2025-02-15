package org.hls.examples;

import jext.optim.heuristics.ants.AntColony;
import jext.optim.heuristics.ants.AntColonyOptimization;
import jext.optim.heuristics.ants.Tour;
import jext.optim.heuristics.ants.stopping.FixedGenerationCount;
import jext.optim.heuristics.ants.stopping.LogGeneration;
import jext.optim.heuristics.ants.stopping.MultipleConditions;
import jext.optim.heuristics.ants.stopping.NeverStop;
import jext.optim.heuristics.ants.stopping.Patience;
import jext.problems.Distances;
import jext.problems.tsblib.TSPProblem;
import jext.problems.tsp.Solution;
import jext.problems.tsp.ch.ChristofidesTSP;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CheckTSP {

    public static void main(String[] args) throws IOException {

        TSPProblem problem = TSPProblem.load(new File("D:\\Projects.github\\java_projects\\jext.tsp\\problems\\tsplib\\a280.tsp"));

        Distances distances = problem.getDistances();
        float[][] distanceMatrix = problem.getDistances().getMatrix();

        ChristofidesTSP tsp = new ChristofidesTSP();
        Solution sol = tsp.solve(distances);
        System.out.println(sol);

        AntColony ac = new AntColony(
            1000, .001,
            2, 2, 1,
            distanceMatrix
        );

        AntColonyOptimization aco = new AntColonyOptimization();

        ac = aco.evolve(ac, new MultipleConditions(new NeverStop()
            , new LogGeneration()
            , new FixedGenerationCount(1000)
            , new Patience(20)
        ));
        Tour tour = ac.getBestTour();

        System.out.printf("%s: %f\n", Arrays.toString(tour.tour), tour.length);
        System.out.println("done");

    }
}
