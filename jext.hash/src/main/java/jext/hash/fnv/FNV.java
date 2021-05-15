package jext.hash.fnv;

import jext.hash.HashAlgorithm;

public class FNV implements FNVConstants {

    public static HashAlgorithm getInstance(int nbits, boolean variant) {
        if (nbits == 32)
            return new FNV1(prime32, offset32, variant);
        if (nbits == 64)
            return new FNV1(prime64, offset64, variant);
        if (nbits == 128)
            return new FNVx(prime128, offset128, variant);
        if (nbits == 256)
            return new FNVx(prime256, offset256, variant);
        if (nbits == 512)
            return new FNVx(prime512, offset512, variant);
        if (nbits == 1024)
            return new FNVx(prime1024, offset1024, variant);
        else
            throw new IllegalArgumentException("Unsupported bits " + nbits);
    }
}
