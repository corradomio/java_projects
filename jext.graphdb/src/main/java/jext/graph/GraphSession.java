package jext.graph;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.LongConsumer;

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
            name[index], value              n.name <- apocx.coll.arraySet(n.name, index, value)
            name[+], value                  n.name <- apocx.coll.append(n.name, value)
            name[!], value                  n.name <- apocx.coll.appendDistinct(n.name, value)

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
            name[index,i2], value           n.name <- apocx.coll.array2Set(n.name, index, i2, value)
            name[index,+], value            n.name <- apocx.coll.append2(n.name, index, value)
            name[index,!], value            n.name <- apocx.coll.appendDistinct2(n.name, index, value)
 */

public interface GraphSession extends AutoCloseable {

    String GRAPH_ID = "$id";
    String GRAPH_LABELS = "$labels";
    String GRAPH_TYPE = "$type";
    String SOURCE_ID = "$source";
    String TARGET_ID = "$target";

    // ----------------------------------------------------------------------
    // Database Session
    // ----------------------------------------------------------------------

    GraphDatabase getDatabase();

    /**
     * Close the connection with the database.
     */
    void close();

    // ----------------------------------------------------------------------
    // DeleteAll (DANGEROUS)
    // ----------------------------------------------------------------------

    /**
     * Delete the content of the database (DANGEROUS)
     */
    void deleteAll();

    // ----------------------------------------------------------------------
    // Node
    // ----------------------------------------------------------------------

    /**
     * Create a new node
     */
    String/*nodeId*/ createNode(String nodeType, Map<String,Object> nodeProps);

    /**
     * Create or update a node.
     * Note: the node is created using the union of findProps and updateProps.
     * If already present, only updateProps are updated
     */
    String/*nodeId*/ createNode(String nodeType, Map<String,Object> findProps, Map<String,Object> updateProps);

    /**
     * Find the node with the specified properties.
     * Equivalent to 'queryNodes(...).id()'
     *
     * @param nodeType  node type or null
     * @param nodeProps node properties
     * @return the nodeId or null
     */
    @Nullable
    String/*nodeId*/ findNode(@Nullable String nodeType, Map<String,Object> nodeProps);

    /**
     *
     * @param nodeType node type
     * @param nodeProps node properties
     * @return the node properties or the empty map
     */
    @Nullable
    Map<String,Object> getNodeProperties(@Nullable String nodeType, Map<String,Object> nodeProps);

    boolean setNodeProperties(@Nullable String nodeType, Map<String,Object> nodeProps, Map<String,Object> updateProps);

    // ----------------------------------------------------------------------

    /**
     * Check if the node there exists
     */
    boolean existsNode(String nodeId);

    /**
     * Delete the node
     */
    boolean deleteNode(String nodeId);

    /**
     * Get the property values of the node
     * @return the node properties or the empty map
     */
    @Nullable
    Map<String,Object> getNodeProperties(String nodeId);

    /**
     * Insert/update/delete (using null) the node property
     *
     * @param nodeId node id
     * @param name name of the property
     * @param value values of the property
     */
    long setNodeProperty(String nodeId, String name, Object value);

    /**
     * Insert/update/delete (using null) the node properties with 'updateProps'
     *
     * @param nodeId node id
     * @param updateProps properties to update
     */
    long setNodeProperties(String nodeId, Map<String,Object> updateProps);

    // ----------------------------------------------------------------------
    // Multiple nodes
    // ----------------------------------------------------------------------

    long setNodesProperties(Collection<String> nodeIds, Map<String,Object> nodeProps);

    /**
     * Delete the nodes
     */
    long deleteNodes(Collection<String> nodeIds);
    long deleteNodes(Collection<String> nodeIds, LongConsumer callback);

    // ----------------------------------------------------------------------
    // Query Nodes
    // ----------------------------------------------------------------------

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
    Query queryNodes(@Nullable String nodeType, Map<String,Object> nodeProps);

    /**
     * Efficient methods to find if there exists at minimum a node with the specified
     * properties.
     * Note: it is an alias for countNodes() > 0
     *
     * @param nodeType node type or null
     * @param nodeProps node properties
     * @return true if there exist one or more nodes
     */
    boolean existsNode(@Nullable String nodeType, Map<String,Object> nodeProps);

    /**
     * Count the number of nodes with the specified
     * properties.
     *
     * @param nodeType node type or null
     * @param nodeProps node properties
     * @return number of nodes
     */
    long countNodes(String nodeType, Map<String,Object> nodeProps);

    /**
     * Get the property values for the nodes
     */
    List<Map<String,Object>> getNodesProperties(Collection<String> nodeIds);

    /**
     * Delete the nodes with the specified properties
     */
    long deleteNodes(@Nullable String nodeType, Map<String,Object> nodeProps);
    long deleteNodes(@Nullable String nodeType, Map<String,Object> nodeProps, LongConsumer callback);

    // ----------------------------------------------------------------------

    void setNodesProperty(String nodeType, Map<String,Object> nodeProps, String name, Object value);
    long setNodesProperties(String nodeType, Map<String,Object> nodeProps, Map<String,Object> updateProps);

    // ----------------------------------------------------------------------
    // Special node queries
    // ----------------------------------------------------------------------
    // It is not used the Neo4J algorithm because it is not able to select
    // edges based on the edge properties

    /**
     * Select the adjacent nodes to the specified node, following the
     * specified edge, and selecting only the nodes/edges with the specified
     * properties
     *
     * @param fromId starting node id
     * @param edgeType type of the edge or null
     * @param direction direction of the edges (Input, Output, Any)
     * @param recursive if to scan recursively until the closure is reached
     * @param nodeType node type to find
     * @param nodeProps node properties to find
     * @param edgeProps edge properties to naviage
     */
    Query queryAdjacentNodes(
        String fromId,
        @Nullable String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String,Object> nodeProps, Map<String,Object> edgeProps);

    /**
     * Select the adjacent nodes to the specified node, following the
     * specified edge, and selecting only the nodes/edges with the specified
     * properties
     *
     * @param fromIds starting node ids
     * @param edgeType type of the edge or null
     * @param direction direction of the edges (Input, Output, Any)
     * @param recursive if to scan recursively until the closure is reached
     * @param nodeType node type to find
     * @param nodeProps node properties to find
     * @param edgeProps edge properties to naviage
     */
    Query queryAdjacentNodes(
        Collection<String> fromIds,
        @Nullable String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String,Object> nodeProps, Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    /**
     * General form to create a single edge. All other methods delegate to this
     *
     * @param edgeType edge type
     * @param fromType source node type
     * @param fromProps source node properties
     * @param toType target node type
     * @param toProps target node properties
     * @param edgeProps edge properties
     * @return edge id
     */
    String/*edgeId*/ createEdge(String edgeType,
                                @Nullable String fromType, Map<String,Object> fromProps,
                                @Nullable String toType,   Map<String,Object> toProps,
                                Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------

    String/*edgeId*/ createEdge(String edgeType, String fromId, String toId);
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
    String/*edgeId*/ createEdge(String edgeType,
                                String fromId,
                                String toId,
                                Map<String,Object> findProps,
                                Map<String,Object> updateProps);

    // ----------------------------------------------------------------------

    String/*edgeId*/ findEdge(String edgeType, String fromId, String toId);

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
    @Nullable
    String/*edgeId*/ findEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps);

    /**
     * Delete the edge
     */
    boolean deleteEdge(String edgeType, @Nullable String fromId, @Nullable String toId);

    boolean deleteEdge(String edgeId);

    // ----------------------------------------------------------------------

    /**
     * Retrieve the properties of the edge
     *
     * @param edgeId edgeId
     * @return the edge properties or the empty map;
     */
    Map<String,Object> getEdgeProperties(String edgeId);

    /**
     * Insert/update/delete (using null) the edge property
     *
     * @param edgeId edge id
     * @param name name of the property
     * @param value values of the property
     */
    boolean   setEdgeProperty(String edgeId, String name, Object value);

    /**
     * Insert/update/delete (using null) the edge properties with 'updateProps'
     *
     * @param edgeId edge id
     * @param updateProps properties to update
     */
    boolean setEdgeProperties(String edgeId, Map<String,Object> updateProps);

    // ----------------------------------------------------------------------
    // Multiple edges
    // ----------------------------------------------------------------------

    /**
     * General form to create edges. All other methods delegate to this
     *
     * @param edgeType edge type
     * @param fromType source node type
     * @param fromProps source node properties
     * @param toType target node type
     * @param toProps target node properties
     * @param edgeProps edges' properties
     * @return number of edges created
     */
    long createEdges(String edgeType,
                     String fromType, Map<String,Object> fromProps,
                     String toType, Map<String,Object> toProps,
                     Map<String,Object> edgeProps);

    /**
     * General form to delete edges
     *
     * @param edgeType edge type
     * @param fromType source node type
     * @param fromProps source node properties
     * @param toType target node type
     * @param toProps target node properties
     * @param edgeProps edge properties
     */
    long deleteEdges(@Nullable String edgeType,
                     @Nullable String fromType, Map<String,Object> fromProps,
                     @Nullable String toType,   Map<String,Object> toProps,
                     Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------
    // Variants

    /**
     * Create multiple edges.
     *
     * @param edgeType edge type
     * @param fromId source node id
     * @param toIds destination node id list
     * @param edgeProps properties of the edge
     */
    long createEdges(String edgeType,
                     String fromId,
                     Collection<String> toIds,
                     Map<String,Object> edgeProps);

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
    long createEdges(String edgeType,
                     String fromId,
                     Collection<String> toIds,
                     Map<String,Object> findProps,
                     Map<String,Object> updateProps);

    /**
     * Delete the edges from the specified nodes
     *
     * @param edgeType edge type (can be null)
     * @param fromId source node id
     * @param toIds target node ids (can be null)
     * @param edgeProps edge properties
     */
    long deleteEdges(@Nullable String edgeType,
                     @Nullable String fromId,
                     List<String> toIds,
                     Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------
    // Query edges
    // ----------------------------------------------------------------------

    /**
     * Retrieve the edge list: the list of pairs (from -> to)
     *
     * @param edgeType edge type (can be null)
     * @param fromType source nodes type (can be null)
     * @param fromProps source nodes properties
     * @param toType target nodes type
     * @param toProps target nodes properties
     * @param edgeProps edge properties
     * @return a list of maps with keys:
     *
     *      idfrom: long                id from node
     *      idto:   long                id to node
     *      edge: Map[String,Object]    edge properties
     */
    Query queryEdges(@Nullable String edgeType,
                     @Nullable String fromType, Map<String,Object> fromProps,
                     @Nullable String toType,   Map<String,Object> toProps,
                     Map<String,Object> edgeProps);

    Query queryEdges(@Nullable String edgeType,
                     Map<String,Object> fromProps,
                     Map<String,Object> toProps,
                     Map<String,Object> edgeProps);

    // ----------------------------------------------------------------------

    long setEdgesProperties(@Nullable String edgeType,
                            @Nullable String fromType, Map<String,Object> fromProps,
                            @Nullable String toType, Map<String,Object> toProps,
                            Map<String,Object> edgeProps,
                            Map<String,Object> updateProps);

    long setEdgesProperties(@Nullable String edgeType,
                            Map<String,Object> fromProps,
                            Map<String,Object> toProps,
                            Map<String,Object> edgeProps,
                            Map<String,Object> updateProps);

    // ----------------------------------------------------------------------
    // Variants

    long setEdgesProperties(String edgeType,
                            String fromId,
                            Collection<String> toIds,
                            Map<String,Object> edgeProps,
                            Map<String,Object> updateProps);

    long setEdgesProperties(String edgeType,
                            String fromId,
                            Collection<String> toIds,
                            Map<String,Object> updateProps);

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

    Query  queryUsing(String queryName, Map<String,Object> queryParams);
    long executeUsing(String queryName, Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // Query using fulltext
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
    long execute(String stmt, Map<String,Object> queryParams);

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
