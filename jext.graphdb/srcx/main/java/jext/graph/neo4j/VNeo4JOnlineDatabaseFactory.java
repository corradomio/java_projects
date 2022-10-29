package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphDatabaseFactory;
import jext.net.URL;

import java.util.Properties;

public class VNeo4JOnlineDatabaseFactory implements GraphDatabaseFactory {

    @Override
    public GraphDatabase newGraphDatabase(URL url, Properties props) {
        return new VNeo4JOnlineDatabase(url, props);
    }
}
