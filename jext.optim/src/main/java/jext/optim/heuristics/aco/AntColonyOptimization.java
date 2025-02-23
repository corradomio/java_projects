package jext.optim.heuristics.aco;

import jext.math.random.UniformRandomGenerator;

import java.util.random.RandomGenerator;


public class AntColonyOptimization {

    private int generationsEvolved;

    public AntColony evolve(AntColony colony, StoppingCondition condition) {
        final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();
        colony.initialize(rng);

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
