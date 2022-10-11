package jext.metrics.providers;

import jext.metrics.AllMetrics;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SciToolsAllMetrics implements AllMetrics {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String ROOT = "";

    private final Map<String, Metric> metrics = new TreeMap<>();
    private final Map<String, List<MetricValue>> metricValues = new TreeMap<>();
    private final Map<String, Set<String>> categories = new TreeMap<>();
    private final Map<String, SciToolsObject> objects = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SciToolsAllMetrics() {
        categories.put(ROOT, new TreeSet<>());
    }

    // ----------------------------------------------------------------------
    // Metric Properties
    // ----------------------------------------------------------------------

    @Override
    public Collection<String> getCategories() {
        return categories.keySet();
    }

    @Override
    public Collection<Metric> getMetrics() {
        return getMetrics(ROOT);
    }

    @Override
    public Collection<Metric> getMetrics(String category) {
        Assert.verify(category != null, "category is null");
        // if (ROOT.equals(category))
        //     return metrics.values();

        if (!categories.containsKey(category))
            return Collections.emptyList();

        return categories.get(category).stream()
                .map(this::getMetric)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Metric getMetric(String name) {
        if (!metrics.containsKey(name))
            Assert.nop();
        return metrics.get(name);
    }

    // ----------------------------------------------------------------------
    // Metric Values
    // ----------------------------------------------------------------------

    @Override
    public Collection<MetricValue> getMetricValues(String id) {
        if (!metricValues.containsKey(id))
            return Collections.emptyList();
        else
            return metricValues.get(id);
    }

    @Override
    public Collection<MetricValue> getMetricValues(String id, String category) {
        if (!metricValues.containsKey(id))
            return Collections.emptyList();
        if (!categories.containsKey(category))
            return Collections.emptyList();

        Set<String> categoryMetrics = categories.get(category);
        return metricValues.get(id).stream()
                .filter(v -> categoryMetrics.contains(v.getName()))
                .collect(Collectors.toList());
    }
    // ----------------------------------------------------------------------
    // Operations/configuration
    // ----------------------------------------------------------------------

    public Metric addMetric(String name) {
        metrics.computeIfAbsent(name, par -> SciToolsMetric.of(name));
        categories.get(ROOT).add(name);
        SciToolsMetric metric = (SciToolsMetric) metrics.get(name);
        return metric;
    }

    public void addCategory(String name, Collection<String> metrics) {
        if (!ROOT.equals(name))
            categories.put(name, new TreeSet<>(metrics));
        metrics.forEach(metric -> {
            this.metrics.computeIfAbsent(metric, par -> SciToolsMetric.of(metric));
        });
    }

    public void addMetricValue(String id, SciToolsMetric metric, String name, String kname, float value) {
        SciToolsMetricValue metricValue = SciToolsMetricValue.of(metric, value);
        objects.computeIfAbsent(id, par -> SciToolsObject.of(id, name, kname));
        metricValues.computeIfAbsent(id, par -> new ArrayList<>());
        metricValues.get(id).add(metricValue);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
