package jext.optim.domain.bitset;

import jext.optim.domain.CandidateFactory;

import java.util.random.RandomGenerator;

public class BitSetFactory implements CandidateFactory<BitSet> {

    private final int nbits;

    public BitSetFactory(int n) {
        this.nbits = n;
    }

    @Override
    public int length() {
        return nbits;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public BitSet candidate(RandomGenerator rng) {
        BitSet bitSet = new BitSet(nbits);
        for(int i = 0; i < nbits; i++)
            if (rng.nextBoolean())
                bitSet.set(i);
        return bitSet;
    }
}
