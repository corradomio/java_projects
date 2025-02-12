package org.hls.examples;

import jext.problems.Distances;
import jext.problems.dist.EuclideanSpace;
import jext.problems.tsp.TSPLIBProblem;
import jext.problems.tsp.TSPSolver;
import jext.problems.tsp.ch.ChristofidesTSP;
import jext.problems.tsp.lkh.LinKernighanTSP;
import jext.problems.tsp.nn.NearestNeighborTSP;
import jext.problems.tsp.Solution;
import jext.problems.tsp.ortools.LinearProgrammingTSP;
import jext.problems.tsp.topt.TwoOptTSP;
import jext.util.logging.Logger;
import org.hls.examples.utils.Location;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class CheckTSP {

    public static void main(String[] args) {

        Logger.configure(new File("logging.properties"));

        File tspProblems = new File("D:\\Projects.github\\java_projects\\jext.tsp\\problems\\tsplib");
        // File tspProblems = new File("D:\\Projects.github\\java_projects\\jext.tsp\\problems\\tspext");

        for(File tspProblem : Objects.requireNonNull(tspProblems.listFiles((dir, name) -> name.endsWith(".tsp")))) {
            try {

                TSPLIBProblem tsp = TSPLIBProblem.load(tspProblem);

                if (tsp.size() < 400 || tsp.size() >= 500)
                    continue;

                System.out.printf("%s: order=%d\n", tsp.getName(), tsp.size());

                Distances dmatrix = tsp.distanceMatrix();

                checkSolver(new NearestNeighborTSP(), dmatrix);
                checkSolver(new LinKernighanTSP(), dmatrix);
                checkSolver(new ChristofidesTSP(), dmatrix);
                checkSolver(new LinearProgrammingTSP(), dmatrix);

                // checkSolver(new BranchAndBoundTSP(), dmatrix);
                // checkSolver(new TwoOptTSP(), dmatrix);
                // break;
            }
            catch(Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("done");
    }

    public static void main1(String[] args) throws Exception {

        List<Location> locations = Location.load(new File("locations_to_visit.csv"));

        // Distance d = Distance.distanceMatrix(locations, 100);
        Distances distanceMatrix = new EuclideanSpace(locations);

        checkSolver(new NearestNeighborTSP(), distanceMatrix);
        checkSolver(new TwoOptTSP(), distanceMatrix);
        checkSolver(new LinKernighanTSP(), distanceMatrix);
        checkSolver(new ChristofidesTSP(), distanceMatrix);


    }

    private static void checkSolver(TSPSolver tsp, Distances distances) {
        Solution sol = tsp.solve(distances);

        System.out.printf("... %s: %f\n", tsp.getClass().getSimpleName(), sol.length(true));
        // System.out.printf("... ...%s\n", TourUtils.toString(sol.tour));
    }

    // private static void checkSolver(TravelSalesmanProblem tsp, Distances distances, int[] locations) {
    //     Solution sol = tsp.solve(distances, locations);
    //
    //     System.out.printf("... %s: %f\n", tsp.getClass().getSimpleName(), sol.length(true));
    // }
}
