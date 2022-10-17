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
            System.out.printf("%s[%s] %s %n", spaces(d), c.getId(), c.getName());
            c.getChildren().forEach(cc -> dump(cc, d+1));
        }
        else
            System.out.printf("%s%s %s%n", spaces(d), c.getId(), c.getName());
    }

    static void dumpMetrics(MetricsComponent c, int d, String category) {
        System.out.printf("%s[%s: %s] %s %n", spaces(d), c.getId(), c.getType(), c.getName());
        c.getMetricValues(category).forEach(mvalue -> {
            System.out.printf("%s    %s: %d %n", spaces(d), mvalue.getMetric().getName(), mvalue.getIntValue());
        });
        c.getChildren().forEach(cc -> dumpMetrics(cc, d+1, category));
    }

}
