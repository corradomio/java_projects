package jext.optim.heuristics.dpso.stopping;

import jext.optim.heuristics.dpso.Population;
import jext.optim.heuristics.dpso.StoppingCondition;

public class Patience implements StoppingCondition {
    private final int count;
    private int current;
    private double bestFitness;

    public Patience(int count) {
        this.count = count;
    }

    @Override
    public boolean isSatisfied(Population population) {
        double fitness = population.getGlobalBest().fitness();

        if (bestFitness != fitness) {
            bestFitness = fitness;
            current = 0;
        }

        current++;

        return (current >= count);
    }
}
