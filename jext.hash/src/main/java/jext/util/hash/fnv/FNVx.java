package jext.util.hash.fnv;

import jext.util.hash.Digester;
import jext.util.hash.HashCode;
import jext.util.hash.HashFunction;

import java.math.BigInteger;
import java.util.Arrays;

public class FNVx  implements Digester {

    private BigInteger hash;
    private BigInteger prime, offset;
    private BigInteger mask;
    private boolean v;
    private byte[] abyte = new byte[1];
    private int nbytes;

    public FNVx(BigInteger prime, BigInteger offset, int nbits, boolean v) {
        this.prime = prime;
        this.offset = offset;
        this.v = v;
        this.hash = offset;
        this.nbytes = (nbits+7)/8;

        BigInteger one = BigInteger.ONE;
        this.mask = one.shiftLeft(nbits).subtract(one);
    }

    public void reset() {
        this.hash = offset;
    }

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

        hash = hash.and(mask);
    }

    public byte[] digest() {
        byte[] hbytes = hash.toByteArray();
        if (hbytes.length != nbytes)
            hbytes = Arrays.copyOf(hbytes, nbytes);
        reverse(hbytes);
        return hbytes;
    }

    private static void reverse(byte[] bytes) {
        for(int i=0, j=bytes.length-1; i<j; ++i,--j) {
            byte t = bytes[i];
            bytes[i] = bytes[j];
            bytes[j] = t;
        }
    }
}
