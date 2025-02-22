package jext.optim.heuristics.ga;

import java.util.List;

public interface FilterPolicy<T>  {

    List<Chromosome<T>> filter(List<Chromosome<T>> population);
}
