package jext.optim.heuristics.genetics.filter;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.FilterPolicy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Remove duplicate candidates based ONLY on the hash code
 * @param <T>
 */
public class DropDuplicates<T> implements FilterPolicy<T> {

    @Override
    public List<Chromosome<T>> filter(List<Chromosome<T>> population) {
        Set<Integer> hashes = new HashSet<>();
        List<Chromosome<T>> selected = new ArrayList<>();
        for (Chromosome<T> chromosome : population) {
            int hash = chromosome.candidate().hashCode();
            if (hashes.contains(hash))
                continue;
            hashes.add(hash);
            selected.add(chromosome);
        }
        return selected;
    }
}
