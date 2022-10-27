package jext.metrics.providers.scitools;

import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsProject;
import jext.metrics.ObjectType;
import jext.util.MapUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SciToolsObject implements MetricsObject {

    public static SciToolsObject of(String id, String name, String kname) {
        return new SciToolsObject(id, name, kname);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String id;
    private final String name;
    private final String kname;

    protected SciToolsProvider provider;
    protected SciToolsProject project;
    private SciToolsObject parent;
    private final List<MetricsObject> children = new ArrayList<>();
    private final List<MetricValue> metricValues = new ArrayList<>();

    private static final Map<String, ObjectType> TYPES;
    static {
        TYPES = new HashMap<>();
        TYPES.put("File", ObjectType.FILE);

        TYPES.put("Type", ObjectType.TYPE);
        TYPES.put("Class", ObjectType.TYPE);
        TYPES.put("Interface", ObjectType.TYPE);
        TYPES.put("Struct", ObjectType.TYPE);
        TYPES.put("Enum", ObjectType.TYPE);

        TYPES.put("Method", ObjectType.METHOD);
        TYPES.put("Indexer", ObjectType.METHOD);
        TYPES.put("Constructor", ObjectType.METHOD);
        TYPES.put("Finalizer", ObjectType.METHOD);
        TYPES.put("Static", ObjectType.METHOD);

        TYPES.put("Delegate", ObjectType.FIELD);
        TYPES.put("Property", ObjectType.FIELD);
        TYPES.put("Event", ObjectType.FIELD);
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected SciToolsObject(String id, String name, String kname) {
        this.id = id;
        this.name = name;
        this.kname = kname;
    }

    void setParent(SciToolsObject parent) {
        this.parent = parent;
        this.project = parent.project;
        this.provider = parent.provider;
        this.parent.children.add(this);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MetricsProject getProject() {
        return project;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ObjectType getType() {
        for (String k : TYPES.keySet())
            if (kname.contains(k))
                return TYPES.get(k);

        return ObjectType.UNKNOWN;
    }

    @Override
    public Map<String, Object> getData() {
        return MapUtils.asMap(
                "id", getId(),
                "name", getName(),
                "type", getType().toString()
        );
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public List<MetricsObject> getChildren() {
        return children;
    }

    // ----------------------------------------------------------------------
    // MetricValues
    // ----------------------------------------------------------------------

    @Override
    public Collection<MetricValue> getMetricValues() {
        return metricValues;
    }

    @Override
    public Collection<MetricValue> getMetricValues(String category) {
        if (!provider.hasCategory(category))
            return Collections.emptyList();

        Set<String> categoryMetrics = provider.getMetricNames(category);
        return metricValues.stream()
                .filter(v -> categoryMetrics.contains(v.getMetric().getId())
                          || categoryMetrics.contains(v.getMetric().getName()))
                .collect(Collectors.toList());
    }

    void addMetricValue(MetricValue metricValue) {
        this.metricValues.add(metricValue);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
