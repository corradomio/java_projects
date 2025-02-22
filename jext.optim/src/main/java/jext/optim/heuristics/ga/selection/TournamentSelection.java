package jext.optim.heuristics.ga.selection;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.util.AbstractSelectionPolicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary Computing - 2015
    pag. 84
 */

public class TournamentSelection<T> extends AbstractSelectionPolicy<T> {

    private final int arity;

    public TournamentSelection(final int arity) {
        this.arity = arity;
    }

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();

        Chromosome<T> c1 = tournamentSelect(chromosomes, rng);
        Chromosome<T> c2 = tournamentSelect(chromosomes, rng);

        return new ChromosomePair<>(c1, c2);
    }

    private Chromosome<T> tournamentSelect(List<Chromosome<T>> chromosomes, RandomGenerator rng) {
        List<Chromosome<T>> selected = new ArrayList<>();
        int n = chromosomes.size();

        while (selected.size() < arity) {
            selected.add(chromosomes.get(rng.nextInt(n)));
        }

        // sort in decreasing order, in such way the best is the top
        Collections.sort(selected);

        return selected.get(0);
    }

}
