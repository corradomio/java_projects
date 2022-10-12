package jext.metrics.providers.scitools;

import jext.metrics.MetricValue;

public class SciToolsMetricValue implements MetricValue {

    public static SciToolsMetricValue of(SciToolsMetric metric, float value) {
        return new SciToolsMetricValue(metric, value);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final SciToolsMetric metric;
    private final float value;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private SciToolsMetricValue(SciToolsMetric metric, float value) {
        this.metric = metric;
        this.value = value;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return metric.getId();
    }

    @Override
    public String getName() {
        return metric.getName();
    }

    @Override
    public String getDescription() {
        return metric.getDescription();
    }

    @Override
    public float getValue() {
        return value;
    }

    // ----------------------------------------------------------------------
    // Done
    // ----------------------------------------------------------------------

}
