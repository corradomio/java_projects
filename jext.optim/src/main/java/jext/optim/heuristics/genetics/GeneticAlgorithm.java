package jext.optim.heuristics.genetics;

import jext.optim.heuristics.genetics.filter.AcceptPolicy;
import jext.optim.heuristics.genetics.util.RandomGeneratorProvider;
import org.apache.commons.math4.legacy.exception.OutOfRangeException;

import java.util.random.RandomGenerator;

public class GeneticAlgorithm<T> extends org.apache.commons.math4.legacy.genetics.GeneticAlgorithm {

    private int generation;
    private final FilterPolicy<T> filterPolicy;

    /**
     * Create a new genetic algorithm.
     *
     * @param crossoverPolicy The {@link CrossoverPolicy}
     * @param crossoverRate   The crossover rate as a percentage (0-1 inclusive)
     * @param mutationPolicy  The {@link MutationPolicy}
     * @param mutationRate    The mutation rate as a percentage (0-1 inclusive)
     * @param selectionPolicy The {@link SelectionPolicy}
     * @throws OutOfRangeException if the crossover or mutation rate is outside the [0, 1] range
     */
    public GeneticAlgorithm(
        CrossoverPolicy<T> crossoverPolicy, double crossoverRate,
        MutationPolicy<T> mutationPolicy, double mutationRate,
        SelectionPolicy<T> selectionPolicy
    ) {
        this(
            crossoverPolicy, crossoverRate,
            mutationPolicy, mutationRate,
            selectionPolicy, new AcceptPolicy<>()
        );
    }

    public GeneticAlgorithm(
        CrossoverPolicy<T> crossoverPolicy, double crossoverRate,
        MutationPolicy<T> mutationPolicy, double mutationRate,
        SelectionPolicy<T> selectionPolicy,
        FilterPolicy<T> filterPolicy
    ) throws OutOfRangeException {
        super(crossoverPolicy, crossoverRate, mutationPolicy, mutationRate, selectionPolicy);
        this.filterPolicy = filterPolicy;
    }

    public Population<T> maximize(Population<T> population, StoppingCondition stoppingCondition) {
        population.setDecreasingOrder(false);
        population.setFilterPolicy(filterPolicy);
        return evolve(population, stoppingCondition);
    }

    public Population<T> minimize(Population<T> population, StoppingCondition stoppingCondition) {
        population.setDecreasingOrder(true);
        population.setFilterPolicy(filterPolicy);
        return evolve(population, stoppingCondition);
    }

    protected Population<T> evolve(Population<T> population, StoppingCondition stoppingCondition) {
        population.initialize();

        generation = 0;
        Population<T> bestPopulation = (Population<T>) super.evolve(population, stoppingCondition);
        return bestPopulation;
    }

    // ----------------------------------------------------------------------
    // RandomGenerator

    private static RandomGenerator randomGenerator = new RandomGeneratorProvider(
        org.apache.commons.math4.legacy.genetics.GeneticAlgorithm.getRandomGenerator()
    );

    public static RandomGenerator getRandomNumberGenerator() {
        return randomGenerator;
    }


    // ----------------------------------------------------------------------
    // events

    protected void onGeneration(int generation, Population<T> population, boolean isEnd) {

    }

    // ----------------------------------------------------------------------
    // compatibility

    @Override
    public org.apache.commons.math4.legacy.genetics.Population nextGeneration(final org.apache.commons.math4.legacy.genetics.Population current) {
        ++generation;
        onGeneration(generation, (Population<T>) current, false);
        org.apache.commons.math4.legacy.genetics.Population next = super.nextGeneration(current);
        onGeneration(generation, (Population<T>) next, true);
        return next;
    }

}
