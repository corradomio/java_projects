package jext.optim.heuristics.pbil;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class PopulationBasedIncrementalLearning<T> {

    // https://en.wikipedia.org/wiki/Population-based_incremental_learning
    //
    // learningRate = 0.1,
    // negativeRate = 0.075,
    // mutationRate = 0.02,
    // mutationShift = 0.05
    // is used. N = 100 and ITER_COUNT = 1000

    private final double learningRate;
    private final double negativeRate;
    private final double mutationRate;
    private final double mutationShift;
    private final UpdatePolicy<T> updatePolicy;
    private int generationsEvolved;

    // ----------------------------------------------------------------------

    public PopulationBasedIncrementalLearning(
        final double learningRate,
        final double negativeRate,
        final double mutationRate,
        final double mutationShift,
        UpdatePolicy<T> updatePolicy
    ) {
        this.learningRate = learningRate;
        this.negativeRate = negativeRate;
        this.mutationRate = mutationRate;
        this.updatePolicy = updatePolicy;
        this.mutationShift = mutationShift;
    }

    // ----------------------------------------------------------------------

    public double getLearningRate() {
        return learningRate;
    }

    public double getNegativeRate() {
        return negativeRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public double getMutationShift() {
        return mutationShift;
    }

    public UpdatePolicy<T> getUpdatePolicy() {
        return updatePolicy;
    }

    // ----------------------------------------------------------------------

    public Solution<T> solve(boolean maximize, Population<T> population, StoppingCondition stoppingCondition) {
        final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();
        population.setDecreasingOrder(maximize);

        population.initialize(rng);

        generationsEvolved = 0;
        while(!stoppingCondition.isSatisfied(population)) {
            double[][] membersProbability = population.getMembersProbility();
            List<Learner<T>> learners = population.getLearners();
            List<Learner<T>> generation = new ArrayList<>();

            for (Learner<T> learner : learners) {
                Learner<T> updated = updatePolicy.updateMembers(learner, rng, membersProbability);

                generation.add(updated);
            }

            population.setLearners(generation);
            population.updateMembersProbability(rng, this);

            ++generationsEvolved;
        }

        return population.getFittestLearner();
    }

}
