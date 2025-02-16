package jext.problems.vrp;

import jext.optim.problems.Distances;

public class VehicleTour {

    public final Distances distances;
    public final int[] tour;

    public VehicleTour(Distances distances, int[] tour) {
        this.distances = distances;
        this.tour = tour;
    }
}
