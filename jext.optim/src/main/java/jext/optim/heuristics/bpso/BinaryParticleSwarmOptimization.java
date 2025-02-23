package jext.optim.heuristics.bpso;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.Solution;
import jext.util.concurrent.Parallel;

import java.util.List;
import java.util.random.RandomGenerator;

public class BinaryParticleSwarmOptimization<T> {

    private final UpdatePolicy<T> updatePolicy;
    private int generationsEvolved;

    // ----------------------------------------------------------------------

    public BinaryParticleSwarmOptimization(
        UpdatePolicy<T> updatePolicy
    ) {
        this.updatePolicy = updatePolicy;
    }

    // ----------------------------------------------------------------------

    public Solution<T> solve(boolean maximize, Population<T> current, StoppingCondition stoppingCondition) {
        current.setDecreasingOrder(maximize);
        current.initialize();

        generationsEvolved = 0;
        while(!stoppingCondition.isSatisfied(current)) {
            List<Particle<T>> swarm = current.getParticles();

            final Population<T> population = current;
            swarm = Parallel.map(swarm, particle -> {
                final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();

                return updatePolicy.updateParticle(particle, rng, population);
            });

            current.setParticles(swarm);
            generationsEvolved++;
        }

        return current.getGlobalBest();
    }

    public int getGenerationsEvolved() {
        return generationsEvolved;
    }
}
