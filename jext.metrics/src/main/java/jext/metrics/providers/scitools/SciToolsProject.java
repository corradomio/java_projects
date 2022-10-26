package jext.metrics.providers.scitools;

import jext.logging.Logger;
import jext.metrics.ComponentType;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import jext.metrics.MetricsException;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.util.BidiMap;
import jext.util.DefaultHashMap;
import jext.util.HashBidiMap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SciToolsProject extends SciToolsObject implements MetricsProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsProject.class);

    private final Map<String, SciToolsObject> objects = new HashMap<>();
    private final Map<String, BidiMap<String, String>> idmaps = new DefaultHashMap<>((key)->new HashBidiMap<>());

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SciToolsProject(String name, SciToolsProvider provider) {
        super("0", name, "");
        this.provider = provider;
        this.objects.put(this.getId(), this);
        this.project = this;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MetricsProvider getProvider() {
        return provider;
    }

    @Override
    public ComponentType getType() {
        return ComponentType.PROJECT;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public Set<Metric> getMetrics() {
        Set<Metric> metrics = new HashSet<>();

        Queue<MetricsComponent> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            MetricsComponent component = queue.remove();
            queue.addAll(component.getChildren());

            component.getMetricValues().forEach(mv -> {
                metrics.add(mv.getMetric());
            });
        }

        return metrics;
    }

    @Override
    public List<MetricValue> getMetricValues(ComponentType type, String category) {
        List<MetricValue> metricValues = new ArrayList<>();

        Queue<MetricsComponent> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            MetricsComponent component = queue.remove();
            queue.addAll(component.getChildren());

            if (component.getType() == type)
                metricValues.addAll(component.getMetricValues(category));
        }
        return metricValues;
    }


    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    void initialize() {
        logger.info("initialize");
        loadNodes();
        loadEdges();
        loadMeasures();
        loadIdMaps();
        logger.info("done");
    }

    private void loadNodes() {
        File nodesFile = new File(provider.getProperty(SciToolsProvider.METRICS_NODES));
        logger.debugf("... load nodes from %s", nodesFile);

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
        logger.debugf("... load edges from %s", edgesFile);

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
        logger.debugf("... load metrics from %s", metricsFile);

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

                    SciToolsMetricValue metricValue = SciToolsMetricValue.of(object, metric, value);
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

    private void loadIdMaps() {
        File idmapsFile = new File(provider.getProperty(SciToolsProvider.METRICS_IDMAPS));
        if (!idmapsFile.exists())
            return;
        else
            logger.debugf("... load idmaps from %s", idmapsFile);

        // 0    1    2
        // type,eid,nid
        try(LineNumberReader rdr = new LineNumberReader(new FileReader(idmapsFile))) {
            // skip header
            String line = rdr.readLine();
            int count = 1;
            while ((line = rdr.readLine()) != null) {
                count += 1;

                // 0    1    2
                // type,eid,nid
                String[] parts = line.split(",");
                try {
                    String type = parts[0];
                    String eid = parts[1];
                    String nid = parts[2];

                    idmaps.get(type).put(eid, nid);
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
