package jext.optim.heuristics.ga.selection;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.util.AbstractSelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary Computing - 2015
    pag. 84
 */

public class StochasticWheelSelection<T> extends AbstractSelectionPolicy<T> {

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();
        double total = total(chromosomes);

        Chromosome<T> bs1 = rouletteSelect(chromosomes, total, 0.0f, rng);
        Chromosome<T> bs2 = rouletteSelect(chromosomes, total, 0.5f, rng);

        return new ChromosomePair<>(bs1, bs2);
    }

    private static <T> Chromosome<T> rouletteSelect(List<Chromosome<T>> chromosomes, double total, double offset, RandomGenerator rng) {
        double prob = rng.nextDouble(0.5f) + offset;
        double cumulative = 0;

        for (Chromosome<T> chromosome : chromosomes) {
            cumulative += chromosome.fitness()/total;
            if (prob <= cumulative) {
                return chromosome;
            }
        }

        return chromosomes.get(0);
    }

    private double total(List<Chromosome<T>> chromosomes) {
        int n = chromosomes.size();
        return 0.5f*n*(n+1);
    }

}
