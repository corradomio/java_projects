package jext.optim.heuristics.aco;

import jext.optim.problems.Solution;

public interface AntColony {

    void initialize();
    void nextGeneration();

    Solution<int[]> getFittestSolution();
}
