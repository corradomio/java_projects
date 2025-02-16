package jext.problems.vrp.ortools;

import com.google.ortools.Loader;
import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.FirstSolutionStrategy;
import com.google.ortools.constraintsolver.RoutingDimension;
import com.google.ortools.constraintsolver.RoutingIndexManager;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.RoutingSearchParameters;
import com.google.ortools.constraintsolver.main;
import jext.optim.problems.Distances;
import jext.optim.problems.distance.TourDistances;
import jext.problems.vrp.AbstractVRP;
import jext.problems.vrp.Solution;
import jext.problems.vrp.VRPUtils;
import jext.util.ArrayUtils;

import java.util.Arrays;

public class LinearProgrammingVRP extends AbstractVRP {

    static {
        // Note: 'loadNativeLibraries' already checl for multiple loads
        Loader.loadNativeLibraries();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LinearProgrammingVRP() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Solution solve() {
        //
        // Data preparation
        //

        // crea a 'virtual distance matrix' referring ONLY to deposits and locations to visit
        // (virtual locations)
        Distances tourDistances = new TourDistances(distances).withDepositsAndLocations(deposits, locations);

        // the deposit locations are the first 'deposits.length' virtual locations
        int[] tourDeposits = ArrayUtils.range(deposits.length);
        // the locations to visit are the remaining virtual loations
        int[] tourDemands = ArrayUtils.moveToEnd(deposits.length, locationsDemand);

        // where te vehicles are located: vehicles starts and comes back to the same deposit
        // there is an element in the array for each vehicle
        int[] vehiclesDeposit = VRPUtils.composeVehiclesDeposit(tourDeposits, numDepositVehicles);

        //
        // ORTools:  RoutingIndexManager & RoutingModel
        //

        RoutingIndexManager manager = new RoutingIndexManager(tourDistances.size(), vehiclesDeposit.length, /*start*/vehiclesDeposit, /*end*/vehiclesDeposit);
        RoutingModel routing = new RoutingModel(manager);

        // callback used to obtain the distances
        final int transitCallbackIndex =
            routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                // Convert from routing variable Index to user NodeIndex.
                int sloc = manager.indexToNode(fromIndex);
                int dloc = manager.indexToNode(toIndex);
                return (int)tourDistances.distance(sloc, dloc);
            });

        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);

        // callback used to obtain the demand for each 'virtual location' (including the deposits)
        final int demandCallbackIndex = routing.registerUnaryTransitCallback((long fromIndex) -> {
            int location = manager.indexToNode((int) fromIndex);
            return tourDemands[location];
        });

        //
        // add distance constraint
        //

        if (constraints.hasDistanceConstraint()) {
            routing.addDimension(transitCallbackIndex, 0, (long)constraints.maxDistance, true, "Distance");

            RoutingDimension distanceDimension = routing.getMutableDimension("Distance");
            distanceDimension.setGlobalSpanCostCoefficient(100);
        }

        //
        // add vehicle capacity constraint
        //
        if (constraints.hasVehicleCapacityConstraint()) {
            long[] vehiclesCapacity = new long[vehiclesDeposit.length];
            Arrays.fill(vehiclesCapacity, (long)constraints.maxVehicleCapacity);

            routing.addDimensionWithVehicleCapacity(
                demandCallbackIndex, 0,
                vehiclesCapacity,
                true, "Capacity"
            );
        }

        //
        // Search parameters
        //
        RoutingSearchParameters searchParameters =
            main.defaultRoutingSearchParameters()
                .toBuilder()
                .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                .build();

        //
        // Solve the problem
        //
        Assignment assignment = routing.solveWithParameters(searchParameters);

        //
        // compose the vehicles' tours
        //
        int[][] vehiclesTour = composeTours(routing, manager, assignment, vehiclesDeposit.length);

        // End
        return new Solution(distances, vehiclesTour);
    }

    private int[][] composeTours(RoutingModel routing, RoutingIndexManager manager, Assignment assignment, int nVehicles) {
        int m = this.distances.size();
        int[] tour = new int[m];
        int[][] vehiclesTour = new int[nVehicles][];

        for (int i=0; i<nVehicles; ++i) {
            int len = 0;
            long index = routing.start(i);
            while (!routing.isEnd(index)) {
                int locIndex = manager.indexToNode(index);
                tour[len++] = toLocation(locIndex);
                index = assignment.value(routing.nextVar(index));
            }

            vehiclesTour[i] = Arrays.copyOf(tour, len);
        }

        return vehiclesTour;
    }

    private int toLocation(int locIndex) {
        if (locIndex < deposits.length)
            return deposits[locIndex];
        else
            return locations[locIndex - deposits.length];
    }
}
