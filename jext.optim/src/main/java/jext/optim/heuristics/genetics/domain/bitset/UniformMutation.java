package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class UniformMutation extends AbstractMutationPolicy<BitSet> {

    private float prob = -1;

    public UniformMutation() {

    }

    public UniformMutation(float prob) {
        this.prob = prob;
    }

    @Override
    public Chromosome<BitSet> mutate(Chromosome<BitSet> original, RandomGenerator rng) {
        BitSet bs = original.candidate().clone();
        int n = bs.length();
        float prob = (this.prob == -1) ? 1.f/n : this.prob;

        for (int i = 0; i < n; i++) {
            float r = rng.nextFloat();
            if (r < prob)
                bs.flip(i);
        }

        return new Chromosome<>(bs, original);
    }
}
