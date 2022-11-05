package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.Param;
import jext.graph.Value;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class CreateEdges {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");
        GraphDatabase gdb = GraphDatabases.create(props);

        String sourceId;
        List<String> targetIds;

        Parameters p1 = Parameters.params("index", 1);
        Parameters p2 = Parameters.params("index", 2);

        try(GraphSession s = gdb.connect()) {
            sourceId = s.queryNodes("test", p1).id();
            targetIds = s.queryNodes("test", p2).ids().toList();

            if (sourceId == null) {
                sourceId = s.createNode("test", p1);
            }
            if (targetIds.isEmpty()) {
                s.createNode("test", p2);
                targetIds = s.queryNodes("test", p2).ids().toList();
            }

            s.deleteEdges("edge", sourceId, null, Parameters.empty());

            // T pari
            targetIds = s.queryNodes("test", Parameters.params("index", new int[]{2})).ids().toList();

            s.createEdges("edge", sourceId, targetIds, Parameters.params(
                "revision", 1
                // Param.at("inRevision", 3), true
            ));

        }
        try(GraphSession s = gdb.connect()) {

            s.setEdgesProperties("edge", sourceId, (Collection<String>)null, Parameters.empty(),
                Parameters.params(
                //"revision", 1
                Param.at("inRevision", 4), false
            ));

            // tutti i T
            targetIds = s.queryNodes("test", Parameters.params("index", Value.gt(1))).ids().toList();
            s.createEdges("edge", sourceId, targetIds, Parameters.params(
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
