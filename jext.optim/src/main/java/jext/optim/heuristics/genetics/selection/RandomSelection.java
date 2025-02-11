package jext.optim.heuristics.genetics.selection;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.util.AbstractSelectionPolicy;

import java.util.List;
import java.util.random.RandomGenerator;

public class RandomSelection<T> extends AbstractSelectionPolicy<T> {

    @Override
    public ChromosomePair<T> select(Population<T> population, RandomGenerator rng) {
        List<Chromosome<T>> chromosomes = population.getChromosomes();

        Chromosome<T> c1 = randomSelect(chromosomes, rng);
        Chromosome<T> c2 = randomSelect(chromosomes, rng);

        return new ChromosomePair<>(c1, c2);
    }

    private Chromosome<T> randomSelect(List<Chromosome<T>> chromosomes, RandomGenerator rng) {
        int n = chromosomes.size();
        return chromosomes.get(rng.nextInt(n));
    }

}
