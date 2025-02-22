package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.neo4j.VGraphDatabase;
import jext.graph.schema.GraphSchema;
import jext.util.logging.Logger;
import jext.util.MapUtils;

import java.io.File;

public class CreateNodes {

    public static void main(String[] args) throws Exception {
        Logger.configure();
        CacheManager.configure();

        VGraphDatabase gdb = (VGraphDatabase) GraphDatabases.create(new File("config/neo4j.properties"));
        gdb.setGraphSchema(GraphSchema.load(new File("config/dbschema.xml")));

        String id;

        try(GraphSession s = gdb.connect("100001")) {
            s.deleteNodes("test", MapUtils.asMap());
        }

        try(GraphSession s = gdb.connect("100001", "test", 0)) {
            s.createNode("test", MapUtils.asMap(
                "name", "test",
                "count", 1,
                "sum", 2.2,
                "digest", 3456789,
                "shuffle", "ciccio"
            ));
        }

        try(GraphSession s = gdb.connect("100001", "test", 3)) {
            id = s.createNode("test", MapUtils.asMap(
                "name", "test",
                "count", 31,
                "sum", 32.2,
                "digest", 33456789,
                "shuffle", "treciccio"
            ));

            s.setNodeProperty(id, "revision", 4);
        }

        gdb.destroy();
    }
}
