package jext.metrics;

public interface MetricsProvider {
    String getName();

    AllMetrics loadMetrics();
}
