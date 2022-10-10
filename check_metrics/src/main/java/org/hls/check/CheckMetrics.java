package org.hls.check;

import jext.metrics.AllMetrics;
import jext.metrics.MetricsProviders;
import jext.metrics.providers.SciToolsMetricsProvider;

import java.io.File;

public class CheckMetrics {

    public static void main(String[] args) {
        SciToolsMetricsProvider provider = MetricsProviders.getProvider("scitools");

        System.out.println(provider.getName());

        AllMetrics allMetrics = provider.loadMetrics(new File("D:\\Dropbox\\Software\\unddb\\lucene\\scitools-metrics.csv"));
    }
}
