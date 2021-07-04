package org.hls.check;

import apoc.coll.CollExt;
import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        CollExt collx = new CollExt();

        System.out.println(collx.setOrExtend2(Arrays.asList("1,2", "2,3"), 1, 1, 22));
        System.out.println(collx.setOrExtend2(Arrays.asList("1,2", "2,3"), 4, 4, 44));

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        String nodeId;
        List<Map<String, Object>> r;
        try(GraphSession s = gdb.connect()) {
            nodeId = s.findNode("Test", Parameters.params("inRevision[0]", false));

            s.setNodeProperty(nodeId, "calls[0,+]", 1);
            s.setNodeProperty(nodeId, "calls[0,!]", 1);

            // nodeId = s.findNode("Test", Parameters.empty());
            // if (nodeId == null)
            //     nodeId = s.createNode("Test",  Parameters.empty());
            //
            s.setNodeProperty(nodeId, "inRevision[!]", true);
            System.out.println(nodeId);
        }

        gdb.destroy();
    }
}
