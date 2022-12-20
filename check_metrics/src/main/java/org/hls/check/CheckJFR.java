package org.hls.check;

import jext.metrics.Metric;
import jext.metrics.MetricsObjects;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.metrics.ObjectType;
import jext.util.PropertiesUtils;

import java.util.Set;

public class CheckJFR {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("javaflightrecorder",
                PropertiesUtils.properties(
                        "javaflightrecorder.metrics.home"
                ));

        MetricsProject project = provider.getProject();

        project.getMetrics(ObjectType.PROJECT);

        for(ObjectType ot : project.getObjectTypes()) {
            MetricsObjects mobjects = project.getMetricsObjects(ot);
            Set<Metric> metrics = project.getMetrics(ot);
            System.out.printf(">> %s: %d, %d\n",ot, mobjects.size(), metrics.size());
        }

    }
}
