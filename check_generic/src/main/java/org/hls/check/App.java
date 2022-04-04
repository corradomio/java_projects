package org.hls.check;

import jext.util.LongHash;
import jext.util.TimeUtils;

public class App {

    public static void main(String[] args) {
        String s = "1/2:3/4";
        String[] parts = s.split("/|:");
        System.out.println(parts.length);
    }

    public static void main2(String[] args) {
        long l = 0xFFFFFFFFFFFFFFFFL;

        while (l != 0) {
            System.out.println(l);
            String s = LongHash.toString(l);
            System.out.println(s);
            l = LongHash.fromString(s);
            System.out.println(l);
            System.out.println("------");

            l >>>= 2;
        }

    }

    public static void main1(String[] args) {

        long l = 0xFFFFFFFFFFFFFFFFL;
        System.out.println(l);
        l >>>= 63;
        System.out.println(l);

        long t = TimeUtils.parse("10000m");
        System.out.println(TimeUtils.format(t, "i"));
        System.out.printf("%1$d, %2$d, %1$d\n", 0, 1);
    }
}
