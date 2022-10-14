package jext.metrics.providers.scitools;

import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;

import java.util.ArrayList;
import java.util.List;

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

    private SciToolsObject parent;
    private final List<MetricsComponent> children = new ArrayList<>();
    private final List<MetricValue> metrics = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected SciToolsObject(String id, String name, String kname) {
        if (parent != null)
            parent.children.add(this);

        this.id = id;
        this.name = name;
        this.kname = kname;
    }

    void setParent(SciToolsObject parent) {
        this.parent = parent;
        if (parent != null)
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
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public List<MetricsComponent> getChildren() {
        return children;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
