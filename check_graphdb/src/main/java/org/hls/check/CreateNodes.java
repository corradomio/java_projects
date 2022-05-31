package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.util.MapUtils;

import java.io.File;

public class CreateNodes {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        GraphDatabase gdb = GraphDatabases.create(new File("config/neo4j.properties"));
        gdb.setGraphSchema(new File("config/dbschema.xml"));

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
            s.createNode("test", MapUtils.asMap(
                "name", "test",
                "count", 31,
                "sum", 32.2,
                "digest", 33456789,
                "shuffle", "treciccio"
            ));
        }

        gdb.destroy();
    }
}
