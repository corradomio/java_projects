package jext.graph.neo4j;

import jext.graph.GraphDatabaseException;
import jext.graph.GraphSession;
import jext.graph.named.NamedIndex;
import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;
import jext.graph.schema.GraphSchema;
import jext.net.URL;
import jext.util.MapUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class VNeo4JOnlineDatabase extends Neo4JOnlineDatabase implements VGraphDatabase {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

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
    public GraphSchema getGraphSchema() {
        return graphSchema;
    }

    @Override
    public VGraphDatabase setGraphSchema(GraphSchema graphSchema) {
        this.graphSchema = graphSchema;
        return this;
    }

    @Override
    public VGraphDatabase setGraphSchema(File schemaFile)  {
        try {
            GraphSchema graphSchema = GraphSchema.load(schemaFile);
            return setGraphSchema(graphSchema);
        } catch (ParserConfigurationException| IOException | SAXException e) {
            throw new GraphDatabaseException(e);
        }
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
