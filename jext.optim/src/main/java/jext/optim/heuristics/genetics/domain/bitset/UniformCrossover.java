package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.CrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class UniformCrossover extends CrossoverPolicy<BitSet> {

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet bs1 = first.candidate();
        BitSet bs2 = second.candidate();

        BitSet br1 = new BitSet(bs1);
        BitSet br2 = new BitSet(bs2);

        int n = bs1.length();
        for (int i = 0; i < n; i++) {
            if (rng.nextBoolean()) {
                br1.set(i, bs2.get(i));
                br2.set(i, bs1.get(i));
            }
        }

        return ChromosomePair.makePair(br1, br2, first);
    }

}
