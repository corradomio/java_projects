package jext.optim.heuristics.dpso;

import java.util.random.RandomGenerator;

public interface UpdatePolicy<T> {

    boolean compareWith(Particle<T> particle, Particle<T> reference, int index);

    Particle<T> updateParticle(Particle<T> particle, RandomGenerator rng, Population<T> population);

}
