package jext.graph;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
    a 'property' is a string that can be:

        'name'          a single value
        'name[index]'   an element in an array
        '$name'         a special property

    Special properties:

        $degree         node degree (indegree+outdegree)
        $indegree       input degree
        $outdegree      output degree
        $label          single node label (in CYPHER: 'labels(n)[0]')

    Supported assignments:

            name, value                     n.name <- value
            name[index], value              n.name <- apoc.coll.arraySet(n.name, index, value)
            name[+], value                  n.name <- apoc.coll.append(n.name, value)
            name[!], value                  n.name <- apoc.coll.appendDistinct(n.name, value)

    Supported predicates:

        name: 'singleValue'

            name, value                     n.name == value
            name, array[]                   n.name IN array[]
            name, collection                n.name IN collection

        name: 'valueArray'

            name[index], value              n.name[index] == value
            name[index], array[]            n.name[index] IN array[]
            name[index], collection         n.name[index] IN collection
            name[i1|i2|...], value          n.name[i1] == value OR n.name[i2] == value OR ...
                                            n.name[i1] OR n.name[i2] OR ...  for boolean value 'true'

    Special predicates:

        name: 'booleanArray'

            name, array[]                   n.name[index1] OR n.name[index2] OR ...

    Extended predicates: the predicate ">", "<", ">=", "<=", "!=" is added at the end of the name

        name>,  value
        name>=, value
        name<,  value
        name<=, value
        name!=, value

     Support for array of array of int:

        assignment:
            name[index,i2], value           n.name <- apoc.coll.array2Set(n.name, index, i2, value)
            name[index,+], value            n.name <- apoc.coll.append2(n.name, index, value)
            name[index,!], value            n.name <- apoc.coll.appendDistinct2(n.name, index, value)
 */

public interface GraphSession extends AutoCloseable {

    String GRAPH_ID = "$id";
    String GRAPH_LABELS = "$labels";
    String GRAPH_TYPE = "$type";

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
    // Unsupported (for now)
    //
    // void beginTransaction();
    // void commit();
    // void rollback();

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
     * Find the node with the specified properties.
     * Equivalent to 'queryNodes(...).id()'
     *
     * @param nodeType node type or null
     * @param nodeProps node properties
     */
    @Nullable
    String/*nodeId*/ findNode(String nodeType, Map<String, Object> nodeProps);

    // ----------------------------------------------------------------------
    // Operations on a single node
    // ----------------------------------------------------------------------

    /**
     * Create a new node
     */
    String/*nodeId*/ createNode(String nodeType, Map<String,Object> nodeProps);

    /**
     * Create or update a node
     */
    String/*nodeId*/ createNode(String nodeType, Map<String,Object> findProps, Map<String,Object> updateProps);

    /**
     * Check if the node there exists
     */
    boolean existsNode(String nodeId);

    /**
     * Get the property values of the node
     */
    Map<String, Object> getNodeValues(String nodeId);

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
    List<Map<String, Object>> getNodesValues(Collection<String> nodeIds);

    /**
     * Delete the nodes
     */
    void deleteNodes(Collection<String> nodeIds);

    /**
     * Delete the nodes with the specified properties
     */
    long deleteNodes(String nodeType, Map<String,Object> nodeProps, long count);
    long deleteNodes(String nodeType, Map<String,Object> nodeProps);

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
    void setNodesProperties(Collection<String> nodeIds, Map<String,Object> nodeProps);

    void setNodeProperty(String nodeId, String name, Object value);
    void setNodeProperty(String nodeId, String name, int index, Object value);

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
     */
    Query queryAdjacentNodes(
        String fromId, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);

    Query queryAdjacentNodes(
        Collection<String> fromIds, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);

    // Query queryAdjacentNodesAlgorithm(
    //     String fromId, String edgeType, Direction direction, boolean recursive,
    //     String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);

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
     *      idto:   long                id to node
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
    Query queryEdges(String edgeType, String fromId, Collection<String> toIds,
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
    Query queryPath(String edgeType,
                    String fromId, String toId, Direction direction, boolean recursive,
                    Map<String, Object> edgeProps);

    // ----------------------------------------------------------------------
    // Edge
    // ----------------------------------------------------------------------

    /**
     * Find the edge between the specified nodes with the specified type and
     * properties.
     *
     * @param edgeType edge type
     * @param fromId source node id
     * @param toId target node id
     * @param edgeProps edge properties
     * @return edgeId or null
     */
    String/*edgeId*/ findEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps);

    /**
     * Create a new edge.
     *
     * @param edgeType edge type
     * @param fromId source node id
     * @param toId target node id
     * @param edgeProps edge properties
     * @return edgeId
     */
    String/*edgeId*/ createEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps);

    /**
     * Create or update an edge.
     *
     * @param edgeType edge type
     * @param fromId source node id
     * @param toId targe node id
     * @param findProps edge properties used to find it
     * @param updateProps edge properties to update
     * @return edgeId
     */
    String/*edgeId*/ createEdge(String edgeType, String fromId, String toId,  Map<String,Object> findProps, Map<String,Object> updateProps);

    /**
     * Create multiple edges.
     *
     * @param edgeType edge type
     * @param fromId source node id
     * @param toIds destination node id list
     * @param edgeProps properties of the edge
     */
    void createEdges(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps);

    /**
     * Create or update multiple edges.
     * The algorithm is:
     *  1) 'findProps' are used to identify the list of nodes already reached
     *  2) it is identified the set of nodes in 'toId' without an edge
     *  3) it is create the edges between 'fromId' and the missing edges
     *  4) the edges are updated with 'updateProps'
     *
     * @param edgeType edge type
     * @param fromId source node id
     * @param toIds destination node id list
     * @param findProps edge properties used to find the edges
     * @param updateProps edge properties to update
     */
    void createEdges(String edgeType, String fromId, Collection<String> toIds,  Map<String,Object> findProps, Map<String,Object> updateProps);

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

    /**
     * Delete the edges from the specified nodes
     *
     * @param edgeType edge type (can be null)
     * @param fromId source node id
     * @param toIds target node ids (can be null)
     * @param edgeProps edge properties
     */
    void deleteEdges(String edgeType, String fromId, List<String> toIds, Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------

    /**
     * Insert/update edge properties based on the source/target nodes and the edge type.
     */
    void setEdgeProperties(String edgeType, String fromId, String toId, Map<String,Object> edgeProps, Map<String,Object> updateProps);
    void setEdgeProperties(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps, Map<String,Object> updateProps);

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
     */
    void   setEdgeProperty(String edgeId, String name, Object value);
    void   setEdgeProperty(String edgeId, String name, int index, Object value);

    /**
     * Retrieve the properties of the edge
     *
     * @param edgeId edgeId
     */
    Map<String, Object> getEdgeProperties(String edgeId);

    /**
     * Set the edge properties to the edges connecting fromId with toIds
     *
     * @param edgeType edge type
     * @param fromId source node
     * @param toIds destination nodes (can be null)
     * @param edgeProps edge properties
     */
    void setEdgesProperties(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // boolean isDAG(String nodeType, Map<String,Object> nodeProps,
    //               String edgeType, Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------
    // Query using named queries
    // ----------------------------------------------------------------------
    // The names query can contains some special text variables:
    //
    //  ${and:name}     ${and:alias:name}
    //  ${where:name}   ${where:alias:name}
    //  ${body:name}    ${body:alias:name}  (not supported for now)
    //
    // In this way, a named query can be extended in the following way:
    //
    //      MATCH (...) ${where:name} ...
    //      MATCH (...) WHERE ... ${and:name} ...
    //      MATCH (... ${body:name}) ...
    //
    // In this case, "queryParam" must contain the parameter 'name' with value
    // a map containing pairs [name, value]
    //

    Query queryUsing(String queryName, Map<String,Object> queryParams);

    void executeUsing(String queryName, Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // Query using named queries
    // ----------------------------------------------------------------------

    /**
     * It is possible to specify the node types passing the parameters
     *
     *  - 'labels'  list of node labels (the condition will be: 'labels(n) = $labels')
     *  - 'refIds'  list of node ids
     *
     * @param query fulltext query as specified in Lucene
     * @param queryParams query parameters
     */
    Query queryUsingFullText(String query,  Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // Low level
    // ----------------------------------------------------------------------

    /**
     * Execute the CYPHER statement specified
     */
    Query  query(String stmt, Map<String,Object> queryParams);
    void execute(String stmt, Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
