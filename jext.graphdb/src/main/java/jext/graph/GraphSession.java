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
    // Node by [nodeType/nodeProps]
    // ----------------------------------------------------------------------

    /**
     * Create a new node
     */
    String/*nodeId*/ createNode(String nodeType, Map<String, Object> nodeProps);

    /**
     * Create or update a node.
     * If not present, it is created with nodeProps
     * If already present, or already created, only updateProps are updated
     */
    String/*nodeId*/ createNode(String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps);

    /**
     * Select the nodes with the specified type and properties.
     * Notes:
     * 1) if nodeType and nodeProps are null, select ALL nodes in the database
     * 2) if the value of a property is 'null' the property is checked for
     * its existence.
     * Using the Query object it is possible to do
     * 1) count() : long
     * 2) exists() : bool
     * 3) update({...}) : long
     * 4) id() : String
     * 5) ids() : iter[String]
     * 6) values() : iter[Map]
     * 7) allValues() : iter[Map]
     * 8) result() : iter[Map]
     * 9) execute() : long
     *
     * @param nodeType  node type or null
     * @param nodeProps node properties
     * @return a Query object
     */
    Query queryNodes(@Nullable String nodeType, Map<String, Object> nodeProps);

    // ----------------------------------------------------------------------
    // Other operations
    // ----------------------------------------------------------------------

    /**
     * Alias: queryNodes(nodeType, nodeProps).delete()
     */
    boolean deleteNode(@Nullable String nodeType, Map<String, Object> nodeProps);

    /**
     * Alias: queryNodes(nodeType, nodeProps).id()
     */
    @Nullable
    String/*nodeId*/ findNode(@Nullable String nodeType, Map<String, Object> nodeProps);

    /**
     * Alias: queryNodes(nodeType, nodeProps).exists()
     */
    boolean existsNode(@Nullable String nodeType, Map<String, Object> nodeProps);

    /**
     * Alias: queryNodes(nodeType, nodeProps).values()
     */
    @Nullable
    Map<String, Object> getNodeProperties(@Nullable String nodeType, Map<String, Object> nodeProps);

    /**
     * Alias: queryNodes(nodeType, nodeProps).update(updateProps)
     */
    boolean setNodeProperties(@Nullable String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps);

    // ----------------------------------------------------------------------
    // Node by [nodeId]
    // ----------------------------------------------------------------------
    // It is equivalent to: [null, {id, nodeId}]
    //

    /**
     * Alias: queryNodes(null, {'id', nodeId}).exists()
     */
    boolean existsNode(String nodeId);

    /**
     * Alias: queryNodes(null, {'id', nodeId}).delete()
     */
    boolean deleteNode(String nodeId);

    /**
     * Alias: queryNodes(null, {'id', nodeId}).values()
     */
    @Nullable
    Map<String, Object> getNodeProperties(String nodeId);

    /**
     * Alias: queryNodes(null, {'id', nodeId}).update({name, value})
     */
    boolean setNodeProperty(String nodeId, String name, Object value);

    /**
     * Alias: queryNodes(null, {'id', nodeId}).update(updateProps)
     */
    boolean setNodeProperties(String nodeId, Map<String, Object> updateProps);

    // ----------------------------------------------------------------------
    // Nodes by {id1,...}
    // ----------------------------------------------------------------------
    // It is equivalent to: [null, {id, nodeIds}]

    /**
     * Alias: queryNodes(null, {'id', nodeIds}).allValues().toList()
     */
    List<Map<String, Object>> getNodesProperties(Collection<String> nodeIds);

    /**
     * Alias: queryNodes(null, {'id', nodeIds}).update(updateProps)
     */
    long setNodesProperties(Collection<String> nodeIds, Map<String, Object> updateProps);

    /**
     * Alias: queryNodes(null, {'id', nodeIds}).delete()
     */
    long deleteNodes(Collection<String> nodeIds);

    long deleteNodes(Collection<String> nodeIds, LongConsumer callback);

    // ----------------------------------------------------------------------
    // Query Nodes
    // ----------------------------------------------------------------------

    /**
     * Alias: queryNodes(nodeType, nodeProps).count()
     */
    long countNodes(String nodeType, Map<String, Object> nodeProps);

    /**
     * Alias: queryNodes(nodeType, nodeProps).delete()
     */
    long deleteNodes(@Nullable String nodeType, Map<String, Object> nodeProps);

    long deleteNodes(@Nullable String nodeType, Map<String, Object> nodeProps, LongConsumer callback);

    /**
     * Alias: queryNodes(nodeType, nodeProps).update({name, value})
     */
    void setNodesProperty(String nodeType, Map<String, Object> nodeProps, String name, Object value);

    /**
     * Alias: queryNodes(nodeType, nodeProps).update(updateProps)
     */
    long setNodesProperties(String nodeType, Map<String, Object> nodeProps,
                            Map<String, Object> updateProps);

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
     * @param fromId    starting node id
     * @param edgeType  type of the edge or null
     * @param direction direction of the edges (Input, Output, Any)
     * @param recursive if to scan recursively until the closure is reached
     * @param nodeType  node type to find
     * @param nodeProps node properties to find
     * @param edgeProps edge properties to naviage
     */
    Query queryAdjacentNodes(String fromId,
                             @Nullable String edgeType, Direction direction, boolean recursive,
                             String nodeType, Map<String, Object> nodeProps,
                             Map<String, Object> edgeProps);

    /**
     * Select the adjacent nodes to the specified node, following the
     * specified edge, and selecting only the nodes/edges with the specified
     * properties
     *
     * @param fromIds   starting node ids
     * @param edgeType  type of the edge or null
     * @param direction direction of the edges (Input, Output, Any)
     * @param recursive if to scan recursively until the closure is reached
     * @param nodeType  node type to find
     * @param nodeProps node properties to find
     * @param edgeProps edge properties to naviage
     */
    Query queryAdjacentNodes(Collection<String> fromIds,
                             @Nullable String edgeType, Direction direction, boolean recursive,
                             String nodeType, Map<String, Object> nodeProps,
                             Map<String, Object> edgeProps);

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    /**
     * General form to create edges. All other methods delegate to this
     *
     * @param edgeType  edge type
     * @param fromType  source node type
     * @param fromProps source node properties
     * @param toType    target node type
     * @param toProps   target node properties
     * @param edgeProps edges' properties
     * @return number of edges created
     */
    List<String> createEdges(String edgeType,
                             @Nullable String fromType, Map<String, Object> fromProps,
                             @Nullable String toType, Map<String, Object> toProps,
                             Map<String, Object> edgeProps);

    /**
     * General form to create or update an edge. All other methods delegate to this
     *
     * @param edgeType  edge type
     * @param fromType  source node type
     * @param fromProps source node properties
     * @param toType    target node type
     * @param toProps   target node properties
     * @param edgeProps edges' properties
     * @return number of edges created
     */
    String/*edgeId*/ createEdge(String edgeType,
                                @Nullable String fromType, Map<String, Object> fromProps,
                                @Nullable String toType, Map<String, Object> toProps,
                                Map<String, Object> edgeProps,
                                Map<String, Object> updateProps);

    /**
     * Retrieve the edge list: the list of pairs (from -> to)
     * Special properties:
     * idfrom: long                id from node
     * idto:   long                id to node
     * edge: Map[String,Object]    edge properties
     *
     * @param edgeType  edge type (can be null)
     * @param fromType  source nodes type (can be null)
     * @param fromProps source nodes properties
     * @param toType    target nodes type
     * @param toProps   target nodes properties
     * @param edgeProps edge properties
     * @return a list of edges:
     */
    Query queryEdges(@Nullable String edgeType,
                     @Nullable String fromType, Map<String, Object> fromProps,
                     @Nullable String toType, Map<String, Object> toProps,
                     Map<String, Object> edgeProps);

    // ----------------------------------------------------------------------
    // Other operations
    // ----------------------------------------------------------------------

    /**
     * Alias: createEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps).get(0)
     */
    String/*edgeId*/ createEdge(String edgeType,
                                @Nullable String fromType, Map<String, Object> fromProps,
                                @Nullable String toType, Map<String, Object> toProps,
                                Map<String, Object> edgeProps);

    /**
     * Alias: queryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps).id()
     */
    String/*edgeId*/ findEdge(String edgeType,
                              @Nullable String fromType, Map<String, Object> fromProps,
                              @Nullable String toType, Map<String, Object> toProps,
                              Map<String, Object> edgeProps);

    /**
     * Alias: createEdge(edgeType, null, {'id', fromId}, null, {'id', toId}, edgeProps)
     */
    String/*edgeId*/ createEdge(String edgeType, String fromId, String toId,
                                Map<String, Object> edgeProps);

    /**
     * Alias: createEdge(edgeType, null, {'id', fromId}, null, {'id', toId}, {})
     */
    String/*edgeId*/ createEdge(String edgeType, String fromId, String toId);

    /**
     * Alias: createEdge(edgeType, null, {'id', fromId}, null, {'id', toId'}, edgeProps, updateProps)
     */
    String/*edgeId*/ createEdge(String edgeType, String fromId, String toId,
                                Map<String, Object> edgeProps,
                                Map<String, Object> updateProps);

    /**
     * Alias: createEdge(edgeType, null, {'id', fromId}, null, {'id', toIds}, edgeProps)
     */
    List<String> createEdges(String edgeType, String fromId, Collection<String> toIds,
                             Map<String, Object> edgeProps);

    /**
     * Alias: createEdge(edgeType, null, {'id', fromId}, null, {'id', toIds}, edgeProps, updateProps)
     */
    List<String> createEdges(String edgeType, String fromId, Collection<String> toIds,
                             Map<String, Object> edgeProps,
                             Map<String, Object> updateProps);

    /**
     * Alias: queryEdges(edgeType, null, {'id', fromId}, null, {'id', toIds}, edgeProps).delete()
     */
    long deleteEdges(@Nullable String edgeType, String fromId, List<String> toIds,
                     Map<String, Object> edgeProps);

    /**
     * Alias: queryEdges(edgeType, null, {'id', fromId}, null, {'id', toIds}, edgeProps).update(updateProps)
     */
    long setEdgesProperties(String edgeType, String fromId, Collection<String> toIds,
                            Map<String, Object> edgeProps,
                            Map<String, Object> updateProps);

    /**
     * Alias: queryEdges(edgeType, null, {'id', fromId}, null, {'id', toIds}, {}).update(updateProps)
     */
    long setEdgesProperties(String edgeType, String fromId, Collection<String> toIds,
                            Map<String, Object> updateProps);

    // ----------------------------------------------------------------------
    // Edge by [edgeType, fromId, toId, edgeProps]
    // ----------------------------------------------------------------------

    /**
     * Alias: queryEdges(edgeType, null, {'id', fromId}, null, {'id', toId}, {}).id()
     */
    String/*edgeId*/ findEdge(@Nullable String edgeType, @Nullable String fromId, @Nullable String toId);

    /**
     * Alias: queryEdges(edgeType, null, {'id', fromId}, null, {'id', toId}, edgeProps).id()
     */
    @Nullable
    String/*edgeId*/ findEdge(@Nullable String edgeType, @Nullable String fromId, @Nullable String toId,
                              Map<String, Object> edgeProps);

    /**
     * Alias: queryEdges(edgeType, null {'id', fromId}, null, {'id', toId}, {}).delete()
     */
    boolean deleteEdge(@Nullable String edgeType, @Nullable String fromId, @Nullable String toId);

    // ----------------------------------------------------------------------
    // Edge by [edgeId]
    // ----------------------------------------------------------------------

    /**
     * Alias: queryEdges(edgeType, null, {}, null, {}, {'id', edgeId}).delete()
     */
    boolean deleteEdge(String edgeId);

    /**
     * Alias: queryEdges(edgeType, null, {}, null, {}, {'id', edgeId}).values()
     */
    @Nullable
    Map<String, Object> getEdgeProperties(String edgeId);

    /**
     * Alias: queryEdges(edgeType, null, {}, null, {}, {'id', edgeId}).update({name, value})
     */
    boolean setEdgeProperty(String edgeId, String name, Object value);

    /**
     * Alias: queryEdges(edgeType, null, {}, null, {}, {'id', edgeId}).update(updateProps)
     */
    boolean setEdgeProperties(String edgeId, Map<String, Object> updateProps);

    // ----------------------------------------------------------------------
    // Multiple edges
    // ----------------------------------------------------------------------

    /**
     * Alias: createEdge(edgeType, null, fromProps, null, toProps, edgeProps)
     */
    String createEdge(String edgeType, Map<String, Object> fromProps, Map<String, Object> toProps,
                      Map<String, Object> edgeProps);

    /**
     * Alias: createEdge(edgeType, null, fromProps, null, toProps, edgeProps, updateProps)
     */
    String createEdge(String edgeType, Map<String, Object> fromProps, Map<String, Object> toProps,
                      Map<String, Object> edgeProps,
                      Map<String, Object> updateProps);

    /**
     * Alias: queryEdges(edgeType, null, fromProps, null, toProps, edgeProps)
     */
    Query queryEdges(@Nullable String edgeType, Map<String, Object> fromProps, Map<String, Object> toProps,
                     Map<String, Object> edgeProps);

    /**
     * Alias: queryEdges(edgeType, null, fromProps, null, toProps, edgeProps).id()
     */
    String findEdge(String edgeType, Map<String, Object> fromProps, Map<String, Object> toProps,
                    Map<String, Object> edgeProps);

    /**
     * Alias: queryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps).delete()
     */
    long deleteEdges(@Nullable String edgeType,
                     @Nullable String fromType, Map<String, Object> fromProps,
                     @Nullable String toType, Map<String, Object> toProps,
                     Map<String, Object> edgeProps);

    /**
     * Alias: queryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps).update(updateProps)
     */
    long setEdgesProperties(@Nullable String edgeType,
                            @Nullable String fromType, Map<String, Object> fromProps,
                            @Nullable String toType, Map<String, Object> toProps,
                            Map<String, Object> edgeProps,
                            Map<String, Object> updateProps);

    /**
     * Alias: queryEdges(edgeType, null, fromProps, null, toProps, edgeProps).update(updateProps)
     */
    long setEdgesProperties(@Nullable String edgeType,
                            Map<String, Object> fromProps,
                            Map<String, Object> toProps,
                            Map<String, Object> edgeProps,
                            Map<String, Object> updateProps);

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

    Query queryUsing(String queryName, Map<String, Object> queryParams);

    long executeUsing(String queryName, Map<String, Object> queryParams);

    // ----------------------------------------------------------------------
    // Query using fulltext
    // ----------------------------------------------------------------------

    /**
     * It is possible to specify the node types passing the parameters
     * <p>
     * - 'labels'  list of node labels (the condition will be: 'labels(n) = $labels')
     * - 'refIds'  list of node ids
     *
     * @param query       fulltext query as specified in Lucene
     * @param queryParams query parameters
     */
    Query queryUsingFullText(String query, Map<String, Object> queryParams);

    // ----------------------------------------------------------------------
    // Low level
    // ----------------------------------------------------------------------

    /**
     * Execute the CYPHER statement specified
     */
    Query query(String stmt, Map<String, Object> queryParams);

    long execute(String stmt, Map<String, Object> queryParams);

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
