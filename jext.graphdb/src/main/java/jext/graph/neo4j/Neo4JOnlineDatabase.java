package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.graph.GraphVersion;
import jext.graph.named.NamedQueries;
import jext.logging.Logger;
import jext.net.URL;
import jext.util.PropertiesUtils;
import jext.util.StringUtils;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(Neo4JOnlineDatabase.class);

    private final URL url;
    private Driver driver;
    protected final Properties props;
    protected GraphVersion version;

    private int maxDelete, maxStatements;

    // private final Map<String/*version*/, Map<String/*name*/, String/*body*/>> namedQueries = new HashMap<>();
    protected NamedQueries namedQueries = new NamedQueries();

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
            this.version = new GraphVersion(result.get("version").toString());
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
        // if (!namedQueries.containsKey(qname))
        //     throw new DatabaseException("USER", String.format("Named query '%s' not defined", qname));

        // Map<String, String> nqueries;
        //
        // // check if is defined in specific version
        // for(String version : namedQueries.keySet()) {
        //     nqueries = namedQueries.get(version);
        //
        //     // skip version ""
        //     if (version.isEmpty()) continue;
        //     // skip wrong version
        //     if (!this.version.getVersion().startsWith(version)) continue;
        //     // skip if not defined
        //     if (!nqueries.containsKey(qname)) continue;
        //
        //     return trim(nqueries.get(qname));
        // }
        //
        // // check if it is defined in version ""
        // nqueries = namedQueries.get("");
        // if (nqueries.containsKey(qname))
        //     return trim(nqueries.get(qname));
        //
        // throw new DatabaseException("USER", String.format("Named query '%s' not defined", qname));
    }

    private String trim(String body) {
        // Doesn't work! It is not enough to change the namespace!
        // if (majorVersion == 4 && body.contains("algo."))
        //     body = body.replace("algo.", "gds.");
        return StringUtils.trim(body);
    }

}
