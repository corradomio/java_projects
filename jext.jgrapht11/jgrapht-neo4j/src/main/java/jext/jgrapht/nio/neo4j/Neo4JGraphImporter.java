package jext.jgrapht.nio.neo4j;

import jext.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class Neo4JGraphImporter<V, E> implements GraphImporter<V, E> {

    private static final String NEO4J_URI      = "neo4j.uri";
    private static final String NEO4J_USERNAME = "neo4j.username";
    private static final String NEO4J_PASSWORD = "neo4j.password";

    private Driver driver;
    private Session session;

    private String cypher;
    private Map<String, Object> params;
    private String slabel = "source", tlabel = "target";

    private static Logger logger = Logger.getLogger(Neo4JGraphImporter.class);

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Neo4JGraphImporter() {
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    /**
     * Note: the query must return 2 values with labels 'source' and 'target'.
     * @param cypher query to use to retrieve the edges
     * @param params parameters passed to the query
     * @return this
     */
    public Neo4JGraphImporter<V, E> query(String cypher, Map<String, Object> params) {
        this.cypher = cypher;
        this.params = params;
        return this;
    }

    /**
     * Set the labels to use to retrieve source and target vertices
     * @param slabel label for the source vertex
     * @param tlabel label for the target vertex
     * @return this
     */
    public Neo4JGraphImporter<V, E> labels(String slabel, String tlabel) {
        this.slabel = slabel;
        this.tlabel = tlabel;
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * The reader is a property file containing the configuration parameters to
     * connect to Neo4j
     *
     * @param graph
     * @param reader
     */
    @Override
    public void importGraph(Graph<V, E> graph, Reader reader) {
        try {
            Properties properties = new Properties();
            properties.load(reader);

            connect(properties);
            query(graph);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        finally {
            disconnect();
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void connect(Properties properties) {
        String uri = properties.getProperty(NEO4J_URI);
        String username = properties.getProperty(NEO4J_USERNAME);
        String password = properties.getProperty(NEO4J_PASSWORD);

        driver = GraphDatabase.driver( uri, AuthTokens.basic( username, password ) );
        session = driver.session();
    }

    private void query(Graph<V, E> graph) {

        Set<V> vertices = new HashSet<>();

        logger.infof("%s\n%s", this.cypher, this.params);

        Result r = session.run(cypher, params);
        while (r.hasNext()) {
            Record rec = r.next();
            V sourceVertex = (V)rec.get(slabel);
            V targetVertex = (V)rec.get(tlabel);

            if (!vertices.contains(sourceVertex)) {
                graph.addVertex(sourceVertex);
                vertices.add(sourceVertex);
            }
            if (!vertices.contains(targetVertex)) {
                graph.addVertex(targetVertex);
                vertices.add(targetVertex);
            }

            graph.addEdge(sourceVertex, targetVertex);
        }
    }

    private void disconnect() {
        if (session != null)
            session.close();
        if (driver != null)
            driver.close();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
