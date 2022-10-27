package jext.metrics;

import javax.annotation.Nullable;
import java.util.Set;

public interface MetricsProject extends MetricsObject {

    MetricsProvider getProvider();

    /**
     * Retrieve the list of all metrics used in the project.
     * Note: projects based on different programing languages use different metrics
     *
     * @return list of all metrics used in the project
     */
    Set<Metric> getMetrics();

    /**
     * retrieve all metrics objects
     *
     * @param type object type
     * @return
     */
    MetricsObjects getMetricsObjects(ObjectType type);

    /**
     * Retrieve all metrics recursively for all objects of the specified type and category
     * Note: the category can be null or ''. In this case are collected all metrics
     *
     * @param type object type
     * @param category metric category, null or ""
     * @return list of all metric values for all objects of the specified type
     */
    MetricsValues getMetricsValues(ObjectType type, @Nullable String category);

    /**
     * Close the connection with the metrics provider for this project
     */
    void close();
}
