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
        int n = locations.length;
        int m = distances.length;
        int[] solution = new int[n];
        boolean[] visited = new boolean[m];
        double seldist;
        int sel, curr = locations[0];


        for(int i=1; i<n; i++) {
            visited[curr] = true;
            solution[solved++] = curr;

            seldist = Double.MAX_VALUE;
            sel = curr;
            for(int j=0; j<n; j++) {
                int next = locations[j];
                if (visited[next]) continue;
                double d = distances[curr][next];
                if (sel < seldist) {
                    seldist = d;
                    sel = next;
                }
            }

            curr = sel;
        }

        // add the last location
        for(int i=0; i<n; i++) {
            if (!visited[locations[i]]) {
                solution[n-1] = locations[i];
                break;
            }
        }

        return new Solution(distances, solution);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
