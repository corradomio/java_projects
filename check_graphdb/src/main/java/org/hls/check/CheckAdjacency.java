package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.Direction;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.util.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.List;
import java.util.Properties;

public class CheckAdjacency {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");
        GraphDatabase gdb = GraphDatabases.create(props);

        // Query queryAdjacentNodes(
        //         String fromId,
        //         @Nullable String edgeType, Direction direction, boolean recursive,
        //         String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);

        try(GraphSession s = gdb.connect()) {
            List<String> ids = s.queryAdjacentNodes("87863",
                "uses", Direction.Any, true,
                "module", Parameters.empty(),
                Parameters.empty()).ids().toList();

            System.out.println(ids.size());
            System.out.println(ids);
        }

    }
}
