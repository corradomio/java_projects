package jext.problems.tsp;

import jext.problems.Distances;
import jext.problems.dist.TourDistances;

/**
 * Abstract base class for TSP solvers
 */
public abstract class AbstractTSP implements TSPSolver {//

    protected TourDistances distances;

    // ----------------------------------------------------------------------

    protected AbstractTSP() {

    }

    // ----------------------------------------------------------------------

    /** Problem size; number of locations to connect */
    public int size() {
        return distances.size();
    }

    /** Distance matrix size (n rows = n cols) */
    public int order() {
        return distances.order();
    }

    /**
     * Solve the problem connecting all points represented in the distance matrix
     * @param distances distance matrix
     * @return a Solution object
     */
    public Solution solve(Distances distances) {
        return solve(distances, TourUtils.defaultTour(distances.order()));
    }

    /**
     * Solve the problem connecting the points specified in ``locations''
     * @param distances distance matrix
     * @param locations locations to connect
     * @return Solution object
     */
    public Solution solve(Distances distances, int[] locations) {
        this.distances = new TourDistances(distances).withLocations(locations);
        return solve();
    }

    /**
     * Implement the solver
     * @return the solution found
     */
    protected abstract Solution solve();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
}
