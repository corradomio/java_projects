package jext.metrics.providers.sonarqube;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.Assert;
import jext.util.JSONUtils;
import jext.util.MapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SonarQubeProvider implements MetricsProvider {

    private static final Logger logger = Logger.getLogger(SonarQubeProvider.class);

    private static final String ROOT = "";
    private static final String NAME = "scitools";
    // used also for token
    private static final String PROPERTY_USERNAME = "username";
    // with token, password must be the empty string
    private static final String PROPERTY_PASSWORD = "password";

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private Properties properties;
    private final Map<String, Set<String>> categories = new TreeMap<>();
    private Map<String, Metric> metricsById = new TreeMap<>();
    private Map<String, Metric> metricsByName = new TreeMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SonarQubeProvider() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void initialize(Properties properties) {
        this.properties = properties;

        loadCategories();
        loadMetrics();
    }

    private void loadCategories() {

    }

    private void loadMetrics() {
        Map<String, Object> sonarmetrics = null;
        try(InputStream stream = MetricsProviders.class.getResourceAsStream("sonarmetrics.json")) {
            sonarmetrics = JSONUtils.parse(stream, HashMap.class);
        }
        catch(IOException e) {
            // in theory never happen
            logger.error(e.getMessage());
        }
        if (sonarmetrics == null)
            return;

        /*
            {
                "id": "AYM2RxnE4wa9zTX5sTDz",
                "key": "new_technical_debt",
                "type": "WORK_DUR",
                "name": "Added Technical Debt",
                "description": "Added technical debt",
                "domain": "Maintainability",
                "direction": -1,
                "qualitative": true,
                "hidden": false
            },
         */

        List<Map<String, Object>> metrics = MapUtils.get(sonarmetrics, "metrics");
        metrics.forEach(data -> {
            boolean hidden = MapUtils.get(data,"hidden");
            if (hidden)
                return;

            String id = MapUtils.get(data,"key");
            String domain = MapUtils.get(data,"domain");
            Metric metric = SonarQubeMetric.of(data);
            addMetric(metric);
            addMetricToCategory(ROOT, metric);
            addMetricToCategory(domain, metric);
        });

    }

    private void addMetricToCategory(String category, Metric metric) {
        categories.computeIfAbsent(category, par -> new TreeSet<>());
        categories.get(category).add(metric.getId());
    }

    private void addMetric(Metric metric) {
        metricsById.put(metric.getId(), metric);
        metricsByName.put(metric.getName(), metric);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public Collection<String> getCategories() {
        return categories.keySet();
    }

    @Override
    public Collection<Metric> getMetrics() {
        return metricsById.values();
    }

    @Override
    public Collection<Metric> getMetrics(String category) {
        Assert.verify(category != null, "category is null");
        if (!categories.containsKey(category))
            return Collections.emptyList();
        return categories.get(category).stream()
                .map(this::getMetric)
                .collect(Collectors.toList());
    }

    @Override
    public Metric getMetric(String name) {
        if (metricsById.containsKey(name))
            return metricsById.get(name);
        if (metricsByName.containsKey(name))
            return metricsByName.get(name);

        logger.errorf("Unknown metric '%s'", name);
        Metric metric = SonarQubeMetric.of(MapUtils.asMap(
                "key", name,
                "name", name,
                "descrition", ""
        ));
        addMetric(metric);
        return metric;
    }

    @Override
    public Collection<MetricValue> getMetricValues(String id) {
        return Collections.emptyList();
    }

    @Override
    public Collection<MetricValue> getMetricValues(String id, String category) {
        return Collections.emptyList();
    }
}
