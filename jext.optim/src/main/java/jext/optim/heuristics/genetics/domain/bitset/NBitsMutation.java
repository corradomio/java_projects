package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

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
