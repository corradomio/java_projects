package org.hls.check;

public class Test {

    public static void main(String[] args) {
        String s = Long.toHexString(-1);
        System.out.println(s);
        long l = Long.parseUnsignedLong(s, 16);
        System.out.println(l);
    }
}
