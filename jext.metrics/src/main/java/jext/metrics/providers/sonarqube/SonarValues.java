package jext.metrics.providers.sonarqube;

import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SonarValues extends ArrayList<MetricValue> implements MetricsValues {

    @Override
    public boolean add(MetricValue e) {
        super.add(e);

        return true;
    }
}
