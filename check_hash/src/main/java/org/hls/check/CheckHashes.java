package org.hls.check;

import jext.util.hash.HashCode;
import jext.util.hash.HashFunction;
import jext.util.hash.Hasher;
import jext.util.hash.Hashes;

import java.math.BigInteger;

public class CheckHashes {

    public static void main(String[] args) {
        HashFunction hf = Hashes.fnv(128, false);

        // BigInteger o = new BigInteger("1");
        // System.out.println(o.toString());
        // BigInteger b = o.shiftLeft(4);
        // System.out.println(b.toString());
        // System.out.println(b.subtract(o).toString());

        Hasher h = hf.newHasher();

        h.putString("Ciao");

        HashCode hc = h.hash();

        System.out.println(hc.bits());
        System.out.println(hc.asHexString());
    }
}
