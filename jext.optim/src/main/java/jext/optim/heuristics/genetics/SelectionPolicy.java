package jext.optim.heuristics.genetics;

public interface SelectionPolicy<T> {

    ChromosomePair<T> select(Population<T> population);

}
