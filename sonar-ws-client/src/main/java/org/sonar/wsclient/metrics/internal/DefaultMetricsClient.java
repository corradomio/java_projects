package org.sonar.wsclient.metrics.internal;

import org.json.simple.JSONValue;
import org.sonar.wsclient.MapUtils;
import org.sonar.wsclient.internal.HttpRequestFactory;
import org.sonar.wsclient.metrics.MetricsClient;
import org.sonar.wsclient.services.Measure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultMetricsClient implements MetricsClient {

    private static final String COMPONENT_MEASURES = "/api/measures/component";
    private static final String HERARCHICAL_MEASURES = "/api/measures/component_tree";

    private final HttpRequestFactory requestFactory;

    public DefaultMetricsClient(HttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public List<Measure> list(String id, Collection<String> metricKeys, boolean recursive) {
        if (metricKeys.size() <= 15)
            return list15(id, metricKeys, recursive);

        List<Measure> allMeasures = new ArrayList<>();
        List<String> allKeys = new ArrayList<>(metricKeys);
        int i,e,n = metricKeys.size();
        for(i=0; i<n; i+= 15) {
            e = Math.min(i+15, n);
            List<Measure> measures = list15(id, allKeys.subList(i, e), recursive);
            allMeasures.addAll(measures);
        }
        return allMeasures;
    }

    private List<Measure> list15(String id, Collection<String> metricKeys, boolean recursive) {
        String json;
        if (recursive) {
            json = requestFactory.get(HERARCHICAL_MEASURES, MapUtils.asMap(
                    "component", id,
                    "metricKeys", toMetricKeys(metricKeys),
                    "pageSize", 10000));
        }
        else {
            json = requestFactory.get(COMPONENT_MEASURES, MapUtils.asMap(
                    "component", id,
                    "metricKeys", toMetricKeys(metricKeys),
                    "pageSize", 10000));
        }

        return jsonToList(json);
    }

    /*
    {
        "component": {
                "key": "Lucene",
                "name": "Lucene",
                "qualifier": "TRK",
                "measures": [{
                        "metric": "cognitive_complexity",
                        "value": "65421",
                        "bestValue": false
                    }, {
                        "metric": "complexity",
                        "value": "60349"
                    }
                ]
            }
        }

     */

    private List<Measure> jsonToList(String json) {
        Map jsonRoot = (Map) JSONValue.parse(json);
        Map component = (Map) jsonRoot.get("component");
        List<Map> measures = (List<Map>) component.get("measures");
        List<Measure> list = new ArrayList<>();
        for(Map jsonc : measures) {
            String metricKey = (String) jsonc.get("metric");
            double value = parseValue((String) jsonc.get("value"));

            Measure measure = new Measure();
            measure.setMetricKey(metricKey);
            measure.setValue(value);

            list.add(measure);
        }
        return list;
    }

    private static double parseValue(String value) {
        try {
            if (value == null)
                return 0.;
            else
                return Float.parseFloat(value);
        }
        catch(NumberFormatException e) {
            System.err.printf("NumberFormatException: '%s'%n", value);
            return -1.;
        }
    }

    private static String toMetricKeys(Collection<String> s) {
        if (s.isEmpty())
            return "";
        if (s.size()==1)
            return s.iterator().next();

        Iterator<String> it = s.iterator();
        StringBuilder sb = new StringBuilder(it.next());
        while(it.hasNext())
            sb.append(",").append(it.next());
        return sb.toString();
    }
}
