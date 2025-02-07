package jext.problems.tsp.nn;

import jext.problems.tsp.AbstractTSP;
import jext.problems.tsp.Solution;

/**
 * Implementation of the NearestNeighbor algorithm to solve the Symmetric TSP problem
 *
 *      https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm
 */
public class NearestNeighborTSP extends AbstractTSP {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public NearestNeighborTSP(){}

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    protected Solution solve() {
        int solved = 0;
        int n = distances.size();
        int m = distances.order();
        int[] tour = new int[n];
        boolean[] visited = new boolean[m];
        double seldist;
        int sel, curr = 0;


        for(int i=1; i<n; i++) {
            visited[curr] = true;
            tour[solved++] = curr;

            seldist = Double.MAX_VALUE;
            sel = curr;
            for(int j=0; j<n; j++) {
                int next = j;
                if (visited[next]) continue;
                double d = distances.distance(curr, next);
                if (sel < seldist) {
                    seldist = d;
                    sel = next;
                }
            }

            curr = sel;
        }

        // add the last location
        for(int i=0; i<n; i++) {
            if (!visited[i]) {
                tour[n-1] = i;
                break;
            }
        }

        return new Solution(distances.distances(), distances.resolve(tour));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
