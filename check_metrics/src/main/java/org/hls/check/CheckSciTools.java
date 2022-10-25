package org.hls.check;

import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.PropertiesUtils;

public class CheckSciTools extends CheckBase {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("scitools",
                PropertiesUtils.properties(
                        "scitools.name", "lucene",
                        "scitools.metrics.home", "D:\\Projects\\CSharp\\Apache-Lucene.Net-4.8.0\\.spl\\scitools.dump",
                        "scitools.metrics.revision", "0"

                        // "scitools.name", "csvquickview",
                        // "scitools.metrics.home", "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump",
                        // "scitools.metrics.revision", "0"

                        // "scitools.metrics.values", "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-r00.csv",
                        // "scitools.metrics.nodes",  "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-nodes-r00.csv",
                        // "scitools.metrics.edges",  "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-edges-r00.csv",
                        // "scitools.metrics.idmaps",  "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-idmaps-r00.csv"
                ));

        for (String category : provider.getCategories())
            if (!category.isEmpty()) {
                System.out.printf("-- %s --\n", category);
                dumpMetrics(provider.getProject(), 0, category);
            }

        // dump(provider.getProject(), 0);
    }
}
