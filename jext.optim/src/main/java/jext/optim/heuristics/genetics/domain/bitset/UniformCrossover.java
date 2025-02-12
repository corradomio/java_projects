package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class UniformCrossover extends AbstractCrossoverPolicy<BitSet> {

    private double prob;

    public UniformCrossover(double prob) {
        this.prob = prob;
    }

    @Override
    public ChromosomePair<BitSet> crossover(Chromosome<BitSet> first, Chromosome<BitSet> second, RandomGenerator rng) {
        BitSet b1 = first.candidate();
        BitSet b2 = second.candidate();

        BitSet r1 = b1.clone();
        BitSet r2 = b2.clone();

        int n = b1.length();
        for (int i = 0; i < n; i++) {
            double r = rng.nextDouble();
            if (r < prob) {
                r1.set(i, b2.get(i));
                r2.set(i, b1.get(i));
            }
        }

        return ChromosomePair.of(r1, r2, first);
    }

}
