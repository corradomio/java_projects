package jext.graph.neo4j;

import jext.graph.Direction;
import jext.graph.GraphDatabase;
import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Query;
import jext.util.logging.Logger;
import jext.util.Assert;
import jext.util.MapUtils;
import jext.util.Parameters;
import jext.util.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.exceptions.TransientException;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

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

import static jext.graph.NodeId.asId;
import static jext.graph.NodeId.asIds;
import static jext.graph.NodeId.invalidId;
import static jext.graph.NodeId.toId;
import static jext.graph.neo4j.CypherFormatter.ablock;
import static jext.graph.neo4j.CypherFormatter.eblock;
import static jext.graph.neo4j.CypherFormatter.label;
import static jext.graph.neo4j.CypherFormatter.pblock;
import static jext.graph.neo4j.CypherFormatter.sblock;
import static jext.graph.neo4j.CypherFormatter.ublock;
import static jext.graph.neo4j.CypherFormatter.wblock;

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

    static final int MAX_STATEMENTS = 8*1024;
    static final int MAX_DELETE_NODES = 16*1024;

    private static final String USES = "uses";
    private static final String TYPE = "type";

    public static final String REF_ID = "refId";
    public static final String REVISION = "revision";
    public static final String REVISIONS = "revisions";
    public static final String IN_REVISION = "inRevision";
    public static final String COUNT = "count";

    // ----------------------------------------------------------------------
    // Special handled parameters
    // ----------------------------------------------------------------------

    private static final String LABELS = "labels";
    private static final String REF_IDS = "refIds";

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
    // 'params' must contain a parameter with name 'name' as another Map<String,Object>
    // to extend the 'namedQuery' conditions
    //

    @Override
    public Query queryUsing(String queryName,  Map<String,Object> params) {
        String query = graphdb.getQuery(queryName);

        if (query.contains(AND_BLOCK) || query.contains(WHERE_BLOCK)) {
            Parameters nparams = Parameters.params(params);
            while (query.contains(AND_BLOCK))
                query = ublock(query, nparams, true);
            while (query.contains(WHERE_BLOCK))
                query = ublock(query, nparams, false);
            params = nparams;
        }

        // replace ${name} with 'name' value in 'params'
        String s = StringUtils.format(query, params);

        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public void executeUsing(String queryName, Map<String,Object> params) {
        String query = graphdb.getQuery(queryName);

        if (query.contains(AND_BLOCK) || query.contains(WHERE_BLOCK)) {
            Parameters nparams = Parameters.params(params);
            while (query.contains(AND_BLOCK))
                query = ublock(query, nparams, true);
            while (query.contains(WHERE_BLOCK))
                query = ublock(query, nparams, false);
            params = nparams;
        }

        String s = StringUtils.format(query, params);

        logStmt(s, params);
        try {
            session_run(s, params);
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }

        // NOTE: 'CALL apoc.cypher.runMany(...)' DOESN'T work!!!
    }

    // ----------------------------------------------------------------------
    // Query using fulltext
    // ----------------------------------------------------------------------

    @Override
    public Query queryUsingFullText(String query,  Map<String,Object> params) {

        // indexName
        // query
        // labels ONLY if size > 0
        // refIds ONLY if size > 0
        boolean hasLabels = params.containsKey(LABELS) && !((Collection<String>)params.get(LABELS)).isEmpty();
        boolean hasRefIds = params.containsKey(REF_IDS) && !((Collection<String>)params.get(REF_IDS)).isEmpty();

        String s = "CALL db.index.fulltext.queryNodes($indexName, $query) YIELD node AS n ";
        if (hasLabels && hasRefIds)
            s += "WHERE labels(n) = $labels AND n.refId IN $refIds ";
        else if (hasLabels)
            s += "WHERE labels(n) = $labels ";
        else if (hasRefIds)
            s += "WHERE n.refId IN $refIds ";

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // Single node
    // ----------------------------------------------------------------------

    @Override
    public boolean existsNode(String nodeId) {
        if (invalidId(nodeId))
            return false;

        String s = "MATCH (n) WHERE id(n) = $id RETURN count(n)";

        return this.count(s, Parameters.params(
            ID, asId(nodeId))) > 0;
    }

    @Override
    public boolean existsNode(@Nullable String nodeType, Map<String,Object> nodeProps) {
        //return findNode(nodeType, nodeProps) != null;
        return countNodes(nodeType, nodeProps) > 0;
    }

    @Override
    public String createNode(String nodeType, Map<String,Object> nodeProps) {
        if(Assert.check(nodeProps.containsKey(TYPE), "Missing 'type' in %s", nodeType))
            Assert.nop();

        String pblock = pblock(N, nodeProps);
        String sblock = sblock(N, nodeProps, true);

        String s = String.format("CREATE (n%s %s) %s RETURN id(n)", label(nodeType), pblock, sblock);

        Parameters params = Parameters.params(nodeProps)
            .prefix(N, nodeProps);

        return this.create(s, params);
    }

    @Override
    public boolean deleteNode(String nodeId) {
        if (invalidId(nodeId)) return false;

        Parameters params = Parameters.params(
            ID, asId(nodeId));
        String s = "MATCH (n) WHERE id(n) = $id DETACH DELETE n";
        long count = this.delete(s, params, false);
        return count > 0;
    }

    @Override
    public Map<String,Object> getNodeProperties(String nodeId) {
        if (invalidId(nodeId)) return Collections.emptyMap();

        Parameters params = Parameters.params(
            ID, asId(nodeId));
        String s = "MATCH (n) WHERE id(n) = $id RETURN n";

        Map<String,Object> nv = this.retrieve(N, s, params);
        return postProcess(nv);
    }

    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String,Object> findProps, Map<String,Object> updateProps) {
        String nodeId = findNode(nodeType, findProps);
        if (invalidId(nodeId))
            nodeId = createNode(nodeType, findProps);

        setNodeProperties(nodeId, updateProps);

        return nodeId;
    }

    // ----------------------------------------------------------------------
    // List of nodes
    // ----------------------------------------------------------------------

    @Override
    public long countNodes(String nodeType, Map<String,Object> nodeProps) {
        return queryNodes(nodeType, nodeProps).count();
    }

    @Override
    public long deleteNodes(Collection<String> nodeIds) {
        int maxdelete = graphdb.getMaxDelete();

        if (nodeIds == null || nodeIds.isEmpty())
            return 0;

        if (nodeIds.size() > maxdelete)
            nodeIds = new ArrayList<>(nodeIds);

        while(nodeIds.size() > maxdelete) {
            int n = nodeIds.size();
            commitAndBegin();

            deleteSomeNodes(((List<String>)nodeIds).subList(0, maxdelete));
            nodeIds = ((List<String>)nodeIds).subList(maxdelete, n);
        }
        if (!nodeIds.isEmpty())
            deleteSomeNodes(nodeIds);

        return nodeIds.size();
    }

    private void deleteSomeNodes(Collection<String> nodeIds) {
        Parameters params = Parameters.params(
            "ids", asIds(nodeIds));
        String s = "MATCH (n) WHERE id(n) IN $ids DETACH DELETE n";
        this.execute(s, params);
    }

    // ----------------------------------------------------------------------

    @Override
    public long deleteNodes(String nodeType, Map<String,Object> nodeProps) {
        return deleteNodes(nodeType, nodeProps, (count)->{ });
    }

    @Override
    public long deleteNodes(String nodeType, Map<String,Object> nodeProps, LongConsumer callable) {
        int maxdelete = graphdb.getMaxDelete();

        // commit the transaction
        long count = 1, total = 0;
        while (count > 0) {
            commitAndBegin();
            count = deleteNodes(nodeType, nodeProps, maxdelete);
            total += count;
            callable.accept(total);
        }

        return total;
    }

    private long deleteNodes(String nodeType, Map<String,Object> nodeProps, long count) {
        String pblock = pblock(N, nodeProps);
        String wblock = wblock(N, nodeProps, false, true);
        String s = String.format("MATCH (n%s %s) %s WITH n LIMIT %d DETACH DELETE n", label(nodeType), pblock, wblock, count);

        Assert.verify(count > 0, "deleteNodes: count MUST BE > 0");

        Parameters nparams = Parameters.params()
            .prefix(N, nodeProps);

        return this.delete(s, nparams, false);
    }

    // ----------------------------------------------------------------------

    @Override
    public List<Map<String,Object>> getNodesProperties(Collection<String> nodeIds) {
        Parameters params = Parameters.params(
            ID, asIds(nodeIds));
        String s = "MATCH (n) WHERE id(n) IN $id RETURN n";

        return this.retrieveAllIter(N, s, params, false).toList();
    }

    // ----------------------------------------------------------------------

    private static boolean noProps(Map<String,Object> props) {
        return props == null || props.isEmpty();
    }

    @Override
    public void setNodeProperties(String nodeId, Map<String,Object> nodeProps) {
        if (noProps(nodeProps))
            return;

        String sblock = sblock(N, nodeProps, false);

        Parameters params = Parameters.params(
            ID, asId(nodeId))
            .prefix(N, nodeProps);

        String s = String.format("MATCH (n) WHERE id(n) = $id %s RETURN n", sblock);

        this.execute(s, params);
    }

    @Override
    public void setNodesProperties(Collection<String> nodeIds, Map<String,Object> nodeProps) {
        if (noProps(nodeProps) || nodeIds == null || nodeIds.isEmpty())
            return;

        String sblock = sblock(N, nodeProps, false);

        Parameters params = Parameters.params(
            "ids", asIds(nodeIds))
            .prefix(N, nodeProps);

        String s = String.format("MATCH (n) WHERE id(n) IN $ids %s RETURN n", sblock);

        this.execute(s, params);
    }

    @Override
    public void setNodesProperties(String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps) {
        if (noProps(updateProps))
            return;

        // MATCH (n:type {...}) WHERE ... SET ... RETURN count(n)

        String pblock = pblock(N, nodeProps);
        String wblock = wblock(N, nodeProps, false, true);
        String sblock = sblock(N, updateProps, false);
        String s = String.format("MATCH (n%s %s) %s %s RETURN count(n)", label(nodeType), pblock, wblock, sblock);

        Parameters params = Parameters.params(nodeProps)
            .prefix(N, nodeProps)
            .prefix(N, updateProps);

        this.execute(s, params);
    }

    @Override
    public void setNodeProperty(String nodeId, String name, Object value) {
        setNodeProperties(nodeId, MapUtils.asMap(name, value));
    }

    @Override
    public void setNodesProperty(String nodeType, Map<String,Object> nodeProps, String name, Object value) {
        setNodesProperties(nodeType, nodeProps, MapUtils.asMap(name, value));
    }

    // ----------------------------------------------------------------------
    // Delete properties
    // ----------------------------------------------------------------------

    // @Override
    // public void deleteNodeProperty(String nodeId, String name) {
    //     deleteNodesProperties(Collections.singletonList(nodeId), Collections.singleton(name));
    // }

    @Override
    public void deleteNodesProperties(Collection<String> nodeIds, Collection<String> names) {
        String ablock = ablock(N, names);
        Parameters params = Parameters.params(
            "ids", asIds(nodeIds));
        String s = String.format("MATCH (n) WHERE id(n) IN $ids REMOVE %s", ablock);
        this.execute(s, params);
    }

    // ----------------------------------------------------------------------
    // Delete ALL
    // ----------------------------------------------------------------------

    @Override
    public void deleteAll() {
        queryNodes(null, null).delete();
    }

    // ----------------------------------------------------------------------
    // Query nodes
    // ----------------------------------------------------------------------

    @Override
    public Query queryNodes(String nodeType, Map<String,Object> nodeProps) {
        String pblock = pblock(N, nodeProps);
        String wblock = wblock(N, nodeProps, false, true);
        String s = String.format("MATCH (n%s %s) %s", label(nodeType), pblock, wblock);

        Parameters params = Parameters.params(nodeProps)
            .prefix(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public String/*nodeId*/ findNode(String nodeType, Map<String,Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps);
        return query.id();
    }

    // ----------------------------------------------------------------------
    // Adjacent nodes
    // ----------------------------------------------------------------------

    @Override
    public Query queryAdjacentNodes(
        String fromId, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String,Object> nodeProps, Map<String,Object> edgeProps) {

        return queryAdjacentNodes(Collections.singletonList(fromId), edgeType, direction, recursive,
            nodeType, nodeProps, edgeProps);
    }

    @Override
    public Query queryAdjacentNodes(
        Collection<String> fromIds, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String,Object> nodeProps, Map<String,Object> edgeProps) {

        if (!recursive)
            return queryAdjacentNodesImpl(fromIds, edgeType, direction, recursive, nodeType,
                nodeProps, edgeProps);
        else
            return new Neo4JAdjacentQuery(this, fromIds, edgeType, direction, nodeType,
                nodeProps, edgeProps);
    }

    Query queryAdjacentNodesImpl(
        Collection<String> fromIds, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String,Object> nodeProps, Map<String,Object> edgeProps) {

        String eblock = eblock(E, edgeType, direction, recursive, edgeProps);
        String pblock = pblock(N, nodeProps);
        String wblock = wblock(E, edgeProps, true, false);
        String s;

        if (recursive) {
            s = String.format("MATCH shortestPath( (t) %s (n: %s %s) ) WHERE id(t) in $id %s",
                eblock, nodeType, pblock, wblock);
        }
        else {
            s = String.format("MATCH (t) %s (n: %s %s) WHERE id(t) in $id %s",
                eblock, nodeType, pblock, wblock);
        }

        Parameters params = Parameters.params(
            ID, asIds(fromIds))
            .prefix(N, nodeProps)
            .prefix(E, edgeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    Query queryAdjacentNodesStep(Collection<String> fromIds, String edgeType, Direction direction, Map<String,Object> edgeProps) {
        String eblock = eblock(E, edgeType, direction, false, edgeProps);
        String wblock = wblock(E, edgeProps, true, false);
        String s = String.format("MATCH (s) %s (n) WHERE id(s) in $id %s",
            eblock, wblock);

        Parameters params = Parameters.params(
            ID, asIds(fromIds))
            .prefix(E, edgeProps);

        return new Neo4JQuery(this, N, s, params).distinct();
    }

    Query selectNodes(Collection<String> ids, String nodeType,  Map<String,Object> nodeProps) {
        String pblock = pblock(N, nodeProps);
        String s = String.format("MATCH (n%s %s) WHERE id(n) in $id", label(nodeType), pblock);

        Parameters params = Parameters.params(
            ID, asIds(ids))
            .prefix(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // Query edges
    // ----------------------------------------------------------------------

    @Override
    public Query queryEdges(String edgeType,
                            String fromType, Map<String,Object> fromProps,
                            String toType,   Map<String,Object> toProps,
                            Map<String,Object> edgeProps) {

        String fblock = pblock(FROM, fromProps);
        String eblock = eblock(E, edgeType, Direction.Output, false, Collections.emptyMap());
        String tblock = pblock(TO, toProps);

        String wfblock = wblock(FROM, fromProps, false, false);
        String wtblock = wblock(TO,   toProps, !wfblock.isEmpty(), false);
        String weblock = wblock(E,            edgeProps, !wtblock.isEmpty(), false);

        String wblock = String.format("%s%s%s", wfblock, wtblock, weblock);

        Parameters allProps = Parameters.params()
            .prefix(FROM,fromProps)
            .prefix(TO,  toProps)
            .prefix(E,   edgeProps);

        String ftype = fromType == null ? NONE : ":" + fromType;
        String ttype = toType   == null ? NONE : ":" + toType;

        String s = String.format("" +
                "MATCH (from%s %s) %s (to%s %s) %s " +
                "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
            ftype,
            fblock,

            eblock,

            ttype,
            tblock,
            wblock
        );

        if(edgeType != null && edgeType.equals("uses"))
            s = s +", e.type as uses";

        return new Neo4JQuery(this, E, s, allProps);
    }


    @Override
    public Query queryEdges(String edgeType,
                            String fromId, Collection<String> toIds,
                            Map<String,Object> edgeProps) {
        return queryEdges(edgeType, Collections.singletonList(fromId), toIds, edgeProps);
    }

    @Override
    public Query queryEdges(String edgeType,
                            Collection<String> fromIds,
                            Collection<String> toIds,
                            Map<String,Object> edgeProps) {

        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
        String s;
        Parameters params;

        if (fromIds == toIds) {
            s = String.format("MATCH (from) %s (to) WHERE id(from) IN $from AND id(to) IN $from " +
                    "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                eblock
            );

            params = Parameters.params(FROM, asIds(fromIds));
        }
        else {
            s = String.format("MATCH (from) %s (to) WHERE id(from) IN $from AND id(to) IN $to " +
                    "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                eblock
            );

            params = Parameters.params(FROM, asIds(fromIds), TO, asIds(toIds));
        }

        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public Query queryPath(String edgeType,
                           String fromId,
                           String toId,
                           Direction direction, boolean recursive,
                           Map<String,Object> edgeProps)
    {
        String eblock = eblock(E, edgeType, direction, recursive, null);
        String wblock = wblock(E, edgeProps, true, false);
        String s;

        if (recursive) {
            s = String.format("MATCH shortestPath( (from) %s (to) ) " +
                            "WHERE id(from) = $from AND id(to) = $to %s",
                    eblock, wblock);
        }
        else {
            s = String.format("MATCH (from) %s (to) " +
                            "WHERE id(from) = $from AND id(to) = $to %s",
                    eblock, wblock);
        }

        Parameters params = Parameters.params(
                        FROM, asId(fromId),
                        TO, asId(toId))
                .prefix(E, edgeProps);

        return new Neo4JQuery(this, E, s, params).edge();
    }

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------
    // Note: the edge 'uses' has DIFFERENT subtypes:
    //
    //      uses/[type]
    //
    // where [type] can be: 'dependsOn', 'extends', 'implements'
    //
    //

    @Nullable
    @Override
    public String findEdge(String edgeType, String fromId, String toId) {
        return findEdge(edgeType, fromId, toId, Collections.emptyMap());
    }

    @Override
    public String createEdge(String edgeType, String fromId, String toId) {
        return createEdge(edgeType, fromId, toId, Collections.emptyMap());
    }

    @Nullable
    @Override
    public String findEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps) {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        String pblock = pblock(E, edgeProps);
        String s = String.format("MATCH (from) -[e:%s %s]-> (to) WHERE id(from) = $from AND id(to)=$to",
            edgeType, pblock);

        Parameters params = Parameters.params(
                FROM, asId(fromId),
                TO, asId(toId));

        return this.query(s, params).id(E);
    }

    @Override
    public String createEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps) {
        // fast version without check for edge existence
        // return createEdge(edgeType, fromId, toId, Collections.emptyMap(), edgeProps);
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        Parameters params = Parameters.params(
                FROM, asId(fromId),
                TO, asId(toId)
        ).prefix(E, edgeProps);

        // search if the edge already exists
        String pblock = pblock(E, edgeProps);
        String s = String.format("MATCH (from),(to) WHERE id(from) = $from AND id(to)=$to " +
                "CREATE (from) -[e:%s %s]-> (to) " +
                "RETURN id(e)", edgeType, pblock);

        String edgeId = this.create(s, params);

        setEdgeProperties(edgeId, edgeProps);

        return edgeId;
    }

    @Nullable
    @Override
    public String createEdge(String edgeType, String fromId, String toId,
                             Map<String,Object> findProps,
                             Map<String,Object> updateProps)
    {
        if (invalidId(fromId) || invalidId(toId) || fromId.equals(toId))
            return null;

        String edgeId = findEdge(edgeType, fromId, toId);
        if (edgeId == null) {
            return createEdge(edgeType, fromId, toId, Parameters.params(findProps).add(updateProps));
        }
        else {
        setEdgeProperties(edgeId, updateProps);
        }

        return edgeId;
    }

    @Override
    public boolean deleteEdge(String edgeId) {

        String s = "MATCH ()-[e]-() WHERE id(e)=$id DELETE e";

        Parameters.params(ID, asId(edgeId));

        this.execute(s, Parameters.params(
                ID, asId(edgeId)));

        return true;
    }

    // ----------------------------------------------------------------------
    // Multiple edges
    // ----------------------------------------------------------------------

    @Override
    public void createEdges(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps) {
        if (USES.equals(edgeType) && edgeProps.containsKey(TYPE)) {
            Map<String,Object> findProps = Parameters.params(TYPE, edgeProps.get(TYPE));
            createEdges(edgeType, fromId, toIds, findProps, edgeProps);
        }
        else {
            createEdges(edgeType, fromId, toIds, Collections.emptyMap(), edgeProps);
        }
    }

    @Override
    public void createEdges(String edgeType, String fromId, Collection<String> toIds,
                            Map<String,Object> findProps, Map<String,Object> updateProps) {
        Parameters pparams = Parameters.params(findProps);
        Parameters wparams = Parameters.params(findProps);
        Parameters sparams = Parameters.params(updateProps);

        String s;
        String pblock = pblock(E, pparams);
        String wblock = wblock(E, wparams, true, true);
        String sblock = sblock(E, sparams, false);

        Parameters edgeProps = Parameters.params(findProps).add(updateProps);
        Set<String> reachableIds, missingIds;

        // 1) toIds can be null then select all nodes already reacheable from 'fromId'
        // 2) if toIds is not null, however to use the previous step to identify which ids are without
        //    the edge, and to create ONLY the missing edges

        //
        // 1) find the reachable nodes
        //
        {
            s = String.format("MATCH (from) -[e:%s %s]-(to) WHERE id(from)=$from %s RETURN id(to)", edgeType,
                pblock, wblock);

            Parameters params = Parameters.params(
                FROM, asId(fromId)
            ).add(edgeProps).prefix(E, edgeProps);

            reachableIds = this.findAllIter(s, params).toSet();
        }

        //
        // 2) nodes not connected
        //
        {
            missingIds = new HashSet<>(toIds);
            missingIds.removeAll(reachableIds);
        }

        //
        // 3) create the missing edges
        //
        {
            pparams = Parameters.params(findProps);
            pblock = pblock(E, pparams);
            s = String.format("MATCH (from),(to) WHERE id(from)=$from AND id(to) IN $to " +
                    "CREATE (from) -[e:%s %s]-> (to) %s RETURN id(e)",
                edgeType, pblock, sblock);

            Parameters params = Parameters.params(
                FROM, asId(fromId),
                TO, asIds(missingIds)           // missingIds
            ).add(edgeProps)
                .prefix(E, edgeProps)
                .prefix(E, pparams)
                .prefix(E, sparams)
                .prefix(E, wparams);

            this.execute(s, params);
        }

        //
        // 4) set the properties for all edges
        //
        {
            pblock = pblock(E, findProps);
            wblock = wblock(E, findProps, true, true);
            sblock = sblock(E, updateProps, false);
            s = String.format("MATCH (from) -[e:%s %s]-> (to) WHERE id(from)=$from AND id(to) IN $to " +
                    "%s " +
                    "%s " +
                    "RETURN id(e)",
                edgeType,
                pblock, wblock, sblock);

            Parameters params = Parameters.params(
                FROM, asId(fromId),
                TO, asIds(reachableIds)             // reachableIds
            ).add(edgeProps)
                .prefix(E, edgeProps)
                .prefix(E, pparams)
                .prefix(E, sparams)
                .prefix(E, wparams);

            this.execute(s, params);
        }
    }

    // ----------------------------------------------------------------------
    // Edge properties
    // ----------------------------------------------------------------------

    @Override
    public void setEdgeProperties(String edgeType, String fromId, String toId,
                                  Map<String,Object> findProps,
                                  Map<String,Object> updateProps) {
        String edgeId = findEdge(edgeType, fromId, toId, findProps);
        if (edgeId == null) {
            Parameters createProps = Parameters.params(findProps).add(updateProps);
            createEdge(edgeType, fromId, toId, createProps);
        }
        else
            setEdgeProperties(edgeId, updateProps);
    }

    @Override
    public void setEdgeProperties(String edgeType, String fromId, Collection<String> toIds,
                                  Map<String,Object> findProps,
                                  Map<String,Object> updateProps) {
        if (toIds != null && toIds.isEmpty())
            return;

        Parameters pparams = Parameters.params(findProps);
        Parameters wparams = Parameters.params(findProps);
        Parameters sparams = Parameters.params(updateProps);

        String pblock = pblock(E, pparams);
        String wblock = wblock(E, wparams, true, true);
        String sblock = sblock(E, sparams, false);
        String s;

        if (edgeType == null && toIds == null)
            s = String.format("MATCH (from) -[e %s]-> (to) " +
                "WHERE id(from) = $from " +
                "%s " +
                "%s " +
                "RETURN id(e)", pblock, wblock, sblock);
        else if (toIds == null)
            s = String.format("MATCH (from) -[e:%s %s]-> (to) " +
                "WHERE id(from) = $from " +
                "%s " +
                "%s " +
                "RETURN id(e)", edgeType, pblock, wblock, sblock);
        else if (edgeType == null)
            s = String.format("MATCH (from) -[e %s]-> (to) " +
                "WHERE id(from) = $from AND id(to) IN $to " +
                "%s " +
                "%s " +
                "RETURN id(e)", pblock, wblock, sblock);
        else
            s = String.format("MATCH (from) -[e:%s %s]-> (to) " +
                "WHERE id(from) = $from AND id(to) IN $to " +
                "%s " +
                "%s " +
                "RETURN id(e)", edgeType, pblock, wblock, sblock);

        Parameters params = Parameters.params(
            FROM, asId(fromId),
            TO, asIds(toIds))
            .prefix(E, pparams)
            .prefix(E, wparams)
            .prefix(E, sparams)
            .add(pparams).add(wparams).add(sparams);

        this.execute(s, params);
    }

    // ----------------------------------------------------------------------

    @Override
    public long deleteEdges(
            @Nullable String edgeType,  // can be null
            String fromType, Map<String,Object> fromProps,
            String toType, Map<String,Object> toProps,
            Map<String,Object> edgeProps,
            LongConsumer callback) {
        String s;
        int maxdelete = graphdb.getMaxDelete();

        if (edgeType == null)
            s = String.format("MATCH (from:%s %s) -[e %s]-> (to:%s %s) WITH e LIMIT %d DELETE e",
                fromType, pblock(FROM, fromProps),
                /*edgeType,*/ pblock(E, edgeProps),
                toType, pblock(TO, toProps), maxdelete);
        else
            s = String.format("MATCH (from:%s %s) -[e:%s %s]-> (to:%s %s) WITH e LIMIT %d DELETE e",
                fromType, pblock(FROM, fromProps),
                edgeType, pblock(E, edgeProps),
                toType, pblock(TO, toProps), maxdelete);

        Parameters params = Parameters.params()
            .prefix(FROM, fromProps)
            .prefix(E, edgeProps)
            .prefix(TO, toProps);

        long count=1, total = 0;
        while (count > 0) {
            this.commitAndBegin();
            count = this.delete(s, params, true);
            total += count;
            callback.accept(total);
        }

        return total;
    }

    @Override
    public long deleteEdges(String edgeType, String fromId, List<String> toIds, Map<String,Object> edgeProps,
                            LongConsumer callback) {
        String s;
        String pblock = pblock(E, edgeProps);
        String wblock = wblock(E, edgeProps, true, true);
        int maxdelete = graphdb.getMaxDelete();

        if (edgeType == null && toIds == null)
            s = String.format("MATCH (f) -[e %s]-> (t) WHERE id(f)=$from %s WITH e LIMIT %d DELETE e",
                pblock, wblock, maxdelete);
        else if (edgeType == null)
            s = String.format("MATCH (f) -[e %s]-> (t) WHERE id(f)=$from AND id(t) IN $to %s WITH E LIMIT %d DELETE e",
                pblock, wblock, maxdelete);
        else if (toIds == null)
            s = String.format("MATCH (f) -[e:%s %s]-> (t) WHERE id(f)=$from %s WITH e LIMIT %d DELETE e",
                edgeType, pblock, wblock, maxdelete);
        else
            s = String.format("MATCH (f) -[e:%s %s]-> (t) WHERE id(f)=$from AND id(t) IN $to %s WITH e LIMIT %d DELETE e",
                edgeType, pblock, wblock, maxdelete);

        Parameters params = Parameters.params(
                FROM, asId(fromId),
                TO, asIds(toIds)
            ).prefix(E, edgeProps);

        long count = 1, total = 0;
        while (count > 0) {
            this.commitAndBegin();
            count = this.delete(s, params, true);
            total += count;
            callback.accept(total);
        }

        return total;
    }

    // ----------------------------------------------------------------------

    // private void setEdgeArrayProperties(String edgeId, Map<String,Object> edgeProps) {
    //     Map<String,Object> arrayProps = new HashMap<>();
    //
    //     edgeProps.forEach((name, value) -> {
    //         if (Param.isArray(name, value))
    //             arrayProps.put(name, value);
    //     });
    //
    //     setEdgeProperties(edgeId, arrayProps);
    // }

    @Override
    public void setEdgeProperties(String edgeId, Map<String,Object> updateProps) {
        if (updateProps == null || updateProps.isEmpty())
            return;

        String sblock = sblock(E, updateProps, false);
        String s = String.format("MATCH ()-[e]-() " +
            "WHERE id(e) = $id %s", sblock);

        Parameters params = Parameters.params(
            ID, asId(edgeId))
            .prefix(E, updateProps);
        this.execute(s, params);
    }

    @Override
    public void setEdgeProperty(String edgeId, String name, Object value) {
        Parameters params = new Parameters();
        params.put(name, value);
        setEdgeProperties(edgeId, params);
    }

    @Override
    public Map<String,Object> getEdgeProperties(String edgeId) {
        String s = String.format("MATCH ()-[e]-() WHERE id(e) = $id RETURN e LIMIT 1");
        Parameters params = Parameters.params(
            ID, asId(edgeId));
        return this.retrieve("e", s, params, true);
    }

    @Override
    public void setEdgesProperties(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps) {
        String s;
        String sblock = sblock(E, edgeProps, false);
        Parameters params;

        if (toIds == null) {
            s = String.format("MATCH (from) -[e:%s]->(to) " +
                "WHERE id(from) = $from " +
                "%s " +
                "RETURN id(from)", edgeType, sblock);
        }
        else {
            s = String.format("MATCH (from) -[e:%s]->(to) " +
                "WHERE id(from) = $from AND id(to) IN $to " +
                "%s " +
                "RETURN id(from)", edgeType, sblock);
        }

        params = Parameters.params(
                FROM, asId(fromId),
                TO, asIds(toIds)
        ).prefix(E, edgeProps);


        this.execute(s, params);
    }

    // ----------------------------------------------------------------------
    // Post processing
    // ----------------------------------------------------------------------

    protected Map<String, Object> postProcess(Map<String, Object> map) {
        return map;
    }

    // ----------------------------------------------------------------------
    // Graph access
    // ----------------------------------------------------------------------

    protected String/*Id*/ create(String s, Map<String,Object> params) {
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

    protected Map<String,Object> retrieve(String alias, String s, Map<String,Object> params) {
        return retrieve(alias, s, params, false);
    }

    protected Map<String,Object> retrieve(String alias, String s, Map<String,Object> params, boolean edge) {
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

    protected String find(String s, Map<String,Object> params) {
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

    protected GraphIterator<String> findAllIter(String s, Map<String,Object> params) {
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

    protected GraphIterator<Map<String,Object>> retrieveAllIter(String alias, String s, Map<String,Object> params, boolean edge) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);

            return new Neo4JResultSet<Map<String,Object>>(result) {
                @Override
                protected Map<String,Object> compose(Record r) {
                    Map<String,Object> nv = edge ? toEdgeMap(alias, r) : toNodeMap(alias, r);
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

    protected GraphIterator<Map<String,Object>> resultIter(String alias, String s, Map<String,Object> params, boolean edge) {
        logStmt(s, params);

        try {
            Result result = session_run(s, params);

            return new Neo4JResultSet<Map<String,Object>>(result) {
                protected Map<String,Object> compose(Record r) {
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

    protected long count(String s, Map<String,Object> params) {
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

    private void logStmt(String s, Map<String,Object> params) {
        logStmt(s, params, null);
    }

    private void logStmt(String s, Map<String,Object> params, Throwable t) {
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
        if (value == null) return "null";
        if (value instanceof String) return String.format("'%s'", value);
        return value.toString();
    }

    // ----------------------------------------------------------------------
    // Low level
    // ----------------------------------------------------------------------

    @Override
    public Query query(String s, Map<String,Object> params) {
        // altready used in Ne4JQuery
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
    public void execute(String s, Map<String,Object> params) {
        logStmt(s, params);

        try {
            s = StringUtils.format(s, params);
            Result result = session_run(s, params);
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private Result session_run(String s, Map<String,Object> params) {
        int c = count.incrementAndGet();
        if (c > graphdb.getMaxStatements()) {
            transaction.commit();
            transaction = session.beginTransaction();
            count.set(0);
        }

        return transaction.run(s, params);
    }

    private long delete(String s, Map<String,Object> params, boolean edge) {
        logStmt(s, params);
        long count = -1, total = 0;

        try {
            if (edge){
                // while (count != 0) {
                    // count = session.writeTransaction(tx ->
                    //     tx.run(s, params)
                    //         .consume()
                    //         .counters()
                    //         .relationshipsDeleted())
                    //     .intValue();

                    count = session_run(s, params).consume().counters().relationshipsDeleted();
                    total += count;
                    // if (count > 0)
                    //     logger.debugft("Deleted %d edges %s", total, params);
                // }
            }
            else {
                // while(count != 0) {
                    // count = session.writeTransaction(tx ->
                    //     tx.run(s, params)
                    //         .consume()
                    //         .counters()
                    //         .nodesDeleted())
                    //     .intValue();

                    count = session_run(s, params).consume().counters().nodesDeleted();
                    total += count;
                    // if (count > 0)
                    //     logger.debugft("Deleted %d nodes %s", total, params);
                // }
            }
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
        return total;
    }

    private static Map<String,Object> toNodeMap(String alias, Record r) {
        if (r == null)
            return Collections.emptyMap();

        Node node = r.get(alias).asNode();
        return toNodeMap(node);

    }

    public static  Map<String,Object> toNodeMap(Node node) {
        Map<String,Object> body = new HashMap<>();

        body.put(GRAPH_ID, toId(node.id()));

        List<String> labels = new ArrayList<>();
        node.labels().forEach(label -> labels.add(label));

        // put_ $id
        // put_ $labels
        // put_ $type     == labels[0]

        body.put(GRAPH_LABELS, labels);

        if (!labels.isEmpty())
            body.put(GRAPH_TYPE, labels.get(0));

        // put_ properties
        body.putAll(node.asMap());

        return body;
    }

    private static Map<String,Object> toEdgeMap(String alias, Record r) {
        Map<String,Object> body = new HashMap<>();

        if (r == null)
            return body;

        Relationship edge = r.get(alias).asRelationship();

        // put properties
        body.putAll(edge.asMap());

        // put $id
        // put $type

        body.put(GRAPH_ID, toId(edge.id()));
        body.put(GRAPH_TYPE, edge.type());

        return body;
    }

    private static Map<String,Object> toResultMap(String alias, Record r) {
        Map<String,Object> body = new HashMap<>();

        if (r == null)
            return body;

        body.putAll(r.asMap());

        Set<String> keys = new HashSet<>(body.keySet());
        for(String k : keys) {
            Object v = body.get(k);
            if (v instanceof Relationship)
                body.put(k, toEdgeMap(k, r));
        }

        return body;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
