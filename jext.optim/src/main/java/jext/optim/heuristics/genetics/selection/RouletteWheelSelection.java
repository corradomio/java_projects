package jext.optim.heuristics.genetics.selection;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.util.AbstractSelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

public class RouletteWheelSelection<T> extends AbstractSelectionPolicy<T> {

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();
        float total = total(chromosomes);

        for (Chromosome<T> chromosome : chromosomes)
            total += chromosome.fitness();

        Chromosome<T> bs1 = rouletteSelect(chromosomes, total, rng);
        Chromosome<T> bs2 = rouletteSelect(chromosomes, total, rng);

        return new ChromosomePair<>(bs1, bs2);
    }

    private static <T> Chromosome<T> rouletteSelect(List<Chromosome<T>> chromosomes, float total, RandomGenerator rng) {
        float cumulative = 0;
        float r = rng.nextFloat();

        for (Chromosome<T> chromosome : chromosomes) {
            cumulative += chromosome.fitness()/total;
            if (r <= cumulative) {
                return chromosome;
            }
        }

        return chromosomes.get(0);
    }

    private float total(List<Chromosome<T>> chromosomes) {
        int n = chromosomes.size();
        return 0.5f*n*(n+1);
    }

}
