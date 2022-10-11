package jext.metrics.providers;

import jext.metrics.Metric;

public class SciToolsMetric implements Metric {

    public static SciToolsMetric of(String name) {
        return new SciToolsMetric(name);
    }

    private final String name;
    private String description;

    private SciToolsMetric(String name) {
        this.name = name;
        this.description = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
