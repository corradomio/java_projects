package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.StoppingCondition;

import static java.lang.Math.abs;
import static java.lang.Math.max;


public class Patience implements StoppingCondition {
    private final int count;
    private final double delta;
    private int current;
    private double bestFitness;

    public Patience(int count) {
        this(count, 0.00001f);
    }

    public Patience(int count, double delta) {
        this.count = count;
        this.delta = delta;
        this.current = 0;
        this.bestFitness = Double.NEGATIVE_INFINITY;
    }

    @Override
    public boolean isSatisfied(Population population) {
        double fitness = population.getFittestChromosome().fitness();
        double delta = abs(fitness - bestFitness)/max(fitness, bestFitness);

        if (delta > this.delta) {
            bestFitness = fitness;
            current = 0;
        }
        else {
            current++;
        }

        return (current >= count);
    }
}
