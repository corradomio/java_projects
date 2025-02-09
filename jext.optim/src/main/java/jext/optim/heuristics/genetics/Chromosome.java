package jext.optim.heuristics.genetics;

import jext.lang.Cloneable;

public class Chromosome<T>
    extends org.apache.commons.math4.legacy.genetics.Chromosome
    implements jext.lang.Cloneable<Chromosome<T>>
{
    private final T candidate;
    private final FitnessFunction<T> fitnessFunction;
    private final boolean decreasingOrder;

    // to support the concept of 'chromosome age'
    // private final long timestamp = System.currentTimeMillis();

    // ----------------------------------------------------------------------

    /** Constructor uset to clone a chromosome */
    public Chromosome(Chromosome<T> chromosome) {
        this.candidate = ((Cloneable<T>)chromosome.candidate).clone();
        this.fitnessFunction = chromosome.fitnessFunction;
        this.decreasingOrder = chromosome.decreasingOrder;
    }

    /**
     * Constructor to create a new chromosome using a new candiate
     * and the same other properties of ``template``
     */
    public Chromosome(final T candidate, final Chromosome<T> template) {
        this.candidate = candidate;
        this.fitnessFunction = template.fitnessFunction;
        this.decreasingOrder = template.decreasingOrder;
    }

    /** Constructor */
    public Chromosome(final T candidate, final FitnessFunction<T> fitnessFunction, boolean decreasingOrder) {
        this.candidate = candidate;
        this.fitnessFunction = fitnessFunction;
        this.decreasingOrder = decreasingOrder;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public T candidate() {
        return this.candidate;
    }

    @Override
    public double fitness() {
        return fitnessFunction.fitness(candidate);
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public Chromosome<T> clone() {
        return new Chromosome<>(this);
    }

    @Override
    public int compareTo(final org.apache.commons.math4.legacy.genetics.Chromosome another) {
        int cmp = Double.compare(getFitness(), another.getFitness());
        return decreasingOrder ? -cmp : cmp;
    }

    @Override
    public String toString() {
        return String.format("%s: %f", candidate.toString(), fitness());
    }
}
