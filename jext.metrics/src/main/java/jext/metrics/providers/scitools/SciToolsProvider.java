package jext.metrics.providers.scitools;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.Assert;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SciToolsProvider implements MetricsProvider {

    static final String ROOT = "";
    static final String NAME = "scitools";
    static final String PROJECT_NAME = "scitools.name";
    static final String METRICS_ROOT = "scitools.metrics.root";
    static final String METRICS_REVISION = "scitools.metrics.revision";
    static final String METRICS_VALUES = "scitools.metrics.values";
    static final String METRICS_NODES = "scitools.metrics.nodes";
    static final String METRICS_EDGES = "scitools.metrics.edges";

    private static final String TEMPLATE_METRICS_VALUES = "%s/scitools-metrics-r%02d.csv";
    private static final String TEMPLATE_METRICS_NODES = "%s/scitools-metrics-nodes-r%02d.csv";
    private static final String TEMPLATE_METRICS_EDGES = "%s/scitools-metrics-edges-r%02d.csv";

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsProvider.class);

    private Properties properties;

    private final Map<String, Metric> metricsById = new TreeMap<>();
    private final Map<String, Metric> metricsByName = new TreeMap<>();
    private final Map<String, Set<String>> categories = new TreeMap<>();

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
        if (properties.containsKey(METRICS_ROOT)) {
            Assert.notNull(properties.getProperty(METRICS_ROOT), METRICS_ROOT);
            Assert.notNull(properties.getProperty(METRICS_REVISION), METRICS_REVISION);

            String root = properties.getProperty(METRICS_ROOT);
            int rev = Integer.parseInt(properties.getProperty(METRICS_REVISION));

            properties.put(METRICS_VALUES, String.format(TEMPLATE_METRICS_VALUES, root, rev));
            properties.put(METRICS_NODES, String.format(TEMPLATE_METRICS_NODES, root, rev));
            properties.put(METRICS_EDGES, String.format(TEMPLATE_METRICS_EDGES, root, rev));
        }
        {
            Assert.notNull(properties.getProperty(METRICS_VALUES), METRICS_VALUES);
            Assert.notNull(properties.getProperty(METRICS_NODES), METRICS_NODES);
            Assert.notNull(properties.getProperty(METRICS_EDGES), METRICS_EDGES);
        }
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
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
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
    // Project
    // ----------------------------------------------------------------------

    @Override
    public MetricsProject getProject() {
        String projectName = getProjectName();
        SciToolsProject project = new SciToolsProject(projectName, this);
        project.initialize();
        return project;
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
        return categories.get(category);
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
                String type = XPathUtils.getValue(elt, "@type", "count");
                String description = XPathUtils.getValue(elt, "#text");

                Metric metric = SciToolsMetric.of(id, name, type, description);
                addMetric(metric);
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
            XPathUtils.selectElements(root, "category").forEach(cat -> {
                String category = XPathUtils.getValue(cat, "@name");
                List<String> metrics = StringUtils.split(cat.getTextContent(), ",");

                addCategory(category, metrics);
            });
        }
        catch(IOException | SAXException | ParserConfigurationException e) {
            // in theory never happen
            logger.error(e.getMessage());
        }
    }

    // private void loadFromFile() {
    //     try(LineNumberReader rdr = new LineNumberReader(new FileReader(this.metricsFile))) {
    //         // skip header
    //         String line = rdr.readLine();
    //         int count = 1;
    //         while((line = rdr.readLine()) != null) {
    //             count += 1;
    //
    //             // 0  1    2     3   4
    //             // id,name,kname,key,value
    //             String[] parts = line.split(",");
    //             try {
    //                 String id = parts[0];
    //                 float value = Float.parseFloat(parts[4]);
    //                 String name = parts[1];
    //                 String kname = parts[2];
    //                 SciToolsMetric metric = (SciToolsMetric) getMetric(parts[3]);
    //                 addMetricValue(id, metric, name, kname, value);
    //
    //                 if (count % 1000 == 0)
    //                     logger.debugft("... %d metrics", count);
    //             }
    //             catch (NumberFormatException e) {
    //                 logger.errorf("Number format exception on line %d on value %s", count, parts[4]);
    //             }
    //         }
    //     }
    //     catch (IOException e) {
    //         throw new MetricsException(e);
    //     }
    // }

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

    // public void addMetricValue(String id, SciToolsMetric metric, String name, String kname, float value) {
    //     SciToolsMetricValue metricValue = SciToolsMetricValue.of(metric, value);
    //     objects.computeIfAbsent(id, par -> SciToolsObject.of(id, name, kname));
    //     metricValues.computeIfAbsent(id, par -> new ArrayList<>());
    //     metricValues.get(id).add(metricValue);
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
