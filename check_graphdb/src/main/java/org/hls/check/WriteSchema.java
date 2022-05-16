package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.util.HashBidiMap;
import jext.util.HashMap;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;
import org.neo4j.driver.types.Node;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class CheckSchema {

    private static String toType(Object o) {
        if (o instanceof String)
            return "string";
        if (o instanceof Integer)
            return "int";
        if (o instanceof Long)
            return "long";
        if (o instanceof Float)
            return "float";
        if (o instanceof Double)
            return "double";
        if (o instanceof Boolean)
            return "bool";

        if (o instanceof List) {
            if (((List)o).isEmpty())
                return null;
            Object i = ((List)o).get(0);
            String ti = toType(i);
            return String.format("%s[]", ti);
        }

        System.out.printf("Unknown type for %s\n", o);
        return null;
    }

    private static void dumpSchema(Map<String, Map<String, String>> schema) {
        System.out.println("<schema>");
        for (String label : schema.keySet()) {
            System.out.printf( "    <node name='%s'>\n", label);
            for (Map.Entry e : schema.get(label).entrySet())
                System.out.printf( "        <property name='%s' type='%s'/>\n", e.getKey(), e.getValue());
            System.out.println("    </node>");
        }
        System.out.println("</schema>");

    }

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        Map<String, Map<String, String>> schema = new TreeMap<>();

        try(GraphSession s = gdb.connect()) {
            List<Map<String, Object>> labels = s.query("MATCH (n) RETURN DISTINCT(labels(n)) AS l",
                Collections.emptyMap()
            ).result().toList();
            labels.forEach(m -> {
                String label = ((List<String>)m.get("l")).get(0);

                Map<String, String> ptypes = new TreeMap<>();
                schema.put(label, ptypes);

                List<Map<String, Object>> rlist = s.query(String.format("MATCH (n:%s) RETURN n", label),
                    Collections.emptyMap()
                ).result().toList();

                rlist.forEach(r -> {
                    Node node = (Node) r.get("n");
                    Map<String, Object> n = node.asMap();
                    n.forEach((k, v) -> {
                        String t = toType(v);
                        if (t == null)
                            return;
                        if (!ptypes.containsKey(k)) {
                            ptypes.put(k, t);
                        }
                        else {
                            String u = ptypes.get(k);
                            if (!t.equals(u))
                                System.out.printf("%s: %s <> %s\n", k, t, u);
                        }
                    });
                });

            });
        }

        dumpSchema(schema);

        gdb.destroy();
        Parallel.shutdown();
    }
}
