package jext.graph;

import java.util.Map;

public interface GraphDatabase {

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

    /**
     * Register a dictionary of named queries
     *
     * @param namedQueries a map 'name -> Cypher statement'
     */
    void registerQueries(Map<String, String> namedQueries);
}
