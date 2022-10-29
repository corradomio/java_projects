package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.graph.schema.GraphSchema;

import javax.annotation.Nullable;

public interface VGraphDatabase extends GraphDatabase {

    VGraphDatabase setGraphSchema(GraphSchema graphSchema);

    GraphSession connect(@Nullable String refId);
    GraphSession connect(@Nullable String refId, @Nullable String model, int rev);
}
