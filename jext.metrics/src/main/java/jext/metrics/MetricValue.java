package jext.metrics;


public interface MetricValue {

    /**
     * Metric referred by this value
     * @return metric descriptor
     */
    Metric getMetric();

    /**
     * Object owner of this metric
     *
     * @return object owner of this metric
     */
    MetricsObject getObject();

    /**
     * Metric value
     *
     * @return metric value
     */
    double getValue();

    /**
     * Metric value as integer
     *
     * @return metric value as integer
     */
    int   getIntValue();

}
