package org.hls.check;

import jext.metrics.Metric;
import jext.metrics.MetricsCategory;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.metrics.ObjectType;
import jext.util.PropertiesUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class CheckSciToolsObjects {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("scitools",
                PropertiesUtils.properties(
                        "scitools.name", "lucene",
                        "scitools.metrics.home", "D:\\Projects\\Java\\hibernate-orm-5.2.0\\.spl\\scitools.dump",
                        // "scitools.project.home", "D:\\Projects\\Java\\hibernate-orm-5.2.0",
                        "scitools.metrics.revision", "0"

                        // "scitools.name", "csvquickview",
                        // "scitools.metrics.home", "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump",
                        // "scitools.metrics.revision", "0"

                        // "scitools.metrics.values", "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-r00.csv",
                        // "scitools.metrics.nodes",  "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-nodes-r00.csv",
                        // "scitools.metrics.edges",  "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-metrics-edges-r00.csv",
                        // "scitools.metrics.idmaps",  "D:\\Projects\\CSharp\\RNoeldner-CSVQuickViewer-d110a80\\.spl\\scitools.dump\\scitools-idmaps-r00.csv"
                ));

        provider.getCategories().forEach(category -> {
            MetricsCategory cat = provider.getCategory(category);
            System.out.printf("%s: %s\n", cat.getName(), cat.getDescription());
        });

        MetricsProject project = provider.getProject();

        Optional<MetricsObject> object = project.getMetricsObjects(ObjectType.SOURCE)
                .findObject("path", "hibernate-core/src/main/java/org/hibernate/AnnotationException.java");

        for(ObjectType ot : Arrays.asList(ObjectType.SOURCE)/*project.getObjectTypes()*/) {
            MetricsObjects mobjects = project.getMetricsObjects(ot);
            Set<Metric> metrics = project.getMetrics(ot);
            System.out.printf(">> %s: %d, %d\n",ot, mobjects.size(), metrics.size());
        }

        // MetricsObjects mobjects = project.getMetricsObjects(ObjectType.TYPE);
        // Optional<MetricsObject> mo = mobjects.findObject(SciToolsObjects.ID, "525296");
        //
        // mo.ifPresent(o -> o.getMetricValues().forEach(mv -> {
        //     System.out.printf("%s: %d\n", mv.getMetric().getId(), mv.getIntValue());
        // }));

    }
}
