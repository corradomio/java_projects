package jext.problems.tsp;

import java.util.Random;

/**
 * Abstract base class for TSP solvers
 */
public abstract class AbstractTSP implements TravelSalesmanProblem {//

    // Distances matrix
    protected double[][] distances;
    // locations to connect
    protected int[] locations;
    // size = locations.length
    protected int size;
    // length = distance.length
    protected int length;

    // ----------------------------------------------------------------------

    protected AbstractTSP() {

    }

    // ----------------------------------------------------------------------

    /** Problem size; number of locations to connect */
    public int size() {
        return size;
    }

    /** Distance matrix size (n rows = n cols) */
    public int length() {
        return length;
    }

    /**
     * Solve the problem connecting all points represented in the distance matrix
     * @param distances distance matrix
     * @return a Solution object
     */
    public Solution solve(double[][] distances) {
        return solve(distances, createTour(distances.length));
    }

    /**
     * Solve the problem connecting the points specified in ``locations''
     * @param distances distance matrix
     * @param locations locations to connect
     * @return Solution object
     */
    public Solution solve(double[][] distances, int[] locations) {
        this.distances = distances;
        this.locations = locations;
        this.size = locations.length;
        this.length = distances.length;

        return solve();
    }

    /**
     * Implement the solver
     * @return the solution found
     */
    protected abstract Solution solve();

    // ----------------------------------------------------------------------

    /**
     * Create a tour of ``n'' locations
     * @param n n of locations
     * @return tour [0,1,2,...,n-1]
     */
    protected static int[] createTour(int n) {
        int[] locations = new int[n];
        for (int i = 0; i < n; i++)
            locations[i] = i;
        return locations;
    }

    /**
     * Create a random tour
     * @param locations initial locations
     * @return random tour
     */
    protected static int[] createRandomTour(int[] locations) {
        Random random = new Random();
        int n= locations.length;

        int[] array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = locations[i];
        }

        for (int i = 0; i < n; ++i) {
            int index = random.nextInt(i + 1);
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }

        return array;
    }

    /**
     * Reorder the tour to have ``start'' as first location
     * @param tour tour to reorder
     * @param start start location
     * @return reordered tour
     */
    protected static int[] reorderTour(int[] tour, int start) {
        int size = tour.length;
        int at;
        for (at = 0; at <size; ++at)
            if (tour[at] == start)
                break;

        int[] reordered = new int[size];
        for (int i = 0; i < size; ++i)
            reordered[i] = tour[(at + i)%size];
        return reordered;
    }

    /**
     * Create a subset of the matrix based on the locations to visit
     * @return the distance matrix
     */
    protected double[][] createDistanceMatrix() {
        // if it is necessari to visit all locations
        if (distances.length == locations.length)
            return distances;

        // create a subset of the matrix
        double[][] distanceMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                distanceMatrix[i][j] = distances[locations[i]][locations[j]];
                distanceMatrix[j][i] = distanceMatrix[i][j];
            }
        }

        return distanceMatrix;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
}
