package jext.problems.tsp.ortools;

import com.google.ortools.Loader;
import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.FirstSolutionStrategy;
import com.google.ortools.constraintsolver.RoutingIndexManager;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.RoutingSearchParameters;
import com.google.ortools.constraintsolver.main;
import jext.problems.tsp.AbstractTSP;
import jext.problems.tsp.Solution;

import jext.util.logging.Logger;

public class LinearProgrammingTSP extends AbstractTSP {

    private static Logger logger = Logger.getLogger(LinearProgrammingTSP.class);

    static {
        //System.loadLibrary("jniortools");
        Loader.loadNativeLibraries();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static double DISTANCE_SCALE = 1;

    private long[][] distanceMatrix;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LinearProgrammingTSP() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    protected Solution solve() {
        createIntegerDistanceMatrix();

        RoutingIndexManager manager = new RoutingIndexManager(this.distanceMatrix.length, 1, 0);
        RoutingModel routing = new RoutingModel(manager);

        final int transitCallbackIndex =
            routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                // Convert from routing variable Index to user NodeIndex.
                int fromNode = manager.indexToNode(fromIndex);
                int toNode = manager.indexToNode(toIndex);
                return this.distanceMatrix[fromNode][toNode];
            });

        RoutingSearchParameters searchParameters =
            main.defaultRoutingSearchParameters()
                .toBuilder()
                .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                .build();

        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);
        Assignment solution = routing.solveWithParameters(searchParameters);

        printSolution(routing, manager, solution);

        int[] tour = composeTour(routing, manager, solution);

        return new Solution(distances, tour);
    }

    private void createIntegerDistanceMatrix() {
        double[][] distanceMatrix = createDistanceMatrix();
        int n = distanceMatrix.length;

        this.distanceMatrix = new long[n][n];

        for (int i=0; i<n; ++i)
            for(int j=0; j<n; ++j)
                this.distanceMatrix[i][j] = (long)(DISTANCE_SCALE*distanceMatrix[i][j]);
    }

    private int[] composeTour(RoutingModel routing, RoutingIndexManager manager, Assignment solution) {
        int[] tour = new int[locations.length];

        int i =0;
        long index = routing.start(0);
        while (!routing.isEnd(index)) {
            tour[i++] = manager.indexToNode(index);
            index = solution.value(routing.nextVar(index));
        }

        return tour;
    }


    /// @brief Print the solution.
    static void printSolution(
        RoutingModel routing, RoutingIndexManager manager, Assignment solution) {
        // Solution cost.
        logger.info("Objective: " + solution.objectiveValue() + "miles");
        // Inspect solution.
        logger.info("Route:");
        long routeDistance = 0;
        String route = "";
        long index = routing.start(0);
        while (!routing.isEnd(index)) {
            route += manager.indexToNode(index) + " -> ";
            long previousIndex = index;
            index = solution.value(routing.nextVar(index));
            routeDistance += routing.getArcCostForVehicle(previousIndex, index, 0);
        }
        route += manager.indexToNode(routing.end(0));
        logger.info(route);
        logger.info("Route distance: " + routeDistance/DISTANCE_SCALE + " miles");
    }
    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
