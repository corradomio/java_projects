package jext.optim.heuristics.sa;

import java.util.List;
import java.util.random.RandomGenerator;

public interface NeighboursPolicy<T> {

    List<Particle<T>> neighbours(Particle<T> candidate, int n, RandomGenerator rng);

}
