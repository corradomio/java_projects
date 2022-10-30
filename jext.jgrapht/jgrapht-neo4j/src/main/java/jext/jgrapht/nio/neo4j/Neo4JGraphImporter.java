package jext.jgrapht.nio.neo4j;

import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.internal.value.IntegerValue;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;


public class Neo4JGraphImporter<V, E> implements GraphImporter<V, E> {

    static final String NEO4J_URI      = "neo4j.uri";
    static final String NEO4J_USERNAME = "neo4j.username";
    static final String NEO4J_PASSWORD = "neo4j.password";
    static final String REF_ID = "refId";

    private Driver driver;
    private Session session;

    /** Query to retrieve all nodes */
    private String vertices;
    /** Query to retrieve all edges */
    private String edges;
    /** Parameters used in the queries */
    private final Map<String, Object> parameters = new HashMap<>();
    /** Label used for the source node */
    private String slabel = "s";
    /** Label used for the target node */
    private String tlabel = "t";
    /** Neo4J record -> vertex */
    private BiFunction<String, Map<String, Object>, V> toVertex = (id, map) -> (V)id;

    private final Map<String, V> vmap = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Neo4JGraphImporter() {

    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    private static boolean isCypher(String s) {
        return s != null && s.toLowerCase().startsWith("match ");
    }

    public Neo4JGraphImporter<V, E> vertices(String cypher) {
        if (isCypher(cypher)) {
            this.vertices = cypher;
            return this;
        }
        else
        {
            String label = cypher;
            if (label == null || label.isEmpty()) {
                vertices("MATCH (s {refId:$refId}) RETURN id(s) AS s, s.fullname AS name, labels(s)[0] AS type");
                edges("MATCH (s {refId:$refId}) --> (t) RETURN id(s) AS s, id(t) AS t");
            } else if ("type" .equals(label)) {
                vertices(String.format("MATCH (s:%1$s {refId:$refId, type:'type'}) RETURN id(s) AS s, s.fullname AS name", label));
                edges(String.format("MATCH (s:%1$s {refId:$refId, type:'type'}) --> (t:%1$s {type:'type'}) RETURN id(s) AS s, id(t) AS t", label));
            } else {
                vertices(String.format("MATCH (s:%1$s {refId:$refId}) RETURN id(s) AS s, s.fullname AS name", label));
                edges(String.format("MATCH (s:%1$s {refId:$refId}) --> (t:%1$s) RETURN id(s) AS s, id(t) AS t", label));
            }
            labels("s", "t");
            return this;
        }
    }

    public Neo4JGraphImporter<V, E> toVertex(BiFunction<String, Map<String, Object>, V> converter) {
        this.toVertex = converter;
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

    public Map<String, Object> parameters() {
        return this.parameters;
    }

    public Neo4JGraphImporter<V, E> parameters(Map<String, Object> params) {
        this.parameters.putAll(params);
        return this;
    }

    public Neo4JGraphImporter<V, E> parameters(Object... params) {
        for(int i=0; i<params.length; i+=2) {
            String name  = params[i+0].toString();
            Object value = params[i+1];
            this.parameters.put(name, value);
        }
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

            // params.putAll(properties); // doesn't work cast problems
            properties.stringPropertyNames().forEach(key -> parameters.put(key, properties.get(key)));

            importGraph(graph);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
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
        String uri = (String) parameters.get(NEO4J_URI);
        String username = (String) parameters.get(NEO4J_USERNAME);
        String password = (String) parameters.get(NEO4J_PASSWORD);

        driver = GraphDatabase.driver( uri, AuthTokens.basic( username, password ) );
        session = driver.session();
    }

    private void query(Graph<V, E> graph) {
        Set<V> vertices = vertices(graph);
        edges(graph, vertices);
    }

    private Set<V> vertices(Graph<V, E> graph) {
        if (vertices == null)
            return Collections.emptySet();

        Result r = session.run(vertices, parameters);
        while (r.hasNext()) {
            Record rec = r.next();
            V vertex = get(rec);

            graph.addVertex(vertex);
        }

        return graph.vertexSet();
    }

    private V get(Record rec) {
        String id = rec.get(slabel).toString();
        Map<String, Object> map = rec.asMap();
        V vertex = toVertex.apply(id, map);
        vmap.put(id, vertex);
        return vertex;
    }

    private V get(Record rec, String label) {
        String id = rec.get(label).toString();
        return vmap.get(id);
    }

    private void edges(Graph<V, E> graph, Set<V> vertices) {
        Result r = session.run(edges, parameters);
        while (r.hasNext()) {
            Record rec = r.next();
            V sourceVertex = get(rec, slabel);
            V targetVertex = get(rec, tlabel);

            if (!vertices.contains(sourceVertex)) {
                vertices.add(sourceVertex);
                graph.addVertex(sourceVertex);
            }
            if (!vertices.contains(targetVertex)) {
                vertices.add(targetVertex);
                graph.addVertex(targetVertex);
            }

            if (!sourceVertex.equals(targetVertex))
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
