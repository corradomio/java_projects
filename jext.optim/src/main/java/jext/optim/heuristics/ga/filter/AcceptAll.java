package jext.optim.heuristics.ga.filter;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.FilterPolicy;

import java.util.List;

public class AcceptAll<T> implements FilterPolicy<T> {

    @Override
    public List<Chromosome<T>> filter(List<Chromosome<T>> chromosomes) {
        return chromosomes;
    }
}
