package jext.problems.tsp.topt;

import jext.problems.tsp.AbstractTSP;
import jext.problems.tsp.Solution;
import jext.problems.tsp.TourUtils;

/**
 * 2-Opt TSP algorithm
 *
 *      https://en.wikipedia.org/wiki/2-opt
 *      https://web2.qatar.cmu.edu/~gdicaro/15382/additional/local-search-and-ILS.pdf
 */
public class TwoOptTSP extends AbstractTSP {

    private int[] tour;
    private int size;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public TwoOptTSP() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    protected Solution solve() {
        size = distances.size();
        tour = TourUtils.randomTour(size);

        runAlgorithm();

        return new Solution(distances.getDistances(), distances.resolve(tour));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private void runAlgorithm() {
        int n = size;
        boolean foundImprovement = true;

        while (foundImprovement) {
            foundImprovement = false;

            for(int i = 0; i < n - 2; i++) {
                for(int j = i + 2; j < n-1; j++) {
                    double delta = -distance(i, i+1) - distance(j, j+1) +
                        distance(i, j) + distance(i+1, j+1);
                    if (delta < 0) {
                        swap(i, j);
                        foundImprovement = true;
                    }
                }
            }
        }
    }

    private double distance(int i, int j) {
        return distances.distance(tour[i], tour[j]);
    }

    private void swap(int i, int j) {
        i += 1;
        while (i < j) {
            int t = tour[i];
            tour[i] = tour[j];
            tour[j] = t;
            ++i;
            --j;
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
