package jext.optim.heuristics.ga.operator.permutation;

import jext.optim.domain.permutation.Permutation;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class CycleCrossover extends AbstractCrossoverPolicy<Permutation> {

    @Override
    public ChromosomePair<Permutation> crossover(Chromosome<Permutation> first, Chromosome<Permutation> second, RandomGenerator rng) {

        Permutation p1 = first.candidate();
        Permutation p2 = second.candidate();

        Permutation r1 = p1.cycx(p2);
        Permutation r2 = p2.cycx(p1);

        return ChromosomePair.of(r1, r2, first);
    }
}
