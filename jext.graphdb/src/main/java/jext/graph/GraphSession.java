package jext.graph;

import java.util.Collection;
import java.util.Map;

public interface GraphSession extends java.lang.AutoCloseable {

    String ID = "$id";
    String LABELS = "$labels";
    String TYPE = "$type";

    // ----------------------------------------------------------------------
    // Database Session
    // ----------------------------------------------------------------------

    GraphDatabase getDatabase();

    /**
     * Check if a connection with the database is active
     * @return if exists a connection
     */
    boolean isConnected();

    /**
     * Close the connection with the database.
     */
    void close();

    // ----------------------------------------------------------------------
    // Transactions
    // ----------------------------------------------------------------------

    //
    // Unsupported for now
    //
    void beginTransaction();
    void commit();
    void rollback();

    // ----------------------------------------------------------------------
    // DeleteAll (DANGEROUS)
    // ----------------------------------------------------------------------

    /**
     * Delete the content of the database (DANGEROUS)
     */
    void deleteAll();

    // ----------------------------------------------------------------------
    // Query Nodes
    // ----------------------------------------------------------------------
    //

    /**
     * Select the nodes with the specified type and properties.
     *
     * Note: if nodeType and nodeProps are null, select ALL nodes in the database
     *
     * @param nodeType node type or null
     * @param nodeProps node properties or null
     * @return
     */
    Query queryNodes(String nodeType, Map<String, Object> nodeProps);

    String/*nodeId*/ findNode(String nodeType, Map<String, Object> nodeProps);

    // ----------------------------------------------------------------------
    // Operations on a single node
    // ----------------------------------------------------------------------

    /**
     * Create a new node
     *
     * @param nodeType node type
     * @param nodeProps  properties of the node
     * @return nodeId
     */
    String/*nodeId*/ createNode(String nodeType, Map<String,Object> nodeProps);

    boolean deleteNode(String nodeId);

    boolean existsNode(String nodeId);

    Map<String, Object> getNodeValues(String nodeId);

    void deleteNodes(Collection<String> nodeIds);

    void deleteNodes(String nodeType, Map<String,Object> nodeProps);

    // ----------------------------------------------------------------------

    /**
     * Insert/onUpdate the props in 'mergeProps'
     *
     * @param nodeId nodeId
     * @param updateProps properties to onUpdate
     */
    void   setNodeProperties(String nodeId, Map<String,Object> updateProps);

    /**
     * Insert/onUpdate the specified prop
     *
     * @param nodeId nodeId
     * @param name name of the property
     * @param value values of the property
     */
    void   setNodeProperty(String nodeId, String name, Object value);

    // ----------------------------------------------------------------------
    // Special node queries
    // ----------------------------------------------------------------------

    /**
     * Select the adjacent nodes to the specified node, following the
     * specified edge, and selecting only the nodes/edges with the specified
     * properties
     *
     * @param edgeType type of the edge or null
     * @param fromId  node id
     * @param direction direction of the edges (Input, Output, Any)
     * @return
     */
    Query queryAdjacentNodes(
        String fromId, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);

    Query queryAdjacentNodesAlgorithm(
        String fromId, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);


    /**
     * Select the nodes with the specified degree
     *
     * @param nodeType node type
     * @param edgeType edge type
     * @param ndegree min/max input/output degrees
     * @param nodeProps properties of the node
     * @param edgeProps properties of the edge
     * @return
     */
    Query queryNodesWithDegree(
        String nodeType, String edgeType, NodeDegree ndegree,
        Map<String, Object> nodeProps, Map<String, Object> edgeProps);

    /**
     * Retrieve the edge list: the list of pairs (from -> to)
     * @param edgeType edge type
     * @param fromProps properties of the 'from' nodes
     * @param toProps properties of the 'to' nodes
     * @param edgeProps properties of the edges
     * @return
     */
    Query queryEdges(String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps);

    // ----------------------------------------------------------------------
    // Edge
    // ----------------------------------------------------------------------

    /**
     * Select the edges starting from the specified
     * @param edgeType edge type
     * @param fromId source node id
     * @param toId destination node id
     * @param direction direction of the edge (Input, Output, Any)
     * @param recursive if to select the edges in a recursive way
     * @param edgeProps edge properties
     * @return
     */
    Query  queryPath(String edgeType,
        String fromId, String toId, Direction direction, boolean recursive,
        Map<String, Object> edgeProps);

    /**
     * Create a new edge.
     * It is possible to specify if the edge is directed or undirected, using the property
     * - "edgeType" "directed" (default), "undirected"
     *
     * @param edgeType edge type
     * @param fromId source node id
     * @param toId destination node id
     * @param edgeProps properties of the edge
     * @return edgeId
     */
    String/*edgeId*/ createEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps);

    /**
     * Create multiple edges (1:n)
     *
     * @param edgeType
     * @param fromId
     * @param toIds
     * @param edgeProps
     */
    void createEdges(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps);

    /**
     * Create multiple edges (1:1 n times)
     */
    void createEdges(String edgeType, Collection<String> fromIds, Collection<String> toIds, Map<String,Object> edgeProps);

    /**
     * Find an edge and if it is not present, create it, otherwise onUpdate it with the createProps
     *
     * @param edgeType  edge type
     * @param fromId source node
     * @param toId destination node
     * @param edgeProps properties of the edge used in the search
     * @param createProps properties of the edge used if the edge is created
     * @return edgeId or null
     */
    String/*edgeId*/ findEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps, Map<String,Object> createProps);

    void deleteEdges(String edgeType,  // can be null
                     String fromNodeType, Map<String, Object> fromProps,
                     String toNodeType, Map<String, Object> toProps,
                     Map<String,Object> edgeProps);

    /**
     * Insert/onUpdate the props with 'updateProps'
     *
     * @param edgeId edgeId
     * @param updateProps properties to onUpdate
     */
    void   setEdgeProperties(String edgeId, Map<String,Object> updateProps);

    /**
     * Insert/onUpdate the prop 'name' with the values 'values'
     *
     * @param edgeId edgeId
     * @param name name of the property
     * @param value values of the property
     * @param override is to override the value
     */
    void   setEdgeProperty(String edgeId, String name, Object value, boolean override);

    /**
     * Retrieve the properties of the edge
     *
     * @param edgeId edgeId
     */
    Map<String, Object> getEdgeProperties(String edgeId);

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    boolean isDAG(String nodeType, Map<String,Object> nodeProps,
                  String edgeType, Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------
    // Special
    // ----------------------------------------------------------------------

    Query queryUsing(String queryName,  Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
