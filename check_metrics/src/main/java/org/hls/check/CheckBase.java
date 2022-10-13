package org.hls.check;

import jext.metrics.MetricsComponent;

public class CheckBase {

    static String spaces(int d) {
        String s = "";
        for(int i=0; i<d; ++i)
            s += "  ";
        return s;
    }

    static void dump(MetricsComponent c, int d) {
        if (c.hasChildren()) {
            System.out.printf("%s[%s]%n", spaces(d), c.getId());
            c.getChildren().forEach(cc -> dump(cc, d+1));
        }
        else
            System.out.printf("%s%s%n", spaces(d), c.getId());
    }

}
