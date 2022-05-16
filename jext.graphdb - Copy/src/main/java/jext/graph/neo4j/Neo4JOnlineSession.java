package jext.graph.neo4j;

import jext.graph.Direction;
import jext.graph.GraphDatabase;
import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Param;
import jext.graph.Query;
import jext.graph.schema.GraphSchema;
import jext.graph.schema.NodeSchema;
import jext.graph.schema.PropertySchema;
import jext.logging.Logger;
import jext.util.ArrayUtils;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static jext.graph.NodeId.asId;
import static jext.graph.NodeId.asIds;
import static jext.graph.NodeId.toId;

public class Neo4JOnlineSession implements GraphSession {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private enum WhereType {
        WHERE,
        AND
    }

    private static final String N = "n";
    private static final String E = "e";
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String NONE = "";
    static final int MAX_STATEMENTS = 8*1024;
    static final int MAX_DELETE_NODES = 16*1024;
    private static final String NO_ID = "-1";

    private static final String USES = "uses";
    private static final String TYPE = "type";

    private static final String REF_ID = "refId";

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
    private AtomicInteger count = new AtomicInteger();

<<<<<<< HEAD
=======
    // project support
    private String refId;
    // revision support
    private String model;
    private int rev = -1;
    private GraphSchema graphSchema;

>>>>>>> 87dd7360 (Updated implementation)
    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    Neo4JOnlineSession(Neo4JOnlineDatabase graphdb) {
        this.graphdb = graphdb;
        this.driver = graphdb.getDriver();
<<<<<<< HEAD
=======
        this.graphSchema = graphdb.getGraphSchema();
        this.refId = refId;
        this.model = model;
        this.rev = rev;
>>>>>>> 87dd7360 (Updated implementation)
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

    private Map<String,Object> ckparams(Map<String,Object> params) {
<<<<<<< HEAD
=======
        if (refId == null && rev <= 0)
            return params;

        params = new HashMap<>(params);
        if (refId != null)
            params.put(REF_ID, refId);
        if (rev >= 0)
            params.put(REVISION, rev);
>>>>>>> 87dd7360 (Updated implementation)
        return params;
    }

    @Override
    public Query queryUsing(String queryName,  Map<String,Object> params) {
        String query = graphdb.getQuery(queryName);
        params = ckparams(params);

        if (query.contains(AND_BLOCK) || query.contains(WHERE_BLOCK)) {
            Parameters nparams = Parameters.params(params);
            while (query.contains(AND_BLOCK))
                query = ublock(query, nparams, WhereType.AND);
            while (query.contains(WHERE_BLOCK))
                query = ublock(query, nparams, WhereType.WHERE);
            params = nparams;
        }

        // replace ${name} with 'name' value in 'params'
        String s = StringUtils.format(query, params);

        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public void executeUsing(String queryName, Map<String,Object> params) {
        String query = graphdb.getQuery(queryName);
        params = ckparams(params);

        if (query.contains(AND_BLOCK) || query.contains(WHERE_BLOCK)) {
            Parameters nparams = Parameters.params(params);
            while (query.contains(AND_BLOCK))
                query = ublock(query, nparams, WhereType.AND);
            while (query.contains(WHERE_BLOCK))
                query = ublock(query, nparams, WhereType.WHERE);
            params = nparams;
        }

        String s = StringUtils.format(query, params);

        // logStmt(s, params);
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
    public Query queryUsingFullText(String query,  Map<String,Object> queryParams) {
        queryParams = ckparams(queryParams);

        // indexName
        // query
        // labels ONLY if size > 0
        // refIds ONLY if size > 0
        boolean hasLabels = queryParams.containsKey(LABELS ) && !((Collection<String>)queryParams.get(LABELS )).isEmpty();
        boolean hasRefIds = queryParams.containsKey(REF_IDS) && !((Collection<String>)queryParams.get(REF_IDS)).isEmpty();

        String s = "CALL db.index.fulltext.queryNodes($indexName, $query) YIELD node AS n ";
        if (hasLabels && hasRefIds)
            s += "WHERE labels(n) = $labels AND n.refId IN $refIds ";
        else if (hasLabels)
            s += "WHERE labels(n) = $labels ";
        else if (hasRefIds)
            s += "WHERE n.refId IN $refIds ";

        return new Neo4JQuery(this, N, s, queryParams);
    }

    // ----------------------------------------------------------------------
    // Single node
    // ----------------------------------------------------------------------

    @Override
    public boolean existsNode(String nodeId) {
        if (nodeId == null)
            return false;

        String s = "MATCH (n) WHERE id(n) = $id RETURN count(n)";

        return this.count(s, Parameters.params("id", asId(nodeId))) > 0;
    }

    /**
     * Create a node with the specified properties.
     *
     * 1) revision > 0, check if the node already exists based on the 'unique' name
     *    if the node exists, updates the properties otherwise creates it
     * 2) check if a property is 'revisioned'. If it is 'revisioned', handle it as an
     *    array
     * 3) check if the node is the model's reference node. If so, it adds/update the property
     *    'revisions'.
     *
     * @param nodeType node type
     * @param nodeProps node properties
     * @return nodeId
     */
    @Override
    public String createNode(String nodeType, Map<String, Object> nodeProps) {
        Map<String, Object> revProps = getRevisionedNode(nodeType, nodeProps);
        String nodeId;

        if (revProps.isEmpty())
            nodeId = _createNode(nodeType, nodeProps);
        else
            nodeId = _updateNode(nodeType, nodeProps, revProps);

        // remove versioned nodes
        // nodeProps = ckparams(nodeProps);
        return nodeId;
    }

    private String/**/ _createNode(String nodeType, Map<String, Object> nodeProps) {
        String pblock = pblock(N, nodeProps);
        String sblock = sblock(N, nodeProps, true);

        String s = String.format("CREATE (n:%s %s) %s RETURN id(n)", nodeType, pblock, sblock);

        Parameters params = Parameters.params(nodeProps)
            .add(N, nodeProps);

        return this.create(s, params);
    }

    public String/**/ _updateNode(String nodeType, Map<String, Object> nodeProps, Map<String, Object> updateProps) {
        return null;
    }

    private Map<String, Object> getRevisionedNode(String nodeType, Map<String, Object> nodeProps) {
        // if (rev <= 0)
        //     return Collections.emptyMap();

        NodeSchema nschema = graphSchema.getNodeSchema(nodeType);
        Map<String, Object> nprops = nschema.getUnique(nodeProps);
        return getNode(nodeType, nprops);
    }

    @Override
    public boolean deleteNode(String nodeId) {
        if (nodeId == null) return false;

        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = "MATCH (n) WHERE id(n) = $id DETACH DELETE n";
        long count = this.delete(s, params, false);
        return count > 0;
    }

    @Override
    public Map<String, Object> getNode(String nodeId) {
        if (nodeId == null)
            return Collections.emptyMap();

        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = "MATCH (n) WHERE id(n) = $id RETURN n";
        return this.retrieve(N, s, params);
    }

    @Override
    public Map<String, Object> getNode(String nodeType, Map<String, Object> nodeProps) {
        nodeProps = ckparams(nodeProps);

        GraphIterator<Map<String, Object>> it = queryNodes(nodeType, nodeProps).allValues();
        if (it.hasNext())
            return it.next();
        else
            return Collections.emptyMap();
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
    public long deleteNodes(String nodeType, Map<String, Object> nodeProps) {
        return deleteNodes(nodeType, nodeProps, (count)->{ });
    }

    @Override
    public long deleteNodes(String nodeType, Map<String, Object> nodeProps, Consumer<Long> callable) {
        nodeProps = ckparams(nodeProps);

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

    private long deleteNodes(String nodeType, Map<String, Object> nodeProps, long count) {
        String pblock = pblock(N, nodeProps);
        String wblock = wblock(N, nodeProps, WhereType.WHERE, true);
        String s;

        Assert.verify(count > 0, "deleteNodes: count MUST BE > 0");

        // if (nodeType == null && count <= 0)
        //     s = String.format("MATCH (n %s) %s DETACH DELETE n", pblock, wblock);
        // else if (nodeType == null)
        //     s = String.format("MATCH (n %s) %s WITH n LIMIT %d DETACH DELETE n", pblock, wblock, count);
        // else if (count <= 0)
        //     s = String.format("MATCH (n:%s %s) %s DETACH DELETE n", nodeType, pblock, wblock);
        // else
        //     s = String.format("MATCH (n:%s %s) %s WITH n LIMIT %d DETACH DELETE n", nodeType, pblock, wblock, count);

        if (nodeType == null)
            s = String.format("MATCH (n %s) %s WITH n LIMIT %d DETACH DELETE n", pblock, wblock, count);
        else
            s = String.format("MATCH (n:%s %s) %s WITH n LIMIT %d DETACH DELETE n", nodeType, pblock, wblock, count);

        Parameters nparams = Parameters.params();
        nparams.add(N, nodeProps);

        return this.delete(s, nparams, false);
    }

    // ----------------------------------------------------------------------

    @Override
    public List<Map<String, Object>> getNodes(Collection<String> nodeIds) {
        Parameters params = Parameters.params(
            "id", asIds(nodeIds));
        String s = "MATCH (n) WHERE id(n) IN $id RETURN n";

        return this.retrieveAllIter(N, s, params, false).toList();
    }

    // ----------------------------------------------------------------------

    @Override
    public void setNodeProperties(String nodeId, Map<String, Object> nodeProps) {
        if (nodeProps == null || nodeProps.isEmpty())
            return;

        String sblock = sblock(N, nodeProps);

        Parameters params = Parameters.params(
            "id", asId(nodeId))
            .add(N, nodeProps);

        String s = String.format("MATCH (n) WHERE id(n) = $id %s RETURN n", sblock);

        this.execute(s, params);
    }

    @Override
    public void setNodesProperties(Collection<String> nodeIds, Map<String, Object> nodeProps) {
        if (nodeProps == null || nodeProps.isEmpty() || nodeIds == null || nodeIds.isEmpty())
            return;

        String sblock = sblock(N, nodeProps);

        Parameters params = Parameters.params(
            "ids", asIds(nodeIds))
            .add(N, nodeProps);

        String s = String.format("MATCH (n) WHERE id(n) IN $ids %s RETURN n", sblock);

        this.execute(s, params);
    }

    @Override
    public void setNodeProperty(String nodeId, String name, Object value) {
        setNodeProperties(nodeId, MapUtils.asMap(name, value));
    }

    @Override
    public void setNodeProperty(String nodeId, String name, int index, Object value) {
        setNodeProperty(nodeId, Param.at(name, index), value);
    }

    // ----------------------------------------------------------------------
    // Delete properties
    // ----------------------------------------------------------------------

    @Override
    public void deleteNodeProperty(String nodeId, String name) {
        deleteNodesProperties(Collections.singletonList(nodeId), Collections.singleton(name));
    }

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
    public Query queryNodes(String nodeType, Map<String, Object> nodeProps) {
        nodeProps = ckparams(nodeProps);

        String pblock = pblock(N, nodeProps);
        String wblock = wblock(N, nodeProps, WhereType.WHERE, true);
        String s;

        if (StringUtils.isEmpty(nodeType))
            s = String.format("MATCH (n %s) %s", pblock, wblock);
        else
            s = String.format("MATCH (n:%s %s) %s", nodeType, pblock, wblock);

        Parameters params = Parameters.params(nodeProps).add(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public Optional<String/*nodeId*/> findNode(String nodeType, Map<String, Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps);
        return Optional.ofNullable(query.id());
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

        nodeProps = ckparams(nodeProps);

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
        String wblock = wblock(E, edgeProps, WhereType.AND);
        String s;

        if (recursive) {
            s = String.format("MATCH shortestPath( (node) %s (n:%s %s) ) WHERE id(node) in $id %s",
                eblock, nodeType, pblock, wblock);
        }
        else {
            s = String.format("MATCH (node) %s (n: %s %s) WHERE id(node) in $id %s",
                eblock, nodeType, pblock, wblock);
        }

        Parameters params = Parameters.params(
            "id", asIds(fromIds))
            .add(N, nodeProps)
            .add(E, edgeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    Query queryAdjacentNodesStep(Collection<String> fromIds, String edgeType, Direction direction, Map<String, Object> edgeProps) {
        String eblock = eblock(E, edgeType, direction, false, edgeProps);
        String wblock = wblock(E, edgeProps, WhereType.AND);
        String s = String.format("MATCH (s) %s (n) WHERE id(s) in $id %s",
            eblock, wblock);

        Parameters params = Parameters.params(
            "id", asIds(fromIds))
            .add(E, edgeProps);

        return new Neo4JQuery(this, N, s, params).distinct();
    }

    Query selectNodes(Collection<String> ids, String nodeType,  Map<String, Object> nodeProps) {
        String pblock = pblock(N, nodeProps);
        String s;

        if (nodeType == null)
            s = String.format("MATCH (n %s) WHERE id(n) in $id", pblock);
        else
            s = String.format("MATCH (n:%s %s) WHERE id(n) in $id", nodeType, pblock);

        Parameters params = Parameters.params(
            "id", asIds(ids))
            .add(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    // ----------------------------------------------------------------------
    // All Edges
    // ----------------------------------------------------------------------

    @Override
    public Query queryEdges(
        String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps) {

        fromProps = ckparams(fromProps);
        toProps   = ckparams(toProps);

        String fblock = pblock(FROM, fromProps);
        String eblock = eblock(E, edgeType, Direction.Output, false, Collections.emptyMap());
        String tblock = pblock(TO, toProps);

        String wfblock = wblock(FROM, fromProps, WhereType.WHERE);
        String wtblock = wblock(TO,   toProps,   wfblock.isEmpty() ? WhereType.WHERE : WhereType.AND);
        String weblock = wblock(E,            edgeProps, wtblock.isEmpty() ? WhereType.WHERE : WhereType.AND);

        String wblock = String.format("%s%s%s", wfblock, wtblock, weblock);

        Parameters allProps = new Parameters();
        allProps.add(FROM,fromProps);
        allProps.add(TO,  toProps);
        allProps.add(E,   edgeProps);

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
    public Query queryEdges(String edgeType, String fromId, Collection<String> toIds,
                            Map<String, Object> edgeProps) {
        return queryEdges(edgeType, Collections.singletonList(fromId), toIds, edgeProps);
    }

    @Override
    public Query queryEdges(String edgeType, Collection<String> fromIds, Collection<String> toIds,
                            Map<String, Object> edgeProps) {

        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
        String s;
        Parameters params;

        if (fromIds == toIds) {
            s = String.format(
                "MATCH (from) %s (to) WHERE id(from) IN $from AND id(to) IN $from " +
                    "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                eblock
            );

            params = Parameters.params()
                .add(FROM, asIds(fromIds));
        }
        else {
            s = String.format(
                "MATCH (from) %s (to) WHERE id(from) IN $from AND id(to) IN $to " +
                    "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                eblock
            );

            params = Parameters.params()
                .add(FROM, asIds(fromIds))
                .add(TO, asIds(toIds));
        }

        // String s = String.format(
        //     "MATCH (from) %s (to) WHERE id(from) IN $from AND id(to) IN $to " +
        //     "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
        //     eblock
        // );
        //
        // Parameters allProps = new Parameters();
        // allProps.put(FROM, asIds(fromIds));
        // allProps.put(TO, asIds(toIds));

        return new Neo4JQuery(this, N, s, params);
    }

    // @Override
    // public Query queryEdges(String edgeType, Collection<String> ids,
    //                         Map<String, Object> edgeProps) {
    //
    //     String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
    //
    //     String s = String.format(
    //         "MATCH (from) %s (to) WHERE id(from) IN $ids AND id(to) IN $ids " +
    //             "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
    //         eblock
    //     );
    //
    //     Parameters allProps = new Parameters();
    //     allProps.put("ids", asIds(ids));
    //
    //     return new Neo4JQuery(this, N, s, allProps);
    // }

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

    @Override
    public String findEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps) {
        if (fromId == null || toId == null)
            return null;
        if (NO_ID.equals(fromId) || NO_ID.equals(toId))
            return null;
        if (fromId.equals(toId))
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
    public String createEdge(String edgeType, String fromId, String toId, Map<String, Object> edgeProps) {
        if (USES.equals(edgeType) && edgeProps.containsKey(TYPE)) {
            Map<String, Object> findProps = Parameters.params(TYPE, edgeProps.get(TYPE));
            return createEdge(edgeType, fromId, toId, findProps, edgeProps);
        }
        else {
            return createEdge(edgeType, fromId, toId, Collections.emptyMap(), edgeProps);
        }
    }

    @Override
    public String createEdge(String edgeType, String fromId, String toId,
                             Map<String, Object> findProps, Map<String, Object> updateProps)
    {
        if (fromId == null || toId == null)
            return null;
        if (NO_ID.equals(fromId) || NO_ID.equals(toId))
            return null;
        if (fromId.equals(toId))
            return null;

        // search if the edge already exists
        String pblock = pblock(E, findProps);
        String wblock = wblock(E, findProps, WhereType.AND, true);

        String s = String.format("MATCH (from) -[e:%s %s]-> (to) WHERE id(from) = $from AND id(to)=$to %s",
            edgeType, pblock, wblock);

        Parameters params = Parameters.params(
            FROM, asId(fromId),
            TO, asId(toId)
        ).add(E, findProps);

        // create the edge
        String edgeId = this.query(s, params).id(E);
        if (edgeId == null) {
            s = String.format("MATCH (from),(to) WHERE id(from) = $from AND id(to)=$to " +
                "CREATE (from) -[e:%s %s]-> (to) " +
                "RETURN id(e)", edgeType, pblock);

            edgeId = this.create(s, params);
        }

        setEdgeProperties(edgeId, updateProps);

        return edgeId;
    }

    @Override
    public void createEdges(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps) {
        if (USES.equals(edgeType) && edgeProps.containsKey(TYPE)) {
            Map<String, Object> findProps = Parameters.params(TYPE, edgeProps.get(TYPE));
            createEdges(edgeType, fromId, toIds, findProps, edgeProps);
        }
        else {
            createEdges(edgeType, fromId, toIds, Collections.emptyMap(), edgeProps);
        }
    }

    @Override
    public void createEdges(String edgeType, String fromId, Collection<String> toIds,
                            Map<String, Object> findProps, Map<String, Object> updateProps) {
        Parameters pparams = Parameters.params(findProps);
        Parameters wparams = Parameters.params(findProps);
        Parameters sparams = Parameters.params(updateProps);

        String s;
        String pblock = pblock(E, pparams);
        String wblock = wblock(E, wparams, WhereType.AND, true);
        String sblock = sblock(E, sparams);

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
            ).add(edgeProps).add(E, edgeProps);

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
                .add(E, edgeProps)
                .add(E, pparams)
                .add(E, sparams)
                .add(E, wparams);

            this.execute(s, params);
        }

        //
        // 4) set the properties for all edges
        //
        {
            pblock = pblock(E, findProps);
            wblock = wblock(E, findProps, WhereType.AND, true);
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
                .add(E, edgeProps)
                .add(E, pparams)
                .add(E, sparams)
                .add(E, wparams);

            this.execute(s, params);
        }
    }

    @Override
    public void setEdgeProperties(String edgeType, String fromId, String toId,
                                  Map<String, Object> findProps,
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
        String wblock = wblock(E, wparams, WhereType.AND, true);
        String sblock = sblock(E, sparams);
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
            .add(E, pparams).add(E, wparams).add(E, sparams)
            .add(pparams).add(wparams).add(sparams);

        this.execute(s, params);
    }

    // ----------------------------------------------------------------------

    @Override
    public Query queryPath(String edgeType, String fromId, String toId, Direction direction, boolean recursive,
                    Map<String, Object> edgeProps)
    {
        String eblock = eblock(E, edgeType, direction, recursive, null);
        String wblock = wblock(E, edgeProps, WhereType.AND);
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
            .add(E, edgeProps);

        return new Neo4JQuery(this, E, s, params).edge();
    }

    @Override
    public long deleteEdges(String edgeType,  // can be null
                     String fromType, Map<String, Object> fromProps,
                     String toType, Map<String, Object> toProps,
                     Map<String,Object> edgeProps,
                     Consumer<Long> callback) {
        String s;
        int maxdelete = graphdb.getMaxDelete();

        fromProps = ckparams(fromProps);
        toProps   = ckparams(toProps);

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
            .add(FROM, fromProps)
            .add(E, edgeProps)
            .add(TO, toProps);

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
                            Consumer<Long> callback) {
        String s;
        String pblock = pblock(E, edgeProps);
        String wblock = wblock(E, edgeProps, WhereType.AND, true);
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
            ).add(E, edgeProps);

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

    // private void setEdgeArrayProperties(String edgeId, Map<String, Object> edgeProps) {
    //     Map<String, Object> arrayProps = new HashMap<>();
    //
    //     edgeProps.forEach((name, value) -> {
    //         if (Param.isArray(name, value))
    //             arrayProps.put(name, value);
    //     });
    //
    //     setEdgeProperties(edgeId, arrayProps);
    // }

    @Override
    public void setEdgeProperties(String edgeId, Map<String, Object> updateProps) {
        if (updateProps == null || updateProps.isEmpty())
            return;

        String sblock = sblock(E, updateProps);
        String s = String.format("MATCH ()-[e]-() " +
            "WHERE id(e) = $id %s", sblock);

        Parameters params = Parameters.params(
            "id", asId(edgeId))
            .add(E, updateProps);
        this.execute(s, params);
    }

    @Override
    public void setEdgeProperty(String nodeId, String name, int index, Object value) {
        setEdgeProperty(nodeId, Param.at(name, index), value);
    }

    @Override
    public void setEdgeProperty(String edgeId, String name, Object value) {
        Parameters params = new Parameters();
        params.put(name, value);
        setEdgeProperties(edgeId, params);
    }

    @Override
    public Map<String, Object> getEdgeProperties(String edgeId) {
        String s = String.format("MATCH ()-[e]-() WHERE id(e) = $id RETURN e LIMIT 1");
        Parameters params = Parameters.params(
            "id", asId(edgeId));
        return this.retrieve("e", s, params, true);
    }

    @Override
    public void setEdgesProperties(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps) {
        String s;
        String sblock = sblock(E, edgeProps);
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
        ).add(E, edgeProps);


        this.execute(s, params);
    }

    // ----------------------------------------------------------------------
    // Graph access
    // ----------------------------------------------------------------------

    protected String/*Id*/ create(String s, Map<String, Object> params) {
        // logStmt(s, params);
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
        // logStmt(s, params);
        try {
            Result result = session_run(s, params);
            if (!result.hasNext())
                return Collections.emptyMap();

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
        // logStmt(s, params);
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
        // logStmt(s, params);
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
        // logStmt(s, params);
        try {
            Result result = session_run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                protected Map<String, Object> compose(Record r) {
                    return edge ? toEdgeMap(alias, r) : toNodeMap(alias, r);
                }
            };
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected GraphIterator<Map<String, Object>> resultIter(String alias, String s, Map<String, Object> params, boolean edge) {
        // logStmt(s, params);
        try {
            Result result = session_run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                protected Map<String, Object> compose(Record r) {
                    return toResultMap(alias, r);
                }
            };
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    protected long count(String s, Map<String, Object> params) {
        // logStmt(s, params);
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

    private void logStmt(String s, Map<String, Object> params) {
        logStmt(s, params, null);
    }

    private void logStmt(String s, Map<String, Object> params, Throwable t) {
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
    public void execute(String s, Map<String, Object> params) {
        // logStmt(s, params);
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

    private Result session_run(String s, Map<String, Object> params) {
        int c = count.incrementAndGet();
        if (c > graphdb.getMaxStatements()) {
            transaction.commit();
            transaction = session.beginTransaction();
            count.set(0);
        }
        logStmt(s, params);
        return transaction.run(s, params);
    }

    private long delete(String s, Map<String, Object> params, boolean edge) {
        // logStmt(s, params);
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

    private static Map<String, Object> toNodeMap(String alias, Record r) {
        if (r == null)
            return Collections.emptyMap();

        Node node = r.get(alias).asNode();
        return toNodeMap(node);

    }

    public static  Map<String, Object> toNodeMap(Node node) {
        Map<String, Object> body = new HashMap<>();

        body.put(GRAPH_ID, toId(node.id()));

        List<String> labels = new ArrayList<>();
        node.labels().forEach(label -> labels.add(label));

        // put_ $id
        // put_ $labels
        // put_ $type     == labels[0]

        body.put(GRAPH_LABELS, labels);

        if (labels.size() > 0)
            body.put(GRAPH_TYPE, labels.get(0));

        // put_ properties
        body.putAll(node.asMap());

        return body;
    }

    private static Map<String, Object> toEdgeMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

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

    private static Map<String, Object> toResultMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

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
    // Implementation
    // ----------------------------------------------------------------------
    // cypher template:
    //
    //      MATCH (n:label {slot: $param, ...})
    //      WHERE n.slot        OP $param
    //         OR n.slot[index] OP $param
    //         OR sslot(n),     OP $param
    //      SET   n.slot = $param,
    //            n.slot = function(n.slot, index, $param),
    //            n.slot = function(n.slot, $param)
    //
    //  There are some 'special' (s)slots:
    //
    //      n.$label     ==  labels(n)[0]
    //      n.$degree    ==  apoc.node.degree(n)
    //      n.$outdegree ==  apoc.node.degree(n, '>')
    //      n.$indegree  ==  apoc.node.degree(n, '<')
    //  .

    private static String label(String l) {
        if (l == null || l.isEmpty())
            return NONE;
        else
            return ":" + l;
    }

    /**
     * Create the block:
     *
     *      { param: $[prefix]param, ... }
     *
     * Note: this block has a different meaning if used ina MATCH or CREATE clause:
     *   - in MATCH clause is a WHERE clause
     *   - in CREATE clause is a SET clause
     *
     * However, we can limit the 'pblock' only to 'properties with simple values'
     */
    private static String pblock(String prefix, Map<String, Object> params) {

        if (params == null || params.size() == 0)
            return NONE;

        StringBuilder sb = new StringBuilder();
        for(String param : params.keySet()) {
            Object value = params.get(param);

            if (Param.isSpecialOrArray(param, value))
                continue;

            if (sb.length() > 1)
                sb.append(",");

            // { ... param: $nparam
            sb.append(param).append(":$").append(prefix).append(param);
        }

        // check if sb is empty
        if (sb.length() == 0)
            return NONE;
        else
            // add {...}
            return sb.insert(0, "{").append("}").toString();
    }

    /**
         * Create the blocks
         *
         *      WHERE n.param = $param AND ...
         *
         * or
         *
         *      AND  n.param = $param AND ...
         *
         * @param params parameters
         * @param where if to include the WHERE keyword or AND
         */
    private static String wblock(String alias, Map<String, Object> params, WhereType where, boolean pblock) {
        if (params == null || params.isEmpty())
            return NONE;

        String s;
        StringBuilder sb = new StringBuilder();
        for (String param : new HashSet<>(params.keySet())) {
            Object value = params.get(param);

            // check if already used in 'pblock'
            if (pblock && !Param.isSpecialOrArray(param, value))
                continue;

            // append "... AND "
            if (sb.length() > 0) sb.append(" AND ");

            // strip 'name[...]' -> 'name'
            String pname = Param.pnameOf(param);
            params.put(pname, value);

            // [param, null] -> "EXISTS(n.param)"
            if (Param.isExists(value)) {
                s = String.format("EXISTS(%s.%s)", alias, param);
            }

            // ['revision', rev] -> "n.inRevision[rev]"
            else if (Param.isRevision(param)) {
                s = revisionCondition(alias, param, value);
            }

            // ['$degree', deg]
            // ['$degree[edge]', deg]
            else if (Param.isDegree(param)) {
                s = degreeCondition(alias, param, value);
            }

            // ['$label', val] -> ...
            else if (Param.isSpecial(param)) {
                s = specialCondition(alias, param, value);
            }

            // [param, value: a collection] -> "n.param IN $param"
            else if (Param.isCollection(value)) {
                s = String.format("%1$s.%2$s IN $%1$s%3$s", alias, param, pname);
            }

            // [param, value]        -> 'name = $param'
            // [param[index], value] -> 'name[index] = $paramIndex'
            else {
                s = String.format("%1$s.%2$s = $%1$s%3$s", alias, param, pname);
            }

            sb.append(s);
        }

        if (sb.length() == 0)
            return NONE;

        switch (where) {
            // case NONE: return sb.toString();
            case AND:   return sb.insert(0, " AND "  ).toString();
            case WHERE: return sb.insert(0, " WHERE ").toString();
            default: return sb.toString();
        }
    }
    private static String wblock(String alias, Map<String, Object> params, WhereType where) {
        return wblock(alias, params, where, false);
    }

    private static String specialCondition(String alias, String param, Object value) {
        String s;
        String pname = Param.pnameOf(param);

        // ["$label", l] -> labels(n)[0] = $_label
        if (param.equals("$label")) {
            s = String.format("labels(%1$s)[0] = $%1$s%2$s", alias, pname);
        }

        else {
            logger.errorf("Unknown special field '%s'", param);
            s = "true";
        }

        return s;
    }

    private static String revisionCondition(String alias, String param, Object value) {
    /*
        [revision, 0 | [0,1,...]] ->
            n.inRevision[0]
               (n.inRevision[0] OR n.inRevision[1] ... )
     */
        // revs == null -> true
        if (value == null) {
            logger.errorf("Revision condition with null value");
            return "true";
        }

        // rev:integer|long -> inRevision[rev]
        if (value instanceof Number) {
            int rev = ((Number) value).intValue();
            return String.format("%s.inRevision[%d]", alias, rev);
        }

        // convert collection in int[]
        int[] revs ;
        if (value instanceof Collection)
            revs = ArrayUtils.asIntArray(value);
        else
            revs = (int[]) value;
        // end

        // revs == []
        if (revs.length == 0) {
            logger.errorf("Revision condition with empty array []");
            return "true";
        }

        // revs == [1]
        if (revs.length == 1)
            return String.format("%s.inRevision[%d]", alias, revs[0]);

        // revs = [1,2,...]
        StringBuilder sb = new StringBuilder("(");
        sb.append(String.format("%s.inRevision[%d]", alias, revs[0]));
        for (int i=1; i<revs.length; ++i) {
            sb.append(" OR ")
              .append(String.format("%s.inRevision[%d]", alias, revs[i]));
        }
        sb.append(")");

        return sb.toString();
    }

    private static String degreeCondition(String alias, String param, Object value) {
        String s = "true";
        String edge = Param.keyOf(param);
        String pname = Param.pnameOf(param);
        String op = Param.opOf(param);

        // ["$degree", d] -> apoc.node.degree(n) = $_degree
        // ["$degree[e]", d]
        if (param.startsWith("$degree") && edge.isEmpty()) {
            s = String.format("apoc.node.degree(%1$s) = $%1$s%2$s", alias, pname);
        }

        else if (param.startsWith("$degree[")) {
            s = String.format("apoc.node.degree(%1$s,%3$s) = $%1$s%2$s", alias, pname, edge);
        }

        // ["$outdegree", d] -> apoc.node.degree(n,'>') = $_outdegree
        else if (param.startsWith("$outdegree")) {
            s = String.format("apoc.node.degree(%1$s, '%3$s>') = $%1$s%2$s", alias, pname, edge);
        }

        // ["$indegree", d] -> apoc.node.degree(n,'<') = $_indegree
        else if (param.startsWith("$indegree")) {
            s = String.format("apoc.node.degree(%1$s, '%3$s<') = $%1$s%2$s", alias, pname, edge);
        }

        return s;
    }

    /**
     * Create the block
     *
     *      SET n.param = $nparam, ...
     *
     * Note: 'pblock' specify if some parameters are already used in 'pblock'
     */
    private static String sblock(String alias, Map<String, Object> params, boolean pblock) {
        if (params == null || params.size() == 0)
            return NONE;

        StringBuilder sb = new StringBuilder();
        for(String param : new HashSet<>(params.keySet())) {
            Object value = params.get(param);

            // check if already used in 'pblock'
            if (pblock && !Param.isSpecialOrArray(param, value))
                continue;

            if (sb.length() > 0) sb.append(",");

            // [revision, rev] -> inRevision[rev] = true -> inRevision = [...true]
            if (Param.isRevision(param)) {
                String index = params.get(param).toString();
                sb.append(String.format("%1$s.inRevision = apocx.coll.arraySet(%1$s.inRevision, $%1$s%3$s, true)",
                    alias, index, param));
            }
            // name[!] -> appendDistinct(n.name, $pname)
            else if (Param.isAppendDistinct(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);

                sb.append(String.format("%1$s.%2$s = apocx.coll.appendDistinct(%1$s.%2$s, $%1$s%3$s)",
                    alias, name, pname));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[+] -> append(n.name, $pname)
            else if (Param.isAppend(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);

                sb.append(String.format("%1$s.%2$s = apocx.coll.append(%1$s.%2$s, $%1$s%3$s)",
                    alias, name, pname));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[index,!] -> appendDistinct2(n.name, index, $pname)
            else if (Param.isAppendDistinct2(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index = Param.indexOf(param, 1);

                sb.append(String.format("%1$s.%2$s = apocx.coll.appendDistinct2(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[index,+] -> append2(n.name, index, $pname)
            else if (Param.isAppend2(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index = Param.indexOf(param, 1);

                sb.append(String.format("%1$s.%2$s = apocx.coll.append2(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[idx1,idx2] -> array2Set(n.name, idx1, idx2, $pname)
            else if (param.contains("[") && param.contains(",")) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index1 = Param.indexOf(param, 1);
                int index2 = Param.indexOf(param, 2);

                sb.append(String.format("%1$s.%2$s = apocx.coll.array2Set(%1$s.%2$s, %4$d, %5$d, $%1$s%3$s)",
                    alias, name, pname, index1, index2));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[index] -> arraySet(n.name, index, $pname)
            else if (param.contains("[")) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index = Param.indexOf(param, 0);

                sb.append(String.format("%1$s.%2$s = apocx.coll.arraySet(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name -> name = $pname
            else {
                sb.append(String.format("%1$s.%2$s = $%1$s%2$s", alias, param));
            }
        }

        if (sb.length() == 0)
            return NONE;
        else
            return sb.insert(0, "SET ").toString();
    }
    private static String sblock(String alias, Map<String, Object> params) {
        return sblock(alias, params, false);
    }

    /**
     * Create the edge syntax block
     *
     *       -[e:etype {...}]-         Any
     *       -[e:etype {...}]->        Output
     *      <-[e:etype {...}]-         Input
     *
     */
    private static String eblock(String alias, String edgeType, Direction direction, boolean recursive,
                                 Map<String, Object> edgeProps) {
        String eblock;

        switch (direction) {
            case Input:
                eblock = "<-[%s%s%s %s]-";
                break;
            case Output:
                eblock = "-[%s%s%s %s]->";
                break;
            default:
                eblock = "-[%s%s%s %s]-";
                break;
        }

        String pblock = pblock(alias, edgeProps);
        String etype = edgeType != null ? ":" + edgeType : NONE;

        return String.format(eblock,
            alias,
            etype,
            recursive ? "*" : NONE,
            pblock);
    }

    /**
     * Create 'n.att1, n.att2, ...' block
     */
    private static String ablock(String alias, Collection<String> attributes) {
        if (attributes.isEmpty())
            return NONE;

        Iterator<String> it = attributes.iterator();
        StringBuilder sb = new StringBuilder()
            .append(alias).append(".").append(it.next());

        while (it.hasNext()) {
            sb.append(",").append(alias).append(".").append(it.next());
        }
        return sb.toString();
    }

    // ----------------------------------------------------------------------
    // ${and:[alias]:[name]}
    // ${where:[alias]:[name]}

    private static final String AND_BLOCK = "${and:";
    private static final String WHERE_BLOCK = "${where:";
    private static final String END_BLOCK = "}";

    /**
     * Create 'WHERE ...' or 'AND ...'
     */
    private static String ublock(String stmt, Parameters params, WhereType utype) {
        String prefix = utype == WhereType.AND ? AND_BLOCK : WHERE_BLOCK;
        int bgn = stmt.indexOf(prefix) ;
        int end = stmt.indexOf(END_BLOCK, bgn);
        String name = stmt.substring(bgn+prefix.length(), end);
        String alias;

        // name | alias:name
        int sep = name.indexOf(':');
        if (sep == -1)
            alias = N;
        else {
            alias = name.substring(0, sep);
            name = name.substring(sep+1);
        }

        //
        // params['name'] is ANOTHER map
        //
        Map<String, Object> aparams = (Map<String, Object>) params.getOrDefault(name, Collections.emptyMap());
        params.remove(name);

        String wblock = wblock(alias, aparams, utype);

        // replace '${and...}' or '${where...}' with 'wblock'
        stmt = stmt.substring(0, bgn) + wblock + stmt.substring(end+END_BLOCK.length());
        params.add(alias, aparams);

        return stmt;
    }

    // ----------------------------------------------------------------------
<<<<<<< HEAD
=======
    // Revision support
    // ----------------------------------------------------------------------

    // @Override
    // public void deleteRevisionMetadata(String model) {
    //     deleteNodes(REVISION, Parameters.params(
    //         REF_ID, refId,
    //         MODEL, model
    //     ));
    // }
    //
    // @Override
    // public void createRevisionMetadata(String model, Map<String, Set<String>> metadata) {
    //     createNode(REVISION, Parameters.params(metadata).add(
    //         REF_ID, refId,
    //         MODEL, model,
    //         REVISIONS, new int[]{1,2,3,4,5}
    //     ));
    // }
    //
    // @Override
    // public Map<String, Set<String>> getRevisionMetadata() {
    //     return getRevisionMetadata(model);
    // }
    //
    // @Override
    // public Map<String, Set<String>> getRevisionMetadata(String model) {
    //     Map<String, Set<String>> metadata = new HashMap<>();
    //     Map<String, Object> nv = queryNodes(REVISION, Parameters.params(
    //         REF_ID, refId,
    //         MODEL, model
    //     )).values();
    //
    //     if (nv == null)
    //         return Collections.emptyMap();
    //
    //     for(String type : nv.keySet()) {
    //         if (type.startsWith("$"))
    //             continue;
    //         if (REF_ID.equals(type))
    //             continue;
    //         if (MODEL.equals(type))
    //             continue;
    //         if (REVISIONS.equals(type))
    //             continue;
    //
    //         List<String> values = (List<String>) nv.get(type);
    //         Set<String> properties = new HashSet<>(values);
    //         metadata.put(type, properties);
    //     }
    //     return metadata;
    // }

    // ----------------------------------------------------------------------
>>>>>>> 87dd7360 (Updated implementation)
    // End
    // ----------------------------------------------------------------------
}
