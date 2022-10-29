package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.graph.schema.GraphSchema;

public interface VGraphDatabase extends GraphDatabase {

    VGraphDatabase setGraphSchema(GraphSchema graphSchema);

    GraphSession connect(String refId);
    GraphSession connect(String refId, String model, int rev);
}
