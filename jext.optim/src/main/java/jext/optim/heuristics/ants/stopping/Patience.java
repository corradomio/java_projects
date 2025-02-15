package jext.optim.heuristics.ants.stopping;


import jext.optim.heuristics.ants.AntColony;
import jext.optim.heuristics.ants.StoppingCondition;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Patience implements StoppingCondition {
    private final int count;
    private final float delta;
    private int current;
    private float bestFitness;

    public Patience(int count) {
        this(count, 0.00001f);
    }

    public Patience(int count, float delta) {
        this.count = count;
        this.delta = delta;
        this.current = 0;
        this.bestFitness = Float.NEGATIVE_INFINITY;
    }

    @Override
    public boolean isSatisfied(final AntColony population) {
        float fitness = population.getBestTour().getTourLength();
        float delta = abs(fitness - bestFitness)/max(fitness, bestFitness);

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
