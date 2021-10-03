package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.Param;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.util.Properties;

public class CheckConnector {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        try (GraphSession s = gdb.connect()) {
            s.queryNodes("component", Parameters.params(
                "refId","4c00c2ca",
                Param.of("$degree", ">"), 0
            )).limit(1).allValues().forEach(nv -> {
                System.out.println(nv);
            });
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        gdb.destroy();
        Parallel.shutdown();
    }
}
