package jext.metrics.providers.sonarqube;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.Assert;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import org.sonar.wsclient.SonarClient;

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

public class SonarProvider implements MetricsProvider {

    private static final Logger logger = Logger.getLogger(SonarProvider.class);

    private static final String ROOT = "";
    private static final String NAME = "sonarqube";
    private static final String SONAR_NAME = "sonar.name";
    private static final String SONAR_URL = "sonar.url";
    // used also for token
    private static final String SONAR_USERNAME = "sonar.username";
    // with token, password must be the empty string
    private static final String SONAR_PASSWORD = "sonar.password";

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

    public SonarProvider() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void initialize(Properties properties) {
        this.properties = properties;

        validate();
        loadCategories();
        loadMetrics();
    }

    private void validate() {
        Assert.notNull(properties.getProperty(SONAR_URL), SONAR_URL);
        Assert.notNull(properties.getProperty(SONAR_USERNAME), SONAR_USERNAME);
        Assert.notNull(properties.getProperty(SONAR_PASSWORD), SONAR_PASSWORD);
        Assert.notNull(properties.getProperty(SONAR_NAME), SONAR_NAME);
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

            // skip hidden metrics
            boolean hidden = MapUtils.get(data,"hidden");
            if (hidden)
                return;

            String id = MapUtils.get(data,"key");
            String domain = MapUtils.get(data,"domain");
            Metric metric = SonarMetric.of(data);
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
    public boolean hasCategory(String category) {
        return categories.containsKey(category);
    }

    @Override
    public Collection<Metric> getMetrics() {
        return metricsById.values();
    }

    @Override
    public Collection<Metric> getMetrics(String category) {
        Assert.notNull(category, "category");
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
        Metric metric = SonarMetric.of(MapUtils.asMap(
                "key", name,
                "name", name,
                "descrition", ""
        ));
        addMetric(metric);
        return metric;
    }

    // ----------------------------------------------------------------------
    // Project
    // ----------------------------------------------------------------------

    @Override
    public MetricsProject getProject() {
        String name = properties.getProperty(SONAR_NAME);
        SonarClient client = connect();
        SonarProject project = new SonarProject(name, this, client);
        project.initialize();
        return project;
    }

    private SonarClient connect() {
        String url = this.getUrl();
        String username = this.getUsername();
        String password = this.getPassword();

        return SonarClient.builder()
                .url(url)
                .login(username)
                .password(password)
                .build();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    String getUrl() {
        return properties.getProperty(SONAR_URL);
    }

    String getUsername() {
        return properties.getProperty(SONAR_USERNAME);
    }

    String getPassword() {
        return properties.getProperty(SONAR_PASSWORD);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
