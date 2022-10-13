package jext.metrics;

import java.util.Collection;
import java.util.Properties;

public interface MetricsProvider {

    String getName();

    Properties getProperties();

    void initialize(Properties properties);

    /**
     * List of metric categories.
     * It contains at minimum the category '' (the empty string)
     * containing all metrics
     */
    Collection<String> getCategories();
    boolean hasCategory(String category);

    /**
     * List of all metrics. It is the content of category ''
     */
    Collection<Metric> getMetrics();

    /**
     * List of metrics classified under the specified category
     * If the category doesn't exists, it returns the empty sonarcube-list.json
     */
    Collection<Metric> getMetrics(String category);

    /**
     * Properties of the metric with the specified name
     * @param name name of the metric
     * @return Metric object
     */
    Metric getMetric(String name);


    MetricsProject getProject();

}
