package jext.util.hash;

/*
Algorithm name: "MD2"
Algorithm name: "MD5"
Algorithm name: "SHA"
Algorithm name: "SHA-224"
Algorithm name: "SHA-256"
Algorithm name: "SHA-384"
Algorithm name: "SHA-512"
 */

import jext.util.hash.digest.DigestFunction;
import jext.util.hash.fnv.FNVHashFunction;

public class HashFunctions {

    public static HashFunction md2() {
        return new DigestFunction("MD2");
    }

    public static HashFunction md5() {
        return new DigestFunction("MD5");
    }

    public static HashFunction sha(int nbits) {
        if (nbits == 0)
            return new DigestFunction("SHA");
        if (nbits <= 224)
            return new DigestFunction("SHA-224");
        if (nbits <= 256)
            return new DigestFunction("SHA-256");
        if (nbits <= 384)
            return new DigestFunction("SHA-384");
        else
            return new DigestFunction("SHA-512");
    }

    public static HashFunction fnv(int nbits, boolean variant) {
        return new FNVHashFunction(nbits, variant);

    }

}
