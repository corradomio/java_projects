package jext.graph;

import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;
import jext.graph.neo4j.VGraphDatabase;

import java.util.Map;

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

    GraphDatabase setNamedQueries(NamedQueries namedQueries);
    NamedQueries getNamedQueries();

    GraphDatabase setNamedIndices(NamedIndices nindices);
    NamedIndices getNamedIndices();

}
