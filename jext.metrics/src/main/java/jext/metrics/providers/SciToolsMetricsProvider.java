package jext.metrics.providers;

import jext.logging.Logger;
import jext.metrics.AllMetrics;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsProviders;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.util.List;

public class SciToolsMetricsProvider implements MetricsProvider {

    private static final String NAME = "scitools";

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsMetricsProvider.class);

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

    public void setMetricsFile(File metricsFile) {
        this.metricsFile = metricsFile;
        this.allMetrics = null;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public AllMetrics loadMetrics() {
        if (allMetrics == null) {
            allMetrics = new SciToolsAllMetrics();
            loadCategories();
            loadFromFile();
        }
        return allMetrics;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public AllMetrics loadMetrics(File metricsFile) {
        setMetricsFile(metricsFile);
        return loadMetrics();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void loadCategories() {
        try(InputStream stream = MetricsProviders.class.getResourceAsStream("scitoolscategories.xml")) {
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
                    SciToolsMetric metric = (SciToolsMetric) allMetrics.addMetric(parts[3]);
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
