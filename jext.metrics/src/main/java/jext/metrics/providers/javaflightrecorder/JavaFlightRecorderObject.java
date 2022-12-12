package jext.metrics.providers.javaflightrecorder;

import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsProject;
import jext.metrics.ObjectType;
import jext.util.MapUtils;
import jext.util.PathUtils;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JavaFlightRecorderObject implements MetricsObject {

    public static JavaFlightRecorderObject of(JavaFlightRecorderObject parent, String id, String name, String longname, String kname) {
        return new JavaFlightRecorderObject(parent, id, name, longname, kname);
    }

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String QUAL_TYPE = "type";
    private static final String QUAL_METHOD = "method";
    private static final String QUAL_UNK = "unk";

    public  static ObjectType toType(String type) {
        if (type.equals(QUAL_TYPE))
            return ObjectType.TYPE;
        if (type.equals(QUAL_METHOD))
            return ObjectType.METHOD;
        else
            return ObjectType.UNKNOWN;
    }

    public static String toKname(ObjectType type) {
        // special case used in aggregate values
        if (type == ObjectType.VIRTUAL)
            return QUAL_UNK;

        if (type == ObjectType.TYPE)
            return QUAL_TYPE;
        if (type == ObjectType.METHOD)
            return QUAL_METHOD;
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

    protected JavaFlightRecorderProvider provider;
    protected JavaFlightRecorderProject project;
    private JavaFlightRecorderObject parent;
    private final List<MetricsObject> children = new ArrayList<>();
    private final List<MetricValue> metricValues = new ArrayList<>();

    private static final Map<String, ObjectType> JAVAFLIGHTRECORDER_TYPES;
    static {
        JAVAFLIGHTRECORDER_TYPES = new HashMap<>() {{
//            put("Module", ObjectType.MODULE);
//            put("Module File", ObjectType.MODULE);
//
//            put("File", ObjectType.SOURCE);

            put("Type", ObjectType.TYPE);
//            put("Class", ObjectType.TYPE);
//            put("Interface", ObjectType.TYPE);
//            put("Struct", ObjectType.TYPE);
//            put("Enum", ObjectType.TYPE);

            put("Method", ObjectType.METHOD);
//            put("Indexer", ObjectType.METHOD);
//            put("Constructor", ObjectType.METHOD);
//            put("Finalizer", ObjectType.METHOD);
//            put("Static", ObjectType.METHOD);
//
//            put("Function", ObjectType.METHOD);
//
//            put("Delegate", ObjectType.FIELD);
//            put("Property", ObjectType.FIELD);
//            put("Event", ObjectType.FIELD);
        }};
    }

    private static ObjectType fromKname(String kname) {
        for (String k : JAVAFLIGHTRECORDER_TYPES.keySet())
            if (kname.contains(k))
                return JAVAFLIGHTRECORDER_TYPES.get(k);

        return ObjectType.UNKNOWN;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected JavaFlightRecorderObject(JavaFlightRecorderObject parent, String id, String name, String longname, String kname) {
        this.id = id;
        this.name = name;
        this.longname = longname;
        this.kname = kname;
        this.type = fromKname(kname);

        if (parent != null) {
            setParent(parent);
//            this.parent = parent;
//            this.project = parent.project;
//            this.provider = parent.provider;
        }

//        if (this.type == ObjectType.SOURCE)
//            normalizePath();
    }

    void setParent(JavaFlightRecorderObject parent) {
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
        if (type == ObjectType.TYPE)
            return project.getObjectByName(path);
        else
            throw new RuntimeException(String.format("Unsupported type %s", type));
    }

    private JavaFlightRecorderObject findFileObject(String path) {
        String[] parts = path.split("/");
        int l = parts.length-1;

        JavaFlightRecorderObject current = this;
        for(int i=0; i<l && current != null; ++i) {
            current = current.getChild(parts[i]);
        }

        if (current != null && l >= 0)
            return current.getChild(parts[l]);
        else // in "theory" never happen
            return null;
    }

    private JavaFlightRecorderObject findTypeObject(String qualifiedName) {
        return project.getObjectByName(qualifiedName);
    }

    private JavaFlightRecorderObject getChild(String name) {
        List<MetricsObject> children = getChildren();
        for (MetricsObject child : children)
            if (child.getName().equals(name))
                return (JavaFlightRecorderObject) child;
        return null;
    }

    @Override
    public MetricsObject newMetricsObject(ObjectType type, String path) {
        String name = PathUtils.getName(path);

        return JavaFlightRecorderObject.of(this, path, name, path, toKname(type));
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
        return "";
    }

    void addMetricValue(MetricValue metricValue) {
        this.metricValues.add(metricValue);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
