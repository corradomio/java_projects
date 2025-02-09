package jext.optim.heuristics.genetics.domain.bitset;

import jext.optim.heuristics.genetics.CandidateFactory;

import java.util.random.RandomGenerator;

public class BitSetFactory implements CandidateFactory<BitSet> {

    private int n;

    public BitSetFactory(int n) {
        this.n = n;
    }

    @Override
    public BitSet candidate(RandomGenerator rng) {
        BitSet bitSet = new BitSet(n);
        for(int i = 0; i < n; i++)
            if (rng.nextBoolean())
                bitSet.set(i);
        return bitSet;
    }
}
