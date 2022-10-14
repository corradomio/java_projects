package jext.metrics;

import java.util.Collection;
import java.util.List;

public interface MetricsComponent {

    String getId();
    String getName();

    boolean hasChildren();
    List<MetricsComponent> getChildren();

    Collection<MetricValue> getMetricValues();
    Collection<MetricValue> getMetricValues(String category);
}
