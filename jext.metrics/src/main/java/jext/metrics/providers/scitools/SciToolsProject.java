package jext.metrics.providers.scitools;

import jext.util.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricsException;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsValues;
import jext.metrics.ObjectType;
import jext.util.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SciToolsProject extends SciToolsObject implements MetricsProject {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SciToolsProject.class);

    private static final Set<ObjectType> SUPPORTED_TYPES = new HashSet<>(){{
        add(ObjectType.SOURCE);
        add(ObjectType.TYPE);
        add(ObjectType.METHOD);
        add(ObjectType.PROJECT);
    }};

    private final Map<String, SciToolsObject> objectsById = new HashMap<>();
    private final Map<String, SciToolsObject> objectsByName = new HashMap<>();
    private final IdMaps idmaps = new IdMaps();

    private final File nodesFile;
    private final File edgesFile;
    private final File metricsFile;
    private final File idmapsFile;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SciToolsProject(String name, SciToolsProvider provider) {
        super(null, "0", name, name, "project");
        this.provider = provider;
        this.objectsById.put(this.getId(), this);
        this.objectsByName.put(name, this);
        this.project = this;

        this.nodesFile = new File(provider.getProperty(SciToolsProvider.METRICS_NODES));
        this.edgesFile = new File(provider.getProperty(SciToolsProvider.METRICS_EDGES));
        this.metricsFile = new File(provider.getProperty(SciToolsProvider.METRICS_VALUES));
        this.idmapsFile = new File(provider.getProperty(SciToolsProvider.METRICS_IDMAPS));
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MetricsProvider getProvider() {
        return provider;
    }

    @Override
    public ObjectType getType() {
        return ObjectType.PROJECT;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------
    // MetricsObjects
    // ----------------------------------------------------------------------

    @Override
    public MetricsObjects getMetricsObjects(ObjectType type) {
        MetricsObjects metricsObjects = new SciToolsObjects(type, idmaps);

        if (!validateType(type)) {
            Assert.check(false, String.format("%s is not available as type to search objects", type));
            return metricsObjects;
        }

        Queue<MetricsObject> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            MetricsObject mo = queue.remove();
            queue.addAll(mo.getChildren());

            metricsObjects.add(mo);
        }
        return metricsObjects;
    }

    private static boolean validateType(ObjectType type) {
        return SUPPORTED_TYPES.contains(type);
    }

    // ----------------------------------------------------------------------
    // Metrics/MetricsValues
    // ----------------------------------------------------------------------

    @Override
    public Set<ObjectType> getObjectTypes() {
        return SUPPORTED_TYPES;
    }

    @Override
    public Set<Metric> getMetrics() {
        Set<Metric> metrics = new HashSet<>();

        Queue<MetricsObject> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            MetricsObject mo = queue.remove();
            queue.addAll(mo.getChildren());

            mo.getMetricValues().forEach(mv -> {
                metrics.add(mv.getMetric());
            });
        }

        return metrics;
    }

    @Override
    public Set<Metric> getMetrics(ObjectType otype) {
        Set<Metric> metrics = new HashSet<>();

        Queue<MetricsObject> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            MetricsObject mo = queue.remove();
            queue.addAll(mo.getChildren());

            if (mo.getType() != otype)
                continue;

            mo.getMetricValues().forEach(mv -> {
                metrics.add(mv.getMetric());
            });
        }

        return metrics;
    }

    @Override
    public MetricsValues getMetricsValues(ObjectType type, String category) {
        MetricsValues metricsValues = new SciToolsValues();

        Queue<MetricsObject> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            MetricsObject mo = queue.remove();
            queue.addAll(mo.getChildren());

            if (mo.getType() == type)
                metricsValues.addAll(mo.getMetricValues(category));
        }
        return metricsValues;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    void initialize() {
        logger.info("initialize");

        if (metricsAvailable()) {
            loadNodes();
            loadEdges();
            loadMeasures();
            loadIdMaps();
        }
        logger.info("done");
    }

    private boolean metricsAvailable() {
        return nodesFile.exists()
                && edgesFile.exists()
                && metricsFile.exists()
                && idmapsFile.exists();
    }

    private void loadNodes() {
        // File nodesFile = new File(provider.getProperty(SciToolsProvider.METRICS_NODES));
        logger.debugf("... load nodes from %s", nodesFile);

        // 0  1    2        3
        // id,name,longname,type
        try(LineNumberReader rdr = new LineNumberReader(new FileReader(nodesFile))) {
            // skip header
            String line = rdr.readLine();
            while((line = rdr.readLine()) != null) {
                String[] parts = line.split(",");

                // OLD files!!
                // in the old files it is missing 'fullname'
                // check to avoid a 'ArrayIndexOutOfBoundException'
                if (parts.length < 4)
                    continue;

                SciToolsObject object = SciToolsObject.of(this, parts[0], parts[1], parts[2], parts[3]);
                objectsById.put(object.getId(), object);
                objectsByName.put(object.getLongname(), object);
            }
        }
        catch (IOException e) {
            throw new MetricsException(e);
        }
    }

    private void loadEdges() {
        // File edgesFile = new File(provider.getProperty(SciToolsProvider.METRICS_EDGES));
        logger.debugf("... load edges from %s", edgesFile);

        // 0      1
        // source,target  (child,parent)
        try(LineNumberReader rdr = new LineNumberReader(new FileReader(edgesFile))) {
            // skip header
            String line = rdr.readLine();
            int count = 1;
            while((line = rdr.readLine()) != null) {
                String[] parts = line.split(",");
                SciToolsObject child = objectsById.get(parts[0]);
                SciToolsObject parent = objectsById.get(parts[1]);

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
        // File metricsFile = new File(provider.getProperty(SciToolsProvider.METRICS_VALUES));
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

                    SciToolsObject object = objectsById.get(id);
                    // special handling for the object with id '0' (the project)
                    // some objects are not useful
                    if (object == null) {
                        if (!"0".equals(id))
                            continue;

                        object = new SciToolsObject(this, id, getName(), getName(), "project");
                    }

                    SciToolsMetric metric = (SciToolsMetric) provider.getMetric(mname);
                    if (metric == null) {
                        logger.errorf("Unknown metric '%s'", mname);
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
        // File idmapsFile = new File(provider.getProperty(SciToolsProvider.METRICS_IDMAPS));
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

                    // type -> Neo4J id -> SciTools id
                    idmaps.get(toType(type)).put(nid, eid);
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

    SciToolsObject getObjectByName(String longname) {
        return objectsByName.get(longname);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
