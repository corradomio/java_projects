package jext.graph;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface GraphSession extends AutoCloseable {

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
     * @param nodeType node type or null
     * @param nodeProps node properties
     *
     * Notes:
     *    1) if nodeType and nodeProps are null, select ALL nodes in the database
     *    2) if the value of a property is 'null' the property is checked for
     *       its existence.
     */
    Query queryNodes(@Nullable String nodeType, @Nullable Map<String, Object> nodeProps);

    /**
     * Find the node with the specified properties
     *
     * @param nodeType node type or null
     * @param nodeProps node properties
     * @return
     */
    @Nullable
    String/*nodeId*/ findNode(String nodeType, Map<String, Object> nodeProps);

    // ----------------------------------------------------------------------
    // Operations on a single node
    // ----------------------------------------------------------------------

    /**
     * Create a new node
     *
     * @param nodeType node type
     * @param nodeProps node properties
     * @return nodeId
     */
    String/*nodeId*/ createNode(String nodeType, Map<String,Object> nodeProps);

    /**
     * Check if the node exists
     */
    boolean existsNode(String nodeId);

    /**
     * Get the property values of the node
     */
    Map<String, Object> getNodeValues(String nodeId);
    Map<String, Object> getNodeValues(String nodeId, boolean cached);

    /**
     * Delete the node
     */
    boolean deleteNode(String nodeId);

    // ----------------------------------------------------------------------
    // Operations on node list
    // ----------------------------------------------------------------------

    long countNodes(String nodeType, Map<String,Object> nodeProps);

    /**
     * Get the property values for the nodes
     */
    List<Map<String, Object>> getNodesValues(List<String> nodeIds);

    /**
     * Delete the nodes
     */
    void deleteNodes(Collection<String> nodeIds);

    /**
     * Delete the nodes with the specified properties
     */
    void deleteNodes(String nodeType, Map<String,Object> nodeProps, long count);

    default void deleteNodes(String nodeType, Map<String,Object> nodeProps) {
        deleteNodes(nodeType, nodeProps, 0);
    }

    // ----------------------------------------------------------------------

    /**
     * Add/update the node properties
     * A property can be:
     *
     *      name
     *      name[index]
     *
     * @param nodeId nodeId
     * @param nodeProps properties to update
     */
    void setNodeProperties(String nodeId, Map<String,Object> nodeProps);
    void setNodeProperty(String nodeId, String name, Object value);
    void setNodeProperty(String nodeId, String name, int index, Object value);
    void setNodesProperties(Collection<String> nodeIds, Map<String,Object> nodeProps);

    void deleteNodeProperty(String nodeId, String name);
    void deleteNodesProperties(Collection<String> nodeId, Collection<String> names);

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

    Query queryAdjacentNodes(
        List<String> fromIds, String edgeType, Direction direction, boolean recursive,
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

    // /**
    //  *
    //  * @param ids list of node ids
    //  * @param ndegree minimum & maximum input/output degree
    //  * @return
    //  */
    // Query queryNodesWithDegree(List<String> ids,  String edgeType, NodeDegree ndegree,
    //     Map<String, Object> edgeProps);

    // ----------------------------------------------------------------------
    // Edge queries
    // ----------------------------------------------------------------------

    /**
     * Retrieve the edge list: the list of pairs (from -> to)
     *
     * @param edgeType edge type (can be null)
     * @param fromType node type (can be null)
     * @param fromProps properties of the 'from' nodes
     * @param toType node type (can be null)
     * @param toProps properties of the 'to' nodes
     * @param edgeProps properties of the edges
     * @return a list of maps with keys:
     *
     *      idfrom: long                id from node
     *      idto: long                  id to node
     *      edge: Map[String,Object]    edge properties
     */
    Query queryEdges(String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps);

    /**
     * Retrieve the edges between the specified nodes
     *
     * @param edgeType edge type (can be null)
     * @param fromIds source node ids
     * @param toIds target node ids
     * @param edgeProps properties of the edges
     * @return a list of maps with keys:
     *
     *      idfrom: long                id from node
     *      idto: long                  id to node
     *      edge: Map[String,Object]    edge properties
     *
     */
    Query queryEdges(String edgeType, Collection<String> fromIds, Collection<String> toIds,
                     Map<String, Object> edgeProps);
    Query queryEdges(String edgeType, Collection<String> ids,
                     Map<String, Object> edgeProps);
    Query queryEdges(String edgeType, String foromId, Collection<String> toIds,
                     Map<String, Object> edgeProps);

    /**
     * Select the edges between the specified nodes
     *
     * @param edgeType edge type (can be null)
     * @param fromId source node
     * @param toId destination node
     * @param direction direction of the edge (Input, Output, Any)
     * @param recursive if to select the edges in a recursive way
     * @param edgeProps edge properties
     * @return
     */
    Query  queryPath(String edgeType,
                     String fromId, String toId, Direction direction, boolean recursive,
                     Map<String, Object> edgeProps);

    // ----------------------------------------------------------------------
    // Edge
    // ----------------------------------------------------------------------

    /**
     * Create a new edge.
     * It is possible to specify if the edge is directed or undirected, using the
     * 'edge property'
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

    /**
     * Delete the edges with the specified properties
     *
     * @param edgeType  edge type
     * @param fromNodeType source node type
     * @param fromProps source node properties
     * @param toNodeType to node type
     * @param toProps to node properties
     * @param edgeProps edge properties
     */
    void deleteEdges(String edgeType,  // can be null
                     String fromNodeType, Map<String, Object> fromProps,
                     String toNodeType, Map<String, Object> toProps,
                     Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------

    /**
     * Insert/update the edge properties with 'updateProps'
     *
     * @param edgeId edgeId
     * @param updateProps properties to onUpdate
     */
    void   setEdgeProperties(String edgeId, Map<String,Object> updateProps);

    /**
     * Insert/update the edge property
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
    // Query using named queries
    // ----------------------------------------------------------------------

    Query queryUsing(String queryName, Map<String,Object> queryParams);

    void executeUsing(String queryName, Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // Query using named queries
    // ----------------------------------------------------------------------

    Query queryUsingFullText(String query,  Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
