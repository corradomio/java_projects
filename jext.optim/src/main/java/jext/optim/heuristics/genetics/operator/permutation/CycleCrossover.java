package jext.optim.heuristics.genetics.operator.permutation;

import jext.optim.heuristics.genetics.domain.permutation.Permutation;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

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
