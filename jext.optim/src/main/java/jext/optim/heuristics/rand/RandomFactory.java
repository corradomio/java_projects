package jext.optim.heuristics.rand;

import jext.optim.domain.CandidateFactory;
import jext.optim.domain.FitnessFunction;

import java.util.random.RandomGenerator;

public class RandomFactory<T> {

    private final CandidateFactory<T> candidateFactory;
    private final FitnessFunction<T> fitnessFunction;
    private boolean decreasingOrder;


    public RandomFactory(CandidateFactory<T> candidateFactory, FitnessFunction<T> fitnessFunction) {
        this.candidateFactory = candidateFactory;
        this.fitnessFunction = fitnessFunction;
    }

    public RandomFactory<T> setDecreasingOrder(boolean decreasingOrder) {
        this.decreasingOrder = decreasingOrder;
        return this;
    }

    RandomCandidate<T> candidate(RandomGenerator rng) {
        T candidate = candidateFactory.candidate(rng);
        double fitness = fitnessFunction.fitness(candidate);

        return new RandomCandidate<>(candidate, fitness, decreasingOrder);
    }


}
