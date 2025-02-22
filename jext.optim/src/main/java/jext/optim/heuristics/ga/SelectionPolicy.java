package jext.optim.heuristics.ga;

public interface SelectionPolicy<T> {

    ChromosomePair<T> select(Population<T> population);

}
