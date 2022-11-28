package jext.metrics.providers.sonarqube;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.metrics.ObjectType;
import jext.util.Assert;
import jext.util.DefaultHashMap;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import org.sonar.wsclient.SonarClient;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SonarProvider implements MetricsProvider {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    static final String NAME = "sonarqube";

    private static final String SONAR_NAME = "sonar.name";
    private static final String SONAR_URL = "sonar.url";
    // used also for SonarQube token
    private static final String SONAR_USERNAME = "sonar.username";
    // with token, password must be the empty string
    private static final String SONAR_PASSWORD = "sonar.password";

    // some metrics to exclude because they don't have a numeric value
    static List<String> INVALID_METRIC_KEYS = Arrays.asList(
            "ncloc_language_distribution",
            "duplications_data",
            "quality_gate_details",
            "alert_status"
    );

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SonarProvider.class);

    private Properties properties;
    private final Map<String, Set<String>> categories = new DefaultHashMap<>((key) -> new TreeSet<>());
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

        categories.put(ALL_METRICS, new HashSet<>());
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

            String mkey = MapUtils.get(data,"key");
            if (INVALID_METRIC_KEYS.contains(mkey))
                return;

            String domain = MapUtils.get(data,"domain");
            Metric metric = new SonarMetric(this, data);
            addMetric(metric);
            categories.get(ALL_METRICS).add(metric.getId());
            categories.get(domain).add(metric.getId());
        });

    }

    private void addMetric(Metric metric) {
        metricsById.put(metric.getId(), metric);
        metricsByName.put(metric.getName(), metric);
    }

    @Override
    public void registerCategory(String category, Collection<String> metrics) {
        categories.put(category, new HashSet<>(metrics));
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return NAME;
    }

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
    public Collection<Metric> getMetrics(@Nullable String category) {
        if (category == null)
            category = MetricsProvider.ALL_METRICS;

        if (!categories.containsKey(category))
            return Collections.emptyList();

        return categories.get(category).stream()
                .filter(mkey -> !INVALID_METRIC_KEYS.contains(mkey))
                .map(this::getMetric)
                .collect(Collectors.toList());
    }

    @Override
    public Metric getMetric(String nameOrId) {
        if (metricsById.containsKey(nameOrId))
            return metricsById.get(nameOrId);
        if (metricsByName.containsKey(nameOrId))
            return metricsByName.get(nameOrId);

        logger.errorf("Unknown metric '%s'", nameOrId);
        Metric metric = new SonarMetric(this, MapUtils.asMap(
                "key", nameOrId,
                "name", nameOrId,
                "descrition", ""
        ));
        addMetric(metric);
        return metric;
    }

    @Override
    public List<ObjectType> getSupportedTypes() {
        return Arrays.asList(ObjectType.PROJECT, ObjectType.MODULE, ObjectType.SOURCE);
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
