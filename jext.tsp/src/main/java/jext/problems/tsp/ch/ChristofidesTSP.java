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
        float[][] distanceMatrix = distances.getMatrix();

        Christofides ch = new Christofides();

        int[] chtour = ch.solve(distanceMatrix);

        return new Solution(distances.getDistances(), distances.resolve(chtour));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
