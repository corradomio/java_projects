package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.neo4j.VGraphDatabase;
import jext.graph.neo4j.VGraphSession;
import jext.graph.schema.GraphSchema;
import jext.logging.Logger;
import jext.util.MapUtils;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import static jext.graph.neo4j.Neo4JOnlineSession.COUNT;

public class CheckRevEdges {

    static String REF_ID = "100001";

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        VGraphDatabase gdb = (VGraphDatabase) GraphDatabases.create(new File("config/neo4j.properties"));
        gdb.setGraphSchema(GraphSchema.load(new File("config/dbschema.xml")));

        try(GraphSession s = gdb.connect(REF_ID)) {
            s.deleteNodes(null, Collections.emptyMap());
        }

        String m1,m2,e12;

        try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
            m1 = s.createNode("module", MapUtils.asMap(
                "name", "m1",
                "fullname", "p.m1",
                "digest", 111,
                "path", "p/m1"
            ));

            m2 = s.createNode("module", MapUtils.asMap(
                "name", "m2",
                "fullname", "p.m2",
                "digest", 111,
                "path", "p/m2"
            ));

            e12 = s.createEdge("uses", m1, m2, MapUtils.asMap(
                // "$count", 1
            ));

        }

        try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {

            e12 = s.createEdge("uses", m1, m2, MapUtils.asMap(
                // "$count", 1
            ));

        }

        try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
            Map<String, Object> nv = s.getNodeProperties(m1);
            System.out.println(nv);
            Map<String, Object> ev = s.getEdgeProperties(e12);
            System.out.println(ev);
        }

        try(VGraphSession s = gdb.connect(REF_ID, "source", 0)) {
            s.deleteNode("module", m1);
            s.deleteEdge("uses", m1, m2);
        }

        gdb.destroy();
        CacheManager.shutdown();
    }
}
