package jext.metrics.aggregate;

import jext.metrics.AggregateMode;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;

public abstract class AggregateValue implements MetricValue {

    public static AggregateValue of(AggregateMode mode, MetricsObject object) {
        switch (mode) {
            case SUM: return new SumValue(object);
            case MEAN: return new MeanValue(object);
            default: return new SumValue(object);
        }
    }


    protected final MetricsObject object;
    protected Metric metric;
    protected double value;

    public AggregateValue(MetricsObject object) {
        this.object = object;
    }

    @Override
    public Metric getMetric() {
        return metric;
    }

    @Override
    public MetricsObject getObject() {
        return object;
    }

    @Override
    public int getIntValue() {
        return (int)getValue();
    }

    @Override
    public double getValue() {
        return value;
    }

    abstract public void  add(MetricValue mvalue);
}
