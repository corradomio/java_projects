package jext.graph.neo4j;

import jext.graph.GraphDatabase;
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

    public static final String NAME = "name";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private NamedQueries namedQueries = new NamedQueries();
    private NamedIndices namedIndices = new NamedIndices();
    private GraphSchema graphSchema = new GraphSchema();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public VNeo4JOnlineDatabase(URL url, Properties props) {
        super(url, props);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // NamedIndices
    // ----------------------------------------------------------------------

    @Override
    public VGraphDatabase setNamedIndices(NamedIndices nindices) {
        this.namedIndices = nindices;
        initIndices();
        return this;
    }

    @Override
    public NamedIndices getNamedIndices() {
        return namedIndices;
    }

    private void initIndices() {
        List<NamedIndex> nindices = namedIndices.getIndices(version.getVersion());
        Set<String> inames = new TreeSet<>();

        try(GraphSession session = connect()) {
            if (version.getMajorVersion() == 3) {
                session.query("CALL db.indexes()", Collections.emptyMap()).result()
                    .forEach(nv -> {
                        String iname = (String) nv.get(NAME);
                        inames.add(iname);
                    });
            }
            else {
                session.query("SHOW INDEX", Collections.emptyMap()).result()
                    .forEach(nv -> {
                        String iname = (String) nv.get(NAME);
                        inames.add(iname);
                    });
            }

        }

        for(NamedIndex nindex : nindices) {
            if (inames.contains(nindex.getName()))
                continue;

            try(GraphSession session = connect()) {
                session.execute(nindex.getBody(), MapUtils.asMap(NAME, nindex.getName()));
            }
        }

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
        return new VNeo4JOnlineSession(this, refId, model, rev)
            .connect();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
