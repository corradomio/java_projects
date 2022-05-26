package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.graph.GraphVersion;
import jext.graph.named.NamedIndex;
import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;
import jext.graph.schema.GraphSchema;
import jext.graph.schema.ModelSchema;
import jext.graph.schema.NodeSchema;
import jext.logging.Logger;
import jext.net.URL;
import jext.util.MapUtils;
import jext.util.PropertiesUtils;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class Neo4JOnlineDatabase implements GraphDatabase {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String USER = "user";
    private static final String PASSWORD = "password";

    // compatibility with a previous implementation
    private static final String URL_EXT = "neo4j.online.url";
    private static final String USER_EXT = "neo4j.online.user";
    private static final String PASSWORD_EXT = "neo4j.online.password";

    private static final String MAX_DELETE = "maxdelete";
    private static final String MAX_STATEMENTS = "maxstatements";

    public static final String NAME = "name";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(Neo4JOnlineDatabase.class);

    private final URL url;
    private final Properties props;
    private Driver driver;
    private GraphVersion version;

    private int maxDelete, maxStatements;

    private NamedQueries namedQueries = new NamedQueries();
    private NamedIndices namedIndices = new NamedIndices();
    private GraphSchema graphSchema = new GraphSchema();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Neo4JOnlineDatabase(URL url, Properties props) {
        this.url = url;
        this.props = props;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void initialize() {

        String uri = this.url.getUrl();
        String user = this.props.getProperty(USER);
        String password = this.props.getProperty(PASSWORD);

        // compatibility with a previous implementation
        if (user == null) user = this.props.getProperty(USER_EXT);
        if (password == null) password = props.getProperty(PASSWORD_EXT);

        this.driver = org.neo4j.driver.GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );

        try(GraphSession session = this.connect()) {
            String s = "CALL dbms.components() YIELD versions, edition UNWIND versions AS version RETURN version, edition";
            Map<String, Object> result = session.query(s, Collections.emptyMap()).result().next();

            String dbversion = result.get("version").toString();
            this.version = new GraphVersion(dbversion);
        }

        this.maxDelete = PropertiesUtils.getInt(props, MAX_DELETE, Neo4JOnlineSession.MAX_DELETE_NODES);
        this.maxStatements = PropertiesUtils.getInt(props, MAX_STATEMENTS, Neo4JOnlineSession.MAX_STATEMENTS);
    }

    @Override
    public GraphDatabase deleteAll() {
        try(GraphSession session = connect()) {
            session.deleteAll();
        }
        return this;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public GraphVersion getVersion() {
        return version;
    }

    // ----------------------------------------------------------------------
    // NamedIndices
    // ----------------------------------------------------------------------

    @Override
    public GraphDatabase setNamedIndices(NamedIndices nindices) {
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
    // NamedQueries
    // ----------------------------------------------------------------------

    @Override
    public String getQuery(String qname) {
        return namedQueries.getQuery(qname, version.getVersion());
    }

    @Override
    public GraphDatabase setNamedQueries(NamedQueries namedQueries) {
        this.namedQueries = namedQueries;
        return this;
    }

    @Override
    public NamedQueries getNamedQueries() {
        return namedQueries;
    }

    // @Override
    // public void registerQueries(Map<String/*name*/, String/*body*/> namedQueries) {
    //     this.namedQueries.registerQueries(namedQueries);
    // }

    // ----------------------------------------------------------------------
    // Graph Schema
    // ----------------------------------------------------------------------

    @Override
    public GraphSchema getGraphSchema() {
        return graphSchema;
    }

    @Override
    public GraphDatabase setGraphSchema(GraphSchema graphSchema) {
        this.graphSchema = graphSchema;
        return this;
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
        return new Neo4JOnlineSession(this, refId, model, rev)
            .connect();
    }

    @Override
    public void destroy() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    int getMaxDelete() {
        return maxDelete;
    }

    int getMaxStatements() {
        return maxStatements;
    }

    Driver getDriver() { return driver; }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
