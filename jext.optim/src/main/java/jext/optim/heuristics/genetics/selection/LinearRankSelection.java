package jext.optim.heuristics.genetics.selection;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.SelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary Computation - 2015
    pag. 80
 */

public class LinearRankSelection<T> extends SelectionPolicy<T> {

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();
        double total = total(chromosomes);

        Chromosome<T> c1 = rankSelect(chromosomes, total, rng).clone();
        Chromosome<T> c2 = rankSelect(chromosomes, total, rng).clone();

        return new ChromosomePair<>(c1, c2);
    }

    private Chromosome<T> rankSelect(List<Chromosome<T>> chromosomes, double total, RandomGenerator rng) {
        int n = chromosomes.size();
        double cumulative = 0;
        double prob = rng.nextDouble();

        int i = 0;
        for (Chromosome<T> chromosome : chromosomes) {
            cumulative += (n-i)/total;
            if (prob <= cumulative)
                return chromosome;
            ++i;
        }

        return chromosomes.get(n-1);
    }

    private double total(List<Chromosome<T>> chromosomes) {
        int n = chromosomes.size();
        return 0.5*n*(n+1);
    }

}
