package org.hls.check;

import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.PropertiesUtils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CheckMetrics {

    static void check() throws SQLException {

        DriverManager.getConnection("", new Properties());
    }

    static void scitools() {
        MetricsProvider provider = MetricsProviders.getProvider("scitools",
                PropertiesUtils.properties("file", "D:\\Dropbox\\Software\\unddb\\lucene\\scitools-metrics.csv"));

        System.out.println(provider.getName());

        provider.getCategories().forEach(cat -> {
            System.out.printf("category '%s'\n", cat);
            provider.getCategory(cat).getMetrics().forEach(m -> {
                System.out.printf("  %s\n", m.getName());
            });
        });

        System.out.println("done");

        //
        // allMetrics.getMetrics().forEach(m -> {
        //     System.out.println(m.getName());
        // });
    }

    static void sonarqube() {
        MetricsProvider provider = MetricsProviders.getProvider("sonarqube",
                PropertiesUtils.properties(
                        "sonar.url", "localhost:9000",
                        "sonar.username", "sonaruser",
                        "sonar.password", "sonaruser",
                        "sonar.project", "Lucene"
                ));

        System.out.println(provider.getName());

        provider.getCategories().forEach(cat -> {
            System.out.printf("category '%s'\n", cat);
            provider.getCategory(cat).getMetrics().forEach(m -> {
                System.out.printf("  %s\n", m.getName());
            });
        });

        System.out.println("done");
    }

    public static void main(String[] args) {
        scitools();
        sonarqube();
    }
}
