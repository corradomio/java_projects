package jext.optim.heuristics.ga.selection;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.util.AbstractSelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary Computing - 2015
    pag. 80
 */

public class LinearRankSelection<T> extends AbstractSelectionPolicy<T> {

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
            cumulative += (n-i)/total;
            if (r <= cumulative)
                return chromosome;
            ++i;
        }

        return chromosomes.get(n-1);
    }

    private double total(List<Chromosome<T>> chromosomes) {
        int n = chromosomes.size();
        return 0.5f*n*(n+1);
    }

}
