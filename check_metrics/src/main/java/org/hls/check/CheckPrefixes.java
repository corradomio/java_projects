package org.hls.check;

import jext.util.ShortestPrefixes;

public class CheckPrefixes {

    public static void main(String[] args) {
        ShortestPrefixes sp = new ShortestPrefixes();

        sp.add("a/b/c/d");
        sp.add("a/b/c/d/e");
        sp.add("a/b");
        sp.add("f/g/h/i");
        sp.add("f/g");

        System.out.println(sp);

    }
}
