package org.hls.check;

import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.neo4j.Neo4JOnlineDatabaseFactory;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.PropertiesUtils;

import java.net.URISyntaxException;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws URISyntaxException {
        Logger.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        try(GraphSession s = gdb.connect()) {
            s.createNode("test", MapUtils.asMap(
                "name", "test",
                "a", 1,
                "b", "due"
            ));
        }

    }
}
