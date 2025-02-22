package jext.optim.heuristics.sa;

import jext.optim.domain.Solution;

public class SimulatedAnnealing<T> {

    private final TemperatureScheduler temperatureScheduler;
    private final NeighboursPolicy<T> neighbourPolicy;
    private final double maxTemperature;
    private int generationsEvolved;

    public SimulatedAnnealing(
        double maxTemperature,
        TemperatureScheduler temperatureScheduler,
        NeighboursPolicy<T> neighboursPolicy
    ) {
        this.maxTemperature = maxTemperature;
        this.temperatureScheduler = temperatureScheduler;
        this.neighbourPolicy = neighboursPolicy;
    }

    public Solution<T> solve(boolean maximize, Population<T> population, StoppingCondition stoppingCondition) {
        population.setDecreasingOrder(maximize);
        population.setNeighbourPolicy(neighbourPolicy);

        population.initialize();
        generationsEvolved = 0;
        while(!stoppingCondition.isSatisfied(population)) {
            double temperature = temperatureScheduler.temperature(maxTemperature, generationsEvolved);
            population.setTemperature(temperature);
            population.nextGeneration();
            generationsEvolved++;
        }

        return population.getFittestParticle();
    }
}
