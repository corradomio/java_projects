package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class UniformMutation extends AbstractMutationPolicy<BitSet> {

    @Override
    public Chromosome<BitSet> mutate(Chromosome<BitSet> original, RandomGenerator rng) {
        BitSet bs = original.candidate().clone();
        int n = bs.length();
        double prob = 1./n;

        for (int i = 0; i < n; i++) {
            double r = rng.nextDouble();
            if (r < prob) {
                bs.flip(i);
            }
        }

        return new Chromosome<>(bs, original);
    }
}
