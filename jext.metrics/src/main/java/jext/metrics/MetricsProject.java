package jext.metrics;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public interface MetricsProject extends MetricsComponent {

    MetricsProvider getProvider();

    /**
     * Retrieve the list of all metrics used in the project.
     *
     * @return list of all metrics used in the project
     */
    Set<Metric> getMetrics();

    /**
     * Retrieve all metrics recursively
     * @param type component type
     * @param category metric category or null or ""
     * @return list of all metric values for the specified object type
     */
    List<MetricValue> getMetricValues(ComponentType type, @Nullable String category);

    void close();
}
