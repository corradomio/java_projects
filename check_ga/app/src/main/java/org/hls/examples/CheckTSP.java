package org.hls.examples;

import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.AntColonyOptimization;
import jext.optim.heuristics.aco.stopping.FixedGenerationCount;
import jext.optim.heuristics.aco.stopping.LogGeneration;
import jext.optim.heuristics.aco.stopping.MultipleConditions;
import jext.optim.heuristics.aco.stopping.NeverStop;
import jext.optim.heuristics.aco.stopping.Patience;
import jext.optim.heuristics.aco.tsp.TSPAntColony;
import jext.optim.problems.Distances;
import jext.problems.tsblib.TSPProblem;
import jext.problems.tsp.Solution;
import jext.problems.tsp.ch.ChristofidesTSP;
import jext.util.FileUtils;
import jext.util.TPrint;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CheckTSP {

    public static void main(String[] args) throws IOException {
        // TPrint.DELAY = 0;

        List<File> tspFiles = FileUtils.listFiles(new File("D:\\Projects.github\\java_projects\\jext.tsp\\TSP_instances\\tsplib"), ".tsp");

        for (File tspFile : tspFiles) {
            TSPProblem problem = TSPProblem.load(tspFile);

            if (problem.getDimension() > 2000)
                continue;

            TPrint.println(problem.getName());


            Distances distances = problem.getDistances();
            double[][] distanceMatrix = problem.getDistances().getMatrix();

            ChristofidesTSP tsp = new ChristofidesTSP();
            Solution sol = tsp.solve(distances);
            TPrint.printf("Cri: %f\n", sol.length());

            // alpha        pheromoneInfluence
            // beta         heuristicInfluence
            // ro           pheromoneDecay
            //
            //      alpha   beta    ro
            //      1       2-5     .5

            AntColony ac = new TSPAntColony(
                100, 1,
                1, 2, .5,
                distanceMatrix
            );

            AntColonyOptimization aco = new AntColonyOptimization();

            ac = aco.evolve(ac, new MultipleConditions(new NeverStop()
                , new LogGeneration()
                , new FixedGenerationCount(1000)
                , new Patience(20)
            ));

            jext.optim.problems.Solution<int[]> tour = ac.getFittestSolution();

            TPrint.printf("ACO: %f\n", tour.fitness());
            TPrint.println("----");

        }
    }
}
