package jext.optim.heuristics.ga.operator.permutation;

import jext.optim.domain.permutation.Permutation;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class PartiallyMappedCrossover extends AbstractCrossoverPolicy<Permutation> {

    @Override
    public ChromosomePair<Permutation> crossover(Chromosome<Permutation> first, Chromosome<Permutation> second, RandomGenerator rng) {

        Permutation p1 = first.candidate();
        Permutation p2 = second.candidate();
        int n = p1.length();
        int i = rng.nextInt(n);
        int j = rng.nextInt(n);

        Permutation r1 = p1.pmx(p2, i, j);
        Permutation r2 = p2.pmx(p1, i, j);

        return ChromosomePair.of(r1, r2, first);
    }
}
