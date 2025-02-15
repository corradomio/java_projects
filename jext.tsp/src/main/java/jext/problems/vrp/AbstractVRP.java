package jext.problems.vrp;

import jext.problems.Distances;
import jext.problems.dist.TourDistances;

import java.util.Arrays;

public abstract class AbstractVRP implements VRPSolver {

    private static final int[] EMPTY_ARRAY = new int[0];

    protected Distances distances;
    protected int[] locations = EMPTY_ARRAY;
    protected int[] locationsDemand = EMPTY_ARRAY;
    protected int[] deposits = EMPTY_ARRAY;
    protected int[] numDepositVehicles = EMPTY_ARRAY;

    protected VRPConstraints constraints;
    protected VRPParameters parameters;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------


    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public Solution solve(
        final Distances distances,
        final int[] locations,
        final int[] deposits,
        final VRPConstraints constraints,
        final VRPParameters parameters
    ) {
        int[] numDepositVehicles = new int[deposits.length];
        Arrays.fill(numDepositVehicles, 1);

        int[] locationsDemand = new int[locations.length];
        Arrays.fill(locationsDemand, 1);

        return solve(distances, locations, locationsDemand, deposits, numDepositVehicles, constraints, parameters);
    }

    @Override
    public Solution solve(
        final Distances distances,
        final int[] locations,
        final int[] deposits,
        final int[] numDepositVehicles,
        final VRPConstraints constraints,
        final VRPParameters parameters
    ) {
        int[] locationsDemand = new int[locations.length];
        Arrays.fill(locationsDemand, 1);

        return solve(distances, locations, locationsDemand, deposits, numDepositVehicles, constraints, parameters);
    }

    @Override
    public Solution solve(
        final Distances distances,
        final int[] locations, final int[] locationsDemand,
        final int[] deposits,  final int[] numDepositVehicles,
        final VRPConstraints constraints,
        final VRPParameters parameters
    ) {
        this.distances = distances; //new TourDistances(distances).withDepositsAndLocations(deposits, locations);
        this.locations = locations;
        this.locationsDemand = locationsDemand;
        this.deposits = deposits;
        this.numDepositVehicles = numDepositVehicles;
        this.constraints = constraints;
        this.parameters = parameters;

        return solve();
    }

    protected abstract Solution solve();
}
