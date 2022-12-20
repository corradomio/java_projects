package jext.metrics;

public interface Metric {

    MetricsProvider getProvider();

    /**
     * Metric id
     * It depends on the tool
     *
     * @return metric id
     */
    String getId();

    /**
     * Full metric name
     *
     * @return full metric name
     */
    String getName();
    String getFullName();

    /**
     * Metric description
     *
     * @return metric description
     */
    String getDescription();

    /**
     * Metric type
     */
    ValueType getType();

    /**
     * How to aggregate multime metrics
     */
    AggregateMode getAggregateMode();

}
