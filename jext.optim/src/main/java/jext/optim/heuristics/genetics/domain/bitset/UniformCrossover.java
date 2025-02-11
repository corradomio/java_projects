package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class UniformCrossover extends AbstractCrossoverPolicy<BitSet> {

    private double prob;

    public UniformCrossover(double prob) {
        this.prob = prob;
    }

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet bs1 = first.candidate();
        BitSet bs2 = second.candidate();

        BitSet br1 = bs1.clone();
        BitSet br2 = bs2.clone();

        int n = bs1.length();
        for (int i = 0; i < n; i++) {
            double r = rng.nextDouble();
            if (r < prob) {
                br1.set(i, bs2.get(i));
                br2.set(i, bs1.get(i));
            }
        }

        return ChromosomePair.makePair(br1, br2, first);
    }

}
