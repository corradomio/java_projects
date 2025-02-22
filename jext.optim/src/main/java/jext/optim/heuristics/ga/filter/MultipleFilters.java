package jext.optim.heuristics.ga.filter;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.FilterPolicy;

import java.util.Arrays;
import java.util.List;

public class MultipleFilters<T> implements FilterPolicy<T> {

    private final List<FilterPolicy<T>> filters;

    public MultipleFilters(FilterPolicy<T>... filters) {
        this.filters = Arrays.asList(filters);
    }

    @Override
    public List<Chromosome<T>> filter(List<Chromosome<T>> population) {
        List<Chromosome<T>> filtered = population;
        for (FilterPolicy<T> filter : filters) {
            filtered = filter.filter(filtered);
        }
        return filtered;
    }
}
