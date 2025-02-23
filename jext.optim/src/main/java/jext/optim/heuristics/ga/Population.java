package jext.optim.heuristics.ga;

import jext.math.exception.LocalizedFormats2;
import jext.math.exception.NotPositiveException;
import jext.math.exception.OutOfRangeException;
import jext.math.exception.util.LocalizedFormats;
import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.CandidateFactory;
import jext.optim.domain.FitnessFunction;
import jext.optim.heuristics.ga.filter.AcceptAll;
import jext.util.QuickSort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

/// Expends Apache genetic.Population adding some useful features:
///
///     - T: the candidate type
///     - CandidateFactory[T]: a factory to generate random candidates
///     - FitnessFunction[T]: the fitness function used to evaluate the candidate.
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
public class Population<T> implements Iterable<Chromosome<T>> {
    /** global random number generator */
    private static final RandomGenerator rng = UniformRandomGenerator.getRandomGenerator();

    /** number of chromosomes/candidates in the population */
    private final int populationLimit;
    /** quota of the best population to transfer in the next generation */
    private final double elitismRate;
    /** quota of the population to add as new random candidates */
    private final double foreignerRate;
    /** candidate generator */
    private final CandidateFactory<T> candidateFactory;
    /** function used to evaluate a candidate */
    private final FitnessFunction<T> fitnessFunction;
    /** if to order the candidates in increasing or decreasing order */
    private boolean decreasingOrder = false;
    /** used to remove invalid candidates */
    private FilterPolicy<T> filterPolicy = new AcceptAll<>();

    /** current population of candidates */
    private final List<Chromosome<T>> chromosomes = new ArrayList<>();

    private final int elitismSize;

    // ----------------------------------------------------------------------

    public Population(
        final int populationSize, final double elitismRate, final double foreignerRate,
        final CandidateFactory<T> candidateFactory,
        final FitnessFunction<T> fitnessFunction
    ) {
        this.populationLimit = populationSize;
        this.elitismRate = (double) elitismRate;
        this.foreignerRate = (double) foreignerRate;
        this.candidateFactory = candidateFactory;
        this.fitnessFunction = fitnessFunction;

        if (populationLimit <= 0)
            throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, populationLimit);
        if (elitismRate < 0 || elitismRate > 1)
            throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, elitismRate, 0, 1);
        if (foreignerRate < 0 || foreignerRate > 1)
            throw new OutOfRangeException(LocalizedFormats2.OFFSPRING_RATE, foreignerRate, 0, 1);

        this.elitismSize = (elitismRate >= 1) ? (int)elitismRate : (int)(elitismRate*populationSize);
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

    public int getPopulationSize() {
        return chromosomes.size();
    }

    public int getPopulationLimit() {
        return populationLimit;
    }

    /**
     * Before to return the list, the chromosomes are ordered.
     * The first chromosomes are the best
     * @return sorted population
     */
    public List<Chromosome<T>> getChromosomes() {
        // return Collections.unmodifiableList(population);
        return chromosomes;
    }

    public Iterator<Chromosome<T>> iterator() {
        return chromosomes.iterator();
    }

    public void forEach(Consumer<? super Chromosome<T>> action) {
        chromosomes.forEach(action);
    }

    // ----------------------------------------------------------------------

    /**
     * Create the initial population
     * @return self
     */
    public Population<T> initialize() {
        while (chromosomes.size() < populationLimit) {
            T candidate = candidateFactory.candidate(rng);
            add(new Chromosome<>(candidate, fitnessFunction, decreasingOrder));
        }
        return this;
    }

    /// This method has the following responsibility:
    ///
    ///  1) to transfer the elitismRate chromosomes in the new generation
    ///  2) to add new random chromosomes based on offspringRate
    ///
    /// The missing chromosomes are added using the current population and using
    /// the genetic operators.
    ///
    /// It is necessary to create a NEW population because the current one is used
    /// to create the missing chromosomes
    ///
    /// @return self
    public Population<T> nextGeneration() {
        // keep at minimum the best solution
        int elitism = elitismSize;
        // add at minimum a new random candidate
        int foreigner = Math.max(1, (int) Math.round(foreignerRate * chromosomes.size()));

        List<Chromosome<T>> chromosomes = filterPolicy.filter(this.chromosomes);

        Population<T> newPopulation = new Population<>(
            populationLimit,
            elitismRate,
            foreignerRate,
            candidateFactory,
            fitnessFunction
        );
        newPopulation.setDecreasingOrder(decreasingOrder);
        newPopulation.setFilterPolicy(filterPolicy);

        // add the best chromosomes
        // WARNING: for some STRANGE reason, List::sort(), Collections::sort() raise the exception:
        //
        //     IllegalArgumentException("Comparison method violates its general contract!")
        //
        // Trick: used a 'custom' sorter (QuickSort)
        chromosomes = QuickSort.sort(chromosomes);

        for (int i=0; i<elitism; i++)
            newPopulation.add(chromosomes.get(i));

        // add the new chromosomes
        for (int i=0; i<foreigner; i++)
            newPopulation.add(new Chromosome<>(candidateFactory.candidate(rng), fitnessFunction, decreasingOrder));

        return newPopulation;
    }

    public void add(Chromosome<T> chromosome) {
        this.chromosomes.add(chromosome);
    }

    // ----------------------------------------------------------------------

    /**
     * Retrieve the fittest chromosome.
     * Note: the chromosomes are NOT ordered
     * @return the fittest chromosome
     */
    public Chromosome<T> getFittestChromosome() {
        Chromosome<T> fittest = chromosomes.get(0);
        for (Chromosome<T> chromosome : chromosomes)
            if (chromosome.compareTo(fittest) < 0)
                fittest = chromosome;

        return fittest;
    }

}
