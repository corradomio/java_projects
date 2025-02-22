package jext.optim.heuristics.bpso;

import jext.optim.domain.Solution;

public class BinaryParticleSwarmOptimization<T> {

    private final UpdatePolicy<T> updatePolicy;
    private int generationsEvolved;

    public BinaryParticleSwarmOptimization(
        UpdatePolicy<T> updatePolicy
    ) {
        this.updatePolicy = updatePolicy;
    }

    public Solution<T> solve(boolean maximize, Swarm<T> swarm, StoppingCondition stoppingCondition) {
        swarm.setDecreasingOrder(maximize);
        swarm.setUpdatePolicy(updatePolicy);

        swarm.initialize();
        generationsEvolved = 0;
        while(!stoppingCondition.isSatisfied(swarm)) {
            swarm.nextGeneration();
            generationsEvolved++;
        }

        return swarm.getGlobalBest();
    }

    public int getGenerationsEvolved() {
        return generationsEvolved;
    }
}
