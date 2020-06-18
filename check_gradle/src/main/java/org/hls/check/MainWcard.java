package org.hls.check;

import jext.util.Wildcard;

public class MainWcard {

    public static void main(String[] args) {
        Wildcard wc;

        wc = new Wildcard("ca$a");

        System.out.println(wc.accept("casa"));
        System.out.println(wc.accept("ca$a"));
    }
}
