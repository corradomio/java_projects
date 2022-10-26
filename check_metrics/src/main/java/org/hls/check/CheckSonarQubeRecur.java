package org.hls.check;

import jext.metrics.ComponentType;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.PropertiesUtils;

public class CheckSonarQubeRecur {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("sonarqube",
                PropertiesUtils.properties(
                        "sonar.url", "http://localhost:9000",
                        "sonar.username", "sonaruser",
                        "sonar.password", "sonaruser",
                        "sonar.name", "Lucene"
                ));

        MetricsProject project = provider.getProject();
        provider.getCategories().forEach(category -> {
            project.getMetricValues(ComponentType.FILE, category).forEach(value -> {
                System.out.println(value);
            });
        });

    }
}
