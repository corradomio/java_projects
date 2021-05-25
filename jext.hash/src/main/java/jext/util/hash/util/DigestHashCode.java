package jext.util.hash.util;

import jext.util.hash.HashCode;

public class DigestHashCode implements HashCode {

    private static final char[] hex = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private byte[] digest;

    public DigestHashCode(byte[] digest) {
        this.digest = digest;
    }

    @Override
    public int bits() {
        return 8 * digest.length;
    }

    @Override
    public int asInt() {
        int n = Math.min(4, digest.length);
        int h = 0;
        for (int i = n - 1; i >= 0; --i)
            h = (h << 8) | digest[i];
        return 0;
    }

    @Override
    public long asLong() {
        int n = Math.min(8, digest.length);
        int h = 0;
        for (int i = n - 1; i >= 0; --i)
            h = (h << 8) | digest[i];
        return 0;
    }

    @Override
    public byte[] asBytes() {
        return digest;
    }

    @Override
    public String asHexString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; ++i) {
            byte b = digest[i];
            sb.append(hex[(b) & 0xF]);
            sb.append(hex[(b >> 4) & 0xF]);
        }
        return sb.toString();
    }

    @Override
    public float similarity(HashCode hc) {
        byte[] other = hc.asBytes();
        if (digest.length != other.length)
            return 0;
        for (int i = 0; i < digest.length; ++i)
            if (digest[i] != other[i])
                return 0;
        return 1;
    }
}
