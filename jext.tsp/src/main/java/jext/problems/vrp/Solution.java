package jext.problems.vrp;

import jext.problems.Distances;

public class Solution {
    public final Distances distances;
    public final VehicleTour[] vehiclesTour;

    public Solution(Distances distances, int[][] vehiclesTour) {
        this.distances = distances;
        this.vehiclesTour = new VehicleTour[vehiclesTour.length];
        for (int i=0; i<vehiclesTour.length; ++i)
            this.vehiclesTour[i] = new VehicleTour(distances, vehiclesTour[i]);
    }

    public Solution(Distances distances, VehicleTour[] vechiclesTour) {
        this.distances = distances;
        this.vehiclesTour = vechiclesTour;
    }
}
