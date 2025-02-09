package jext.optim.heuristics.genetics.selection;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.SelectionPolicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

public class TournamentSelection<T> extends SelectionPolicy<T> {

    private final int arity;

    public TournamentSelection(final int arity) {
        this.arity = arity;
    }

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();

        Chromosome<T> c1 = tournamentSelect(chromosomes, rng).clone();
        Chromosome<T> c2 = tournamentSelect(chromosomes, rng).clone();

        return new ChromosomePair<>(c1, c2);
    }

    private Chromosome<T> tournamentSelect(List<Chromosome<T>> chromosomes, RandomGenerator rng) {
        List<Chromosome<T>> selected = new ArrayList<>();
        int n = chromosomes.size();

        while (selected.size() < arity) {
            selected.add(chromosomes.get(rng.nextInt(n)));
        }

        // sort in decreasing order, in such way the best is the top
        Collections.sort(selected, Collections.reverseOrder());

        return selected.get(0);
    }

}
