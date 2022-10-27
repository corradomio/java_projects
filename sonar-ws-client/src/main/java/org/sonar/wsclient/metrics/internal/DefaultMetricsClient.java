package org.sonar.wsclient.metrics.internal;

import org.json.simple.JSONValue;
import org.sonar.wsclient.MapUtils;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.internal.HttpRequestFactory;
import org.sonar.wsclient.metrics.MetricsClient;
import org.sonar.wsclient.services.Measure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultMetricsClient implements MetricsClient {

    private static final String COMPONENT_MEASURES = "/api/measures/component";
    private static final String HERARCHICAL_MEASURES = "/api/measures/component_tree";

    private final HttpRequestFactory requestFactory;

    /**
     *
     * @param requestFactory
     */

    public DefaultMetricsClient(HttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public List<Measure> list(String id, Collection<String> metricKeys, boolean recursive) {
        if (metricKeys.isEmpty())
            return Collections.emptyList();
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

    /*
    private List<Measure> list15(String id, Collection<String> metricKeys, boolean recursive) {
        String json;
        if (recursive) {
            json = requestFactory.get(HERARCHICAL_MEASURES, MapUtils.asMap(
                    "component", id,
                    "metricKeys", toMetricKeys(metricKeys),
                    "pageSize", 10000));
            return jsonListToList(json);
        }
        else {
            json = requestFactory.get(COMPONENT_MEASURES, MapUtils.asMap(
                    "component", id,
                    "metricKeys", toMetricKeys(metricKeys),
                    "pageSize", 10000));
            return jsonToList(json);
        }
    }
     */

    private List<Measure> list15(String id, Collection<String> metricKeys, boolean recursive) {
        String json;
        if (!recursive) {
            json = requestFactory.get(COMPONENT_MEASURES, MapUtils.asMap(
                    "component", id,
                    "metricKeys", toMetricKeys(metricKeys))
            );
            return jsonToList(json);
        }
        else {
            List<Measure> measures = new ArrayList<>();
            String mkeys = toMetricKeys(metricKeys);
            int p = 1;
            int nPages = 1;

            while(p <= nPages) {
                json = requestFactory.get(HERARCHICAL_MEASURES, MapUtils.asMap(
                        "component", id,
                        "metricKeys", mkeys,
                        "p", p));

                Map jsonRoot = (Map) JSONValue.parse(json);

                // 1) retrieve the current page and the page numbers
                int total = MapUtils.getInt(jsonRoot, "paging", "total");
                int pageSize = MapUtils.getInt(jsonRoot, "paging", "pageSize");
                nPages = (total + pageSize - 1)/pageSize;

                // 2) retrieve all measures
                measures.addAll(toListOfList(jsonRoot));
            }

            return measures;
        }
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
        Map cmap = (Map) jsonRoot.get("component");
        return measures(cmap, false);
    }

    private List<Measure> toList(Map jsonRoot) {
        Map cmap = (Map) jsonRoot.get("component");
        return measures(cmap, false);
    }

    private List<Measure> measures(Map cmap, boolean component) {
        List<Map> measures = (List<Map>) cmap.get("measures");
        List<Measure> list = new ArrayList<>();
        for(Map jsonc : measures) {
            String metricKey = (String) jsonc.get("metric");
            double value = parseValue(metricKey, (String) jsonc.get("value"));

            if (component) {
                CMeasure measure = new CMeasure();
                measure.setMetricKey(metricKey);
                measure.setValue(value);
                measure.setComponent(cmap);
                list.add(measure);
            }
            else {
                Measure measure = new Measure();
                measure.setMetricKey(metricKey);
                measure.setValue(value);
                list.add(measure);
            }
        }
        return list;
    }

    /*
        {
            "paging": {
                "pageIndex": 1,
                "pageSize": 100,
                "total": 4347
            },
            "baseComponent": {
                "key": "Lucene",
                "name": "Lucene",
                "qualifier": "TRK",
                "measures": [{
                        "metric": "duplicated_lines_density",
                        "value": "11.0",
                        "bestValue": false
                    }, ...
                ]
            },
            "components": [{
                    "key": "Lucene:Lucene.Net.Grouping/AbstractAllGroupHeadsCollector.cs",
                    "name": "AbstractAllGroupHeadsCollector.cs",
                    "qualifier": "FIL",
                    "path": "Lucene.Net.Grouping/AbstractAllGroupHeadsCollector.cs",
                    "language": "cs",
                    "measures": [{
                            "metric": "major_violations",
                            "value": "2",
                            "bestValue": false
                        }, ...
                    ]
                },
     */

    public static class CMeasure extends Measure {
        // String key;
        // String name;
        // String qualifier;
        // String path;
        // String language;
        private Map<String, String> cmap;

        private void setComponent(Map cmap) {
            this.cmap = cmap;
        }

        public Component getComponent() {
            return new Component(cmap);
        }
    }

    private List<Measure> jsonListOfList(String json) {
        Map jsonRoot = (Map) JSONValue.parse(json);
        List<Map> components = (List<Map>) jsonRoot.get("components");
        List<Measure> list = new ArrayList<>();
        for(Map cmap : components) {
            List<Measure> clist = measures(cmap, true);
            list.addAll(clist);
        }
        return list;
    }

    private List<Measure> toListOfList(Map jsonRoot) {
        List<Map> components = (List<Map>) jsonRoot.get("components");
        List<Measure> list = new ArrayList<>();
        for(Map cmap : components) {
            List<Measure> clist = measures(cmap, true);
            list.addAll(clist);
        }
        return list;
    }

    private static double parseValue(String metricKey, String value) {
        try {
            if (value == null)
                return 0.;
            else
                return Float.parseFloat(value);
        }
        catch(NumberFormatException e) {
            System.err.printf("[%s] NumberFormatException: '%s'%n", metricKey, value);
            return -1.;
        }
    }

    private static String toMetricKeys(Collection<String> s) {
        if (s.isEmpty())
            return "";
        if (s.size()==1)
            return s.iterator().next();

        Set<String> mkeys = new HashSet<>();
        Iterator<String> it = s.iterator();
        StringBuilder sb = new StringBuilder(it.next());
        while(it.hasNext()) {
            String mkey = it.next();
            if (mkeys.contains(mkey))
                continue;
            sb.append(",").append(mkey);
            mkeys.add(mkey);
        }
        return sb.toString();
    }
}
