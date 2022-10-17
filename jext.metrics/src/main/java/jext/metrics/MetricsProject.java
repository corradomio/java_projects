package jext.metrics;

import java.util.Collection;

public interface MetricsProject extends MetricsComponent {

    /**
     * Retrieve all metrics recursively
     * @param type
     * @param category
     * @return
     */
    Collection<MetricValue> getAllMetricValues(ComponentType type, String category);

    void close();
}
