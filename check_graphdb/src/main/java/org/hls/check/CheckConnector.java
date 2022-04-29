package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.Param;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.Parameters;
import jext.util.PropertiesUtils;
import jext.util.SetUtils;
import jext.util.concurrent.Parallel;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/*
    refId
        ForSalwa            d23054f1
        Spark               2d40db2b
        check_java_syntax   b9ee0f37
        test                12345678
 */

public class CheckConnector {

    static final String REF_ID = "2d40db2b";

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        try (GraphSession s = gdb.connect("12345678")) {
            s.deleteRevisionMetadata("component");
            s.createRevisionMetadata("component", MapUtils.asMap(
                "component", SetUtils.asSet("complexity", "count", "countTypes", "digest", "inRevision",
                    "role")
            ));

            Map<String, Set<String>> metadata = s.getRevisionMetadata("component");
        }

        try (GraphSession s = gdb.connect(REF_ID, "dependency", 0)) {
            s.queryNodes("type", Parameters.params(
                "type", "type"
            )).allValues().forEach(nv -> System.out.println(nv));
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        gdb.destroy();
        Parallel.shutdown();
    }
}
