package jext.hash.fnv;

import jext.hash.Hash;
import jext.hash.HashAlgorithm;

public class FNV1 implements HashAlgorithm {

    private long hash;
    private long prime, offset;
    private boolean v;

    FNV1(long prime, long offset, boolean v) {
        this.prime = prime;
        this.offset = offset;
        this.v = v;
    }

    @Override
    public void reset() {
        this.hash = offset;
    }

    @Override
    public void update(byte[] data, int offset, int length) {
        for (int i=0; i<length; ++i, ++offset)
            update(data[offset]);
    }

    public void update(byte data) {
        if (v) {
            hash = hash ^ data;
            hash = hash*prime;
        }
        else {
            hash = hash * prime;
            hash = hash ^ data;
        }
    }

    @Override
    public Hash hash() {
        return null;
    }
}
