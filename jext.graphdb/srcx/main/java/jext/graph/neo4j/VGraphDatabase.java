package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.graph.named.NamedIndices;
import jext.graph.schema.GraphSchema;

import java.io.File;

public interface VGraphDatabase extends GraphDatabase {

    GraphSchema getGraphSchema();
    VGraphDatabase setGraphSchema(GraphSchema graphSchema);
    VGraphDatabase setGraphSchema(File schemaFile);

    GraphSession connect(String refId);
    GraphSession connect(String refId, String model, int rev);
}
