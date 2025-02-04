package jext.problems.tsp;

/**
 * TSP solver solution:
 *
 */
public class Solution {
    public final double[][] distances;
    public final int[] tour;
    public final int size;

    public Solution(double[][] distances, int[] tour) {
        this.distances = distances;
        this.tour = tour;
        this.size = tour.length;
    }

    /**
     * Sequence of locations to visit
     */
    public int[] tour() {
        return tour;
    }

    /**
     * Total distance. It is not included the distance from the last point to the first one
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
    public double length(boolean closed) {
        double length = closed ? distances[0][size-1] : 0;
        for(int i = 0, j=1; j < distances.length; i++, j++)
            length += distances[i][j];
        return length;
    }

}
