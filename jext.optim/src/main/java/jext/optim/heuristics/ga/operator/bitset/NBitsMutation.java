package jext.optim.heuristics.ga.operator.bitset;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class NBitsMutation extends AbstractMutationPolicy<BitSet> {

    private final int nbits;

    public NBitsMutation(int nbits) {
        this.nbits = nbits;
    }

    @Override
    public Chromosome<BitSet> mutate(Chromosome<BitSet> original, RandomGenerator rng) {
        BitSet bs = original.candidate().clone();
        int n = bs.length();

        for (int k=0; k<nbits; ++k) {
            int i = rng.nextInt(n);
            bs.flip(i);
        }

        return new Chromosome<>(bs, original);
    }
}
