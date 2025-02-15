package jext.problems.tsp.beb;

import jext.problems.tsp.AbstractTSP;
import jext.problems.tsp.Solution;
import lilyinstarlight.Solver;

public class BranchAndBoundTSP extends AbstractTSP {

    @Override
    public Solution solve() {

        Solver solver = new Solver(distances.getDistances().getMatrix());

        int[] bbtour = solver.calculate();


        return new Solution(distances, distances.resolve(bbtour));
    }

}
