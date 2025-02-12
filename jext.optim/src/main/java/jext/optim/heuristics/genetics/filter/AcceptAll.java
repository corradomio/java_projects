package jext.optim.heuristics.genetics.filter;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.FilterPolicy;

import java.util.List;

public class AcceptAll<T> implements FilterPolicy<T> {

    @Override
    public List<Chromosome<T>> filter(List<Chromosome<T>> chromosomes) {
        return chromosomes;
    }
}
