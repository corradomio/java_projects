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
import java.util.Arrays;
import java.util.Collections;

public class CheckRevNodes {

    static String REF_ID = "100001";

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        VGraphDatabase gdb = (VGraphDatabase) GraphDatabases.create(new File("config/neo4j.properties"));
        gdb.setGraphSchema(GraphSchema.load(new File("config/dbschema.xml")));

        try(VGraphSession s = gdb.connect(REF_ID)) {
            s.deleteNodes(null, Parameters.empty());
        }

        // try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
        //     s.createNode("msource", MapUtils.asMap(
        //         "name", "projectTest"
        //     ));
        // }

        String n1, n2;
        try(GraphSession s = gdb.connect(REF_ID, "source", 1)) {
            n1 = s.createNode("module", MapUtils.asMap(
                "name", "mymodule",
                "fullname", "p.mymodule",
                "digest", 111,
                "mrefId", "01",
                "path", "mypath"
            ));
        }

        try(GraphSession s = gdb.connect(REF_ID, "source", 4)) {
            n2 = s.createNode("module", MapUtils.asMap(
                "name", "mymodule",
                "fullname", "p.mymodule",
                "digest", 444,
                "mrefId", "01",
                "path", "mypath"
            ));
        }

        try(GraphSession s = gdb.connect(REF_ID, "source", 5)) {
            s.deleteNodes(Arrays.asList(n1, n2));
        }

        try(GraphSession s = gdb.connect(REF_ID, "source", 5)) {
            s.deleteNodes("module", MapUtils.asMap(
                "fullname", "p.mymodule"
            ));
        }

        gdb.destroy();
        CacheManager.shutdown();
    }
}
