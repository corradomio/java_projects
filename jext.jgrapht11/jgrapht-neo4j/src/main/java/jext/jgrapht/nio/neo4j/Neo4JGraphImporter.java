package jext.jgrapht.nio.neo4j;

import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class Neo4JGraphImporter<V, E> implements GraphImporter<V, E> {

    private static final String NEO4J_URI      = "neo4j.uri";
    private static final String NEO4J_USERNAME = "neo4j.username";
    private static final String NEO4J_PASSWORD = "neo4j.password";

    private Properties properties;
    private Driver driver;
    private Session session;

    private boolean direct;
    private String fromType, toType, edgeType;
    private Map<String, Object> fromProps, toProps, edgeProps;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Neo4JGraphImporter() {

    }

    public Neo4JGraphImporter(String fromType, String toType, String edgeType) {
        this(fromType, toType, edgeType, true);
    }

    public Neo4JGraphImporter(String fromType, String toType, String edgeType, boolean direct) {
        this.from(fromType, Collections.emptyMap());
        this.to(toType, Collections.emptyMap());
        this.edge(edgeType, Collections.emptyMap());
        this.direct = direct;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public Neo4JGraphImporter<V, E> direct() {
        this.direct = true;
        return this;
    }

    public Neo4JGraphImporter<V, E> from(String label, Map<String, Object> props) {
        this.fromType = label;
        this.fromProps = props;
        return this;
    }

    public Neo4JGraphImporter<V, E> to(String label, Map<String, Object> props) {
        this.toType = label;
        this.toProps = props;
        return this;
    }

    public Neo4JGraphImporter<V, E> edge(String label, Map<String, Object> props) {
        this.edgeType = label;
        this.edgeProps = props;
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    @Override
    public void importGraph(Graph<V, E> graph, Reader reader) {
        properties = new Properties();
        try {
            properties.load(reader);

            connect();
            query();
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        finally {
            disconnect();
        }
    }

    private void connect() {
        String uri = properties.getProperty(NEO4J_URI);
        String username = properties.getProperty(NEO4J_USERNAME);
        String password = properties.getProperty(NEO4J_PASSWORD);

        driver = GraphDatabase.driver( uri, AuthTokens.basic( username, password ) );
        session = driver.session();
    }

    private void disconnect() {
        if (session != null)
            session.close();
        if (driver != null)
            driver.close();
    }

    private void query() {

        new Neo4JSession(session)
            .queryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps)
            .result()
            .forEach(System.out::println);
    }
}
