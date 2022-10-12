package jext.metrics.providers.scitools;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsException;
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

public class SciToolsAllMetrics {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsMetric.class);

    private static final String ROOT = "";

    private final Map<String, Metric> metricsById = new TreeMap<>();
    private final Map<String, Metric> metricsByName = new TreeMap<>();
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

    public Collection<String> getCategories() {
        return categories.keySet();
    }

    public Collection<Metric> getMetrics() {
        return getMetrics(ROOT);
    }

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

    public Metric getMetric(String name) {
        if (metricsById.containsKey(name))
            return metricsById.get(name);
        if (metricsByName.containsKey(name))
            return metricsByName.get(name);

        logger.errorf("Unknown metric '%s'", name);
        Metric metric = SciToolsMetric.of(name, name, "", "");
        addMetric(metric);
        return metric;
    }

    // ----------------------------------------------------------------------
    // Metric Values
    // ----------------------------------------------------------------------

    public Collection<MetricValue> getMetricValues(String id) {
        if (!metricValues.containsKey(id))
            return Collections.emptyList();
        else
            return metricValues.get(id);
    }

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

    public void addMetric(Metric metric) {
        metricsById.put(metric.getId(), metric);
        metricsByName.put(metric.getName(), metric);
    }

    public void addCategory(String name, Collection<String> metrics) {
        if (!ROOT.equals(name))
            categories.put(name, new TreeSet<>(metrics));
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
