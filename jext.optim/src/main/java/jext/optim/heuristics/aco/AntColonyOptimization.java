package jext.optim.heuristics.aco;

import java.util.Random;
import java.util.random.RandomGenerator;


public class AntColonyOptimization {

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

    public int getGenerationsEvolved() {
        return generationsEvolved;
    }
}
