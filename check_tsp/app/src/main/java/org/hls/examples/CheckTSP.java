package org.hls.examples;

import jext.problems.tsp.ortools.LinearProgrammingTSP;
import jext.problems.tsp.TSPLIBProblem;
import jext.problems.tsp.TravelSalesmanProblem;
import jext.problems.tsp.ch.ChristofidesTSP;
import jext.problems.tsp.lkh.LinKernighanTSP;
import jext.problems.tsp.nn.NearestNeighborTSP;
import jext.problems.tsp.Solution;
import jext.problems.tsp.topt.TwoOptTSP;
import jext.util.logging.Logger;
import org.hls.examples.utils.Distance;
import org.hls.examples.utils.Location;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CheckTSP {

    public static void main(String[] args) throws IOException {

        Logger.configure(new File("logging.properties"));

        File tspProblems = new File("D:\\Projects.github\\java_projects\\jext.tsp\\problems\\tsplib");

        for(File tspProblem : tspProblems.listFiles()) {
            try {
                TSPLIBProblem tsp = TSPLIBProblem.load(tspProblem);
                if (tsp.size() >= 100) continue;

                System.out.println(tspProblem.getName());

                double[][] dmatrix = tsp.distanceMatrix;

                checkSolver(new NearestNeighborTSP(), dmatrix);
                // checkSolver(new LinKernighanTSP(), dmatrix);
                // checkSolver(new ChristofidesTSP(), dmatrix);
                // checkSolver(new TwoOptTSP(), dmatrix);
                checkSolver(new LinearProgrammingTSP(), dmatrix);
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

        Distance d = Distance.distanceMatrix(locations, 100);

        checkSolver(new NearestNeighborTSP(), d.matrix);
        checkSolver(new TwoOptTSP(), d.matrix);
        checkSolver(new LinKernighanTSP(), d.matrix);
        checkSolver(new ChristofidesTSP(), d.matrix);


    }

    private static void checkSolver(TravelSalesmanProblem tsp, double[][] distanceMatrix) {
        // System.out.println(tsp.getClass().getSimpleName());

        Solution sol = tsp.solve(distanceMatrix);

        // for (int s : sol.tour) {
        //     System.out.print(s);
        //     System.out.print(' ');
        // }
        // System.out.println();
        // System.out.println(sol.length());

        System.out.printf("... %s: %f\n", tsp.getClass().getSimpleName(), sol.length(true));
    }
}
