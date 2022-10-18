package jext.metrics;

import java.util.Collection;
import java.util.Properties;

public interface MetricsProvider {

    /**
     * Provider name
     * @return provider name
     */
    String getName();

    /**
     * Properties used to configure the provider
     * @return configuration properties
     */
    Properties getProperties();

    /** Used for provider initialization 
     * It is atumatically called quen a provider is requested
     * @param properties configuration properties
     */
    void initialize(Properties properties);

    /**
     * List of metric categories.
     * It contains at minimum the category '' (the empty string)
     * containing all metrics
     */
    Collection<String> getCategories();
    boolean hasCategory(String category);

    /**
     * Register a new category
     * @param category category name
     * @param measures list of measures belonging to the category
     */
    void registerCategory(String category, Collection<String> measures);

    /**
     * List of all metrics. It is the content of category ''
     */
    Collection<Metric> getMetrics();

    /**
     * List of metrics classified under the specified category
     * If the category doesn't exists, it returns the empty list.
     * The category '' contains all metrics
     */
    Collection<Metric> getMetrics(String category);

    /**
     * Properties of the metric with the specified name
     * @param name name of the metric
     * @return Metric object
     */
    Metric getMetric(String name);

    /**
     * Navigate the hierarchical structure
     *
     * @return root
     */
    MetricsProject getProject();

}
