package jext.metrics;

import java.util.List;

public interface MetricCategory {
    String getName();

    List<Metric> getMetrics();

}
