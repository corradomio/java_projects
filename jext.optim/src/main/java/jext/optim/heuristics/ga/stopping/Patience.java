package jext.optim.heuristics.ga.stopping;

import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.StoppingCondition;


public class Patience implements StoppingCondition {
    private final int count;
    private int current;
    private double bestFitness;

    public Patience(int count) {
        this.count = count;
    }

    @Override
    public boolean isSatisfied(Population population) {
        double fitness = population.getFittestChromosome().fitness();

        if (bestFitness != fitness) {
            bestFitness = fitness;
            current = 0;
        }

        current++;

        return (current >= count);
    }
}
