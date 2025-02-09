package jext.optim.heuristics.genetics.selection;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.SelectionPolicy;
import org.apache.commons.math4.legacy.exception.MathIllegalArgumentException;
import org.apache.commons.math4.legacy.exception.MathIllegalNumberException;

import java.util.List;
import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary Computation - 2015
    pag. 82
 */

public class RankSelection<T> extends SelectionPolicy<T> {

    private double sigma = 1;

    public RankSelection(double sigma) {
        if (sigma < 1 || sigma > 2)
            throw new RuntimeException("Sigma must b in range [1,2]: " + sigma);
        this.sigma = sigma;
    }


    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();

        Chromosome<T> c1 = rankSelect(chromosomes, rng).clone();
        Chromosome<T> c2 = rankSelect(chromosomes, rng).clone();

        return new ChromosomePair<>(c1, c2);
    }

    private Chromosome<T> rankSelect(List<Chromosome<T>> chromosomes, RandomGenerator rng) {
        int n = chromosomes.size();
        double s = sigma;
        double foffs = (2.-s)/n;
        double frank = 2*(s-1)/(n*(n-1));
        double prob = rng.nextDouble();
        double cumulative = 0;

        int i = 1;
        for (Chromosome<T> chromosome : chromosomes) {
            cumulative += foffs + (n-i)*frank;
            if (prob <= cumulative)
                return chromosome;
            ++i;
        }

        return chromosomes.get(n-1);
    }

}
