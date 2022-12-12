package jext.metrics.providers.scitools;

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
import jext.util.PathUtils;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.annotation.Nullable;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SciToolsProvider implements MetricsProvider {

    static final String NAME = "scitools";
    static final String PROJECT_NAME = "scitools.name";
    static final String PROJECT_HOME = "scitools.project.home";
    static final String METRICS_HOME = "scitools.metrics.home";
    static final String METRICS_REVISION = "scitools.metrics.revision";
    static final String GENERIC_CATEGORY = "generic";
    static final String DEFAULT_TYPE = "count";

    static final String METRICS_VALUES = "scitools.metrics.values";
    static final String METRICS_NODES  = "scitools.metrics.nodes";
    static final String METRICS_EDGES  = "scitools.metrics.edges";
    static final String METRICS_IDMAPS = "scitools.metrics.idmaps";

    private static final String TEMPLATE_METRICS_VALUES = "%s/scitools-metrics-r%02d.csv";
    private static final String TEMPLATE_METRICS_NODES  = "%s/scitools-metrics-nodes-r%02d.csv";
    private static final String TEMPLATE_METRICS_EDGES  = "%s/scitools-metrics-edges-r%02d.csv";
    private static final String TEMPLATE_METRICS_IDMAPS = "%s/scitools-idmaps-r%02d.csv";

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsProvider.class);

    private Properties properties;

    private final Map<String, Metric> metricsById = new TreeMap<>();
    private final Map<String, Metric> metricsByName = new TreeMap<>();
    private final Map<String, MetricsCategory> categories = new DefaultHashMap<>((name) -> new MetricsCategory(this, name));

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SciToolsProvider() {
        // none to do
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    @Override
    public void initialize(Properties properties) {
        this.properties = properties;

        validate();
        loadMetrics();
        loadCategories();
    }

    private void validate() {
        if (properties.containsKey(METRICS_HOME)) {
            Assert.notNull(properties.getProperty(METRICS_HOME), METRICS_HOME);
            Assert.notNull(properties.getProperty(METRICS_REVISION), METRICS_REVISION);

            String root = properties.getProperty(METRICS_HOME);
            int rev = Integer.parseInt(properties.getProperty(METRICS_REVISION));

            properties.put(METRICS_VALUES, String.format(TEMPLATE_METRICS_VALUES, root, rev));
            properties.put(METRICS_NODES, String.format(TEMPLATE_METRICS_NODES, root, rev));
            properties.put(METRICS_EDGES, String.format(TEMPLATE_METRICS_EDGES, root, rev));
            properties.put(METRICS_IDMAPS, String.format(TEMPLATE_METRICS_IDMAPS, root, rev));
        }
        {
            Assert.notNull(properties.getProperty(METRICS_VALUES), METRICS_VALUES);
            Assert.notNull(properties.getProperty(METRICS_NODES), METRICS_NODES);
            Assert.notNull(properties.getProperty(METRICS_EDGES), METRICS_EDGES);
        }
        {
            registerCategory(new MetricsCategory(this, ALL_METRICS));
        }
        if (!properties.containsKey(PROJECT_HOME))
        {
            File projectHome = new File(properties.getProperty(METRICS_HOME)).getParentFile().getParentFile();
            String phome = FileUtils.getAbsolutePath(projectHome);
            properties.setProperty(PROJECT_HOME, phome);
        }
        else {
            String phome = properties.getProperty(PROJECT_HOME);
            properties.setProperty(PROJECT_HOME, PathUtils.normalize(phome));
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
    //     return categories.get(category).getMetrics();
    // }

    @Override
    public Metric getMetric(String nameOrId) {
        if (metricsById.containsKey(nameOrId))
            return metricsById.get(nameOrId);
        if (metricsByName.containsKey(nameOrId))
            return metricsByName.get(nameOrId);

        logger.errorf("Unknown metric '%s'", nameOrId);
        Metric metric = new SciToolsMetric(this, nameOrId, nameOrId, DEFAULT_TYPE, "");
        addMetric(GENERIC_CATEGORY, metric);
        return metric;
    }

    @Override
    public List<ObjectType> getSupportedTypes() {
        return Arrays.asList(ObjectType.PROJECT, ObjectType.SOURCE, ObjectType.TYPE, ObjectType.METHOD);
    }

    // ----------------------------------------------------------------------
    // Project
    // ----------------------------------------------------------------------

    @Override
    public MetricsProject getProject() {
        String projectName = getProjectName();
        SciToolsProject project = new SciToolsProject(projectName, this);
        project.initialize();
        return project;
    }

    String getProjectHome() {
        return properties.getProperty(PROJECT_HOME);
    }

    private String getProjectName() {
        if (properties.containsKey(PROJECT_NAME))
            return properties.getProperty(PROJECT_NAME);

        File file = new File(properties.getProperty(METRICS_VALUES));
        String path = file.getAbsolutePath().replace('\\', '/');
        if (path.contains("/.spl")) {
            int p = path.lastIndexOf("/.spl");
            file = new File(path.substring(0, p));
        }
        else {
            file = file.getParentFile();
        }
        return file.getName();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    String getProperty(String key) {
        return properties.getProperty(key);
    }

    Set<String> getMetricNames(String category) {
        return categories.get(category).getMetricNames();
    }

    /*
            <metric id="CountDeclPropertyAuto" name="Auto Implemented Properties" type="count">
            Number of auto-implemented properties.
            </metric>
     */
    private void loadMetrics() {
        try(InputStream stream = MetricsProviders.class.getResourceAsStream("scitoolsmetrics.xml")) {
            Element root = XPathUtils.parse(stream).getDocumentElement();
            XPathUtils.selectElements(root, "metrics/metric").forEach(elt -> {
                String id = XPathUtils.getValue(elt, "@id");
                String name = XPathUtils.getValue(elt, "@name");
                String category = XPathUtils.getValue(elt, "@category", GENERIC_CATEGORY);
                String type = XPathUtils.getValue(elt, "@type", DEFAULT_TYPE);
                String description = XPathUtils.getValue(elt, "#text");

                Metric metric = new SciToolsMetric(this, id, name, type, description);
                addMetric(category, metric);
            });
        }
        catch(IOException | SAXException | ParserConfigurationException e) {
            // in theory never happen
            logger.error(e.getMessage());
        }
    }

    private void loadCategories() {
        try(InputStream stream = MetricsProviders.class.getResourceAsStream("scitoolsmetrics.xml")) {
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
    // Operations/configuration
    // ----------------------------------------------------------------------

    public void registerCategory(MetricsCategory category) {
        categories.put(category.getName(), category);
    }

    void addMetric(String category, Metric metric) {
        metricsById.put(metric.getId(), metric);
        metricsByName.put(metric.getName(), metric);
        categories.get(ALL_METRICS).add(metric.getId());
        categories.get(category).add(metric.getId());
    }



    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
