package jext.problems.tsp;

import jext.optim.problems.Distances;

/**
 * Generic TSP solver based on distance matrix
 */
public interface TSPSolver {

    /**
     * Solve the TSP problem: found the best permutation of locations
     * based on the distance matrix
     *
     * @param distances locations distances
     * @param locations locations to visit
     * @return
     */
    Solution solve(Distances distances, int[] locations);

    /**
     * Solve the TSP problem: found the best permutation of locations
     * to visit all locations specified in the distance matrix
     *
     * @param distances locations distances
     * @return
     */
    Solution solve(Distances distances);
}
