package jext.metrics.providers.scitools;

import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;

import java.util.Objects;

public class SciToolsMetricValue implements MetricValue {

    public static SciToolsMetricValue of(SciToolsObject object, SciToolsMetric metric, float value) {
        return new SciToolsMetricValue(object, metric, value);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final SciToolsObject object;
    private final SciToolsMetric metric;
    private final double value;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private SciToolsMetricValue(SciToolsObject object, SciToolsMetric metric, float value) {
        this.object = object;
        this.metric = metric;
        this.value = value;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // @Override
    // public String getId() {
    //     return metric.getId();
    // }
    //
    // @Override
    // public String getName() {
    //     return metric.getName();
    // }
    //
    // @Override
    // public String getDescription() {
    //     return metric.getDescription();
    // }

    @Override
    public Metric getMetric() {
        return metric;
    }

    @Override
    public MetricsObject getObject() {
        return object;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public int getIntValue() {
        return (int)value;
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("%s: %f", metric.getName(), getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash( metric.getName(), getValue());
    }

    // ----------------------------------------------------------------------
    // Done
    // ----------------------------------------------------------------------

}
