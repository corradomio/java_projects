package jext.metrics.providers.sonarqube;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricsCategory;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.metrics.ObjectType;
import jext.util.Assert;
import jext.util.DefaultHashMap;
import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import jext.util.PropertiesUtils;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.sonar.wsclient.SonarClient;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class SonarProvider implements MetricsProvider {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    static final String NAME = "sonarqube";

    public static final String SONAR_HOST_URL = "sonar.host.url";
    public static final String WRONG_SONAR_URL_HOST = "sonar.url.host";
    // used also for SonarQube token
    public static final String SONAR_LOGIN = "sonar.login";
    // with token, password must be the empty string
    public static final String SONAR_PASSWORD = "sonar.password";
    public static final String SONAR_PROJECT_HOME = "sonar.projectHome";
    public static final String SONAR_PROJECT_KEY = "sonar.projectKey";

    public static final String SONAR_PROJECT_PROPERTIES = "sonar-project.properties";

    // some metrics to exclude because they don't have a numeric value
    static List<String> INVALID_METRIC_KEYS = Arrays.asList(
            "ncloc_language_distribution",
            "duplications_data",
            "quality_gate_details",
            "alert_status"
    );

    private static final String DEFAULT_AGGREGATE = "";

    private static final String SONAR_METRICS_JSON = "sonarmetrics.json";
    private static final String SONAR_METRICS_XML  = "sonarmetrics.xml";

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SonarProvider.class);

    private final Properties properties = new Properties();
    private final Map<String, MetricsCategory> categories = new DefaultHashMap<>((name) -> new MetricsCategory(this, name));
    private final Map<String, Metric> metricsById = new TreeMap<>();
    private final Map<String, Metric> metricsByName = new TreeMap<>();

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
        this.properties.putAll(properties);

        validate();
        loadMetrics();
        loadCategories();
    }

    private void validate() {
        Assert.notNull(properties.getProperty(SONAR_PROJECT_HOME), SONAR_PROJECT_HOME);
        // Assert.notNull(properties.getProperty(SONAR_URL), SONAR_URL);
        // Assert.notNull(properties.getProperty(SONAR_USERNAME), SONAR_USERNAME);
        // Assert.notNull(properties.getProperty(SONAR_PASSWORD), SONAR_PASSWORD);
        // Assert.notNull(properties.getProperty(SONAR_NAME), SONAR_NAME);

        File sonarProjectFile = new File(properties.getProperty(SONAR_PROJECT_HOME));
        if (sonarProjectFile.isDirectory())
            sonarProjectFile = new File(sonarProjectFile, SONAR_PROJECT_PROPERTIES);
        if (sonarProjectFile.exists()) {
            logger.infof("Loading SonarQube properties from %s", FileUtils.getAbsolutePath(sonarProjectFile));
            properties.putAll(PropertiesUtils.load(sonarProjectFile));
        }
        else {
            logger.warnf("No SonarQube properties file found at %s", FileUtils.getAbsolutePath(sonarProjectFile));
        }

        registerCategory(new MetricsCategory(this, ALL_METRICS));

        if (properties.contains(WRONG_SONAR_URL_HOST) && !properties.contains(SONAR_HOST_URL))
            properties.put(SONAR_HOST_URL, properties.get(WRONG_SONAR_URL_HOST));

        logger.infof("Connected to SonaQube server at %s", properties.getProperty(SONAR_HOST_URL));
    }

    private void loadMetrics() {
        Map<String, Object> sonarmetrics = null;
        try(InputStream stream = MetricsProviders.class.getResourceAsStream(SONAR_METRICS_JSON)) {
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
            registerMetric(metric);
            categories.get(ALL_METRICS).add(metric.getId());
            categories.get(domain).add(metric.getId());
        });

    }

    private void registerMetric(Metric metric) {
        metricsById.put(metric.getId(), metric);
        metricsByName.put(metric.getName(), metric);
    }

    @Override
    public void registerCategory(MetricsCategory category) {
        categories.put(category.getName(), category);
    }

    // ----------------------------------------------------------------------
    // Extra configuration
    // ----------------------------------------------------------------------

    private void loadCategories() {
        setMetricsAggregateMode();
        setCustomCategories();
    }

    private void setMetricsAggregateMode() {
        // set the metric's aggregate mode
        try(InputStream stream = MetricsProviders.class.getResourceAsStream(SONAR_METRICS_XML)) {
            Element root = XPathUtils.parse(stream).getDocumentElement();
            XPathUtils.selectElements(root, "metrics/metric").forEach(elt -> {
                String id = XPathUtils.getValue(elt, "@id");
                String aggregate = XPathUtils.getValue(elt, "@aggregate", DEFAULT_AGGREGATE);

                SonarMetric metric = (SonarMetric) getMetric(id);
                if (metric == null) {
                    logger.errorf("Unknown SonarQube metric %s", id);
                    connect();
                }
                metric.setAggregate(aggregate);
            });
        }
        catch(IOException | SAXException | ParserConfigurationException e) {
            // in theory never happen
            logger.error(e.getMessage());
        }
    }

    private void setCustomCategories() {
        // set the metric's aggregate mode
        try(InputStream stream = MetricsProviders.class.getResourceAsStream(SONAR_METRICS_XML)) {
            Element root = XPathUtils.parse(stream).getDocumentElement();
            XPathUtils.selectElements(root, "categories/category").forEach(cat -> {
                String category = XPathUtils.getValue(cat, "@name");
                String description = XPathUtils.getValue(cat, "description/#text");
                List<String> metrics = StringUtils.split(XPathUtils.getValue(cat, "metrics", ""), "\n");

                // remove invalid metrics
                metrics = metrics.stream()
                        .filter(metric -> metricsById.containsKey(metric) || metricsByName.containsKey(metric))
                        .collect(Collectors.toList());

                MetricsCategory mcat = categories.get(category);
                mcat.setDescription(description);
                categories.get(category).addAll(metrics);
            });
        }
        catch(IOException | SAXException | ParserConfigurationException e) {
            // in theory never happen
            logger.error(e.getMessage());
        }
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
    public MetricsCategory getCategory(String category) {
        if (category == null)
            category = ALL_METRICS;
        if (!categories.containsKey(category))
            return new MetricsCategory(this, category);
        else
            return categories.get(category);
    }

    @Override
    public boolean hasCategory(String category) {
        return categories.containsKey(category);
    }

    @Override
    public Collection<Metric> getMetrics() {
        return metricsById.values();
    }

    // @Override
    // public Collection<Metric> getMetrics(@Nullable String category) {
    //     if (category == null)
    //         category = MetricsProvider.ALL_METRICS;
    //
    //     if (!categories.containsKey(category))
    //         return Collections.emptyList();
    //
    //     return categories.get(category).getMetrics().stream()
    //             .filter(metric -> !INVALID_METRIC_KEYS.contains(metric.getName()))
    //             .collect(Collectors.toList());
    // }

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
        registerMetric(metric);
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
        String name = properties.getProperty(SONAR_PROJECT_KEY);
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
        return properties.getProperty(SONAR_HOST_URL);
    }

    String getUsername() {
        return properties.getProperty(SONAR_LOGIN);
    }

    String getPassword() {
        return properties.getProperty(SONAR_PASSWORD);
    }

    Logger getLogger() {
        return logger;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
