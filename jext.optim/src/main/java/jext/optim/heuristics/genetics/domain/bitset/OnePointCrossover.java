package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class OnePointCrossover extends AbstractCrossoverPolicy<BitSet> {

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet bs1 = first.candidate();
        BitSet bs2 = second.candidate();

        int len = rng.nextInt(bs1.length());

        BitSet br1 = bs1.clone();
        BitSet br2 = bs2.clone();

        br1.set(0, bs2, 0, len);
        br2.set(0, bs1, 0, len);

        return ChromosomePair.makePair(br1, br2, first);
    }
}
