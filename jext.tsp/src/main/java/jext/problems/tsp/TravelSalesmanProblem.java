package jext.problems.tsp;

/**
 * Generic TSP solver based on distance matrix
 */
public interface TravelSalesmanProblem {

    /// number of locations to visit
    int size();
    /// number of locations in the distance matrix:
    /// it can greater the number of locations to visit
    int length();

    /** solve the TSP problem visiting all locations specified in the distance matrix */
    Solution solve(double[][] distanceMatrix);
    /** solve the TSP problem visiting the locations specified in ``locations'' */
    Solution solve(double[][] distanceMatrix, int[] locations);
}
