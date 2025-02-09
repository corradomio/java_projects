package jext.optim.heuristics.genetics;

import java.util.List;

public interface FilterPolicy<T>  {

    List<Chromosome<T>> filter(List<Chromosome<T>> population);
}
