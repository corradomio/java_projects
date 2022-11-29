package jext.metrics.aggregate;

import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsValues;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.Map;

public class AggregateMetricValues extends AbstractList<MetricValue> implements MetricsValues {

    private Map<String, AggregateValue> avmap = new HashMap<>();
    private final MetricsObject object;

    public AggregateMetricValues(MetricsObject object) {
        this.object = object;
    }

    @Override
    public boolean add(MetricValue value) {
        String id = value.getMetric().getId();
        if (!avmap.containsKey(id)) {
            AggregateValue av = AggregateValue.of(value.getMetric().getAggregateMode(), object);
            avmap.put(id, av);
        }
        AggregateValue av = avmap.get(id);
        av.add(value);
        return true;
    }

    @Override
    public void add(int index, MetricValue element) {
        this.add(element);
    }

    @Override
    public MetricValue get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return avmap.size();
    }

}
