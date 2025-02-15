package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

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
