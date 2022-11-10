package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.graph.schema.GraphSchema;

import javax.annotation.Nullable;

public interface VGraphDatabase extends GraphDatabase {

    VGraphDatabase setGraphSchema(GraphSchema graphSchema);

    VGraphSession connect(@Nullable String refId);
    VGraphSession connect(@Nullable String refId, @Nullable String model, int rev);
}
