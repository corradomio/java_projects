package jext.graph.neo4j;

import jext.graph.Direction;
import jext.graph.GraphDatabase;
import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Query;
import jext.logging.Logger;
import jext.util.Assert;
import jext.util.Parameters;
import jext.util.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.exceptions.TransientException;
import org.neo4j.driver.summary.SummaryCounters;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.LongConsumer;

import static jext.graph.NodeId.invalidId;
import static jext.graph.NodeId.toId;
import static jext.graph.neo4j.WhereFormatter.eblock;
import static jext.graph.neo4j.WhereFormatter.label;
import static jext.graph.neo4j.WhereFormatter.pblock;
import static jext.graph.neo4j.WhereFormatter.sblock;
import static jext.graph.neo4j.WhereFormatter.ublock;
import static jext.graph.neo4j.WhereFormatter.wblock;

public class Neo4JOnlineSession implements GraphSession {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    static final String N = "n";
    static final String E = "e";
    static final String FROM = "from";
    static final String TO = "to";
    static final String NONE = "";
    static final String ID = "id";

    static final String AND_BLOCK = "${and:";
    static final String WHERE_BLOCK = "${where:";
    static final String END_BLOCK = "}";

    public static final int MAX_STATEMENTS = 8*1024;
    public static final int MAX_DELETE_NODES = 16*1024;

    public static final String REF_ID = "refId";
    public static final String REVISION = "revision";
    public static final String REVISIONS = "revisions";
    public static final String IN_REVISION = "inRevision";
    public static final String COUNT = "count";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(Neo4JOnlineSession.class);

    private final Neo4JOnlineDatabase graphdb;
    private final Driver driver;
    private Session session;
    private Transaction transaction;
    private final AtomicInteger count = new AtomicInteger();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    Neo4JOnlineSession(Neo4JOnlineDatabase graphdb) {
        this.graphdb = graphdb;
        this.driver = graphdb.getDriver();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public GraphDatabase getDatabase() { return graphdb; }

    // ----------------------------------------------------------------------
    // Connect/Disconnect
    // ----------------------------------------------------------------------

    public Neo4JOnlineSession connect() {
        this.session = this.driver.session();
        this.transaction = this.session.beginTransaction();
        return this;
    }

    private void commitAndBegin() {
        transaction.commit();
        transaction = session.beginTransaction();
    }

    @Override
    public void close() {
        if (transaction != null) {
            transaction.commit();
            transaction = null;
        }
        if (session != null) {
            session.close();
            session = null;
        }
    }

    // ----------------------------------------------------------------------
    // Named Queries
    // ----------------------------------------------------------------------
    // Special fields:
    //
    //      ${and:name}
    //      ${where:name}
    //
    // 'params' must contain a parameter with name 'name' as another Map<String, Object>
    // to extend the 'namedQuery' conditions
    //

    @Override
    public Query queryUsing(String queryName,  Map<String, Object> params) {
        String s = composeQuery(queryName, params);
        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public long executeUsing(String queryName, Map<String, Object> params) {
        String s = composeQuery(queryName, params);
        return execute(s, params);
    }

    protected String composeQuery(String queryName, Map<String, Object> params) {
        String query = graphdb.getQuery(queryName);

        if (query.contains(AND_BLOCK) || query.contains(WHERE_BLOCK)) {
            Parameters nparams = Parameters.params().add(params);
            while (query.contains(AND_BLOCK))
                query = ublock(query, nparams, true);
            while (query.contains(WHERE_BLOCK))
                query = ublock(query, nparams, false);
            params = nparams;
        }

        // replace ${name} with 'name' value in 'params'
        String s = StringUtils.format(query, params);
        return s;
    }

    // ----------------------------------------------------------------------
    // Query using fulltext
    // ----------------------------------------------------------------------

    @Override
    public Query queryUsingFullText(String query,  Map<String, Object> params) {

        // $label = l | {l1,...}
        // refId  = r | {r1,...}
        String wblock = wblock(NONE, params, false, false);

        String s = String.format("CALL db.index.fulltext.queryNodes($indexName, $query) YIELD node AS n %s", wblock);

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // Single node
    // ----------------------------------------------------------------------

    @Nonnull
    @Override
    public String createNode(String nodeType, Map<String, Object> nodeProps) {
        Assert.notNull(nodeType, "nodeType is null");
        Assert.notNull(nodeProps, "nodeProps is null");

        String pblock = pblock(N, nodeProps);
        String sblock = sblock(N, nodeProps, true);

        String s = String.format("CREATE (n:%s %s) %s RETURN id(n)", nodeType, pblock, sblock);

        Parameters params = Parameters.params().add(N, nodeProps);

        return this.create(s, params);
    }

    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String, Object> findProps, Map<String, Object> updateProps) {
        Assert.notNull(nodeType, "nodeType is null");
        Assert.notNull(findProps, "findProps is null");
        Assert.notNull(updateProps, "updateProps is null");

        String nodeId = findNode(nodeType, findProps);
        if (invalidId(nodeId))
            nodeId = createNode(nodeType, findProps);

        setNodeProperties(nodeId, updateProps);
        return nodeId;
    }

    // ----------------------------------------------------------------------
    // Query nodes
    // ----------------------------------------------------------------------

    @Override
    public Query queryNodes(String nodeType, Map<String, Object> nodeProps) {
        Assert.notNull(nodeProps, "nodeProps is null");

        String pblock = pblock(N, nodeProps);
        String wblock = wblock(N, nodeProps, false, true);
        String s = String.format("MATCH (n%s%s)%s", label(nodeType), pblock, wblock);

        Parameters params = Parameters.params().add(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // Single node by [nodeType, nodeProps]
    // ----------------------------------------------------------------------

    @Override
    public boolean deleteNode(@Nullable String nodeType, Map<String, Object> nodeProps) {
        return deleteNodes(nodeType, nodeProps) > 0;
    }

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
    public long setNodesProperties(String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps) {
        Query query = queryNodes(nodeType, nodeProps);
        return query.update(updateProps);
    }

    @Override
    public void setNodesProperty(String nodeType, Map<String, Object> nodeProps, String name, Object value) {
        setNodesProperties(nodeType, nodeProps, Parameters.params(name, value));
    }

    @Override
    public long countNodes(@Nullable String nodeType, Map<String, Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps);
        return query.count();
    }

    @Override
    public long deleteNodes(@Nullable String nodeType, Map<String, Object> nodeProps) {
        return deleteNodes(nodeType, nodeProps, (count) -> { });
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

    private static Map<String, Object> nodeId(Object id) {
        return id == null ? Parameters.empty() : Parameters.params(ID, id);
    }

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
    public long setNodeProperty(String nodeId, String name, Object value) {
        return setNodeProperties(nodeId, Parameters.params(name, value));
    }

    @Override
    public long setNodeProperties(String nodeId, Map<String, Object> nodeProps) {
        Query query = queryNodes(null, nodeId(nodeId));
        return query.update(nodeProps);
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
        return deleteNodes(nodeIds, (count) -> {});
    }

    @Override
    public long deleteNodes(Collection<String> nodeIds, LongConsumer callable) {
        int maxdelete = graphdb.getMaxDelete();

        if (invalidId(nodeIds))
            return 0;

        if (nodeIds.size() > maxdelete)
            nodeIds = new ArrayList<>(nodeIds);

        int total = 0;
        while(nodeIds.size() > maxdelete) {
            int n = nodeIds.size();
            commitAndBegin();

            total += deleteSomeNodes(((List<String>)nodeIds).subList(0, maxdelete), callable);
            callable.accept(total);
            nodeIds = ((List<String>)nodeIds).subList(maxdelete, n);
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
        queryNodes(null, Parameters.emptyMap()).delete();
    }

    // ----------------------------------------------------------------------
    // Create edges
    // ----------------------------------------------------------------------

    @Override
    public String/*edgeId*/ createEdge(
        String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps) {

        String s = innerCreateEdge(edgeType, fromType, fromProps, toType, toProps, edgeProps)
            + " RETURN id(e)";

        Parameters params = Parameters.params()
            .add(FROM, fromProps)
            .add(TO, toProps)
            .add(E, edgeProps);

        return this.create(s, params);
    }

    @Override
    public long createEdges(
        String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps) {

        String s = innerCreateEdge(edgeType, fromType, fromProps, toType, toProps, edgeProps)
            + " RETURN count(e)";

        Parameters params = Parameters.params()
            .add(FROM, fromProps)
            .add(TO, toProps)
            .add(E, edgeProps);

        return this.execute(s, params);
    }

    private String innerCreateEdge(
        String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps)
    {
        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
        String esblock = sblock(E, edgeProps,  true);

        String m = innerQueryEdges(edgeType, fromType, fromProps, toType, toProps, null);
        String s = String.format("%s CREATE (from)%s(to)%s",
            m,
            eblock,
            esblock
        );
        return s;
    }

    // ----------------------------------------------------------------------
    // Query edges
    // ----------------------------------------------------------------------

    @Override
    public Query queryEdges(String edgeType,
                            String fromType, Map<String, Object> fromProps,
                            String toType,   Map<String, Object> toProps,
                            Map<String, Object> edgeProps) {

        String s = innerQueryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps);

        Parameters allProps = Parameters.params()
            .add(FROM, fromProps)
            .add(TO, toProps)
            .add(E, edgeProps);

        return new Neo4JQuery(this, E, s, allProps).edge();
    }

    private static String innerQueryEdges(String edgeType,
                                          String fromType, Map<String, Object> fromProps,
                                          String toType,   Map<String, Object> toProps,
                                          Map<String, Object> edgeProps) {

        boolean edge = edgeProps != null;
        String fblock  = pblock(FROM, fromProps);
        String tblock  = pblock(TO, toProps);
        String eblock  = edge
            ? eblock(E, edgeType, Direction.Output, false, edgeProps)
            : ",";

        String wfblock = wblock(FROM, fromProps, false, true);
        String wtblock = wblock(TO,   toProps,   !wfblock.isEmpty(), true);
        String weblock = edge
            ? wblock(E,    edgeProps, !wtblock.isEmpty(), true)
            : NONE;

        String wblock = String.format("%s%s%s", wfblock, wtblock, weblock);

        return String.format(
            "MATCH (from%s%s)%s(to%s%s)%s ",
            label(fromType),  fblock,
            eblock,
            label(toType), tblock,
            wblock
        );
    }

    @Override
    public Query queryEdges(@Nullable String edgeType,
                            Map<String, Object> fromProps,
                            Map<String, Object> toProps,
                            Map<String, Object> edgeProps) {
        return queryEdges(edgeType,
            null, fromProps,
            null, toProps,
            edgeProps);
    }

    // ----------------------------------------------------------------------
    // Edge by [edgeType, fromId, toId]
    // ----------------------------------------------------------------------

    @Nullable
    @Override
    public String/*edgeId*/ findEdge(String edgeType, String fromId, String toId) {
        return findEdge(edgeType, fromId, toId, Parameters.emptyMap());
    }

    @Nullable
    @Override
    public String/*edgeId*/ findEdge(String edgeType, String fromId, String toId, Map<String, Object> edgeProps) {
        Query query = queryEdges(edgeType,
            null, nodeId(fromId),
            null, nodeId(toId),
            edgeProps);

        return query.id();
    }

    @Override
    public String createEdge(String edgeType, String fromId, String toId) {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        return createEdge(edgeType,
            null, nodeId(fromId),
            null, nodeId(toId),
            Parameters.emptyMap());
    }

    @Override
    public String createEdge(String edgeType,
                             String fromId,
                             String toId,
                             Map<String, Object> edgeProps) {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        return createEdge(
            edgeType,
            null, nodeId(fromId),
            null, nodeId(toId),
            edgeProps);
    }

    @Override
    public String createEdge(String edgeType,
                             String fromId,
                             String toId,
                             Map<String, Object> findProps,
                             Map<String, Object> updateProps)
    {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        String edgeId = findEdge(edgeType, fromId, toId);
        if (invalidId(edgeId))
            edgeId = createEdge(edgeType, fromId, toId, findProps);

        setEdgeProperties(edgeId, updateProps);

        return edgeId;
    }

    @Override
    public boolean deleteEdge(String edgeType, String fromId, String toId) {
        if (invalidId(fromId) || invalidId(toId))
            return false;

        Query query = queryEdges(edgeType,
            nodeId(fromId),
            nodeId(toId),
            Parameters.empty());

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

        return createEdges(edgeType,
            null, nodeId(fromId),
            null, nodeId(toIds),
            edgeProps);
    }

    @Override
    public long createEdges(String edgeType,
                            String fromId,
                            Collection<String> toIds,
                            Map<String, Object> findProps,
                            Map<String, Object> updateProps) {

        //
        // 1) find the reachable nodes
        //
        Set<String> reachableIds = queryEdges(edgeType, nodeId(fromId), nodeId(toIds), findProps).ids().toSet();

        //
        // 2) nodes not connected
        //
        Set<String> missingIds = new HashSet<>(toIds);
        missingIds.removeAll(reachableIds);

        //
        // 3) create the missing edges
        //
        long count = createEdges(edgeType, fromId, missingIds, findProps);

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
        Query query = queryEdges(edgeType,
            nodeId(fromId),
            nodeId(toIds),
            edgeProps);
        return query.update(updateProps);
    }

    @Override
    public long deleteEdges(String edgeType,
                            String fromId,
                            List<String> toIds,
                            Map<String, Object> edgeProps) {

        Query query = queryEdges(edgeType,
            null, nodeId(fromId),
            null, nodeId(toIds),
            edgeProps);

        return query.delete();
    }

    // ----------------------------------------------------------------------
    // Edge by [edgeId]
    // ----------------------------------------------------------------------

    @Override
    public boolean deleteEdge(String edgeId) {
        if (invalidId(edgeId))
            return false;

        Query queryEdge = queryEdges(null,
            Parameters.empty(),
            Parameters.empty(),
            Parameters.params(ID, edgeId));

        return queryEdge.delete() > 0;
    }

    @Nullable
    @Override
    public Map<String, Object> getEdgeProperties(String edgeId) {
        Query query = queryEdges(null,
            Parameters.empty(),
            Parameters.empty(),
            nodeId(edgeId));

        Map<String, Object> map = query.values();
        return postProcess(map);
    }

    @Override
    public boolean setEdgeProperty(String edgeId, String name, Object value) {
        return setEdgeProperties(edgeId, Parameters.params(name, value));
    }

    @Override
    public boolean setEdgeProperties(String edgeId, Map<String, Object> updateProps) {
        Query query = queryEdges(null,
            Parameters.empty(),
            Parameters.empty(),
            nodeId(edgeId));

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
        Query query = queryEdges(edgeType,
            fromType, fromProps,
            toType, toProps,
            edgeProps);
        return query.update(updateProps);
    }

    @Override
    public long setEdgesProperties(@Nullable String edgeType,
                                   Map<String, Object> fromProps,
                                   Map<String, Object> toProps,
                                   Map<String, Object> edgeProps,
                                   Map<String, Object> updateProps) {
        Query query = queryEdges(edgeType,
            fromProps,
            toProps,
            edgeProps);
        return query.update(updateProps);
    }

    // ----------------------------------------------------------------------

    @Override
    public long deleteEdges(
            @Nullable String edgeType,
            @Nullable String fromType, Map<String, Object> fromProps,
            @Nullable String toType,   Map<String, Object> toProps,
            Map<String, Object> edgeProps) {

        Query query = queryEdges(edgeType,
            fromType, fromProps,
            toType, toProps,
            edgeProps);

        return query.delete();
    }

    // ----------------------------------------------------------------------
    // Adjacent nodes
    // ----------------------------------------------------------------------

    @Override
    public Query queryAdjacentNodes(
        String fromId, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps) {

        return queryAdjacentNodes(Collections.singletonList(fromId), edgeType, direction, recursive,
            nodeType, nodeProps, edgeProps);
    }

    @Override
    public Query queryAdjacentNodes(
        Collection<String> fromIds, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps) {

        if (!recursive)
            return queryAdjacentNodesImpl(fromIds, edgeType, direction, recursive, nodeType,
                nodeProps, edgeProps);
        else
            return new Neo4JAdjacentQuery(this, fromIds, edgeType, direction, nodeType,
                nodeProps, edgeProps);
    }

    Query queryAdjacentNodesImpl(
        Collection<String> fromIds, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps) {

        String eblock = eblock(E, edgeType, direction, recursive, edgeProps);
        String pblock = pblock(N, nodeProps);
        String wblock = wblock(E, edgeProps, true, false);
        String s;

        if (recursive) {
            s = String.format("MATCH shortestPath( (t) %s (n:%s %s) ) WHERE id(t) in $id %s",
                eblock, nodeType, pblock, wblock);
        }
        else {
            s = String.format("MATCH (t) %s (n:%s %s) WHERE id(t) in $id %s",
                eblock, nodeType, pblock, wblock);
        }

        Parameters params = Parameters.params(ID, fromIds)
            .add(N, nodeProps)
            .add(E, edgeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    Query queryAdjacentNodesStep(Collection<String> fromIds, String edgeType, Direction direction, Map<String, Object> edgeProps) {
        String eblock = eblock(E, edgeType, direction, false, edgeProps);
        String wblock = wblock(E, edgeProps, true, false);
        String s = String.format("MATCH (s) %s (n) WHERE id(s) in $id %s",
            eblock, wblock);

        Parameters params = Parameters.params(ID, fromIds)
            .add(E, edgeProps);

        return new Neo4JQuery(this, N, s, params).distinct();
    }

    Query selectNodes(Collection<String> ids, String nodeType,  Map<String, Object> nodeProps) {
        String pblock = pblock(N, nodeProps);
        String s = String.format("MATCH (n%s %s) WHERE id(n) IN $id", label(nodeType), pblock);

        Parameters params = Parameters.params(ID, ids)
            .add(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // Post processing
    // ----------------------------------------------------------------------

    protected Map<String, Object> postProcess(Map<String, Object> map) {
        return map;
    }

    // ----------------------------------------------------------------------
    // Low level
    // ----------------------------------------------------------------------

    protected String/*Id*/ create(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);
            return toId(result.next().get(0).asLong());
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected Map<String, Object> retrieve(String alias, String s, Map<String, Object> params) {
        return retrieve(alias, s, params, false);
    }

    protected Map<String, Object> retrieve(String alias, String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);
            if (!result.hasNext())
                return null;

            Record r = result.single();
            if (edge)
                return toEdgeMap(alias, r);
            else
                return toNodeMap(alias, r);
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected String find(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);
            if (!result.hasNext())
                return null;
            else
                return toId(result.next().get(0).asLong());
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected GraphIterator<String> findAllIter(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);
            return new Neo4JResultSet<String>(result) {
                protected String compose(Record r) {
                    return toId(r.get(0).asLong());
                }
            };
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected GraphIterator<Map<String, Object>> retrieveAllIter(String alias, String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                @Override
                protected Map<String, Object> compose(Record r) {
                    Map<String, Object> nv = edge ? toEdgeMap(alias, r) : toNodeMap(alias, r);
                    Neo4JOnlineSession.this.postProcess(nv);
                    return nv;
                }
            };
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected GraphIterator<Map<String, Object>> resultIter(String alias, String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                protected Map<String, Object> compose(Record r) {
                    Map<String, Object> nv = toResultMap(alias, r);
                    nv = Neo4JOnlineSession.this.postProcess(nv);
                    return nv;
                }
            };
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected long count(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);

            return result.single().get(0).asLong();
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    static void logStmt(String s, Map<String, Object> params) {
        logStmt(s, params, null);
    }

    static void logStmt(String s, Map<String, Object> params, Throwable t) {
        if (!logger.isDebugEnabled() && t == null)
            return;

        String msg = s;

        StringBuilder sb = new StringBuilder(s);
        if (t != null)
            sb.append("\n    ").append(t.getMessage()).append("\n");

        if (params != null && params.size() > 0) {
            for(String key : params.keySet())
                sb.append("\n    ")
                    .append(key)
                    .append("=")
                    .append(asString(params.get(key)));
            msg = sb.toString();
        }

        if (t == null)
            logger.debug(msg);
        else if (t instanceof TransientException)
            logger.error(String.format("[TransientException] %s", msg));
        else
            logger.error(msg, t);
    }

    private static String asString(Object value) {
        if (value == null)
            return "null";
        if (value instanceof String)
            return String.format("'%s'", value);
        else
            return value.toString();
    }

    // ----------------------------------------------------------------------
    // Low level
    // ----------------------------------------------------------------------

    @Override
    public Query query(String s, Map<String, Object> params) {
        // already used in Ne4JQuery
        // logStmt(s, params);
        try {
            s = StringUtils.format(s, params);

            return new Neo4JQuery(this, N, s, params);
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    @Override
    public long execute(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            s = StringUtils.format(s, params);
            Result result = session_run(s, params);
            return total(result);
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    private static long total(Result result) {
        SummaryCounters summary = result.consume().counters();
        return summary.nodesDeleted()
            + summary.relationshipsDeleted()
            + summary.nodesCreated()
            + summary.relationshipsCreated()
            + summary.propertiesSet();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public Result session_run(String s, Map<String, Object> params) {
        int c = count.incrementAndGet();
        if (c > graphdb.getMaxStatements()) {
            transaction.commit();
            transaction = session.beginTransaction();
            count.set(0);
        }

        return transaction.run(s, params);
    }

    private static Map<String, Object> toNodeMap(String alias, Record r) {
        if (r == null)
            return Parameters.emptyMap();

        Node node = r.get(alias).asNode();
        return toNodeMap(node);

    }

    public static  Map<String, Object> toNodeMap(Node node) {
        Map<String, Object> body = new HashMap<>();

        // put $id
        // put $labels
        // put $type     == labels[0]

        body.put(GRAPH_ID, toId(node.id()));
        body.put(GRAPH_LABELS, node.labels());
        body.put(GRAPH_TYPE, ((List<String>)body.get(GRAPH_LABELS)).get(0));

        // put properties
        body.putAll(node.asMap());

        return body;
    }

    private static Map<String, Object> toEdgeMap(String alias, Record r) {
        if (r == null)
            return Parameters.emptyMap();

        Relationship edge = r.get(alias).asRelationship();
        return toEdgeMap(edge);
    }

    private static Map<String, Object> toEdgeMap(Relationship edge) {
        Map<String, Object> body = new HashMap<>();

        // put $id
        // put $type
        // put $source
        // put $target

        body.put(GRAPH_ID, toId(edge.id()));
        body.put(GRAPH_TYPE, edge.type());
        body.put(SOURCE_ID, toId(edge.startNodeId()));
        body.put(TARGET_ID, toId(edge.endNodeId()));

        // put properties
        // put fromId
        // put toId
        body.putAll(edge.asMap());
        body.put("fromId", toId(edge.startNodeId()));
        body.put("toId",   toId(edge.endNodeId()));

        return body;
    }

    private static Map<String, Object> toResultMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

        if (r == null)
            return body;

        body.putAll(r.asMap());

        Set<String> keys = new HashSet<>(body.keySet());
        for(String k : keys) {
            Object v = body.get(k);
            if (v instanceof Relationship)
                body.put(k, toEdgeMap((Relationship) v));
            else if (v instanceof Node)
                body.put(k, toNodeMap((Node) v));
            else
                body.put(k, v);
        }

        return body;
    }

    // @Override
    // public Query queryPath(String edgeType,
    //                        String fromId,
    //                        String toId,
    //                        Direction direction, boolean recursive,
    //                        Map<String, Object> edgeProps)
    // {
    //     String eblock = eblock(E, edgeType, direction, recursive, null);
    //     String wblock = wblock(E, edgeProps, true, false);
    //     String s;
    //
    //     if (recursive) {
    //         s = String.format("MATCH shortestPath( (from) %s (to) ) " +
    //                         "WHERE id(from) = $from AND id(to) = $to %s",
    //                 eblock, wblock);
    //     }
    //     else {
    //         s = String.format("MATCH (from) %s (to) " +
    //                         "WHERE id(from) = $from AND id(to) = $to %s",
    //                 eblock, wblock);
    //     }
    //
    //     Parameters params = Parameters.params(
    //                     FROM, asId(fromId),
    //                     TO, asId(toId))
    //             .add(E, edgeProps);
    //
    //     return new Neo4JQuery(this, E, s, params).edge();
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
