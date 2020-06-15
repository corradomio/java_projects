package org.hls.check;

public class Main {

    public static void main(String[] args) {
        Integer i = Integer.valueOf(0);
        Number n = i;
        Integer j = Integer.class.cast(n);
        System.out.println(j.intValue());
    }
}
