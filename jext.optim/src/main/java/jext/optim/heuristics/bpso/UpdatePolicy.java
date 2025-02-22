package jext.optim.heuristics.bpso;

import jext.optim.domain.bitset.BitSet;

import java.util.random.RandomGenerator;

public interface UpdatePolicy<T> {

    boolean compareWith(Particle<T> particle, Particle<T> reference, int location);

    // Particle<T> updateParticle(Particle<T> particle, int nLocations, int[] locations);

    Particle<T> updateParticle(Particle<T> particle, RandomGenerator rng, Swarm<T> swarm);
}
