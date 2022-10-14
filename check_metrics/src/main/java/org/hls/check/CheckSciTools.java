package org.hls.check;

import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.PropertiesUtils;

public class CheckSciTools extends CheckBase {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("scitools",
                PropertiesUtils.properties(
                        "scitools.name", "csvquickview",
                "scitools.metrics.values", "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-r00.csv",
                       "scitools.metrics.nodes",   "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-nodes-r00.csv",
                       "scitools.metrics.edges",   "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-edges-r00.csv"
                ));

        dump(provider.getProject(), 0);
    }
}
