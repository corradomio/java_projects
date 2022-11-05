package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.neo4j.VGraphDatabase;
import jext.graph.neo4j.VGraphSession;
import jext.graph.schema.GraphSchema;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.Parameters;

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

        String m1, m2, m3, e12, e13, e23;

        try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
            m1 = s.createNode("module", Parameters.params(
                "name", "m1",
                "fullname", "p.m1",
                "digest", 111,
                "path", "p/m1"
            ));

            m2 = s.createNode("module", Parameters.params(
                "name", "m2",
                "fullname", "p.m2",
                "digest", 222,
                "path", "p/m2"
            ));

            m3 = s.createNode("module", Parameters.params(
                    "name", "m3",
                    "fullname", "p.m3",
                    "digest", 333,
                    "path", "p/m3"
            ));

            e12 = s.createEdge("uses", m1, m2, Parameters.params(
                // "$count", 1
            ));

            e13 = s.createEdge("uses", m1, m3, Parameters.params(
                    // "$count", 1
            ));

            e23 = s.createEdge("uses", m2, m3, Parameters.params(
                    // "$count", 1
            ));

        }

        // try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
        //
        //     e12 = s.createEdge("uses", m1, m2, Parameters.params(
        //         // "$count", 1
        //     ));
        //
        // }
        //
        // try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
        //     Map<String, Object> nv = s.getNodeProperties(m1);
        //     System.out.println(nv);
        //     Map<String, Object> ev = s.getEdgeProperties(e12);
        //     System.out.println(ev);
        // }
        //
        // try(VGraphSession s = gdb.connect(REF_ID, "source", 1)) {
        //     s.deleteNode("module", m1);
        // }
        //
        // try(VGraphSession s = gdb.connect(REF_ID, "source", 1)) {
        //     s.deleteEdge("uses", m1, m2);
        // }

        gdb.destroy();
        CacheManager.shutdown();
    }
}
