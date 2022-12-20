package jext.metrics.providers.sonarqube;

import jext.metrics.AggregateMode;
import jext.metrics.Metric;
import jext.metrics.MetricsProvider;
import jext.metrics.ValueType;
import jext.util.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class SonarMetric implements Metric {

    // ----------------------------------------------------------------------
    // Provate fields
    // ----------------------------------------------------------------------

    /*
        {
            "id": "AYM2RxnE4wa9zTX5sTDz",
            "key": "new_technical_debt",
            "type": "WORK_DUR",
            "name": "Added Technical Debt",
            "description": "Added technical debt",
            "domain": "Maintainability",
            "direction": -1,
            "qualitative": true,
            "hidden": false
        },
     */

    private final SonarProvider provider;
    private final Map<String, Object> data = new HashMap<>();
    private final ValueType type;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarMetric(SonarProvider provider, Map<String, Object> data) {
        this.data.putAll(data);
        this.provider = provider;
        this.type = typeOf(MapUtils.get(data, "type"));
    }

    private static ValueType typeOf(String type) {
        // WORK_DUR
        // BOOL
        // INT
        // PERCENT
        // DATA
        // FLOAT
        // MILLIS
        // STRING
        // DISTRIB
        // RATING
        // LEVEL
        if ("WORK_DUR".equals(type))
            return ValueType.INTEGER;
        if ("BOOL".equals(type))
            return ValueType.BOOLEAN;
        if ("INT".equals(type))
            return ValueType.INTEGER;
        if ("PERCENT".equals(type))
            return ValueType.PERCENT;
        if ("DATA".equals(type))
            return ValueType.NOT_A_NUMBER;
        if ("FLOAT".equals(type))
            return ValueType.FLOAT;
        if ("MILLIS".equals(type))
            return ValueType.INTEGER;
        if ("STRING".equals(type))
            return ValueType.NOT_A_NUMBER;
        if ("DISTRIB".equals(type))
            return ValueType.NOT_A_NUMBER;
        if ("RATING".equals(type))
            return ValueType.FLOAT;
        if ("LEVEL".equals(type))
            return ValueType.NOT_A_NUMBER;
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
        return MapUtils.get(data, "key");
    }

    @Override
    public String getName() {
        return MapUtils.get(data, "name");
    }

    @Override
    public String getFullName() {
        return String.format("%s.%s", provider.getName(), getId());
    }

    @Override
    public ValueType getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return MapUtils.get(data, "description");
    }

    @Override
    public AggregateMode getAggregateMode() {
        String mode = MapUtils.get(data, "aggregate");
        if (mode != null)
            return AggregateMode.valueOf(mode);

        ValueType type = getType();
        switch (type) {
            case COUNT:
                return AggregateMode.SUM;
            case INTEGER:
                return AggregateMode.MEAN;
            case FLOAT:
                return AggregateMode.MEAN;
            case PERCENT:
                return AggregateMode.MEAN;
            default:
                return AggregateMode.SUM;
        }
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void setAggregate(String aggregate) {
        data.put("aggregate", aggregate);
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        SonarMetric that = (SonarMetric) obj;
        return this.getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
