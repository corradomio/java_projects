package jext.metrics.providers.scitools;

import jext.metrics.ComponentType;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
    private SciToolsObject parent;
    private final List<MetricsComponent> children = new ArrayList<>();
    private final List<MetricValue> metricValues = new ArrayList<>();

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
        this.provider = parent.provider;
        this.parent.children.add(this);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

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
        if (kname.contains("File"))
            return ComponentType.FILE;
        if (kname.contains("Type"))
            return ComponentType.TYPE;
        if (kname.contains("Class"))
            return ComponentType.TYPE;
        if (kname.contains("Interface"))
            return ComponentType.TYPE;
        if (kname.contains("Struct"))
            return ComponentType.TYPE;
        if (kname.contains("Enum"))
            return ComponentType.TYPE;
        if (kname.contains("Method"))
            return ComponentType.METHOD;
        if (kname.contains("Indexer"))
            return ComponentType.METHOD;
        if (kname.contains("Constructor"))
            return ComponentType.METHOD;
        if (kname.contains("Delegate"))
            return ComponentType.FIELD;
        if (kname.contains("Static"))
            return ComponentType.METHOD;
        if (kname.contains("Finalizer"))
            return ComponentType.METHOD;
        if (kname.contains("Property"))
            return ComponentType.FIELD;
        if (kname.contains("Event"))
            return ComponentType.FIELD;
        else
            return ComponentType.UNKNOWN;
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
