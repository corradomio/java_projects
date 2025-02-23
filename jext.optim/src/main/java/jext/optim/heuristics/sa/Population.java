package jext.optim.heuristics.sa;

import jext.optim.domain.CandidateFactory;
import jext.optim.domain.FitnessFunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

public class Population<T> implements Iterable<Particle<T>> {

    private final int populationSize;
    private final int neighboursCount;
    private final CandidateFactory<T> candidateFactory;
    private final FitnessFunction<T> fitnessFunction;
    private boolean decreasingOrder;
    private List<Particle<T>> particles = new ArrayList<>();
    private Particle<T> fittestParticle;

    // ----------------------------------------------------------------------

    public Population(final int populationSize,
                      final int neighboursCount,
                      final CandidateFactory<T> candidateFactory,
                      final FitnessFunction<T> fitnessFunction) {
        this.populationSize = populationSize;
        this.neighboursCount = neighboursCount;
        this.candidateFactory = candidateFactory;
        this.fitnessFunction = fitnessFunction;
    }

    // ----------------------------------------------------------------------

    public int getNeighboursCount() {
        return neighboursCount;
    }

    public List<Particle<T>> getParticles() {
        return particles;
    }

    public void setParticles(final List<Particle<T>> particles) {
        this.particles = particles;
        updateFittestParticle();
    }

    public void setDecreasingOrder(final boolean decreasingOrder) {
        this.decreasingOrder = decreasingOrder;
    }

    public Particle<T> getFittestParticle() {
        return fittestParticle;
    }

    public Iterator<Particle<T>> iterator() {
        return particles.iterator();
    }

    public void forEach(Consumer<? super Particle<T>> action) {
        particles.forEach(action);
    }

    // ----------------------------------------------------------------------

    public void initialize(RandomGenerator rng) {
        for (int i=0; i<populationSize; i++) {
            T candidate = candidateFactory.candidate(rng);

            Particle<T> particle = new Particle<>(candidate, fitnessFunction, decreasingOrder);

            particles.add(particle);
        }

        updateFittestParticle();
    }

    private void updateFittestParticle() {
        if (fittestParticle == null)
            fittestParticle = particles.get(0);

        for (Particle<T> particle : particles) {
            if (particle.compareTo(fittestParticle) < 0)
                fittestParticle = particle;
        }
    }


}
