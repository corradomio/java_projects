package jext.optim.heuristics.ga.operator.bitset;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneBitMutation extends AbstractMutationPolicy<BitSet> {

    @Override
    public Chromosome<BitSet> mutate(Chromosome<BitSet> original, RandomGenerator rng) {
        BitSet bs = original.candidate().clone();
        int n = bs.length();
        int i = rng.nextInt(n);

        bs.flip(i);

        return new Chromosome<>(bs, original);
    }
}
