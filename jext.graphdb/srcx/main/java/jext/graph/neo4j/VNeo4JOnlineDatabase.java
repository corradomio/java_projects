package jext.graph.neo4j;

import jext.graph.GraphSession;
import jext.graph.schema.GraphSchema;
import jext.net.URL;

import javax.annotation.Nullable;
import java.util.Properties;

import static jext.graph.schema.NodeSchema.NO_REV;

public class VNeo4JOnlineDatabase extends Neo4JOnlineDatabase implements VGraphDatabase {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private GraphSchema  graphSchema = new GraphSchema();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public VNeo4JOnlineDatabase(URL url, Properties props) {
        super(url, props);
    }

    // ----------------------------------------------------------------------
    // Graph Schema
    // ----------------------------------------------------------------------

    @Override
    public VGraphDatabase setGraphSchema(GraphSchema graphSchema) {
        this.graphSchema = graphSchema;
        return this;
    }

    GraphSchema getGraphSchema() {
        return graphSchema;
    }

    // ----------------------------------------------------------------------
    // IO
    // ----------------------------------------------------------------------

    @Override
    public GraphSession connect() {
        return connect(null, null, NO_REV);
    }

    @Override
    public GraphSession connect(@Nullable String refId) {
        return connect(refId, null, NO_REV);
    }

    @Override
    public GraphSession connect(@Nullable String refId, @Nullable String model, int rev) {
        Neo4JOnlineSession session = new VNeo4JOnlineSession(this, refId, model, rev);
        return session.connect();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
