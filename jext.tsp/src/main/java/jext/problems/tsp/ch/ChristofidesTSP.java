package jext.problems.tsp.ch;

import christofides.Christofides;
import jext.problems.tsp.AbstractTSP;
import jext.problems.tsp.Solution;

/**
 * Christofides Algorithm
 *
 *     https://en.wikipedia.org/wiki/Christofides_algorithm
 *     https://alon.kr/posts/christofides
 *     https://github.com/faisal22/Christofides
 *     https://github.com/originalname51/Christofides-Algorithm
 *     https://github.com/Retsediv/ChristofidesAlgorithm
 */
public class ChristofidesTSP extends AbstractTSP {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ChristofidesTSP(){}

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    protected Solution solve() {

        // create the sub distance matrix used by Christofides
        double[][] distanceMatrix = createDistanceMatrix();

        Christofides ch = new Christofides();

        int[] chtour = ch.solve(distanceMatrix);

        int[] tour = createTour(chtour);

        tour = reorderTour(tour, locations[0]);

        return new Solution(distances, tour);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private int[] createTour(int[] chtour) {
        int[] tour = new int[chtour.length];
        for (int i = 0; i < chtour.length; i++) {
            tour[i] = locations[chtour[i]];
        }
        return tour;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
