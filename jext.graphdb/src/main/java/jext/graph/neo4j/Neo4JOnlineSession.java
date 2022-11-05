package jext.graph.neo4j;

import jext.graph.GraphSession;
import jext.graph.Query;
import jext.util.Parameters;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.LongConsumer;

import static jext.graph.NodeId.invalidId;

public class Neo4JOnlineSession extends Neo4JBaseSession implements GraphSession {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    Neo4JOnlineSession(Neo4JOnlineDatabase graphdb) {
        super(graphdb);
    }

    // ----------------------------------------------------------------------
    // Single node by [nodeType, nodeProps]
    // ----------------------------------------------------------------------

    @Override
    public boolean existsNode(@Nullable String nodeType, Map<String, Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps).limit(1);
        return query.exists();
    }

    @Nullable
    @Override
    public String/*nodeId*/ findNode(@Nullable String nodeType, Map<String, Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps).limit(1);
        return query.id();
    }

    @Override
    public boolean deleteNode(@Nullable String nodeType, Map<String, Object> nodeProps) {
        return deleteNodes(nodeType, nodeProps) > 0;
    }

    @Override
    public boolean setNodeProperties(@Nullable String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps) {
        Query query = queryNodes(nodeType, nodeProps).limit(1);
        return query.update(updateProps) > 0;
    }

    @Override
    public Map<String, Object> getNodeProperties(@Nullable String nodeType, Map<String, Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps).limit(1);
        Map<String, Object> map = query.values();
        return postProcess(map);
    }

    // ----------------------------------------------------------------------
    // Multiple nodes by [nodeType, nodeProps]
    // ----------------------------------------------------------------------

    @Override
    public void setNodesProperty(String nodeType, Map<String, Object> nodeProps, String name, Object value) {
        setNodesProperties(nodeType, nodeProps, Parameters.params(name, value));
    }

    @Override
    public long setNodesProperties(String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps) {
        Query query = queryNodes(nodeType, nodeProps);
        return query.update(updateProps);
    }

    @Override
    public long countNodes(@Nullable String nodeType, Map<String, Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps);
        return query.count();
    }

    @Override
    public long deleteNodes(@Nullable String nodeType, Map<String, Object> nodeProps) {
        return deleteNodes(nodeType, nodeProps, (count) -> {
        });
    }

    @Override
    public long deleteNodes(@Nullable String nodeType, Map<String, Object> nodeProps, LongConsumer callable) {
        int maxdelete = graphdb.getMaxDelete();

        // commit the transaction
        long count = 1, total = 0;
        while (count > 0) {
            commitAndBegin();
            count = deleteSomeNodes(nodeType, nodeProps, maxdelete);
            total += count;
            callable.accept(total);
        }

        return total;
    }

    private long deleteSomeNodes(String nodeType, Map<String, Object> nodeProps, long count) {
        Query query = queryNodes(nodeType, nodeProps).limit(count);
        return query.delete();
    }

    // ----------------------------------------------------------------------
    // Single node by [nodeId]
    // ----------------------------------------------------------------------

    @Override
    public boolean existsNode(String nodeId) {
        if (invalidId(nodeId))
            return false;

        Query query = queryNodes(null, nodeId(nodeId)).limit(1);
        return query.count() > 0;
    }

    @Override
    public boolean deleteNode(String nodeId) {
        if (invalidId(nodeId))
            return false;

        Query query = queryNodes(null, nodeId(nodeId)).limit(1);
        return query.delete() > 0;
    }

    @Nullable
    @Override
    public Map<String, Object> getNodeProperties(String nodeId) {
        if (invalidId(nodeId))
            return null;

        Query query = queryNodes(null, nodeId(nodeId)).limit(1);
        Map<String, Object> map = query.values();
        return postProcess(map);
    }

    @Override
    public boolean setNodeProperty(String nodeId, String name, Object value) {
        return setNodeProperties(nodeId, Parameters.params(name, value));
    }

    @Override
    public boolean setNodeProperties(String nodeId, Map<String, Object> nodeProps) {
        if (invalidId(nodeId))
            return false;

        Query query = queryNodes(null, nodeId(nodeId));
        return query.update(nodeProps) > 0;
    }

    // ----------------------------------------------------------------------
    // Multiple nodes by [nodeId]
    // ----------------------------------------------------------------------

    @Override
    public List<Map<String, Object>> getNodesProperties(Collection<String> nodeIds) {
        Query query = queryNodes(null, nodeId(nodeIds));
        return query.allValues().toList();
    }

    @Override
    public long setNodesProperties(Collection<String> nodeIds, Map<String, Object> updateProps) {
        Query query = queryNodes(null, nodeId(nodeIds));
        return query.update(updateProps);
    }

    @Override
    public long deleteNodes(Collection<String> nodeIds) {
        return deleteNodes(nodeIds, (count) -> {
        });
    }

    @Override
    public long deleteNodes(Collection<String> nodeIds, LongConsumer callable) {
        int maxdelete = graphdb.getMaxDelete();

        if (invalidId(nodeIds))
            return 0;

        if (nodeIds.size() > maxdelete)
            nodeIds = new ArrayList<>(nodeIds);

        int total = 0;
        while (nodeIds.size() > maxdelete) {
            int n = nodeIds.size();
            commitAndBegin();

            total += deleteSomeNodes(((List<String>) nodeIds).subList(0, maxdelete), callable);
            callable.accept(total);
            nodeIds = ((List<String>) nodeIds).subList(maxdelete, n);
        }
        if (!nodeIds.isEmpty()) {
            total += deleteSomeNodes(nodeIds, callable);
            callable.accept(total);
        }

        return nodeIds.size();
    }

    private long deleteSomeNodes(Collection<String> nodeIds, LongConsumer callable) {
        return deleteNodes(null, nodeId(nodeIds), callable);
    }

    // ----------------------------------------------------------------------
    // Delete ALL
    // ----------------------------------------------------------------------

    @Override
    public void deleteAll() {
        queryNodes(null, Parameters.empty()).delete();
    }

    // ----------------------------------------------------------------------
    // Query edges
    // ----------------------------------------------------------------------

    @Override
    public String findEdge(String edgeType,
                           Map<String, Object> fromProps,
                           Map<String, Object> toProps,
                           Map<String, Object> edgeProps) {
        return queryEdges(edgeType, fromProps, toProps, edgeProps).id();
    }

    @Override
    public String/*edgeId*/ createEdge(String edgeType,
                                       Map<String, Object> fromProps,
                                       Map<String, Object> toProps,
                                       Map<String, Object> edgeProps) {
        return createEdge(edgeType, null, fromProps, null, toProps, edgeProps);
    }

    @Override
    public String createEdge(String edgeType,
                      Map<String, Object> fromProps,
                      Map<String, Object> toProps,
                      Map<String, Object> edgeProps,
                      Map<String, Object> updateProps) {
        return createEdge(edgeType, null, fromProps, null, toProps, edgeProps, updateProps);
    }

    @Override
    public Query queryEdges(@Nullable String edgeType,
                            Map<String, Object> fromProps,
                            Map<String, Object> toProps,
                            Map<String, Object> edgeProps) {
        return queryEdges(edgeType, null, fromProps, null, toProps, edgeProps);
    }

    // ----------------------------------------------------------------------
    // Edge by [edgeType, fromId, toId]
    // ----------------------------------------------------------------------

    @Nullable
    @Override
    public String/*edgeId*/ findEdge(String edgeType, String fromId, String toId) {
        return findEdge(edgeType, fromId, toId, Parameters.empty());
    }

    @Nullable
    @Override
    public String/*edgeId*/ findEdge(String edgeType, String fromId, String toId, Map<String, Object> edgeProps) {
        Query query = queryEdges(edgeType, nodeId(fromId), nodeId(toId), edgeProps);
        return query.id();
    }

    @Override
    public String createEdge(String edgeType, String fromId, String toId) {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        return createEdge(edgeType,
            nodeId(fromId),
            nodeId(toId),
            Parameters.empty());
    }

    @Override
    public String createEdge(String edgeType,
                             String fromId,
                             String toId,
                             Map<String, Object> edgeProps) {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        return createEdge(edgeType, nodeId(fromId), nodeId(toId), edgeProps);
    }

    @Override
    public String createEdge(String edgeType,
                             String fromId,
                             String toId,
                             Map<String, Object> edgeProps,
                             Map<String, Object> updateProps) {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        String edgeId = queryEdges(edgeType, nodeId(fromId), nodeId(toId), Parameters.empty()).id();
        if (invalidId(edgeId))
            edgeId = createEdge(edgeType, fromId, toId, edgeProps);

        queryEdges(null, Parameters.empty(), Parameters.empty(), nodeId(edgeId)).update(updateProps);
        return edgeId;
    }

    @Override
    public boolean deleteEdge(String edgeType, String fromId, String toId) {
        if (invalidId(fromId) || invalidId(toId))
            return false;

        Query query = queryEdges(edgeType, nodeId(fromId), nodeId(toId), Parameters.empty());
        return query.delete() > 0;
    }

    // ----------------------------------------------------------------------
    // Edges by [edgeType, fromId, toIds]
    // ----------------------------------------------------------------------

    @Override
    public long createEdges(String edgeType,
                            String fromId,
                            Collection<String> toIds,
                            Map<String, Object> edgeProps) {

        return createEdges(edgeType, null, nodeId(fromId), null, nodeId(toIds), edgeProps);
    }

    @Override
    public long createEdges(String edgeType,
                            String fromId,
                            Collection<String> toIds,
                            Map<String, Object> edgeProps,
                            Map<String, Object> updateProps) {

        //
        // 1) find the reachable nodes
        //
        Set<String> reachableIds = queryEdges(edgeType, nodeId(fromId), nodeId(toIds), edgeProps).ids().toSet();

        //
        // 2) nodes not connected
        //
        Set<String> missingIds = new HashSet<>(toIds);
        missingIds.removeAll(reachableIds);

        //
        // 3) create the missing edges
        //
        long count = createEdges(edgeType, fromId, missingIds, edgeProps);

        //
        // 4) set the properties for all edges
        //
        setEdgesProperties(edgeType, fromId, toIds, updateProps);

        return count;
    }

    @Override
    public long setEdgesProperties(String edgeType,
                                   String fromId,
                                   Collection<String> toIds,
                                   Map<String, Object> updateProps) {
        return setEdgesProperties(edgeType, nodeId(fromId), nodeId(toIds), Parameters.empty(), updateProps);
    }

    @Override
    public long setEdgesProperties(String edgeType,
                                   String fromId,
                                   Collection<String> toIds,
                                   Map<String, Object> edgeProps,
                                   Map<String, Object> updateProps) {
        Query query = queryEdges(edgeType, nodeId(fromId), nodeId(toIds), edgeProps);
        return query.update(updateProps);
    }

    @Override
    public long deleteEdges(String edgeType,
                            String fromId,
                            List<String> toIds,
                            Map<String, Object> edgeProps) {

        Query query = queryEdges(edgeType, nodeId(fromId), nodeId(toIds), edgeProps);
        return query.delete();
    }

    // ----------------------------------------------------------------------
    // Edge by [edgeId]
    // ----------------------------------------------------------------------

    @Override
    public boolean deleteEdge(String edgeId) {
        if (invalidId(edgeId))
            return false;

        Query query = queryEdges(null, Parameters.empty(), Parameters.empty(), nodeId(edgeId));
        return query.delete() > 0;
    }

    @Nullable
    @Override
    public Map<String, Object> getEdgeProperties(String edgeId) {
        if (invalidId(edgeId))
            return null;

        Query query = queryEdges(null, Parameters.empty(), Parameters.empty(), nodeId(edgeId));
        Map<String, Object> map = query.values();
        return postProcess(map);
    }

    @Override
    public boolean setEdgeProperty(String edgeId, String name, Object value) {
        return setEdgeProperties(edgeId, Parameters.params(name, value));
    }

    @Override
    public boolean setEdgeProperties(String edgeId, Map<String, Object> updateProps) {
        Query query = queryEdges(null, Parameters.empty(), Parameters.empty(), nodeId(edgeId));
        return query.update(updateProps) > 0;
    }

    // ----------------------------------------------------------------------
    // Edges by [all props]
    // ----------------------------------------------------------------------

    @Override
    public long setEdgesProperties(@Nullable String edgeType,
                                   @Nullable String fromType, Map<String, Object> fromProps,
                                   @Nullable String toType, Map<String, Object> toProps,
                                   Map<String, Object> edgeProps,
                                   Map<String, Object> updateProps) {
        Query query = queryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps);
        return query.update(updateProps);
    }

    @Override
    public long setEdgesProperties(@Nullable String edgeType,
                                   Map<String, Object> fromProps,
                                   Map<String, Object> toProps,
                                   Map<String, Object> edgeProps,
                                   Map<String, Object> updateProps) {
        Query query = queryEdges(edgeType, fromProps, toProps, edgeProps);
        return query.update(updateProps);
    }

    // ----------------------------------------------------------------------

    @Override
    public long deleteEdges(@Nullable String edgeType,
                            @Nullable String fromType, Map<String, Object> fromProps,
                            @Nullable String toType, Map<String, Object> toProps,
                            Map<String, Object> edgeProps) {
        Query query = queryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps);
        return query.delete();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
