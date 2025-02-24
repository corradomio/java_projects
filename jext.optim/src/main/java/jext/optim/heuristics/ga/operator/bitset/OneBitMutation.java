package jext.optim.heuristics.ga.operator.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneBitMutation extends AbstractMutationPolicy<BitSet> {

    @Override
    public Chromosome<BitSet> mutate(Chromosome<BitSet> original, RandomGenerator rng) {
        BitSet candidate = original.candidate().clone();
        int n = candidate.length();
        int i = rng.nextInt(n);

        candidate.flip(i);

        return new Chromosome<>(candidate, original);
    }
}
