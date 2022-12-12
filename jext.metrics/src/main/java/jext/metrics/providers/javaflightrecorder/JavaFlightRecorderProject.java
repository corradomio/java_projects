package jext.metrics.providers.javaflightrecorder;

import jext.logging.Logger;
import jext.metrics.*;
import jext.metrics.providers.scitools.*;
import jext.util.Assert;
import jext.util.JSONUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

public class JavaFlightRecorderProject extends JavaFlightRecorderObject implements MetricsProject {
    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(JavaFlightRecorderProject.class);

    private static final Set<ObjectType> SUPPORTED_TYPES = new HashSet<>(){{
        add(ObjectType.TYPE);
        add(ObjectType.METHOD);
    }};

    private final Map<String, JavaFlightRecorderObject> objectsById = new HashMap<>();
    private final Map<String, JavaFlightRecorderObject> objectsByName = new HashMap<>();
   // private final IdMaps idmaps = new IdMaps();

    private final File typeNodesFile;

    private final File methodNodesFile;
    private final File methodMetricsFile;
    private final File typeMetricsFile;
    private final File edgesFile;
    //private final File idmapsFile;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    JavaFlightRecorderProject(String name, JavaFlightRecorderProvider provider) {
        super(null, "0", name, name, "project");
        this.provider = provider;
        this.objectsById.put(this.getId(), this);
        this.objectsByName.put(name, this);
        this.project = this;

        this.typeNodesFile =  new File(provider.getProperty(JavaFlightRecorderProvider.TYPES_NODES));
        this.methodNodesFile = new File(provider.getProperty(JavaFlightRecorderProvider.METHODS_NODES));
        this.methodMetricsFile = new File(provider.getProperty(JavaFlightRecorderProvider.METRICS_METHODS_VALUES));
        this.typeMetricsFile = new File(provider.getProperty(JavaFlightRecorderProvider.METRICS_TYPES_VALUES));
        this.edgesFile = new File(provider.getProperty(JavaFlightRecorderProvider.METRICS_EDGES_VALUES));
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
        MetricsObjects metricsObjects = new JavaFlightRecorderObjects(type);

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
        MetricsValues metricsValues = new JavaFlightRecorderValues();

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
            loadTypeNodes();
            loadMethodNodes();
            loadTypeMetrics();
            loadMethodMetrics();
            //loadEdgeMetrics();
        }
        logger.info("done");
    }

    private boolean metricsAvailable() {
        return typeNodesFile.exists()
                && methodNodesFile.exists()
                && typeMetricsFile.exists()
                && methodMetricsFile.exists()
                && edgesFile.exists();
    }

    private void loadTypeNodes(){
        logger.debugf("... load type nodes from %s", typeNodesFile);
        List<Map<String, Object>> types;

        try {
            types = (List<Map<String, Object>>) JSONUtils.load(typeNodesFile, List.class);
        } catch (IOException e) {
            logger.errorf("Unable to load type nodes from %s",typeNodesFile.getAbsolutePath());
            throw new MetricsException(e);
        }

        for(Map<String, Object> type: types){
            String typeId = type.get("id").toString();
            String name = type.get("name").toString();
            String longname = type.get("longname").toString();
            String oType = type.get("type").toString();

            JavaFlightRecorderObject object = JavaFlightRecorderObject.of(this, typeId, name, longname, oType);
            objectsById.put(object.getId(), object);
            objectsByName.put(object.getLongname(), object);
        }
    }

    private void loadTypeMetrics() {
        // File metricsFile = new File(provider.getProperty(SciToolsProvider.METRICS_VALUES));
        logger.debugf("... load metrics from %s", typeMetricsFile);

        Map<String, Map<String, Object>> nodeMetrics;
        try {
            nodeMetrics = JSONUtils.load(typeMetricsFile, Map.class);
        } catch (IOException e) {
            logger.errorf("Unable to load method metrics from %s",methodMetricsFile.getAbsolutePath());
            throw new MetricsException(e);
        }

        nodeMetrics.forEach((id, metricsMap) -> {
            JavaFlightRecorderObject object = objectsById.get(id);
            if (object == null) {
                return;
            }
            Set<String> metrics = metricsMap.keySet();
            metrics.forEach(metricName -> {
                JavaFlightRecorderMetric metric = (JavaFlightRecorderMetric) provider.getMetric(metricName);
                if (metric == null) {
                    logger.errorf("Unknown metric %s", metricName);
                    return;
                }
                JavaFlightRecorderMetricValue metricValue = JavaFlightRecorderMetricValue.of(object, metric, Float.parseFloat(metricsMap.get(metricName).toString()));
                object.addMetricValue(metricValue);
            });
        });
    }

    private void loadMethodNodes(){
        logger.debugf("... load nodes from %s", methodNodesFile);
        List<Map<String, Object>> methods;

        try {
            methods = (List<Map<String, Object>>) JSONUtils.load(methodNodesFile, List.class);
        } catch (IOException e) {
            logger.errorf("Unable to load method nodes from %s",methodNodesFile.getAbsolutePath());
            throw new MetricsException(e);
        }

        for(Map<String, Object> method: methods){
            String methodId = method.get("id").toString();
            String name = method.get("name").toString();
            String longname = method.get("longname").toString();
            String oType = method.get("type").toString();
            String parentId = method.get("parent").toString(); //ID of Parent type
            JavaFlightRecorderObject parentType = objectsById.getOrDefault(parentId, this);

            JavaFlightRecorderObject object = JavaFlightRecorderObject.of(parentType, methodId, name, longname, oType);
            objectsById.put(object.getId(), object);
            objectsByName.put(object.getLongname(), object);
        }
    }
    private void loadMethodMetrics() {
        // File metricsFile = new File(provider.getProperty(SciToolsProvider.METRICS_VALUES));
        logger.debugf("... load metrics from %s", methodMetricsFile);

        Map<String, Map<String, Object>> nodeMetrics;
        try {
             nodeMetrics = JSONUtils.load(methodMetricsFile, Map.class);
        } catch (IOException e) {
            logger.errorf("Unable to load method metrics from %s",methodMetricsFile.getAbsolutePath());
            throw new MetricsException(e);
        }

        nodeMetrics.forEach((id, metricsMap) -> {
            JavaFlightRecorderObject object = objectsById.get(id);
            if (object == null) {
                   return;
            }
            Set<String> metrics = metricsMap.keySet();
            metrics.forEach(metricName -> {
                JavaFlightRecorderMetric metric = (JavaFlightRecorderMetric) provider.getMetric(metricName);
                if (metric == null) {
                    logger.errorf("Unknown metric %s", metricName);
                    return;
                }
                JavaFlightRecorderMetricValue metricValue = JavaFlightRecorderMetricValue.of(object, metric, Float.parseFloat(metricsMap.get(metricName).toString()));
                object.addMetricValue(metricValue);
            });
        });
    }

//    private void loadIdMaps() {
//        // File idmapsFile = new File(provider.getProperty(SciToolsProvider.METRICS_IDMAPS));
//        if (!idmapsFile.exists())
//            return;
//        else
//            logger.debugf("... load idmaps from %s", idmapsFile);
//
//        // 0    1    2
//        // type,eid,nid
//        try(LineNumberReader rdr = new LineNumberReader(new FileReader(idmapsFile))) {
//            // skip header
//            String line = rdr.readLine();
//            int count = 1;
//            while ((line = rdr.readLine()) != null) {
//                count += 1;
//
//                // 0    1    2
//                // type,eid,nid
//                String[] parts = line.split(",");
//                try {
//                    String type = parts[0];
//                    String eid = parts[1];
//                    String nid = parts[2];
//
//                    // type -> Neo4J id -> SciTools id
//                    idmaps.get(toType(type)).put(nid, eid);
//                }
//                catch (NumberFormatException e) {
//                    logger.errorf("Number format exception on line %d on value %s", count, parts[4]);
//                }
//
//            }
//        }
//        catch (IOException e) {
//            throw new MetricsException(e);
//        }
//    }

    JavaFlightRecorderObject getObjectByName(String longname) {
        return objectsByName.get(longname);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
