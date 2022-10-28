package org.hls.check;

import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.metrics.ObjectType;
import jext.metrics.providers.scitools.SciToolsObjects;
import jext.util.PropertiesUtils;

import java.util.Optional;

public class CheckSciToolsObjects {

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

        MetricsProject project = provider.getProject();
        MetricsObjects mobjects = project.getMetricsObjects(ObjectType.TYPE);
        Optional<MetricsObject> mo = mobjects.findObject(SciToolsObjects.ID, "525296");

        mo.ifPresent(o -> o.getMetricValues().forEach(mv -> {
            System.out.printf("%s: %d\n", mv.getMetric().getId(), mv.getIntValue());
        }));

    }
}
