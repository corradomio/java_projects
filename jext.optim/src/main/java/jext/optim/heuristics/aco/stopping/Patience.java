package jext.optim.heuristics.aco.stopping;


import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.StoppingCondition;

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
    public boolean isSatisfied(final AntColony antColony) {
        double fitness = antColony.getFittestSolution().fitness();
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
