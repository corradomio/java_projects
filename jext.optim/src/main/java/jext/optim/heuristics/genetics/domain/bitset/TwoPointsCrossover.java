package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class TwoPointsCrossover extends AbstractCrossoverPolicy<BitSet> {

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet b1 = first.candidate();
        BitSet b2 = second.candidate();

        int i = rng.nextInt(b1.length());
        int j = rng.nextInt(b1.length());
        if (i > j) { int tmp = i; i = j; j = tmp; }

        BitSet r1 = b1.clone();
        BitSet r2 = b2.clone();

        r1.set(i, b2, j-i);
        r2.set(i, b1, j-i);

        return ChromosomePair.of(r1, r2, first);
    }

}
