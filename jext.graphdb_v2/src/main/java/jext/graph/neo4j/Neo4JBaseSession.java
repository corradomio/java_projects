package jext.graph.neo4j;

import jext.graph.Direction;
import jext.graph.GraphDatabase;
import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Query;
import jext.util.logging.Logger;
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

import static jext.graph.NodeId.asId;
import static jext.graph.NodeId.invalidId;
import static jext.graph.NodeId.toId;
import static jext.graph.neo4j.WhereFormatter.eblock;
import static jext.graph.neo4j.WhereFormatter.label;
import static jext.graph.neo4j.WhereFormatter.pblock;
import static jext.graph.neo4j.WhereFormatter.sblock;
import static jext.graph.neo4j.WhereFormatter.ublock;
import static jext.graph.neo4j.WhereFormatter.wblock;
import static jext.util.Parameters.empty;

public abstract class Neo4JBaseSession implements GraphSession {

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

    public static final int MAX_STATEMENTS = 8 * 1024;
    public static final int MAX_DELETE_NODES = 16 * 1024;

    public static final String REF_ID = "refId";
    public static final String REVISION = "revision";
    public static final String REVISIONS = "revisions";
    public static final String IN_REVISION = "inRevision";
    public static final String COUNT = "count";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected static final Logger logger = Logger.getLogger(Neo4JOnlineSession.class);

    protected final Neo4JOnlineDatabase graphdb;
    protected final Driver driver;
    protected Session session;
    protected Transaction transaction;
    protected final AtomicInteger count = new AtomicInteger();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    Neo4JBaseSession(Neo4JOnlineDatabase graphdb) {
        this.graphdb = graphdb;
        this.driver = graphdb.getDriver();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public GraphDatabase getDatabase() {
        return graphdb;
    }

    // ----------------------------------------------------------------------
    // Connect/Disconnect
    // ----------------------------------------------------------------------

    public Neo4JBaseSession connect() {
        this.session = this.driver.session();
        this.transaction = this.session.beginTransaction();
        return this;
    }

    protected void commitAndBegin() {
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
    public Query queryUsing(String queryName, Map<String, Object> params) {
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
    public Query queryUsingFullText(String query, Map<String, Object> params) {

        // $label = l | {l1,...}
        // refId  = r | {r1,...}
        String wblock = wblock(NONE,
            Parameters.exclude(params, "$indexName", "$query"), false, false);

        String s = String.format("CALL db.index.fulltext.queryNodes($indexName, $query) YIELD node AS n %s", wblock);

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // Single node
    // ----------------------------------------------------------------------

    @Nonnull
    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String, Object> nodeProps) {
        String pblock = pblock(N, nodeProps);
        String sblock = sblock(N, nodeProps, true);

        String s = String.format("CREATE (n:%s %s) %s RETURN id(n)", nodeType, pblock, sblock);

        Parameters params = Parameters.params().prefix(N, nodeProps);

        return this.create(s, params);
    }

    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps) {
        String nodeId = queryNodes(nodeType, nodeProps).id();
        if (invalidId(nodeId))
            nodeId = createNode(nodeType, nodeProps);

        queryNodes(null, nodeId(nodeId)).update(updateProps);
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

        Parameters params = Parameters.params().prefix(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // Create edges
    // ----------------------------------------------------------------------

    @Override
    public List<String> createEdges(String edgeType,
                                    @Nullable String fromType, Map<String, Object> fromProps,
                                    @Nullable String toType, Map<String, Object> toProps,
                                    Map<String, Object> edgeProps) {

        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
        String esblock = sblock(E, edgeProps, true);

        String m = innerQueryEdges(edgeType, fromType, fromProps, toType, toProps, null);

        String s = String.format("%s CREATE (from)%s(to)%s RETURN id(%s)",
            m,
            eblock,
            esblock,
            E
        );

        Parameters params = Parameters.params()
            .prefix(FROM, fromProps)
            .prefix(TO, toProps)
            .prefix(E, edgeProps);

        return this.creates(s, params);
    }

    // ----------------------------------------------------------------------
    // Query edges
    // ----------------------------------------------------------------------

    @Override
    public Query queryEdges(String edgeType,
                            @Nullable String fromType, Map<String, Object> fromProps,
                            @Nullable String toType, Map<String, Object> toProps,
                            Map<String, Object> edgeProps) {

        String s = innerQueryEdges(edgeType, fromType, fromProps, toType, toProps, edgeProps);

        Parameters params = Parameters.params()
            .prefix(FROM, fromProps)
            .prefix(TO, toProps)
            .prefix(E, edgeProps);

        return new Neo4JQuery(this, E, s, params).edge();
    }

    private static String innerQueryEdges(String edgeType,
                                          String fromType, Map<String, Object> fromProps,
                                          String toType, Map<String, Object> toProps,
                                          Map<String, Object> edgeProps) {

        boolean edge = edgeProps != null;
        String fblock = pblock(FROM, fromProps);
        String tblock = pblock(TO, toProps);
        String eblock = edge
            ? eblock(E, edgeType, Direction.Output, false, edgeProps)
            : ",";

        String wfblock = wblock(FROM, fromProps, false, true);
        String wtblock = wblock(TO, toProps, !wfblock.isEmpty(), true);
        String weblock = edge
            ? wblock(E, edgeProps, !wtblock.isEmpty(), true)
            : NONE;

        String wblock = String.format("%s%s%s", wfblock, wtblock, weblock);

        return String.format(
            "MATCH (from%s%s)%s(to%s%s)%s ",
            label(fromType), fblock,
            eblock,
            label(toType), tblock,
            wblock
        );
    }

    // ----------------------------------------------------------------------
    // Adjacent nodes
    // ----------------------------------------------------------------------

    @Override
    public Query queryAdjacentNodes(String fromId,
                                    String edgeType, Direction direction, boolean recursive,
                                    String nodeType, Map<String, Object> nodeProps,
                                    Map<String, Object> edgeProps) {

        return queryAdjacentNodes(Collections.singletonList(fromId), edgeType, direction, recursive,
            nodeType, nodeProps, edgeProps);
    }

    @Override
    public Query queryAdjacentNodes(Collection<String> fromIds,
                                    String edgeType, Direction direction, boolean recursive,
                                    String nodeType, Map<String, Object> nodeProps,
                                    Map<String, Object> edgeProps) {

        if (!recursive)
            return queryAdjacentNodesImpl(fromIds, edgeType, direction, recursive, nodeType,
                nodeProps, edgeProps);
        else
            return new Neo4JAdjacentQuery(this, fromIds, edgeType, direction, nodeType,
                nodeProps, edgeProps);
    }

    Query queryAdjacentNodesImpl(Collection<String> fromIds,
                                 String edgeType, Direction direction, boolean recursive,
                                 String nodeType, Map<String, Object> nodeProps,
                                 Map<String, Object> edgeProps) {

        String eblock = eblock(E, edgeType, direction, recursive, edgeProps);
        String pblock = pblock(N, nodeProps);
        String wblock = wblock(E, edgeProps, true, false);
        String s;

        if (recursive) {
            s = String.format("MATCH shortestPath( (t) %s (n:%s %s) ) WHERE id(t) in $id %s",
                eblock, nodeType, pblock, wblock);
        } else {
            s = String.format("MATCH (t) %s (n:%s %s) WHERE id(t) in $id %s",
                eblock, nodeType, pblock, wblock);
        }

        Parameters params = Parameters.params(ID, normalizeId(fromIds))
            .prefix(N, nodeProps)
            .prefix(E, edgeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    Query queryAdjacentNodesStep(Collection<String> fromIds,
                                 String edgeType, Direction direction,
                                 Map<String, Object> edgeProps) {
        String eblock = eblock(E, edgeType, direction, false, edgeProps);
        String wblock = wblock(E, edgeProps, true, false);
        String s = String.format("MATCH (s) %s (n) WHERE id(s) in $id %s",
            eblock, wblock);

        Parameters params = Parameters.params(ID, asId(fromIds))
            .prefix(E, edgeProps);

        return new Neo4JQuery(this, N, s, params).distinct();
    }

    Query selectNodes(Collection<String> ids, String nodeType, Map<String, Object> nodeProps) {
        String pblock = pblock(N, nodeProps);
        String s = String.format("MATCH (n%s %s) WHERE id(n) IN $id", label(nodeType), pblock);

        Parameters params = Parameters.params(ID, asId(ids))
            .prefix(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
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
    // Post processing
    // ----------------------------------------------------------------------

    protected Map<String, Object> preProcess(Map<String, Object> map) {
        // if contains 'id', convert the is in a set of long
        if (map.containsKey(ID))
            map.put(ID, normalizeId(map.get(ID)));

        return map;
    }

    protected Map<String, Object> postProcess(Map<String, Object> map) {
        return map;
    }

    // ----------------------------------------------------------------------
    // Pre processing
    // ----------------------------------------------------------------------

    protected static Map<String, Object> nodeId(Object id) {
        return id == null ?  empty() : Parameters.params(ID, id);
    }

    private static Object/*Long or long[]*/ normalizeId(Object id) {
        if (id == null)
            return null;
        if (id instanceof String)
            return asId((String) id);
        if (id instanceof String[])
            return asId((String[]) id);
        // if (id instanceof Long)
        //     return id;
        // if (id instanceof long[])
        //     return id;
        // if (id instanceof Long[])
        //     return id;
        if (!(id instanceof Collection))
            return id;
        if (((Collection) id).isEmpty())
            return id;
        if ((((Collection) id).iterator().next()) instanceof String)
            return asId((Collection<String>) id);
        else
            return id;
    }

    // ----------------------------------------------------------------------
    // Low level
    // ----------------------------------------------------------------------

    protected String/*Id*/ create(String s, Map<String, Object> params) {
        logStmt(s, params);
        try {
            Result result = session_run(s, params);
            return toId(result.next().get(0).asLong());
        } catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected List<String> creates(String s, Map<String, Object> params) {
        logStmt(s, params);
        try {
            List<String> ids = new ArrayList<>();
            Result result = session_run(s, params);
            while(result.hasNext())
                ids.add(result.next().get(0).toString());
            return ids;
        } catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected Map<String, Object> retrieve(String s, Map<String, Object> params) {
        Map<String, Object> nv;
        logStmt(s, params);
        try {
            Result result = session_run(s, params);
            if (!result.hasNext())
                return null;

            Record r = result.single();
            nv = toResultMap(r);
            return postProcess(nv);
        } catch (Throwable t) {
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
        } catch (Throwable t) {
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
        } catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected GraphIterator<Map<String, Object>> retrieveAllIter(String s, Map<String, Object> params) {
        logStmt(s, params);
        try {
            Result result = session_run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                @Override
                protected Map<String, Object> compose(Record r) {
                    Map<String, Object> nv = toResultMap(r);
                    return postProcess(nv);
                }
            };
        } catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    // protected GraphIterator<Map<String, Object>> resultIter(String s, Map<String, Object> params) {
    //     logStmt(s, params);
    //     try {
    //         Result result = session_run(s, params);
    //
    //         return new Neo4JResultSet<Map<String, Object>>(result) {
    //             @Override
    //             protected Map<String, Object> compose(Record r) {
    //                 Map<String, Object> nv = toResultMap(r);
    //                 return postProcess(nv);
    //             }
    //         };
    //     } catch (Throwable t) {
    //         logStmt(s, params, t);
    //         throw t;
    //     }
    // }

    protected long count(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);
            return result.single().get(0).asLong();
        } catch (Throwable t) {
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
            for (String key : params.keySet())
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
        } catch (Throwable t) {
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
        } catch (Throwable t) {
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

    private static Map<String, Object> toResultMap(Record r) {
        Map<String, Object> body = new HashMap<>();

        if (r == null)
            return body;

        body.putAll(r.asMap());

        Set<String> keys = new HashSet<>(body.keySet());
        for (String k : keys) {
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

    // private static Map<String, Object> toNodeMap(String alias, Record r) {
    //     if (r == null)
    //         return empty();
    //
    //     Node node = r.get(alias).asNode();
    //     return toNodeMap(node);
    // }

    public static Map<String, Object> toNodeMap(Node node) {
        Map<String, Object> body = new HashMap<>();

        // put $id
        // put $labels
        // put $type     == labels[0]

        body.put(GRAPH_ID, toId(node.id()));
        body.put(GRAPH_LABELS, node.labels());
        body.put(GRAPH_TYPE, ((List<String>) body.get(GRAPH_LABELS)).get(0));

        // put properties
        body.putAll(node.asMap());

        return body;
    }

    // private static Map<String, Object> toEdgeMap(String alias, Record r) {
    //     if (r == null)
    //         return empty();
    //
    //     Relationship edge = r.get(alias).asRelationship();
    //     return toEdgeMap(edge);
    // }

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
        body.put("toId", toId(edge.endNodeId()));

        return body;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
