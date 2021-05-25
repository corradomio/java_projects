package org.hls.check;

import java.math.BigInteger;

public class CheckInteger {

    public static void main(String[] args) {
        System.out.println(Integer.toHexString(-1));

        BigInteger b = BigInteger.ONE;
        b = b.shiftLeft(16);
        byte[] ba = b.toByteArray();

        for (int i=0; i<ba.length; ++i)
            System.out.printf("%2d: %d\n", i, ba[i]);
    }
}
