package jext.graph.neo4j;

import jext.graph.GraphSession;
import jext.graph.schema.GraphSchema;
import jext.net.URL;

import java.util.Properties;

public class VNeo4JOnlineDatabase extends Neo4JOnlineDatabase implements VGraphDatabase {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private GraphSchema  graphSchema  = new GraphSchema();

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
        return connect(null, null, -1);
    }

    @Override
    public GraphSession connect(String refId) {
        return connect(refId, null, -1);
    }

    @Override
    public GraphSession connect(String refId, String model, int rev) {
        Neo4JOnlineSession session = new VNeo4JOnlineSession(this, refId, model, rev);
        return session.connect();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
