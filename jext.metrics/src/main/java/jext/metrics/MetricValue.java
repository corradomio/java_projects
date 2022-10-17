package jext.metrics;


public interface MetricValue /*extends Metric*/ {

    Metric getMetric();
    MetricsComponent getComponent();

    float getValue();
    int   getIntValue();

}
