package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.io.File;
import java.util.Properties;

public class CreateNodes {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        
        GraphDatabase gdb = GraphDatabases.create(new File("config/neo4j.properties"));
        gdb.setGraphSchema(new File("config/dbschema.xml"));

        try(GraphSession s = gdb.connect("0001", "source", 0)) {
            s.createNode("msource", MapUtils.asMap());
            // s.createNode("U", Parameters.empty(), Parameters.params(
            //     "revision", 3
            // ));
        }
        finally {
            gdb.destroy();
        }
    }
}
