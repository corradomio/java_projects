package aext.metrics;

import jext.metrics.*;
import jext.util.PropertiesUtils;

import java.util.Set;

public class CheckJFRP {

    static final String JAVAFLIGHTRECORDER = "javaflightrecorder";

    static final String JAVAFLIGHTRECORDER_NAME = "javaflightrecorder.name";
    static final String JAVAFLIGHTRECORDER_METRICS_HOME = "javaflightrecorder.metrics.home";
    static final String JAVAFLIGHTRECORDER_METRICS_REVISION = "javaflightrecorder.metrics.revision";
    //static final String DEFAULT_DUMPDIR = "runtime";

    public static void main(String[] args){
            MetricsProvider provider = MetricsProviders.getProvider(JAVAFLIGHTRECORDER,
                    PropertiesUtils.properties(
                            JAVAFLIGHTRECORDER_NAME, "splv321",
                            JAVAFLIGHTRECORDER_METRICS_HOME, "/Users/ahmed/Desktop/SPL/sample-projects/splv321/.spl/runtime",
                            JAVAFLIGHTRECORDER_METRICS_REVISION,"0",
                            "refId", "39674290"
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
