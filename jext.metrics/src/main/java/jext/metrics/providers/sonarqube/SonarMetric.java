package jext.metrics.providers.sonarqube;

import jext.metrics.Metric;
import jext.metrics.MetricsProvider;
import jext.util.MapUtils;

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
    private final Map<String, Object> data;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarMetric(SonarProvider provider, Map<String, Object> data) {
        this.data = data;
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
        return MapUtils.get(data, "key");
    }

    @Override
    public String getName() {
        return MapUtils.get(data, "name");
    }

    @Override
    public String getType() {
        return MapUtils.get(data, "type");
    }

    @Override
    public String getDescription() {
        return MapUtils.get(data, "description");
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
