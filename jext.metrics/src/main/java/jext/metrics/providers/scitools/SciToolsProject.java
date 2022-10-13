package jext.metrics.providers.scitools;

import jext.logging.Logger;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
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

public class SciToolsProject implements MetricsProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsProject.class);

    private final File metricsFile;
    private final SciToolsProvider provider;

    private final Map<String, List<MetricValue>> metricValues = new TreeMap<>();
    private final Map<String, SciToolsObject> objects = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SciToolsProject(File metricsFile, SciToolsProvider provider) {
        this.metricsFile = metricsFile;
        this.provider = provider;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return "0";
    }

    @Override
    public String getName() {
        return metricsFile.getName();
    }

    @Override
    public boolean hasChildren() {
        return true;
    }

    @Override
    public List<MetricsComponent> getChildren() {
        return Collections.emptyList();
    }

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

    void loadData() {
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
    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
