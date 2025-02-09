package jext.optim.heuristics.genetics;

import java.util.random.RandomGenerator;

public interface TypedSelectionPolicy<T> extends org.apache.commons.math4.legacy.genetics.SelectionPolicy {

    ChromosomePair<T> select(Population<T> population, RandomGenerator rng);

}
