package org.hls.check;

import jext.util.LongHash;

import java.util.Arrays;
import java.util.Collections;

public class ChechDigests {

    public static void main(String[] args) {
        System.out.println(LongHash.hash("Hello World"));
        System.out.println(LongHash.hash(Arrays.asList("Hello World")));
        System.out.println(LongHash.hash(""));
        System.out.println(LongHash.hash(Collections.emptyList()));

        long lc = 0xFFFFFFFFFFFFFFFFL;
             lc = 0x8000000000000000L;

        System.out.println(lc);
        String hs = LongHash.toString(lc);
        System.out.println(hs);
        lc = LongHash.fromString(hs);
        System.out.println(lc);

    }
}
