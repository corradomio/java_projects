package jext.graph;

import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;

import java.util.Optional;

public interface GraphDatabase {

    /**
     * Neo4j version as string "3.5.13"
     */
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

    /**
     * Set the registered named queries
     * @param namedQueries named queries registry
     */
    GraphDatabase setNamedQueries(NamedQueries namedQueries);
    Optional<NamedQueries> getNamedQueries();

    /**
     * Set the list of mandatory indices
     * @param nindices list of mandatory indices
     */
    GraphDatabase setNamedIndices(NamedIndices nindices);
    Optional<NamedIndices> getNamedIndices();

}
