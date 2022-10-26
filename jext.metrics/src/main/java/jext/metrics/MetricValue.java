package jext.metrics;


public interface MetricValue {

    /**
     * Metric referred by this value
     * @return metric descriptor
     */
    Metric getMetric();

    /**
     * Component owner of this metric
     *
     * @return component owner of this metric
     */
    MetricsComponent getComponent();

    /**
     * Metric value
     *
     * @return metric value
     */
    float getValue();

    /**
     * Metric value as integer
     *
     * @return metric value as integer
     */
    int   getIntValue();

}
