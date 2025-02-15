package jext.optim.heuristics.genetics.selection;

import jext.math.exception.OutOfRangeException;
import jext.math.exception.util.LocalizedFormats;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.util.AbstractSelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary Computing - 2015
    pag. 82
 */

public class RankSelection<T> extends AbstractSelectionPolicy<T> {

    private float sigma = 1;

    public RankSelection(float sigma) {
        if (sigma < 1 || sigma > 2)
            throw new OutOfRangeException(LocalizedFormats.SIGMA_VALUE,
                sigma, 1, 2);
        this.sigma = sigma;
    }


    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();

        Chromosome<T> c1 = rankSelect(chromosomes, rng);
        Chromosome<T> c2 = rankSelect(chromosomes, rng);

        return new ChromosomePair<>(c1, c2);
    }

    private Chromosome<T> rankSelect(List<Chromosome<T>> chromosomes, RandomGenerator rng) {
        int n = chromosomes.size();
        float s = sigma;
        float foffs = (2f-s)/n;
        float frank = 2*(s-1)/(n*(n-1));
        float cumulative = 0;
        float r = rng.nextFloat();

        int i = 1;
        for (Chromosome<T> chromosome : chromosomes) {
            cumulative += foffs + (n-i)*frank;
            if (r <= cumulative)
                return chromosome;
            ++i;
        }

        return chromosomes.get(n-1);
    }

}
