package jext.optim.heuristics.genetics;

import jext.math.exception.OutOfRangeException;
import jext.math.exception.util.LocalizedFormats;
import jext.optim.heuristics.genetics.filter.AcceptAll;

import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * Implementation of a genetic algorithm. All factors that govern the operation
 * of the algorithm can be configured for a specific problem.
 *
 * @since 2.0
 */
public class GeneticAlgorithm<T> {

    /**
     * Static random number generator shared by GA implementation classes.
     * Use {@link #setRandomGenerator(RandomGenerator)} to supply an
     * alternative to the default PRNG, and/or select a specific seed.
     */
    //@GuardedBy("this")
    private static RandomGenerator randomGenerator = new Random();

    /** the crossover policy used by the algorithm. */
    private final CrossoverPolicy<T> crossoverPolicy;

    /** the rate of crossover for the algorithm. */
    private final double crossoverRate;

    /** the mutation policy used by the algorithm. */
    private final MutationPolicy<T> mutationPolicy;

    /** the rate of mutation for the algorithm. */
    private final double mutationRate;

    /** the selection policy used by the algorithm. */
    private final SelectionPolicy<T> selectionPolicy;

    /** the filter policy used by the algorithm. */
    private final FilterPolicy<T> filterPolicy;

    /** the number of generations evolved to reach {@link StoppingCondition} in the last run. */
    private int generationsEvolved;

    /**
     * Create a new genetic algorithm.
     * @param crossoverPolicy The {@link CrossoverPolicy}
     * @param crossoverRate The crossover rate as a percentage (0-1 inclusive)
     * @param mutationPolicy The {@link MutationPolicy}
     * @param mutationRate The mutation rate as a percentage (0-1 inclusive)
     * @param selectionPolicy The {@link SelectionPolicy}
     * @throws OutOfRangeException if the crossover or mutation rate is outside the [0, 1] range
     */
    public GeneticAlgorithm(final CrossoverPolicy<T> crossoverPolicy,
                            final double crossoverRate,
                            final MutationPolicy<T> mutationPolicy,
                            final double mutationRate,
                            final SelectionPolicy<T> selectionPolicy,
                            final FilterPolicy<T> filterPolicy
    ) throws OutOfRangeException {

        if (crossoverRate < 0 || crossoverRate > 1) {
            throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE,
                crossoverRate, 0, 1);
        }
        if (mutationRate < 0 || mutationRate > 1) {
            throw new OutOfRangeException(LocalizedFormats.MUTATION_RATE,
                mutationRate, 0, 1);
        }
        this.crossoverPolicy = crossoverPolicy;
        this.crossoverRate = crossoverRate;
        this.mutationPolicy = mutationPolicy;
        this.mutationRate = mutationRate;
        this.selectionPolicy = selectionPolicy;
        this.filterPolicy = filterPolicy;
    }

    public GeneticAlgorithm(final CrossoverPolicy<T> crossoverPolicy,
                            final double crossoverRate,
                            final MutationPolicy<T> mutationPolicy,
                            final double mutationRate,
                            final SelectionPolicy<T> selectionPolicy
    ) throws OutOfRangeException {
        this(crossoverPolicy, crossoverRate, mutationPolicy, mutationRate, selectionPolicy, new AcceptAll<>());
    }

    /**
     * Set the (static) random generator.
     *
     * @param random random generator
     */
    public static synchronized void setRandomGenerator(final RandomGenerator random) {
        randomGenerator = random;
    }

    /**
     * Returns the (static) random generator.
     *
     * @return the static random generator shared by GA implementation classes
     */
    public static synchronized RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    // ----------------------------------------------------------------------

    public Population<T> maximize(Population<T> population, StoppingCondition stoppingCondition) {
        if (population.getPopulationSize() < population.getPopulationLimit())
            population.initialize();
        population.setDecreasingOrder(true);
        population.setFilterPolicy(filterPolicy);
        return evolve(population, stoppingCondition);
    }

    public Population<T> minimize(Population<T> population, StoppingCondition stoppingCondition) {
        if (population.getPopulationSize() < population.getPopulationLimit())
            population.initialize();
        population.setDecreasingOrder(false);
        population.setFilterPolicy(filterPolicy);
        return evolve(population, stoppingCondition);
    }

    /**
     * Evolve the given population. Evolution stops when the stopping condition
     * is satisfied. Updates the {@link #getGenerationsEvolved() generationsEvolved}
     * property with the number of generations evolved before the StoppingCondition
     * is satisfied.
     *
     * @param initial the initial, seed population.
     * @param condition the stopping condition used to stop evolution.
     * @return the population that satisfies the stopping condition.
     */
    protected Population<T> evolve(final Population<T> initial, final StoppingCondition condition) {
        Population<T> current = initial;
        generationsEvolved = 0;
        while (!condition.isSatisfied(current)) {
            onGenerationStart(generationsEvolved, current);
            current = nextGeneration(current);
            onGeneration(generationsEvolved, current);
            generationsEvolved++;
        }
        return current;
    }

    /**
     * Evolve the given population into the next generation.
     * <ol>
     *  <li>Get nextGeneration population to fill from <code>current</code>
     *      generation, using its nextGeneration method</li>
     *  <li>Loop until new generation is filled:
     *  <ul><li>Apply configured SelectionPolicy to select a pair of parents
     *          from <code>current</code></li>
     *      <li>With probability = {@link #getCrossoverRate()}, apply
     *          configured {@link CrossoverPolicy} to parents</li>
     *      <li>With probability = {@link #getMutationRate()}, apply
     *          configured {@link MutationPolicy} to each of the offspring</li>
     *      <li>Add offspring individually to nextGeneration,
     *          space permitting</li>
     *  </ul></li>
     *  <li>Return nextGeneration</li>
     * </ol>
     *
     * @param current the current population.
     * @return the population for the next generation.
     */
    public Population<T> nextGeneration(final Population<T> current) {
        Population<T> nextGeneration = current.nextGeneration();

        RandomGenerator randGen = getRandomGenerator();

        while (nextGeneration.getPopulationSize() < nextGeneration.getPopulationLimit()) {
            // select parent chromosomes
            ChromosomePair<T> pair = getSelectionPolicy().select(current);

            // crossover?
            if (randGen.nextDouble() < getCrossoverRate()) {
                // apply crossover policy to create two offspring
                pair = getCrossoverPolicy().crossover(pair.first(), pair.second());
            }

            // mutation?
            if (randGen.nextDouble() < getMutationRate()) {
                // apply mutation policy to the chromosomes
                pair = new ChromosomePair<>(
                    getMutationPolicy().mutate(pair.first()),
                    getMutationPolicy().mutate(pair.second())
                );
            }

            // add the first chromosome to the population
            nextGeneration.addChromosome(pair.first());
            // is there still a place for the second chromosome?
            if (nextGeneration.getPopulationSize() < nextGeneration.getPopulationLimit()) {
                // add the second chromosome to the population
                nextGeneration.addChromosome(pair.second());
            }
        }

        return nextGeneration;
    }

    /**
     * Returns the crossover policy.
     * @return crossover policy
     */
    public CrossoverPolicy<T> getCrossoverPolicy() {
        return crossoverPolicy;
    }

    /**
     * Returns the crossover rate.
     * @return crossover rate
     */
    public double getCrossoverRate() {
        return crossoverRate;
    }

    /**
     * Returns the mutation policy.
     * @return mutation policy
     */
    public MutationPolicy<T> getMutationPolicy() {
        return mutationPolicy;
    }

    /**
     * Returns the mutation rate.
     * @return mutation rate
     */
    public double getMutationRate() {
        return mutationRate;
    }

    /**
     * Returns the selection policy.
     * @return selection policy
     */
    public SelectionPolicy<T> getSelectionPolicy() {
        return selectionPolicy;
    }

    /**
     * Returns the number of generations evolved to reach {@link StoppingCondition} in the last run.
     *
     * @return number of generations evolved
     * @since 2.1
     */
    public int getGenerationsEvolved() {
        return generationsEvolved;
    }

    // ----------------------------------------------------------------------
    // Events
    // ----------------------------------------------------------------------

    protected void onGenerationStart(int generationsEvolved, Population<T> population) {

    }

    protected void onGeneration(int generationsEvolved, Population<T> population) {

    }


}
