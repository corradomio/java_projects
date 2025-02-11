package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.StoppingCondition;


public class Patience implements StoppingCondition {
    private final int count;
    private int current;
    private double bestFitness;

    public Patience(int count) {
        this.count = count;
        this.current = 0;
        this.bestFitness = Double.NEGATIVE_INFINITY;
    }

    @Override
    public boolean isSatisfied(Population population) {
        double fitness = population.getFittestChromosome().fitness();
        if (fitness > bestFitness) {
            bestFitness = fitness;
            current = 0;
        }
        else {
            current++;
        }

        return (current >= count);
    }
}
