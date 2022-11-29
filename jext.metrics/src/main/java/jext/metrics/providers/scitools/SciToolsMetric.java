package jext.metrics.providers.scitools;

import jext.metrics.AggregateMode;
import jext.metrics.Metric;
import jext.metrics.MetricsProvider;
import jext.metrics.ValueType;

public class SciToolsMetric implements Metric {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private final String id;
    private final String name;
    private final ValueType type;
    private final String description;
    private final SciToolsProvider provider;
    private String aggregate;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SciToolsMetric(SciToolsProvider provider, String id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = typeOf(type);
        this.description = description;
        this.provider = provider;
        this.aggregate = null;
    }

    private static ValueType typeOf(String type) {
        if ("count".equals(type))
            return ValueType.COUNT;
        if ("float".equals(type))
            return ValueType.FLOAT;
        if ("int".equals(type))
            return ValueType.INTEGER;
        else
            return ValueType.FLOAT;
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
    public ValueType getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public AggregateMode getAggregateMode() {
        if (aggregate != null)
            return AggregateMode.valueOf(aggregate);

        ValueType type = getType();
        switch (type) {
            case FLOAT:
                return AggregateMode.MEAN;
            case COUNT:
                return AggregateMode.SUM;
            default:
                return AggregateMode.SUM;
        }
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
