package org.hls.check;

import jext.metrics.Metric;
import jext.metrics.MetricsObjects;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.metrics.ObjectType;
import jext.util.PropertiesUtils;

import java.util.Collection;
import java.util.Set;

public class CheckSonarQube extends CheckBase {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("sonarqube",
                PropertiesUtils.properties(
                        "sonar.url", "http://localhost:9000",
                        "sonar.username", "sonaruser",
                        "sonar.password", "sonaruser",
                        "sonar.name", "acme-beta4"
                ));

        MetricsProject project = provider.getProject();

        project.getMetrics(ObjectType.PROJECT);

        for(ObjectType ot : project.getObjectTypes()) {
            MetricsObjects mobjects = project.getMetricsObjects(ot);
            Set<Metric> metrics = project.getMetrics(ot);
            System.out.printf(">> %s: %d, %d\n",ot, mobjects.size(), metrics.size());
        }

        // provider.getMetrics().forEach(metric -> {
        //     System.out.println(metric.getName());
        // });
        // provider.getCategories().forEach(category -> {
        //     Collection<Metric> metrics = provider.getMetrics(category);
        //     System.out.printf("%s: %s\n", category, metrics);
        // });
        //
        // for (String category : provider.getCategories())
        //     if (!category.isEmpty()) {
        //         System.out.printf("-- %s --\n", category);
        //         dumpMetrics(provider.getProject(), 0, category);
        //     }

        // dump(provider.getProject(), 0);
    }
}
