package org.hls.check;

import jext.metrics.Metric;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.PropertiesUtils;

import java.util.Collection;

public class CheckSonarQube extends CheckBase {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("sonarqube",
                PropertiesUtils.properties(
                        "sonar.url", "http://localhost:9000",
                        "sonar.username", "sonaruser",
                        "sonar.password", "sonaruser",
                        "sonar.name", "Lucene"
                ));

        provider.getMetrics().forEach(metric -> {
            System.out.println(metric.getName());
        });
        provider.getCategories().forEach(category -> {
            Collection<Metric> metrics = provider.getMetrics(category);
            System.out.printf("%s: %s\n", category, metrics);
        });

        for (String category : provider.getCategories())
            if (!category.isEmpty()) {
                System.out.printf("-- %s --\n", category);
                dumpMetrics(provider.getProject(), 0, category);
            }

        // dump(provider.getProject(), 0);
    }
}
