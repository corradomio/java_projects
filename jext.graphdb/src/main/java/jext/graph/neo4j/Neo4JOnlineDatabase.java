package jext.graph.neo4j;

import jext.graph.GraphDatabase;
import jext.graph.GraphSession;
import jext.logging.Logger;
import jext.net.URL;
import jext.util.StringUtils;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.exceptions.DatabaseException;

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

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(Neo4JOnlineDatabase.class);

    private final URL url;
    private final Properties props;
    private Driver driver;
    private String version;
    private int majorVersion;

    //private final Map<String, String> namedQueries = new HashMap<>();
    private final Map<String/*version*/, Map<String/*name*/, String/*body*/>> namedQueries = new HashMap<>();

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

        String uri = url.getUrl();
        String user = props.getProperty(USER);
        String password = props.getProperty(PASSWORD);

        // compatibility with a previous implementation
        if (user == null) user = props.getProperty(USER_EXT);
        if (password == null) password = props.getProperty(PASSWORD_EXT);

        this.driver = org.neo4j.driver.GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );

        try(GraphSession session = this.connect()) {
            String s = "CALL dbms.components() YIELD versions, edition UNWIND versions AS version RETURN version, edition";
            Map<String, Object> result = session.query(s, Collections.emptyMap()).result().next();
            version = result.get("version").toString();
            if (version.startsWith("4."))
                majorVersion = 4;
            else
                majorVersion = 3;
        }
    }

    @Override
    public GraphDatabase deleteAll() {
        try(GraphSession session = connect()) {
            session.deleteAll();
        }
        return this;
    }

    // ----------------------------------------------------------------------
    // Named queries
    // ----------------------------------------------------------------------

    /**
     * Register a list of named queries:
     *
     * @param nqueries named queries
     */
    public void registerQueries(Map<String, String> nqueries) {
        for(String qname : nqueries.keySet())
            add("", qname, nqueries.get(qname));
    }

    public void registerVersionedQueries(Map<String/*version*/, Map<String/*name*/, String/*body*/>> namedQueries) {
        for(String version : namedQueries.keySet()) {
            Map<String, String> nqueries = namedQueries.get(version);
            for(String qname : nqueries.keySet())  {
                String body = nqueries.get(qname);
                add(version, qname, body);
            }
        }
    }

    private void add(String version, String qname, String body) {
        if (!version.isEmpty() && !version.endsWith("."))
            version += ".";
        if (!namedQueries.containsKey(version))
            namedQueries.put(version, new HashMap<>());

        Map<String, String> nqueries = namedQueries.get(version);
        if (nqueries.containsKey(qname))
            logger.errorf("Query %s with body '%s' already defined as '%s' for version '%s'",
                qname, body, namedQueries.get(qname), version);
        else {
            nqueries.put(qname, body);
        }
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
    public String getVersion() {
        return version;
    }

    @Override
    public int getMajorVersion() {
        return majorVersion;
    }

    Driver getdriver() { return driver; }

    String getQuery(String qname) {
        // if (!namedQueries.containsKey(qname))
        //     throw new DatabaseException("USER", String.format("Named query '%s' not defined", qname));

        Map<String, String> nqueries;

        // check if is defined in specific version
        for(String version : namedQueries.keySet()) {
            nqueries = namedQueries.get(version);

            // skip version ""
            if (version.isEmpty()) continue;
            // skip wrong version
            if (!this.version.startsWith(version)) continue;
            // skip if not defined
            if (!nqueries.containsKey(qname)) continue;

            return trim(nqueries.get(qname));
        }

        // check if it is defined in version ""
        nqueries = namedQueries.get("");
        if (nqueries.containsKey(qname))
            return trim(nqueries.get(qname));

        throw new DatabaseException("USER", String.format("Named query '%s' not defined", qname));
    }

    private String trim(String body) {
        // Doesn't work! It is not enough to change the namespace!
        // if (majorVersion == 4 && body.contains("algo."))
        //     body = body.replace("algo.", "gds.");
        return StringUtils.trim(body);
    }

}
