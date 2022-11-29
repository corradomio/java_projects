package jext.metrics.aggregate;

import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.util.Assert;

public class MeanValue extends AggregateValue {

    private int count;

    public MeanValue(MetricsObject object) {
        super(object);
    }

    @Override
    public void add(MetricValue mvalue) {
        if (metric == null)
            metric = mvalue.getMetric();
        else
            Assert.verify(metric.getId().equals(mvalue.getMetric().getId()), "Not the same Metric");

        this.value += mvalue.getValue();
        this.count += 1;
    }

    @Override
    public double getValue() {
        return count == 0 ? value : value/count;
    }
}
