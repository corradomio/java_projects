package jext.metrics.providers.scitools;

import jext.metrics.Metric;
import jext.metrics.MetricsProvider;

public class SciToolsMetric implements Metric {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private final String id;
    private final String name;
    private final String type;
    private final String description;
    private final SciToolsProvider provider;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SciToolsMetric(SciToolsProvider provider, String id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.provider = provider;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MetricsProvider getProvider() {
        return provider;
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
    public String getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        SciToolsMetric that = (SciToolsMetric) obj;
        return this.getId().equals(that.getId());
    }

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
