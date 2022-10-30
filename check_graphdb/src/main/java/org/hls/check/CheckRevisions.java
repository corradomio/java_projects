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

public class CheckRevisions {

    static String REF_ID = "100001";

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        VGraphDatabase gdb = (VGraphDatabase) GraphDatabases.create(new File("config/neo4j.properties"));
        gdb.setGraphSchema(GraphSchema.load(new File("config/dbschema.xml")));

        try(GraphSession s = gdb.connect(REF_ID)) {
            s.deleteNodes(null, Collections.emptyMap());
        }

        // try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
        //     s.createNode("msource", MapUtils.asMap(
        //         "name", "projectTest"
        //     ));
        // }

        try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
            s.createNode("module", MapUtils.asMap(
                "name", "mymodule",
                "fullname", "p.mymodule",
                "digest", 111,
                "mrefId", "01",
                "path", "mypath"
            ));
        }

        try(GraphSession s = gdb.connect(REF_ID, "source", 4)) {
            s.createNode("module", MapUtils.asMap(
                "name", "mymodule",
                "fullname", "p.mymodule",
                "digest", 444,
                "mrefId", "01",
                "path", "mypath"
            ));
        }

        try(GraphSession s = gdb.connect(REF_ID, "source", 5)) {
            s.deleteNodes("module", MapUtils.asMap(
                "name", "mymodule",
                "fullname", "p.mymodule",
                "digest", 444,
                "mrefId", "01",
                "path", "mypath"
            ));
        }

        gdb.destroy();
        CacheManager.shutdown();
    }
}
