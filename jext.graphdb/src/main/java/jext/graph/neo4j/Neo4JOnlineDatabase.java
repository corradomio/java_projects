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

    private final Map<String, String> namedQueries = new HashMap<>();

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

        for(String qname : nqueries.keySet()) {
            if (namedQueries.containsKey(qname))
                logger.errorf("Query %s with body '%s' already defined as '%s'",
                    qname, nqueries.get(qname), namedQueries.get(qname));
            else {
                namedQueries.put(qname, nqueries.get(qname));
            }
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

    Driver getdriver() { return driver; }

    String getQuery(String qname) {
        if (!namedQueries.containsKey(qname))
            throw new DatabaseException("USER", String.format("Named query '%s' not defined", qname));

        return StringUtils.trim(namedQueries.get(qname));
    }

}
