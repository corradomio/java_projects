package org.hls.check;

import jext.net.QueryString;
import jext.util.Parameters;

public class Main {

    public static void main(String[] args) {
        QueryString qs = QueryString.of("rev=1,2");

        System.out.println(qs.isArray("rev"));
        System.out.println(qs.getIntArray("rev").length);
    }
}
