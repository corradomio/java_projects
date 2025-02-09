package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.CrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class TwoPointsCrossover extends CrossoverPolicy<BitSet> {

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet bs1 = first.candidate();
        BitSet bs2 = second.candidate();

        int p1 = rng.nextInt(bs1.length());
        int p2 = rng.nextInt(bs1.length());
        if (p1 > p2) { int tmp = p1; p1 = p2; p2 = tmp; }

        BitSet br1 = new BitSet(bs1);
        BitSet br2 = new BitSet(bs2);

        br1.set(p1, bs2, p1, p2-p1);
        br2.set(p1, bs1, p1, p2-p1);

        return ChromosomePair.makePair(br1, br2, first);
    }

}
