package jext.optim.heuristics.aco;

import jext.optim.domain.Solution;

import java.util.random.RandomGenerator;

public interface AntColony {

    void initialize(RandomGenerator rng);
    void nextGeneration();

    Solution<int[]> getFittestSolution();
}
