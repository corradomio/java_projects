package jext.optim.heuristics.genetics;

public interface CrossoverPolicy<T> {

    ChromosomePair<T> crossover(Chromosome<T> first, Chromosome<T> second);

}
