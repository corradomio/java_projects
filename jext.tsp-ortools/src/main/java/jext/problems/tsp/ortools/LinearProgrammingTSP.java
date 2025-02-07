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

public class LinearProgrammingTSP extends AbstractTSP {

    static {
        //System.loadLibrary("jniortools");
        Loader.loadNativeLibraries();
    }

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
        RoutingIndexManager manager = new RoutingIndexManager(this.distances.size(), 1, 0);
        RoutingModel routing = new RoutingModel(manager);

        final int transitCallbackIndex =
            routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                // Convert from routing variable Index to user NodeIndex.
                int sloc = manager.indexToNode(fromIndex);
                int dloc = manager.indexToNode(toIndex);
                return (int)this.distances.distance(sloc, dloc);
            });

        RoutingSearchParameters searchParameters =
            main.defaultRoutingSearchParameters()
                .toBuilder()
                .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                .build();

        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);
        Assignment solution = routing.solveWithParameters(searchParameters);

        int[] tour = composeTour(routing, manager, solution);

        return new Solution(distances.distances(), distances.resolve(tour));
    }

    private int[] composeTour(RoutingModel routing, RoutingIndexManager manager, Assignment solution) {
        int[] tour = new int[this.distances.size()];

        int i =0;
        long index = routing.start(0);
        while (!routing.isEnd(index)) {
            tour[i++] = manager.indexToNode(index);
            index = solution.value(routing.nextVar(index));
        }

        return tour;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
