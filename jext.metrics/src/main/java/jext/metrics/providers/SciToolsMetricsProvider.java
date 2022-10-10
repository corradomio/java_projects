package jext.metrics.providers;

import jext.logging.Logger;
import jext.metrics.AllMetrics;
import jext.metrics.Metric;
import jext.metrics.MetricsProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (allMetrics == null)
            loadFromFile();
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

    private void loadFromFile() {
        allMetrics = new SciToolsAllMetrics();

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
                    int id = Integer.parseInt(parts[0]);
                    float value = Float.parseFloat(parts[4]);
                    SciToolsMetric metric = SciToolsMetric.of(parts[1], parts[2], parts[3], value);
                    allMetrics.addMetric(id, metric);

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
