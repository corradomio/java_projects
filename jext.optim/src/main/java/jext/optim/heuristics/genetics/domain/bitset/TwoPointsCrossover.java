package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class TwoPointsCrossover extends AbstractCrossoverPolicy<BitSet> {

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet bs1 = first.candidate();
        BitSet bs2 = second.candidate();

        int p1 = rng.nextInt(bs1.length());
        int p2 = rng.nextInt(bs1.length());
        if (p1 > p2) { int tmp = p1; p1 = p2; p2 = tmp; }

        BitSet br1 = bs1.clone();
        BitSet br2 = bs2.clone();

        br1.set(p1, bs2, p1, p2-p1);
        br2.set(p1, bs1, p1, p2-p1);

        return ChromosomePair.makePair(br1, br2, first);
    }

}
