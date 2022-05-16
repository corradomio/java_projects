package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.Param;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class CheckCreateEdges {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");
        GraphDatabase gdb = GraphDatabases.create(props);

        String sourceId;
        List<String> targetIds;

        try(GraphSession s = gdb.connect()) {
            sourceId = s.queryNodes("S", Parameters.empty()).id();
            targetIds = s.queryNodes("T", Parameters.empty()).ids().toList();

            if (sourceId == null) {
                s.createNode("S", Parameters.empty());
            }
            if (targetIds.isEmpty()) {
                for (int id = 1; id < 5000; ++id)
                    s.createNode("T", Parameters.params("id", id));
            }

            s.deleteEdges("E", sourceId, null, Parameters.empty(), (v)->{});

            List<Integer> ids = new ArrayList<>();
            for (int i = 0; i < 5000; i += 2)
                ids.add(i);

            // T pari
            targetIds = s.queryNodes("T", Parameters.params("id", ids)).ids().toList();

            s.createEdges("E", sourceId, targetIds, Parameters.params(
                //"revision", 1
                Param.at("inRevision", 3), true
            ));

        }
        try(GraphSession s = gdb.connect()) {

            s.setEdgeProperties("E", sourceId, (Collection<String>)null, Parameters.empty(), Parameters.params(
                //"revision", 1
                Param.at("inRevision", 4), false
            ));

            // tutti i T
            targetIds = s.queryNodes("T", Parameters.params()).ids().toList();
            s.createEdges("E", sourceId, targetIds, Parameters.params(
                //"revision", 1
                Param.at("inRevision", 4), true
            ));

            // System.out.println(sourceId);
            // System.out.println(targetIds.size());
        }
        finally {
            gdb.destroy();
        }

    }
}
