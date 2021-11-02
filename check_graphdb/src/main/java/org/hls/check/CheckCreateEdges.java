package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.Param;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.List;
import java.util.Properties;

public class CheckCreateEdges {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");
        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        String sourceId;
        List<String> targetIds;

        try(GraphSession s = gdb.connect()) {
            sourceId  = s.queryNodes("S", Parameters.empty()).id();
            targetIds = s.queryNodes("T", Parameters.empty()).ids().toList();

            // s.deleteEdges("E", sourceId, targetIds, Parameters.empty());
            // s.createEdges("E", sourceId, targetIds, Parameters.params(
            //     // "revision", 3
            //     // Param.of("inRevision", 3), true
            //     "inRevision", new boolean[]{false, false, false, true}
            // ));

            s.updateEdges("E", sourceId, targetIds, Parameters.params(
                //"revision", 1
                Param.at("inRevision", 3), true
            ));

            // System.out.println(sourceId);
            // System.out.println(targetIds.size());
        }
        finally {
            gdb.destroy();
        }

    }
}
