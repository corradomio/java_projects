package jext.metrics.providers.sonarqube;

import jext.metrics.Metric;
import jext.util.MapUtils;

import java.util.Map;

public class SonarQubeMetric implements Metric {

    static SonarQubeMetric of(Map<String, Object> data) {
        return new SonarQubeMetric(data);
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

    private SonarQubeMetric(Map<String, Object> data) {
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
    // End
    // ----------------------------------------------------------------------

}
