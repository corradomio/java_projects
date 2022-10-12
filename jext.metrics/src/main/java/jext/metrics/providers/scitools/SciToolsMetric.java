package jext.metrics.providers.scitools;

import jext.metrics.Metric;

public class SciToolsMetric implements Metric {

    public static SciToolsMetric of(String id, String name, String type, String description) {
        if (type.isEmpty())
            type = "count";
        return new SciToolsMetric(id, name, type, description);
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private final String id;
    private final String name;
    private final String type;
    private final String description;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private SciToolsMetric(String id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
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
    public String getDescription() {
        return description;
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
