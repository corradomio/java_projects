package jext.optim.heuristics.genetics;

import java.util.random.RandomGenerator;

public interface SelectionPolicy<T> {

    ChromosomePair<T> select(Population<T> population);

}
