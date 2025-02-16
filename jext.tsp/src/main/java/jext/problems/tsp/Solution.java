package jext.problems.tsp;

import jext.optim.problems.Distances;
import jext.util.ArrayUtils;

/**
 * TSP solver solution:
 *
 */
public class Solution {
    public final Distances distances;
    public final int[] tour;

    public Solution(Distances distances, int[] tour) {
        this.distances = distances;
        this.tour = tour;
    }

    /**
     * Sequence of locations to visit
     */
    public int[] tour() {
        return tour;
    }

    /**
     * Total distance. It is included the distance from the last point to the first one
     * @return total distance
     */
    public double length() {
        return length(true);
    }

    /**
     * Total distance
     * @param closed if to include the distance from the last location to the first one
     * @return total distance
     */
    public float length(boolean closed) {
        int n = tour.length;
        float length = closed ? distances.distance(tour[n-1], tour[0]) : 0;
        for(int j=1; j < n; j++)
            length += distances.distance(tour[j-1], tour[j]);
        return length;
    }

    @Override
    public String toString() {
        return String.format("%s: %f", ArrayUtils.asList(tour), length());
    }

}
