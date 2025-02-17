package jext.optim.heuristics.aco;

import java.util.Random;
import java.util.random.RandomGenerator;

import jext.optim.heuristics.aco.AntColony;


public class AntColonyOptimization {

    private static RandomGenerator randomGenerator = new Random();

    private int generationsEvolved;


    public AntColony evolve(AntColony colony, StoppingCondition condition) {
        colony.initialize();

        generationsEvolved = 0;
        while(!condition.isSatisfied(colony)) {
            colony.nextGeneration();
            generationsEvolved++;
        }

        return colony;
    }

    public static synchronized RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public int getGenerationsEvolved() {
        return generationsEvolved;
    }
}
