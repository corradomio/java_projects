package jext.metrics.providers.scitools;

import jext.metrics.ComponentType;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import jext.metrics.MetricsProject;
import jext.util.MapUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SciToolsObject implements MetricsComponent {

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
    private final List<MetricsComponent> children = new ArrayList<>();
    private final List<MetricValue> metricValues = new ArrayList<>();

    private static final Map<String, ComponentType> TYPES;
    static {
        TYPES = new HashMap<>();
        TYPES.put("File", ComponentType.FILE);

        TYPES.put("Type", ComponentType.TYPE);
        TYPES.put("Class", ComponentType.TYPE);
        TYPES.put("Interface", ComponentType.TYPE);
        TYPES.put("Struct", ComponentType.TYPE);
        TYPES.put("Enum", ComponentType.TYPE);

        TYPES.put("Method", ComponentType.METHOD);
        TYPES.put("Indexer", ComponentType.METHOD);
        TYPES.put("Constructor", ComponentType.METHOD);
        TYPES.put("Finalizer", ComponentType.METHOD);
        TYPES.put("Static", ComponentType.METHOD);

        TYPES.put("Delegate", ComponentType.FIELD);
        TYPES.put("Property", ComponentType.FIELD);
        TYPES.put("Event", ComponentType.FIELD);
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
    public ComponentType getType() {
        // if (kname.contains("File"))
        //     return ComponentType.FILE;
        //
        // if (kname.contains("Type"))
        //     return ComponentType.TYPE;
        // if (kname.contains("Class"))
        //     return ComponentType.TYPE;
        // if (kname.contains("Interface"))
        //     return ComponentType.TYPE;
        // if (kname.contains("Struct"))
        //     return ComponentType.TYPE;
        // if (kname.contains("Enum"))
        //     return ComponentType.TYPE;
        //
        // if (kname.contains("Method"))
        //     return ComponentType.METHOD;
        // if (kname.contains("Indexer"))
        //     return ComponentType.METHOD;
        // if (kname.contains("Constructor"))
        //     return ComponentType.METHOD;
        // if (kname.contains("Static"))
        //     return ComponentType.METHOD;
        // if (kname.contains("Finalizer"))
        //     return ComponentType.METHOD;
        //
        // if (kname.contains("Delegate"))
        //     return ComponentType.FIELD;
        // if (kname.contains("Property"))
        //     return ComponentType.FIELD;
        // if (kname.contains("Event"))
        //     return ComponentType.FIELD;
        // else
        //     return ComponentType.UNKNOWN;

        for (String k : TYPES.keySet())
            if (kname.contains(k))
                return TYPES.get(k);

        return ComponentType.UNKNOWN;
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
    public List<MetricsComponent> getChildren() {
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
