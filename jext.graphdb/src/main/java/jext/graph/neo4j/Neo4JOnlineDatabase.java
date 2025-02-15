package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.graph.GraphVersion;
import jext.graph.named.NamedIndex;
import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;
import jext.util.logging.Logger;
import jext.net.URL;
import jext.util.MapUtils;
import jext.util.PropertiesUtils;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.exceptions.ServiceUnavailableException;

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
    private static final String NAME = "name";
    private static final String REVBOLT = "revbolt";

    // compatibility with a previous implementation
    private static final String URL_EXT = "neo4j.online.url";
    private static final String USER_EXT = "neo4j.online.user";
    private static final String PASSWORD_EXT = "neo4j.online.password";

    private static final String MAX_DELETE = "maxdelete";
    private static final String MAX_STATEMENTS = "maxstatements";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(Neo4JOnlineDatabase.class);

    protected final URL url;
    protected Driver driver;
    protected final Properties properties;
    protected GraphVersion version;
    protected NamedQueries namedQueries = new NamedQueries();
    protected NamedIndices namedIndices = new NamedIndices();

    // used for autocommit & delete callbacks
    private int maxDelete, maxStatements;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Neo4JOnlineDatabase(URL url, Properties props) {
        this.url = url;
        this.properties = props;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void initialize() {

        String uri = this.url.getUrl();
        String user = this.properties.getProperty(USER);
        String password = this.properties.getProperty(PASSWORD);

        // compatibility with a previous implementation
        if (user == null) user = this.properties.getProperty(USER_EXT);
        if (password == null) password = properties.getProperty(PASSWORD_EXT);
        // support for 'bolt://...' and 'bolt:rev://...'
        if (uri.startsWith(REVBOLT)) uri = uri.substring(3);

        this.driver = org.neo4j.driver.GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );

        try(GraphSession session = this.connect()) {
            String s = "CALL dbms.components() YIELD versions, edition UNWIND versions AS version RETURN version, edition";
            Map<String,Object> result = session.query(s, Collections.emptyMap()).result().next();
            this.version = new GraphVersion(result.get("version").toString());
        }
        catch (ServiceUnavailableException e) {
            logger.errorf("Unable to connect to the database. Used default version '4.3'");
            this.version = new GraphVersion("4.3");
        }

        this.maxDelete = PropertiesUtils.getInt(properties, MAX_DELETE, Neo4JOnlineSession.MAX_DELETE_NODES);
        this.maxStatements = PropertiesUtils.getInt(properties, MAX_STATEMENTS, Neo4JOnlineSession.MAX_STATEMENTS);
    }

    @Override
    public GraphDatabase deleteAll() {
        try(GraphSession session = connect()) {
            session.deleteAll();
        }
        return this;
    }

    // ----------------------------------------------------------------------
    // IO
    // ----------------------------------------------------------------------

    @Override
    public GraphSession connect() {
        Neo4JOnlineSession session = new Neo4JOnlineSession(this);
        return session.connect();
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

    @Override
    public GraphVersion getVersion() {
        return version;
    }

    // ----------------------------------------------------------------------

    int getMaxDelete() {
        return maxDelete;
    }

    int getMaxStatements() {
        return maxStatements;
    }

    Driver getDriver() { return driver; }

    // ----------------------------------------------------------------------
    // Named queries
    // ----------------------------------------------------------------------

    @Override
    public GraphDatabase setNamedQueries(NamedQueries namedQueries) {
        this.namedQueries = namedQueries;
        return this;
    }

    @Override
    public NamedQueries getNamedQueries() {
        return namedQueries;
    }

    String getQuery(String qname) {
        return namedQueries.getQuery(qname, getVersion().getVersion());
    }

    // ----------------------------------------------------------------------
    // NamedIndices
    // ----------------------------------------------------------------------

    @Override
    public NamedIndices getNamedIndices() {
        return namedIndices;
    }

    @Override
    public GraphDatabase setNamedIndices(NamedIndices nindices) {
        this.namedIndices = nindices;
        initIndices();
        return this;
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
    // End
    // ----------------------------------------------------------------------

}
