package org.hls.check;

public class Test {

    public static void main(String[] args) {
        System.out.println(Math.min(1, 2));
        System.out.println(java.lang.Math.min(1, 2));

        Math m = null;
        System.out.println(m.min(1, 2));
    }
}
