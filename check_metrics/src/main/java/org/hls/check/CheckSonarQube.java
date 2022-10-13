package org.hls.check;

import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.PropertiesUtils;

public class CheckSonarQube extends CheckBase {

    public static void main(String[] args) {

        MetricsProvider provider = MetricsProviders.getProvider("sonarqube",
                PropertiesUtils.properties(
                        "sonar.url", "http://localhost:9000",
                        "sonar.username", "sonaruser",
                        "sonar.password", "sonaruser",
                        "sonar.name", "Lucene"
                ));

        dump(provider.getProject(), 0);
    }
}
