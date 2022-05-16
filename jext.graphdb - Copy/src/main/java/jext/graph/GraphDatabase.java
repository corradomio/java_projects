package jext.graph;

import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;
import jext.graph.schema.GraphSchema;

public interface GraphDatabase {

    GraphVersion getVersion();

    /**
     * Initialize the database Connector
     */
    void initialize();

    /**
     * Create a connection to the database.
     * It is necessary to create a connection for each thread.
     * Inside the same thread, it is possible to use the connection multiple times
     *
     * @return a Session, a wrapper to the connection
     */
    GraphSession connect();

    /**
     * Delete the content of the database
     */
    GraphDatabase deleteAll();

    /**
     * Release the resources assigned to the driver
     */
    void destroy();

    // ----------------------------------------------------------------------
    // Named queries
    // ----------------------------------------------------------------------

    /**
     * Register a dictionary of named queries
     *
     * @param namedQueries a map 'name -> Cypher statement'
     */
    GraphDatabase setNamedQueries(NamedQueries namedQueries);
    NamedQueries  getNamedQueries();

    /**
     * Retrieve the named query
     * @param qname query name to use
     * @return query body
     */
    String getQuery(String qname);

    // ----------------------------------------------------------------------
    // Database Schema
    // ----------------------------------------------------------------------

    GraphDatabase setGraphSchema(GraphSchema graphSchema);
    GraphSchema   getGraphSchema();

    // ----------------------------------------------------------------------
    // Database indices
    // ----------------------------------------------------------------------

    GraphDatabase setNamedIndices(NamedIndices nindices);
    NamedIndices  getNamedIndices();
}
