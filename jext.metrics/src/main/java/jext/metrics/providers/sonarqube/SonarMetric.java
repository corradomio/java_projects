package jext.metrics.providers.sonarqube;

import jext.metrics.Metric;
import jext.metrics.providers.scitools.SciToolsMetric;
import jext.util.MapUtils;

import java.util.Map;

public class SonarMetric implements Metric {

    static SonarMetric of(Map<String, Object> data) {
        return new SonarMetric(data);
    }

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

    private Map<String, Object> data;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private SonarMetric(Map<String, Object> data) {
        this.data = data;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return MapUtils.get(data, "key");
    }

    @Override
    public String getName() {
        return MapUtils.get(data, "name");
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
