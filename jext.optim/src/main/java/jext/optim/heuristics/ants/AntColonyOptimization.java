package jext.optim.heuristics.ants;

import java.util.Random;
import java.util.random.RandomGenerator;

public class AntColonyOptimization {

    private static RandomGenerator randomGenerator = new Random();

    private int generationsEvolved;


    public AntColony evolve(AntColony colony, StoppingCondition condition) {
        colony.initialize();

        generationsEvolved = 0;
        while(!condition.isSatisfied(colony)) {
            nextGeneration(colony);
            generationsEvolved++;
        }

        return colony;
    }

    private AntColony nextGeneration(AntColony current) {
        current = current.nextGeneration();
        return current;
    }

    public static synchronized RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }
}
