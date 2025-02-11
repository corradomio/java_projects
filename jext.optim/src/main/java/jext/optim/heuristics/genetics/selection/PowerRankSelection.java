package jext.optim.heuristics.genetics.selection;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.util.AbstractSelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

import static java.lang.Math.exp;

/*
    Introduction to Evolutionary Computing - 2015
    pag. 82
 */

public class PowerRankSelection<T> extends AbstractSelectionPolicy<T> {

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();
        double total = total(chromosomes);

        Chromosome<T> c1 = rankSelect(chromosomes, total, rng);
        Chromosome<T> c2 = rankSelect(chromosomes, total, rng);

        return new ChromosomePair<>(c1, c2);
    }

    private Chromosome<T> rankSelect(List<Chromosome<T>> chromosomes, double total, RandomGenerator rng) {
        int n = chromosomes.size();
        double cumulative = 0;
        double r = rng.nextDouble();

        int i = 0;
        for (Chromosome<T> chromosome : chromosomes) {
            cumulative += (1 - exp(-i))/total;
            if (r <= cumulative)
                return chromosome;
            ++i;
        }

        return chromosomes.get(n-1);
    }

    private double total(List<Chromosome<T>> chromosomes) {
        int n = chromosomes.size();
        double total = 0;

        for (int i=0; i<n; ++i) {
            total += 1 - exp(-i);
        }

        return total;
    }

}
