package jext.optim.heuristics.ga.operator.bitset;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class UniformMutation extends AbstractMutationPolicy<BitSet> {

    private double prob = -1;

    public UniformMutation() {

    }

    public UniformMutation(double prob) {
        this.prob = prob;
    }

    @Override
    public Chromosome<BitSet> mutate(Chromosome<BitSet> original, RandomGenerator rng) {
        BitSet bs = original.candidate().clone();
        int n = bs.length();
        double prob = (this.prob == -1) ? 1.f/n : this.prob;

        for (int i = 0; i < n; i++) {
            double r = rng.nextDouble();
            if (r < prob)
                bs.flip(i);
        }

        return new Chromosome<>(bs, original);
    }
}
