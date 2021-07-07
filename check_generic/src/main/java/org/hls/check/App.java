package org.hls.check;

import jext.util.TimeUtils;

public class App {

    public static void main(String[] args) {

        long t = TimeUtils.parse("10000m");
        System.out.println(TimeUtils.format(t, "i"));
        System.out.printf("%1$d, %2$d, %1$d\n", 0, 1);
    }
}
