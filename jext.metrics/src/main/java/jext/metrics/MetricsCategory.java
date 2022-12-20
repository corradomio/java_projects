package jext.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MetricsCategory {

    private final MetricsProvider provider;
    private final String name;
    private final String fullname;
    private String description = "";
    private final Set<String> metricNames = new TreeSet<>();
    private final List<Metric> metrics = new ArrayList<>();

    public MetricsCategory(String name, String fullname, Collection<Metric> metrics) {
        this.provider = null;
        this.name = name;
        this.fullname = fullname;
        this.metrics.addAll(metrics);
    }

    public MetricsCategory(MetricsProvider provider, String name) {
        this.provider = provider;
        this.name = name;
        this.fullname = String.format("%s.%s", provider.getName(), name);
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullname;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getMetricNames() {
        if (!metricNames.isEmpty())
            return this.metricNames;
        if (metrics.isEmpty())
            return Collections.emptySet();
        else
            return metrics.stream()
                    .map(Metric::getName)
                    .collect(Collectors.toSet());
    }

    public List<Metric> getMetrics() {
        if (provider == null || !metrics.isEmpty())
            return metrics;
        return metricNames.stream()
                .map(mname -> provider.getMetric(mname))
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return metricNames.isEmpty() && metrics.isEmpty();
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void addAll(List<String> metricNames) {
        this.metricNames.addAll(metricNames);
    }

    public void addMetrics(Collection<Metric> metrics) {
        this.metrics.addAll(metrics);
    }

    public void add(String metricName) {
        this.metricNames.add(metricName);
    }
}
