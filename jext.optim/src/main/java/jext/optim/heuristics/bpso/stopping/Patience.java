package jext.optim.heuristics.bpso.stopping;

import jext.optim.heuristics.bpso.Swarm;
import jext.optim.heuristics.bpso.StoppingCondition;

public class Patience implements StoppingCondition {
    private final int count;
    private int current;
    private double bestFitness;

    public Patience(int count) {
        this.count = count;
    }

    @Override
    public boolean isSatisfied(Swarm population) {
        double fitness = population.getGlobalBest().fitness();

        if (bestFitness != fitness) {
            bestFitness = fitness;
            current = 0;
        }

        current++;

        return (current >= count);
    }
}
