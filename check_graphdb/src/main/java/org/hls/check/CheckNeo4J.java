package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.Map;
import java.util.Properties;

public class CheckNeo4J {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        gdb.registerQueries(MapUtils.asMap("test", "MATCH (n:splproject)"));

        Map<String, Object> p = MapUtils.asMap(
            "other", MapUtils.asMap(
                "refId", "4c00c2ca"
            )
        );

        try(GraphSession s = gdb.connect()) {
            s.queryUsing("test", p)
                .allValues().toList().forEach(nv -> {
                    System.out.println(nv);
                });
        }

        gdb.destroy();
    }
}
