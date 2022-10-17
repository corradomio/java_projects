package jext.metrics.providers.sonarqube;

import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import org.sonar.wsclient.services.Measure;

import java.util.Objects;

public class SonarMetricValue implements MetricValue {

    static SonarMetricValue of(SonarObject component, SonarMetric metric, Measure measure) {
        return new SonarMetricValue(component, metric, measure);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final SonarObject component;
    private final SonarMetric metric;
    private final Measure measure;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private SonarMetricValue(SonarObject component, SonarMetric metric, Measure measure) {
        this.component = component;
        this.metric = metric;
        this.measure = measure;
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
    public MetricsComponent getComponent() {
        return component;
    }

    @Override
    public float getValue() {
        return measure.getValue().floatValue();
    }

    @Override
    public int getIntValue() {
        return measure.getValue().intValue();
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
