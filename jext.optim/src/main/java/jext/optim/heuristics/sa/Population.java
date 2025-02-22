package jext.optim.heuristics.sa;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.CandidateFactory;
import jext.optim.domain.FitnessFunction;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.min;

public class Population<T> {

    private static final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();

    private final int populationSize;
    private final int neighboursCount;
    private final CandidateFactory<T> candidateFactory;
    private final FitnessFunction<T> fitnessFunction;
    private NeighboursPolicy<T> neighboursPolicy;
    private boolean decreasingOrder;
    private double temperature;
    private List<Particle<T>> population = new ArrayList<>();
    private Particle<T> fittestParticle;

    public Population(final int populationSize,
                      final int neighboursCount,
                      final CandidateFactory<T> candidateFactory,
                      final FitnessFunction<T> fitnessFunction) {
        this.populationSize = populationSize;
        this.neighboursCount = neighboursCount;
        this.candidateFactory = candidateFactory;
        this.fitnessFunction = fitnessFunction;
    }

    public void setDecreasingOrder(final boolean decreasingOrder) {
        this.decreasingOrder = decreasingOrder;
    }

    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    public void setNeighbourPolicy(final NeighboursPolicy<T> neighboursPolicy) {
        this.neighboursPolicy = neighboursPolicy;
    }

    public Particle<T> getFittestParticle() {
        return fittestParticle;
    }

    public void initialize() {
        for (int i=0; i<populationSize; i++) {
            T candidate = candidateFactory.candidate(rng);

            Particle<T> particle = new Particle<>(candidate, fitnessFunction, decreasingOrder);

            population.add(particle);
        }

        updateFittestParticle();
    }

    public Population<T> nextGeneration() {

        population = Parallel.map(population, particle -> {
            List<Particle<T>> neighbours = neighboursPolicy.neighbours(particle, neighboursCount, rng);
            Particle<T> update = neighbours.get(0);
            for (Particle<T> neighbour : neighbours)
                if (neighbour.compareTo(update) < 0)
                    update = neighbour;

            if (update.compareTo(particle) < 0) {
                return update;
            }

            double prob = min(1., exp(-abs(particle.fitness() - update.fitness())/temperature));
            double r = rng.nextDouble();

            if (r < prob)
                return update;
            else
                return particle;
        });

        updateFittestParticle();

        return this;
    }

    private void updateFittestParticle() {
        if (fittestParticle == null)
            fittestParticle = population.get(0);

        for (Particle<T> particle : population) {
            if (particle.compareTo(fittestParticle) < 0)
                fittestParticle = particle;
        }
    }


}
