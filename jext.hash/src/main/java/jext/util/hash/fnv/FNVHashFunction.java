package jext.util.hash.fnv;

import jext.util.hash.Digester;
import jext.util.hash.HashFunction;
import jext.util.hash.Hasher;
import jext.util.hash.util.DigestToHasher;

public class FNVHashFunction implements HashFunction {

    private int nbits;
    private boolean variant;

    public FNVHashFunction(int nbits, boolean variant) {
        this.nbits = nbits;
        this.variant = variant;
    }

    @Override
    public Hasher newHasher() {
        Digester digest;
        if (nbits <= 32)
            digest = new FNV1(FNVConstants.prime32, FNVConstants.offset32, 32, variant);
        else if (nbits <= 64)
            digest = new FNV1(FNVConstants.prime64, FNVConstants.offset64, 64, variant);
        else if (nbits <= 128)
            digest = new FNVx(FNVConstants.prime128, FNVConstants.offset128, 128, variant);
        else if (nbits <= 256)
            digest = new FNVx(FNVConstants.prime256, FNVConstants.offset256, 256, variant);
        else if (nbits <= 512)
            digest = new FNVx(FNVConstants.prime512, FNVConstants.offset512, 512, variant);
        else
            digest = new FNVx(FNVConstants.prime1024, FNVConstants.offset1024, 1024, variant);

        return new DigestToHasher(digest);
    }
}
