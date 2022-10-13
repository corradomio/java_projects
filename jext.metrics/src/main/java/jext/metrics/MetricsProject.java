package jext.metrics;

import java.util.Collection;

public interface MetricsProject extends MetricsComponent {

    /**
     * Retrieve all metrics fo the specified object
     * @param id object's id
     * @return sonarcube-list.json of metrics
     */
    Collection<MetricValue> getMetricValues(String id);

    /**
     * Retrieve all metrics of the specified object under the specified category
     * @param id object's id
     * @param category metrics category
     * @return sonarcube-list.json of metrics
     */
    Collection<MetricValue> getMetricValues(String id, String category);
}
