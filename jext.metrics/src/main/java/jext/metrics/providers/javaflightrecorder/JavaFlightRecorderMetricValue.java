package jext.metrics.providers.javaflightrecorder;

import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;

import java.util.Objects;

public class JavaFlightRecorderMetricValue implements MetricValue {

    public static JavaFlightRecorderMetricValue of(JavaFlightRecorderObject object, JavaFlightRecorderMetric metric, float value) {
        return new JavaFlightRecorderMetricValue(object, metric, value);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final JavaFlightRecorderObject object;
    private final JavaFlightRecorderMetric metric;
    private final double value;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private JavaFlightRecorderMetricValue(JavaFlightRecorderObject object, JavaFlightRecorderMetric metric, float value) {
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
