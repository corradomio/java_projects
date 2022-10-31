package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.neo4j.VGraphDatabase;
import jext.graph.schema.GraphSchema;
import jext.logging.Logger;
import jext.util.MapUtils;

import java.io.File;
import java.util.Collections;
import java.util.Map;

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

        try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
            String m1 = s.createNode("module", MapUtils.asMap(
                "name", "m1",
                "fullname", "p.m1",
                "digest", 111,
                "path", "p/m1"
            ));
            String m2 = s.createNode("module", MapUtils.asMap(
                "name", "m2",
                "fullname", "p.m2",
                "digest", 111,
                "path", "p/m2"
            ));

            String e12 = s.createEdge("uses", m1, m2, MapUtils.asMap(
                // "$count", 1
            ));


            Map<String, Object> nv = s.getNodeProperties(m1);
            Map<String, Object> ev = s.getEdgeProperties(e12);

        }

        gdb.destroy();
        CacheManager.shutdown();
    }
}
