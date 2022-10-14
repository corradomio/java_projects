package jext.metrics.providers.scitools;

import jext.logging.Logger;
import jext.metrics.MetricsException;
import jext.metrics.MetricsProject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

public class SciToolsProject extends SciToolsObject implements MetricsProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsProject.class);

    private final Map<String, SciToolsObject> objects = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SciToolsProject(String name, SciToolsProvider provider) {
        super("0", name, "");
        this.provider = provider;
        this.objects.put(this.getId(), this);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    void initialize() {
        loadNodes();
        loadEdges();
        loadMeasures();
    }

    private void loadNodes() {
        File nodesFile = new File(provider.getProperty(SciToolsProvider.METRICS_NODES));

        // 0  1    3
        // id,name,type
        try(LineNumberReader rdr = new LineNumberReader(new FileReader(nodesFile))) {
            // skip header
            String line = rdr.readLine();
            int count = 1;
            while((line = rdr.readLine()) != null) {
                String[] parts = line.split(",");

                SciToolsObject object = SciToolsObject.of(parts[0], parts[1], parts[2]);
                objects.put(object.getId(), object);
            }
        }
        catch (IOException e) {
            throw new MetricsException(e);
        }
    }

    private void loadEdges() {
        File edgesFile = new File(provider.getProperty(SciToolsProvider.METRICS_EDGES));

        // 0      1
        // source,target  (child,parent)
        try(LineNumberReader rdr = new LineNumberReader(new FileReader(edgesFile))) {
            // skip header
            String line = rdr.readLine();
            int count = 1;
            while((line = rdr.readLine()) != null) {
                String[] parts = line.split(",");
                SciToolsObject child = objects.get(parts[0]);
                SciToolsObject parent = objects.get(parts[1]);

                if (child == null)
                    logger.errorf("Missing object with id %s", parts[0]);
                if (parent == null)
                    logger.errorf("Missing object with id %s", parts[1]);

                if (child != null && parent != null)
                    child.setParent(parent);
            }
        }
        catch (IOException e) {
            throw new MetricsException(e);
        }
    }

    private void loadMeasures() {
        File metricsFile = new File(provider.getProperty(SciToolsProvider.METRICS_VALUES));

        // 0  1    2     3   4
        // id,name,kname,key,value
        try(LineNumberReader rdr = new LineNumberReader(new FileReader(metricsFile))) {
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
                    String mname = parts[3];
                    float value = Float.parseFloat(parts[4]);

                    SciToolsObject object = objects.get(id);
                    // some object are not useful
                    if (object == null)
                        continue;

                    SciToolsMetric metric = (SciToolsMetric) provider.getMetric(mname);
                    if (metric == null) {
                        logger.errorf("Unknown metric %s", mname);
                        continue;
                    }

                    SciToolsMetricValue metricValue = SciToolsMetricValue.of(metric, value);
                    object.addMetricValue(metricValue);
                }
                catch (NumberFormatException e) {
                    logger.errorf("Number format exception on line %d on value %s", count, parts[4]);
                }
            }
        }
        catch (IOException e) {
            throw new MetricsException(e);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
