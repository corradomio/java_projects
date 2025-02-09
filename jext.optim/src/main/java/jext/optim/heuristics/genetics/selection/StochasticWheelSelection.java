package jext.optim.heuristics.genetics.selection;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.SelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary COmputation - 2015
    pag. 84
 */

public class StochasticWheelSelection<T> extends SelectionPolicy<T> {

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();
        double total = total(chromosomes);

        Chromosome<T> bs1 = rouletteSelect(chromosomes, total, 0.0, rng).clone();
        Chromosome<T> bs2 = rouletteSelect(chromosomes, total, 0.5, rng).clone();

        return new ChromosomePair<>(bs1, bs2);
    }

    private static <T> Chromosome<T> rouletteSelect(List<Chromosome<T>> chromosomes, double total, double offset, RandomGenerator rng) {
        double prob = rng.nextDouble(0.5) + offset;
        double cumulative = 0;

        for (Chromosome<T> chromosome : chromosomes) {
            cumulative += chromosome.getFitness()/total;
            if (prob <= cumulative) {
                return chromosome;
            }
        }

        return chromosomes.get(0);
    }

    private double total(List<Chromosome<T>> chromosomes) {
        int n = chromosomes.size();
        return 0.5*n*(n+1);
    }

}
