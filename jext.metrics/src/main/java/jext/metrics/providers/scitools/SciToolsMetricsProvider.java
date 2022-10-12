package jext.metrics.providers.scitools;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsException;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class SciToolsMetricsProvider implements MetricsProvider {

    private static final String NAME = "scitools";
    private static final String PROPERTY_FILE = "file";

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsMetricsProvider.class);

    private Properties properties;
    private File metricsFile;
    private SciToolsAllMetrics allMetrics;


    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SciToolsMetricsProvider() {

    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    @Override
    public void initialize(Properties properties) {
        this.properties = properties;
        String metricsFile = properties.getProperty(PROPERTY_FILE);
        if (metricsFile == null)
            throw new MetricsException("Missing 'file' property");

        this.allMetrics = new SciToolsAllMetrics();
        this.metricsFile = new File(metricsFile);
        loadMetrics();
        loadCategories();
        loadFromFile();
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
        return allMetrics.getCategories();
    }

    @Override
    public Collection<Metric> getMetrics() {
        return allMetrics.getMetrics();
    }

    @Override
    public Collection<Metric> getMetrics(String category) {
        return allMetrics.getMetrics(category);
    }

    @Override
    public Metric getMetric(String name) {
        return allMetrics.getMetric(name);
    }

    @Override
    public Collection<MetricValue> getMetricValues(String id) {
        return allMetrics.getMetricValues(id);
    }

    @Override
    public Collection<MetricValue> getMetricValues(String id, String category) {
        return allMetrics.getMetricValues(id, category);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------
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
                allMetrics.addMetric(metric);
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

                allMetrics.addCategory(category, metrics);
            });
        }
        catch(IOException | SAXException | ParserConfigurationException e) {
            // in theory never happen
            logger.error(e.getMessage());
        }
    }

    private void loadFromFile() {
        try(LineNumberReader rdr = new LineNumberReader(new FileReader(this.metricsFile))) {
            // skip header
            String line = rdr.readLine();
            int count = 1;
            while((line = rdr.readLine()) != null) {
                count += 1;

                // 0  1    2     3   4
                // id,name,kname,key,value
                String[] parts = line.split(",");
                try {
                    String id = parts[0];
                    float value = Float.parseFloat(parts[4]);
                    String name = parts[1];
                    String kname = parts[2];
                    SciToolsMetric metric = (SciToolsMetric) allMetrics.getMetric(parts[3]);
                    allMetrics.addMetricValue(id, metric, name, kname, value);

                    if (count % 1000 == 0)
                        logger.debugft("... %d metrics", count);
                }
                catch (NumberFormatException e) {
                    logger.errorf("Number format exception on line %d on value %s", count, parts[4]);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
