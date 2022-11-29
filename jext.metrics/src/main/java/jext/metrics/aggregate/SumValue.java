package jext.metrics.aggregate;

import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.util.Assert;

public class SumValue extends AggregateValue {

    public SumValue(MetricsObject object) {
        super(object);
    }

    @Override
    public void add(MetricValue mvalue) {
        if (metric == null)
            metric = mvalue.getMetric();
        else
            Assert.verify(metric.getId().equals(mvalue.getMetric().getId()), "Not the same Metric");

        this.value += mvalue.getValue();
    }
}
