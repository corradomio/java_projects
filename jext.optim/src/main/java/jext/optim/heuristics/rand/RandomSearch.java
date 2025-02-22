package jext.optim.heuristics.rand;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.Solution;

import java.util.Random;
import java.util.random.RandomGenerator;

public class RandomSearch<T> {

    private RandomFactory<T> randomFactory;

    public RandomSearch() {

    }

    public Solution<T> solve(boolean maximize, RandomFactory<T> randomFactory, StoppingCondition stoppingCondition) {
        randomFactory.setDecreasingOrder(maximize);

        RandomGenerator randGen = UniformRandomGenerator.getRandomGenerator();

        RandomCandidate<T> best = randomFactory.candidate(randGen);
        while (!stoppingCondition.isSatisfied(best)) {
            RandomCandidate<T> sol = randomFactory.candidate(randGen);
            best = best.compareTo(sol) < 0 ? best : sol;
        }

        return best;
    }

}
