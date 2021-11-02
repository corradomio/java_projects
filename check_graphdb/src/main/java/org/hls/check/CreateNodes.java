package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.Properties;

public class CreateNodes {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");
        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        try(GraphSession s = gdb.connect()) {
            s.updateNode("U", Parameters.empty(), Parameters.params(
                "revision", 3
            ));
        }
        finally {
            gdb.destroy();
        }
    }
}
