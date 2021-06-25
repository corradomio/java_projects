package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        List<Map<String, Object>> r;
        try(GraphSession s = gdb.connect()) {
            String nodeId = s.findNode("Test", Parameters.empty());

            s.setNodeProperty(nodeId, "inRevision[3]", true);
        }

        gdb.destroy();
    }
}
