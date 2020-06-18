package org.hls.check;

import jext.util.Wildcard;

public class MatchWildcard {

    public static void main(String[] args) {
        Wildcard wc;

        wc = new Wildcard("**/*.java");

        System.out.println(wc.matches("ciccio.java"));
        System.out.println(wc.matches("/ciccio.java"));
    }
}
