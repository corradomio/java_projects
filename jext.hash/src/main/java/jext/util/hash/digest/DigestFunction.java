package jext.util.hash.digest;

import jext.util.hash.HashFunction;
import jext.util.hash.Hasher;

public class DigestFunction implements HashFunction {

    private String algorithm;

    public DigestFunction(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Hasher newHasher() {
        return new DigestHasher(algorithm);
    }

}
