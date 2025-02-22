package jext.optim.heuristics.aco;

import jext.optim.domain.Solution;

public interface AntColony {

    void initialize();
    void nextGeneration();

    Solution<int[]> getFittestSolution();
}
