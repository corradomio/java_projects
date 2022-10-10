package jext.metrics.providers;

import jext.metrics.AllMetrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SciToolsAllMetrics implements AllMetrics {

    private Map<Integer, List<SciToolsMetric>> metrics = new HashMap<>();

    public void addMetric(int id, SciToolsMetric metric) {
        if (!metrics.containsKey(id))
            metrics.put(id, new ArrayList<>());
        metrics.get(id).add(metric);
    }
}
