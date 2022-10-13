package org.hls.check;

import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.PropertiesUtils;

public class CheckSciTools extends CheckBase {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("scitools",
                PropertiesUtils.properties("file", "D:\\Dropbox\\Software\\unddb\\lucene\\scitools-metrics.csv"));

        dump(provider.getProject(), 0);
    }
}
