package jext.optim.heuristics.ga;

public interface CrossoverPolicy<T> {

    ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second);

}
