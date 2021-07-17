package jext.jgrapht.nio.neo4j;

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
import java.util.HashMap;
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

    /** Query to retrieve all nodes */
    private String vertices;
    /** Query to retrieve all edges */
    private String edges;
    private Map<String, Object> params = new HashMap<>();
    private String slabel = "s", tlabel = "t";

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Neo4JGraphImporter() {
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public Neo4JGraphImporter<V, E> vertices(String cypher) {
        this.vertices = cypher;
        return this;
    }

    /**
     * Note: the query must return 2 values with labels 'source' and 'target'.
     * @param cypher query to use to retrieve the edges
     * @return this
     */
    public Neo4JGraphImporter<V, E> edges(String cypher) {
        this.edges = cypher;
        return this;
    }

    public Neo4JGraphImporter<V, E> parameters(Map<String, Object> params) {
        this.params.putAll(params);
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
        if (reader != null)
        try {
            Properties properties = new Properties();
            properties.load(reader);
            properties.stringPropertyNames().forEach(key -> {
                params.put(key, properties.get(key));
            });
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        importGraph(graph);
    }

    public void importGraph(Graph<V, E> graph) {
        try {
            connect();
            query(graph);
        }
        finally {
            disconnect();
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void connect() {
        String uri = (String) params.get(NEO4J_URI);
        String username = (String) params.get(NEO4J_USERNAME);
        String password = (String) params.get(NEO4J_PASSWORD);

        driver = GraphDatabase.driver( uri, AuthTokens.basic( username, password ) );
        session = driver.session();
    }

    private void query(Graph<V, E> graph) {
        Set<V> vertices = vertices(graph);
        edges(graph, vertices);
    }

    private Set<V> vertices(Graph<V, E> graph) {
        Set<V> vset = new HashSet<>();
        if (vertices == null)
            return vset;

        Result r = session.run(vertices, params);
        while (r.hasNext()) {
            Record rec = r.next();
            V vertex = (V)rec.get(slabel);
            if (!vset.contains(vertex)) {
                vset.add(vertex);
                graph.addVertex(vertex);
            }
        }

        return vset;
    }

    private void edges(Graph<V, E> graph, Set<V> vertices) {
        Result r = session.run(edges, params);
        while (r.hasNext()) {
            Record rec = r.next();
            V sourceVertex = (V)rec.get(slabel);
            V targetVertex = (V)rec.get(tlabel);

            if (!vertices.contains(sourceVertex)) {
                vertices.add(sourceVertex);
                graph.addVertex(sourceVertex);
            }
            if (!vertices.contains(targetVertex)) {
                vertices.add(targetVertex);
                graph.addVertex(targetVertex);
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
