package jext.optim.heuristics.ga.operator.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class OnePointCrossover extends AbstractCrossoverPolicy<BitSet> {

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet b1 = first.candidate();
        BitSet b2 = second.candidate();

        int i = rng.nextInt(b1.length());

        BitSet r1 = b1.clone();
        BitSet r2 = b2.clone();

        r1.set(0, b2, i);
        r2.set(0, b1, i);

        return ChromosomePair.of(r1, r2, first);
    }
}
