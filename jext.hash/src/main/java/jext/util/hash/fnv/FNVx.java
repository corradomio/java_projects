package jext.util.hash.fnv;

import jext.util.hash.Hash;
import jext.util.hash.HashAlgorithm;

import java.math.BigInteger;

public class FNVx implements HashAlgorithm {

    private BigInteger hash;
    private BigInteger prime, offset;
    private boolean v;
    private byte[] abyte = new byte[1];

    FNVx(BigInteger prime, BigInteger offset, boolean v) {
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
        abyte[0] = data;
        if (v) {
            hash = hash.xor(new BigInteger(abyte));
            hash = hash.multiply(prime);
        }
        else {
            hash = hash.multiply(prime);
            hash = hash.xor(new BigInteger(abyte));
        }
    }

    @Override
    public Hash hash() {
        return null;
    }
}
