package jext.metrics;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MetricsComponent {

    String getId();
    String getName();
    ComponentType getType();

    Map<String, Object> getData();

    boolean hasChildren();
    List<MetricsComponent> getChildren();

    Collection<MetricValue> getMetricValues();
    Collection<MetricValue> getMetricValues(String category);

}
