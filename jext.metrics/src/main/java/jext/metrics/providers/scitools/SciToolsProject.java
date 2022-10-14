package jext.metrics.providers.scitools;

import jext.logging.Logger;
import jext.metrics.MetricValue;
import jext.metrics.MetricsException;
import jext.metrics.MetricsProject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SciToolsProject extends SciToolsObject implements MetricsProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsProject.class);

    private final SciToolsProvider provider;

    private File metricsValues;
    private File metricsNodes;
    private File metricsEdges;

    private final Map<String, List<MetricValue>> metricValues = new TreeMap<>();
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
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Collection<MetricValue> getMetricValues(String id) {
        return metricValues.getOrDefault(id, Collections.emptyList());
    }

    @Override
    public Collection<MetricValue> getMetricValues(String id, String category) {
        if (!metricValues.containsKey(id))
            return Collections.emptyList();
        if (!provider.hasCategory(category))
            return Collections.emptyList();

        Set<String> categoryMetrics = provider.getMetricNames(category);
        return metricValues.get(id).stream()
                .filter(v -> categoryMetrics.contains(v.getName()))
                .collect(Collectors.toList());
    }
    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    void initialize() {
        loadData();
        loadHierarchy();
    }

    private void loadData() {
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
                    float value = Float.parseFloat(parts[4]);
                    String name = parts[1];
                    String kname = parts[2];
                    SciToolsMetric metric = (SciToolsMetric) provider.getMetric(parts[3]);
                    addMetricValue(id, metric, name, kname, value);

                    if (count % 1000 == 0)
                        logger.debugft("... %d metrics", count);
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

    private void addMetricValue(String id, SciToolsMetric metric, String name, String kname, float value) {
        SciToolsMetricValue metricValue = SciToolsMetricValue.of(metric, value);
        objects.computeIfAbsent(id, par -> SciToolsObject.of(id, name, kname));
        metricValues.computeIfAbsent(id, par -> new ArrayList<>());
        metricValues.get(id).add(metricValue);
    }

    private void loadHierarchy() {
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

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
