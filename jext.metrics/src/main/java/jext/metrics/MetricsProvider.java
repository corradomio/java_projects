package jext.metrics;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public interface MetricsProvider {

    String ALL_METRICS = "all";

    /**
     * Provider Id
     * @return
     */
    String getId();

    /**
     * Provider name
     * @return provider name
     */
    String getName();

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Properties used to configure the provider
     * @return configuration properties
     */
    Properties getProperties();

    /**
     * List of metric categories.
     * It contains at minimum the category '' (the empty string)
     * containing all metrics
     */
    Collection<String> getCategories();

    /**
     * Check if it contains the specified category
     *
     * @param category categor id or name
     * @return true if the category is present
     */
    boolean hasCategory(String category);

    /**
     * List of supported object types
     */
    List<ObjectType> getSupportedTypes();

    /**
     * List of all metrics. It is the content of category ''
     */
    Collection<Metric> getMetrics();

    /**
     * List of metrics classified under the specified category
     * If the category doesn't exists, it returns the empty list.
     * The category '' (or null) contains all metrics
     */
    Collection<Metric> getMetrics(@Nullable String category);

    /**
     * Properties of the metric with the specified name or id.
     * Note: it is possible to have multiple metrics with the same name but different id.
     * In this case the metric selected is not specified (randomly)
     *
     * @param nameOrId id of the metric
     * @return Metric object
     */
    Metric getMetric(String nameOrId);

    /**
     * Navigate the hierarchical structure.
     * Note: if necessary it open a connection with the metric provider for this project.
     * When the project is not more necessary, it is useful to call 'MetricsProject.close()'
     *
     * @return root object of the metrics project
     */
    MetricsProject getProject();

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * Used for provider initialization
     * It is automatically called when a provider is requested
     * @param properties configuration properties
     */
    void initialize(Properties properties);

    /**
     * Register a new custom category.
     * Note: it can override a already defined category
     *
     * @param category category name
     * @param measures list of measure ids/names belonging to the category
     */
    void registerCategory(String category, Collection<String> measures);

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
