package jext.metrics.providers.scitools;

import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsProject;
import jext.metrics.ObjectType;
import jext.util.MapUtils;
import jext.util.PathUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SciToolsObject implements MetricsObject {

    public static SciToolsObject of(SciToolsObject parent, String id, String name, String longname, String kname) {
        return new SciToolsObject(parent, id, name, longname, kname);
    }

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String QUAL_MODULE = "module";
    private static final String QUAL_SOURCE = "source";
    private static final String QUAL_TYPE = "type";
    private static final String QUAL_METHOD = "method";

    public  static ObjectType toType(String type) {
        if (type.equals(QUAL_MODULE))
            return ObjectType.MODULE;
        if (type.equals(QUAL_SOURCE))
            return ObjectType.SOURCE;
        if (type.equals(QUAL_TYPE))
            return ObjectType.TYPE;
        if (type.equals(QUAL_METHOD))
            return ObjectType.METHOD;
        else
            return ObjectType.UNKNOWN;
    }

    public static String toKname(ObjectType type) {
        if (type == ObjectType.TYPE)
            return QUAL_TYPE;
        if (type == ObjectType.METHOD)
            return QUAL_METHOD;
        if (type == ObjectType.SOURCE)
            return QUAL_SOURCE;
        if (type == ObjectType.MODULE)
            return QUAL_MODULE;
        else
            throw new RuntimeException(String.format("Unsupported type %s", type));
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String id;
    private final String name;
    private String longname;
    private final String kname;
    private final ObjectType type;

    protected SciToolsProvider provider;
    protected SciToolsProject project;
    private SciToolsObject parent;
    private final List<MetricsObject> children = new ArrayList<>();
    private final List<MetricValue> metricValues = new ArrayList<>();

    private static final Map<String, ObjectType> SCITOOLS_TYPES;
    static {
        SCITOOLS_TYPES = new HashMap<>() {{
            put("Module", ObjectType.MODULE);
            put("Module File", ObjectType.MODULE);

            put("File", ObjectType.SOURCE);

            put("Type", ObjectType.TYPE);
            put("Class", ObjectType.TYPE);
            put("Interface", ObjectType.TYPE);
            put("Struct", ObjectType.TYPE);
            put("Enum", ObjectType.TYPE);

            put("Method", ObjectType.METHOD);
            put("Indexer", ObjectType.METHOD);
            put("Constructor", ObjectType.METHOD);
            put("Finalizer", ObjectType.METHOD);
            put("Static", ObjectType.METHOD);

            put("Function", ObjectType.METHOD);

            put("Delegate", ObjectType.FIELD);
            put("Property", ObjectType.FIELD);
            put("Event", ObjectType.FIELD);
        }};
    }

    private static ObjectType fromKname(String kname) {
        for (String k : SCITOOLS_TYPES.keySet())
            if (kname.contains(k))
                return SCITOOLS_TYPES.get(k);

        return ObjectType.UNKNOWN;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected SciToolsObject(SciToolsObject parent, String id, String name, String longname, String kname) {
        this.id = id;
        this.name = name;
        this.longname = longname;
        this.kname = kname;
        this.type = fromKname(kname);

        if (parent != null) {
            this.parent = parent;
            this.project = parent.project;
            this.provider = parent.provider;
        }

        if (this.type == ObjectType.SOURCE)
            normalizePath();
    }

    void setParent(SciToolsObject parent) {
        this.parent = parent;
        this.project = parent.project;
        this.provider = parent.provider;
        this.parent.children.add(this);
    }

    private void normalizePath() {
        String projectHome = provider.getProjectHome();
        longname = PathUtils.normalize(longname);
        longname = PathUtils.relativePath(projectHome, longname);
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
    public String getLongname() {
        return longname;
    }

    @Override
    public ObjectType getType() {
        return type;
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

    @Nullable
    @Override
    public MetricsObject getMetricsObject(ObjectType type, String path) {
        String[] parts = path.split("/");
        int l = parts.length-1;

        SciToolsObject current = this;
        for(int i=0; i<l && current != null; ++i) {
            current = current.getChild(parts[i]);
        }

        if (current != null && l >= 0)
            return current.getChild(parts[l]);
        else // in "theory" never happen
            return null;
    }

    private SciToolsObject getChild(String name) {
        List<MetricsObject> children = getChildren();
        for (MetricsObject child : children)
            if (child.getName().equals(name))
                return (SciToolsObject) child;
        return null;
    }

    @Override
    public MetricsObject newMetricsObject(ObjectType type, String path) {
        String name = PathUtils.getName(path);

        return SciToolsObject.of(this, path, name, path, toKname(type));
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

    // ----------------------------------------------------------------------

    @Override
    public void getMetricValues(Consumer<MetricValue> callback) {
        metricValues.forEach(callback);
    }

    @Override
    public void getMetricValues(String category, Consumer<MetricValue> callback) {
        Set<String> categoryMetrics = provider.getMetricNames(category);
        metricValues.stream()
                .filter(v -> categoryMetrics.contains(v.getMetric().getId())
                        || categoryMetrics.contains(v.getMetric().getName())).
                forEach(callback);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    String getPath() {
        return longname;
    }

    void addMetricValue(MetricValue metricValue) {
        this.metricValues.add(metricValue);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
