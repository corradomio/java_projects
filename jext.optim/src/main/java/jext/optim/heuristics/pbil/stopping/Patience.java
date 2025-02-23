package jext.optim.heuristics.pbil.stopping;

import jext.optim.heuristics.pbil.Population;
import jext.optim.heuristics.pbil.StoppingCondition;


public class Patience implements StoppingCondition {
    private final int count;
    private int current;
    private double bestFitness;

    public Patience(int count) {
        this.count = count;
    }

    @Override
    public boolean isSatisfied(Population population) {
        double fitness = population.getFittestLearner().fitness();

        if (bestFitness != fitness) {
            bestFitness = fitness;
            current = 0;
        }

        current++;

        return (current >= count);
    }
}
