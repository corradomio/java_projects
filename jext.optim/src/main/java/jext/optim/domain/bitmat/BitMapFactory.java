package jext.optim.domain.bitmat;

import jext.optim.domain.CandidateFactory;
import jext.optim.domain.ComponentFactory;
import jext.optim.domain.bitset.BitSet;

import java.util.random.RandomGenerator;

public class BitMapFactory implements ComponentFactory<BitMat> {

    private final int rows;
    private final int cols;

    public BitMapFactory(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public int length() {
        return rows * cols;
    }

    @Override
    public BitMat candidate(RandomGenerator rng) {
        int nbits = rows*cols;
        BitMat bitMat = new BitMat(rows, cols);
        for(int i = 0; i < nbits; i++)
            if (rng.nextBoolean())
                bitMat.set(i);
        return bitMat;
    }
}
