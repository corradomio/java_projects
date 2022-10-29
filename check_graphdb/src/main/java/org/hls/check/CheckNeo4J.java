package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.neo4j.VGraphDatabase;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.PropertiesUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class CheckNeo4J {

    static final String REF_ID = "2d40db2b";

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        VGraphDatabase gdb = (VGraphDatabase) GraphDatabases.create(props);

        gdb.getNamedQueries().registerQueries(MapUtils.asMap("test", "MATCH (n:source {refId:$refId}) RETURN n"));

        // Map<String,Object> p = MapUtils.asMap(
        //     "other", MapUtils.asMap(
        //         "refId", REF_ID
        //     )
        // );

        try(GraphSession s = gdb.connect(REF_ID)) {
            s.queryUsing("test", Collections.emptyMap())
                .result().forEach(nv -> {
                    System.out.println(nv);
                });
        }

        gdb.destroy();
    }
}
