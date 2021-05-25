package jext.util.hash.fnv;

import jext.util.hash.Digester;
import jext.util.hash.HashCode;
import jext.util.hash.HashFunction;

public class FNV1 implements Digester {

    private long hash;
    private long prime, offset;
    private boolean v;
    private int nbits;

    public FNV1(long prime, long offset, int nbits, boolean v) {
        this.prime = prime;
        this.offset = offset;
        this.v = v;
        this.nbits = nbits;
    }

    public void reset() {
        this.hash = offset;
    }

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

    public byte[] digest() {
        if (nbits == 32)
            return new byte[]{
                (byte) (hash),
                (byte) (hash >> 8),
                (byte) (hash >> 16),
                (byte) (hash >> 24),
            }   ;
        else
            return new byte[]{
                (byte)(hash),
                (byte)(hash >>  8),
                (byte)(hash >> 16),
                (byte)(hash >> 24),
                (byte)(hash >> 32),
                (byte)(hash >> 40),
                (byte)(hash >> 48),
                (byte)(hash >> 56),
            };
    }
}
