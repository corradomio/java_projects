package jext.optim.heuristics.sa.stopping;

import jext.optim.heuristics.sa.StoppingCondition;
import jext.optim.heuristics.sa.Population;

public class Patience implements StoppingCondition {
    private final int count;
    private int current;
    private double bestFitness;

    public Patience(int count) {
        this.count = count;
    }

    @Override
    public boolean isSatisfied(Population population) {
        double fitness = population.getFittestParticle().fitness();

        if (bestFitness != fitness) {
            bestFitness = fitness;
            current = 0;
        }

        current++;

        return (current >= count);
    }
}
