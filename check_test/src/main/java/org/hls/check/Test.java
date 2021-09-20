package org.hls.check;

import jext.util.LongUtils;

public class Test {

    public static void main(String[] args) {
        long l = -8279749907707637070L;

        System.out.println(Long.toString(l, -1));

        String s = Long.toString(l, Character.MAX_RADIX);
        System.out.println(s);

        l = Long.parseLong(s, Character.MAX_RADIX);
        System.out.println(l);

        s = LongUtils.toString(l, 64);
        System.out.println(s);

        l = LongUtils.parseLong(s, 64);
        System.out.println(l);
    }
}
