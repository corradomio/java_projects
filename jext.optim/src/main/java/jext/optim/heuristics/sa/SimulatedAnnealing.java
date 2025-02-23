package jext.optim.heuristics.sa;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.Solution;
import jext.util.concurrent.Parallel;

import java.util.List;
import java.util.random.RandomGenerator;

import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.min;

public class SimulatedAnnealing<T> {

    private final TemperatureScheduler temperatureScheduler;
    private final NeighboursPolicy<T> neighboursPolicy;
    private final double maxTemperature;
    private int generationsEvolved;

    public SimulatedAnnealing(
        double maxTemperature,
        TemperatureScheduler temperatureScheduler,
        NeighboursPolicy<T> neighboursPolicy
    ) {
        this.maxTemperature = maxTemperature;
        this.temperatureScheduler = temperatureScheduler;
        this.neighboursPolicy = neighboursPolicy;
    }

    public Solution<T> solve(boolean maximize, Population<T> population, StoppingCondition stoppingCondition) {
        final int neighboursCount = population.getNeighboursCount();
        final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();

        population.setDecreasingOrder(maximize);
        population.initialize(rng);

        generationsEvolved = 0;
        while(!stoppingCondition.isSatisfied(population)) {
            double temperature = temperatureScheduler.temperature(maxTemperature, generationsEvolved);

            List<Particle<T>> particles = population.getParticles();
            particles = Parallel.map(particles, particle -> {

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

            population.setParticles(particles);

            generationsEvolved++;
        }

        return population.getFittestParticle();
    }
}
