package jext.optim.heuristics.genetics;

import jext.optim.heuristics.genetics.filter.AcceptPolicy;
import org.apache.commons.math4.legacy.exception.NotPositiveException;
import org.apache.commons.math4.legacy.exception.OutOfRangeException;
import org.apache.commons.math4.legacy.exception.util.LocalizedFormats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.random.RandomGenerator;

/// Expends Apache genetic.Population adding some useful features:
///
///      - T: the candidate type
///      - CandidateFactory[T]: a factory to generate random candidates
///      - FitnessFunction[T]: the fitness function used to evaluate the candidate.
///
/// The Population container must be ``initialized'' to create the first generation of
/// chromosomes. The population for the next generation is implemented in
/// ``nextGeneration''
///
/// In this way, it s not necessary to implement Chromosome or Population, but it is enough
/// to implement the candidate factory and the fitness function.
/// Another feature is the possibility to order the candidates in increasing/decreasing ways
/// based on if it is necessary to maximize or minimize the fitness function
///
/// Now, Chromosome[T] is the triple
///      - T candidate
///      - FitnessFunction[T] fitnessFunction
///      - decreasingOrder
///
/// @param <T>
public class Population<T> implements org.apache.commons.math4.legacy.genetics.Population {

    private final int populationLimit;
    private final double elitismRate;
    private final CandidateFactory<T> candidateFactory;
    private final FitnessFunction<T> fitnessFunction;
    private static final RandomGenerator rng = GeneticAlgorithm.getRandomNumberGenerator();

    private boolean decreasingOrder;
    private FilterPolicy<T> filterPolicy = new AcceptPolicy<>();
    private final List<Chromosome<T>> population = new ArrayList<>();

    // ----------------------------------------------------------------------

    public Population(
        int populationSize, double elitismRate,
        CandidateFactory<T> candidateFactory,
        FitnessFunction<T> fitnessFunction
    ) {
        this.populationLimit = populationSize;
        this.elitismRate = elitismRate;
        this.candidateFactory = candidateFactory;
        this.fitnessFunction = fitnessFunction;

        if (populationLimit <= 0)
            throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, populationLimit);
        if (elitismRate < 0 || elitismRate > 1)
            throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, elitismRate, 0, 1);
    }

    // ----------------------------------------------------------------------
    // Used internally by GeneticAlgorithm to transfer some special
    // configurations

    public Population<T> setDecreasingOrder(boolean decreasingOrder) {
        this.decreasingOrder = decreasingOrder;
        return this;
    }

    public Population<T> setFilterPolicy(FilterPolicy<T> filterPolicy) {
        this.filterPolicy = filterPolicy;
        return this;
    }

    // ----------------------------------------------------------------------

    @Override
    public int getPopulationSize() {
        return population.size();
    }

    @Override
    public int getPopulationLimit() {
        return populationLimit;
    }

    /**
     * Before to return the list, the chromosomes are ordered.
     * The first chromosomes are the best
     * @return sorted population
     */
    public List<Chromosome<T>> getChromosomes() {
        return Collections.unmodifiableList(population);
    }

    // ----------------------------------------------------------------------

    /**
     * Create the initial population
     * @return self
     */
    public Population<T> initialize() {
        population.clear();
        for (int i = 0; i < populationLimit; i++) {
            T candidate = candidateFactory.candidate(rng);
            addCandidate(candidate);
        }
        return this;
    }

    /**
     * Generate a new population
     * @return self
     */
    @Override
    public Population<T> nextGeneration() {
        // keep at minimum the best solution
        int elitism = Math.max(1, (int) Math.round(elitismRate*population.size()));

        List<Chromosome<T>> population = filterPolicy.filter(this.population);
        List<Chromosome<T>> nextPopulation = new ArrayList<>();

        // Population<T> nextGen = new Population<>(
        //     populationLimit,
        //     elitismRate,
        //     candidateFactory,
        //     fitnessFunction
        // ).withDecreasingOrder(decreasingOrder);

        if (elitism <= population.size()) {
            sort();
            for (int i=0; i<elitism; i++)
                // nextGen.addChromosome(population.get(i));
                nextPopulation.add(population.get(i));
        }

        // generate new random candidates
        // for (; nextGen.getPopulationSize() < populationLimit; )
        //     nextGen.addCandidate(candidateFactory.candidate(rng));

        for (; nextPopulation.size() < populationLimit; )
            nextPopulation.add(new Chromosome<>(candidateFactory.candidate(rng), fitnessFunction, decreasingOrder));

        // update THIS Population object
        this.population.clear();
        this.population.addAll(nextPopulation);

        return this;
    }

    private void addCandidate(T candidate) {
        addChromosome(new Chromosome<>(candidate, fitnessFunction, decreasingOrder));
    }

    private void addChromosome(Chromosome<T> chromosome) {
        this.population.add(chromosome);
    }

    // ----------------------------------------------------------------------

    /**
     * Retrieve the fittest chromosome.
     * Note: it orders the chromosomes
     * @return the fittest chromosome
     */
    @Override
    public Chromosome<T> getFittestChromosome() {
        sort();
        return population.get(0);
    }

    /**
     * Order the chromosomes on the fitness value AND if lowest or highest values are the best
     * The top elements of the list are the best
     *
     */
    public Population<T> sort() {
        Collections.sort(population, Collections.reverseOrder());
        return this;
    }

    /**
     * Shuffle the chromosomes to avoid an order
     */
    public Population<T> shuffle() {
        Collections.shuffle(population);
        return this;
    }

    // ----------------------------------------------------------------------
    // for compatibility

    @Override
    public void addChromosome(org.apache.commons.math4.legacy.genetics.Chromosome chromosome) {
        this.addChromosome((Chromosome<T>) chromosome);
    }

    @Override
    public Iterator<org.apache.commons.math4.legacy.genetics.Chromosome> iterator() {

        final Iterator<Chromosome<T>> iter = population.iterator();

        // adapter to convert 'Chromosome<T>' -> 'Chromosome'
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public org.apache.commons.math4.legacy.genetics.Chromosome next() {
                return iter.next();
            }
        };
    }

}
