package jext.graph.neo4j;

import jext.graph.*;
import jext.logging.Logger;
import jext.util.Parameters;
import jext.util.StringUtils;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.util.*;
import java.util.stream.Collectors;

public class Neo4JOnlineSession implements GraphSession {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String FULLNAME = "fullname";
    private static final String NAMESPACE = "namespace";
    private static final String N = "n";
    private static final String E = "e";
    private static final int MAX_IDS = 1024;
    private static final int MAX_DELETE_NODES = 16*1024;
    private static final int MAX_DELETE_EDGES = 128*1024;

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private Logger logger = Logger.getLogger(Neo4JOnlineSession.class);

    private Neo4JOnlineDatabase graphdb;
    private Driver driver;
    private Session session;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    Neo4JOnlineSession(Neo4JOnlineDatabase graphdb) {
        this.graphdb = graphdb;
        this.driver = graphdb.getdriver();
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
        // close();
        session = driver.session();
        return this;
    }

    @Override
    public boolean isConnected() {
        return session != null && session.isOpen();
    }

    @Override
    public void close() {
        if (session != null) {
            session.close();
            session = null;
        }
    }

    // ----------------------------------------------------------------------
    // Transactions
    // ----------------------------------------------------------------------

    @Override
    public void beginTransaction() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    // ----------------------------------------------------------------------
    // Named Queries
    // ----------------------------------------------------------------------

    @Override
    public Query queryUsing(String queryName,  Map<String,Object> queryParams) {
        String query = graphdb.getQuery(queryName);

        String stmt = StringUtils.format(query, queryParams);

        return new Neo4JQuery(this, N, stmt, queryParams);
    }

    // ----------------------------------------------------------------------
    // Nodes
    // ----------------------------------------------------------------------

    @Override
    public boolean existsNode(String nodeId) {
        if (nodeId == null)
            return false;
        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = String.format("MATCH (n) WHERE id(n) = $id RETURN count(n)");
        return this.count(s, params) > 0;
    }

    @Override
    public String createNode(String nodeType, Map<String, Object> nodeProps) {
        String pblock = pblock(nodeProps);
        String s = String.format("CREATE (n:%s %s) RETURN id(n)", nodeType, pblock);
        return this.create(s, nodeProps);
    }

    @Override
    public boolean deleteNode(String nodeId) {
        if (nodeId == null)
            return false;

        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = String.format("MATCH (n) WHERE id(n) = $id DETACH DELETE n");
        this.delete(s, params, false);
        return true;
    }

    @Override
    public void deleteNodes(Collection<String> nodeIds) {
        if (nodeIds == null || nodeIds.isEmpty())
            return;

        if (nodeIds.size() > MAX_IDS)
            nodeIds = new ArrayList<>(nodeIds);

        while(nodeIds.size() > MAX_IDS) {
            int n = nodeIds.size();
            deleteSomeNodes(((List<String>)nodeIds).subList(0, MAX_IDS));
            nodeIds = ((List<String>)nodeIds).subList(MAX_IDS, n);
        }
        deleteSomeNodes(nodeIds);
    }

    @Override
    public void deleteNodes(String nodeType, Map<String, Object> nodeProps) {
        String pblock = pblock(nodeProps);
        String s = String.format("MATCH (n:%s %s) WITH n LIMIT %d DETACH DELETE n", nodeType, pblock, MAX_DELETE_NODES);

        this.delete(s, nodeProps, false);
    }

    private void deleteSomeNodes(Collection<String> nodeIds) {
        Parameters params = Parameters.params(
            "ids", asId(nodeIds));
        String s = String.format("MATCH (n) WHERE id(n) IN $ids DETACH DELETE n");
        this.execute(s, params);
    }

    @Override
    public Map<String, Object> getNodeValues(String nodeId) {
        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = String.format("MATCH (n) WHERE id(n) = $id RETURN n");

        return this.retrieve(N, s, params);
    }

    @Override
    public void setNodeProperties(String nodeId, Map<String, Object> updateProps) {
        if (updateProps == null || updateProps.size() == 0)
            return;

        String sblock = sblock(N, updateProps);
        Parameters params = Parameters.params(
            "id", asId(nodeId))
            .add(updateProps);
        String s = String.format("MATCH (n) WHERE id(n) = $id %s", sblock);
        this.execute(s, params);
    }

    @Override
    public void setNodeProperty(String nodeId, String name, Object value) {
        Map<String, Object> params = new HashMap<>();
        params.put(name, value);
        setNodeProperties(nodeId, params);
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
        String pblock = pblock(nodeProps);
        String s;

        if (nodeType == null)
            s = String.format("MATCH (n %s)", pblock);
        else
            s = String.format("MATCH (n:%s %s)", nodeType, pblock);

        return new Neo4JQuery(this, N, s, nodeProps);
    }

    @Override
    public String/*nodeId*/ findNode(String nodeType, Map<String, Object> nodeProps) {
        Query query = queryNodes(nodeType, nodeProps);
        return query.id();
    }

    // ----------------------------------------------------------------------
    // Adjacent nodes
    // ----------------------------------------------------------------------

    @Override
    public Query queryAdjacentNodes(
        String fromId, String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps) {

        String eblock = eblock(E, edgeType, direction, recursive, edgeProps);
        String pblock  = pblock(nodeProps);
        String s;

        if (recursive) {
            s = String.format("MATCH shortestPath( (from) %s (n:%s %s) ) WHERE id(from) = $id ",
                eblock, nodeType, pblock);
        }
        else {
            s = String.format("MATCH (from) %s (n: %s %s) WHERE id(from) = $id ",
                eblock, nodeType, pblock);
        }

        Parameters params = Parameters.params(
            "id", asId(fromId))
            .add(nodeProps)
            .add(edgeProps);

        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public Query queryAdjacentNodesAlgorithm(
            String fromId, String edgeType, Direction direction, boolean recursive,
            String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps
    ){
        String eblock = eblock(E, edgeType, direction, false, edgeProps); //recursive has to be false for algorithm
        String pblock = pblock(nodeProps);

        HashMap<String, Object> allParams = new HashMap<>();
        allParams.putAll(nodeProps);
        allParams.putAll(edgeProps);
        String tblock = tblock(allParams);
        String s;

        if(recursive){
            s = String.format("Match (from) where id(from) = $id " +
                    "CALL algo.shortestPaths.stream(from, null,{nodeQuery:'Match(n:%s %s) return id(n) as id',relationshipQuery: 'Match (n1) %s (n2) RETURN id(n1) as source, id(n2) as target',graph:'cypher',params:%s}) " +
                    "YIELD nodeId as n,distance " +
                    "Where algo.isFinite(distance) = true return distinct n",nodeType, pblock, eblock,tblock);
        }
        else{
            return queryAdjacentNodes(fromId, edgeType, direction, false, nodeType, nodeProps, edgeProps);
        }

        Parameters params = Parameters.params(
                "id", asId(fromId));

        return new Neo4JQuery(this, N, s, params);
    }
    // ----------------------------------------------------------------------
    // Node Degree
    // ----------------------------------------------------------------------

    @Override
    public Query queryNodesWithDegree(
        String nodeType, String edgeType, NodeDegree ndegree,
        Map<String, Object> nodeProps, Map<String, Object> edgeProps) {

        String pblock  = pblock(nodeProps);
        String dblock = dblock(N, edgeType, ndegree, edgeProps);

        Map<String, Object> allProps = new jext.util.HashMap<String, Object>()
            .putAll_(nodeProps)
            .putAll_(edgeProps);

        String s = String.format("MATCH (n:%s %s) %s",
            nodeType, pblock, dblock);

        return new Neo4JQuery(this, N, s, allProps);
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

        String fblock = pblock(fromProps);
        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
        String tblock = pblock(toProps);

        Map<String, Object> allProps = new jext.util.HashMap<String, Object>()
            .putAll_(fromProps)
            .putAll_(edgeProps)
            .putAll_(toProps);

        String s = String.format("MATCH (from:%s %s) %s (to:%s %s) RETURN id(from) AS idfrom, id(to) AS idto",
            fromType,
            fblock,
            eblock,
            toType,
            tblock
        );

        if(edgeType.equals("uses"))
            s = s +", e.uses as uses";

        return new Neo4JQuery(this, N, s, allProps);
    }

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    @Override
    public String createEdge(String edgeType, String fromId, String toId, Map<String, Object> edgeProps) {
        if (fromId.equals(toId)) {
            logger.errorf("Try to create the loop on node %s of type %s", fromId, edgeType);
            return null;
        }

        String pblock = pblock(edgeProps);
        String s = String.format("MATCH (from),(to) " +
            "WHERE id(from) = $fid AND id(to) = $tid " +
            "CREATE (from) -[e:%s %s]-> (to) " +
            "RETURN id(e)", edgeType, pblock);

        Parameters params = Parameters.params(
            "fid", asId(fromId),
            "tid", asId(toId))
            .add(edgeProps);

        return this.create(s, params);
    }

    @Override
    public void createEdges(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps) {
        String pblock = pblock(edgeProps);
        String s = String.format("MATCH (from),(to) " +
            "WHERE id(from) = $from AND id(to) IN $to " +
            "CREATE (from) -[e:%s %s]-> (to) " +
            "RETURN id(e)", edgeType, pblock);

        Parameters params = Parameters.params(
            "from", asId(fromId),
            "to", asId(toIds)
        ).add(edgeProps);

        this.execute(s, params);
    }

    @Override
    public void createEdges(String edgeType, Collection<String> fromIds, Collection<String> toIds, Map<String,Object> edgeProps) {
        int n = fromIds.size();
        String[] fromId_ = fromIds.toArray(StringUtils.emptyArray());
        String[] toId_ = toIds.toArray(StringUtils.emptyArray());

        assert fromIds.size() == n && toIds.size() == n;

        for (int i=0; i<n; ++i)
            createEdge(edgeType, fromId_[i], toId_[i], edgeProps);
    }

    // ----------------------------------------------------------------------

    @Override
    public Query queryPath(String edgeType, String fromId, String toId, Direction direction, boolean recursive,
                    Map<String, Object> edgeProps)
    {
        String eblock = eblock(E, edgeType, direction, recursive, null);
        String wblock = wblock(E, edgeProps, false);
        String s;

        if (recursive) {
            s = String.format("MATCH shortestPath( (from) %s (to) ) " +
                    "WHERE id(from) = $fid AND id(to) = $tid %s",
                eblock, wblock);
        }
        else {
            s = String.format("MATCH (from) %s (to) " +
                    "WHERE id(from) = $fid AND id(to) = $tid %s",
                eblock, wblock);
        }

        Parameters params = Parameters.params(
            "fid", asId(fromId),
            "tid", asId(toId))
            .add(edgeProps);

        return new Neo4JQuery(this, E, s, params).edge();
    }

    @Override
    public String findEdge(String edgeType, String fromId, String toId, Map<String, Object> edgeProps, Map<String, Object> createProps) {
        String edgeId = queryPath(edgeType, fromId, toId, Direction.Output, false, edgeProps).id();
        if (edgeId == null)
            edgeId = createEdge(edgeType, fromId, toId, edgeProps);

        if (edgeId != null)
            setEdgeProperties(edgeId, createProps);
        return edgeId;
    }


    @Override
    public void deleteEdges(String edgeType,  // can be null
                     String fromType, Map<String, Object> fromProps,
                     String toType, Map<String, Object> toProps,
                     Map<String,Object> edgeProps) {

        String s = String.format("MATCH (f:%s %s) -[e:%s %s]- (t:%s %s) WITH e LIMIT %d DELETE e",
            fromType, pblock(fromProps),
            edgeType, pblock(edgeProps),
            toType, pblock(toProps),
            MAX_DELETE_EDGES);

        Parameters params = Parameters.params().add(fromProps).add(toProps).add(edgeProps);
        this.delete(s, params, true);
    }

    // ----------------------------------------------------------------------

    @Override
    public void setEdgeProperties(String edgeId, Map<String, Object> updateProps) {
        if (updateProps == null || updateProps.size() == 0)
            return;

        String sblock = sblock("e", updateProps);
        String s = String.format("MATCH ()-[e]-() " +
            "WHERE id(e) = $id %s", sblock);

        Parameters params = Parameters.params(
            "id", asId(edgeId))
            .add(updateProps);
        this.execute(s, params);
    }

    @Override
    public void setEdgeProperty(String edgeId, String name, Object value, boolean override) {
        if (!override && getEdgeProperties(edgeId).containsKey(name))
            return;

        Map<String, Object> params = new HashMap<>();
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

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isDAG(String nodeType, Map<String,Object> nodeProps,
                         String edgeType, Map<String,Object> edgeProps)
    {
        String nblock = pblock(nodeProps);
        String eblock = pblock(edgeProps);
        String s = String.format("MATCH p=shortestPath( (n:%s %s) -[:%s* %s]-> (n) )",
            nodeType, nblock,
            edgeType, eblock);

        Parameters allParams = Parameters.params(nodeProps)
            .add(edgeProps);

        return new Neo4JQuery(this, "p", s, allParams).count() == 0;
    }

    // ----------------------------------------------------------------------
    // Graph access
    // ----------------------------------------------------------------------

    protected String/*Id*/ create(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);
            return toId(result.single().get(0).asLong());
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    protected Map<String, Object> retrieve(String alias, String s, Map<String, Object> params) {
        return retrieve(alias, s, params, false);
    }

    protected Map<String, Object> retrieve(String alias, String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);
            if (!result.hasNext())
                return null;

            Record r = result.single();
            if (edge)
                return toEdgeMap(alias, r);
            else
                return toNodeMap(alias, r);
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    protected String find(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);
            if (!result.hasNext())
                return null;
            else
                return toId(result.single().get(0).asLong());
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    protected GraphIterator<String> findAllIter(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);
            return new Neo4JResultSet<String>(result) {
                protected String compose(Record r) {
                    return toId(r.get(0).asLong());
                }
            };
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    protected GraphIterator<Map<String, Object>> retrieveAllIter(String alias, String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                protected Map<String, Object> compose(Record r) {
                    return edge ? toEdgeMap(alias, r) : toNodeMap(alias, r);
                }
            };
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    protected GraphIterator<Map<String, Object>> resultIter(String alias, String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                protected Map<String, Object> compose(Record r) {
                    return toResultMap(alias, r);
                }
            };
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    protected long count(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);

            return result.single().get(0).asLong();
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------
    private void logStmt(String s, Map<String, Object> params) {
        logStmt(s, params, false);
    }

    private void logStmt(String s, Map<String, Object> params, boolean error) {
        if (!logger.isDebugEnabled() && !error)
            return;

        String msg = s;

        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder(s);
            for(String key : params.keySet())
                sb.append("\n    ")
                    .append(key)
                    .append("='")
                    .append(params.get(key))
                    .append("'");
            msg = sb.toString();
        }

        if (error)
            logger.errorf(msg);
        else
            logger.debug(msg);
    }

    void execute(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            StatementResult result = session.run(s, params);
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
    }

    long delete(String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);
        long count = -1, total = 0;

        try {
            if (edge){
                while (count != 0) {
                    count = session.writeTransaction(tx -> {
                        return tx.run(s, params).consume().counters().relationshipsDeleted();
                    }).intValue();
                    total += count;
                    logger.debugft("Deleted %d edges %s", total, params);
                }
            }
            else {
                count = session.writeTransaction(tx -> {
                    return tx.run(s, params).consume().counters().nodesDeleted();
                }).intValue();
                total += count;
                logger.debugft("Deleted %d nodes %s", total, params);
            }
            // if (edge)
            //     while((count = result.consume().counters().relationshipsDeleted()) > 0) {
            //         total += count;
            //         logger.debugft("Deleted %d edges %s", total, params);
            //         connect();
            //     }
            // else
            //     while((count = result.consume().counters().nodesDeleted()) > 0) {
            //         total += count;
            //         logger.debugft("Deleted %d nodes %s", total, params);
            //         connect();
            //     }
        }
        catch(Throwable t) {
            logStmt(s, params, true);
            throw t;
        }
        return total;
    }

    private static Map<String, Object> toNodeMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

        if (r == null)
            return body;

        Node node = r.get(alias).asNode();

        body.put(ID, toId(node.id()));

        List<String> labels = new ArrayList<>();
        node.labels().forEach(label -> labels.add(label));

        // put_ $id
        // put_ $labels
        // put_ $type     == labels[0]

        body.put(LABELS, labels);

        if (labels.size() > 0)
            body.put(TYPE, labels.get(0));

        // put_ properties
        body.putAll(node.asMap());

        //
        // TODO: HA SENSO????
        //       DA RIMUOVERE!!!!
        //
        // if the node contains 'fullname' but not 'namespace', inserts it
        if (body.containsKey(FULLNAME) && !body.containsKey(NAMESPACE)) {
            // full.name
            String namespace = "";
            String fullName = body.get(FULLNAME).toString();
            int pos = fullName.lastIndexOf('.');
            if (pos != -1)
                namespace = fullName.substring(0, pos);
            body.put(NAMESPACE, namespace);
        }

        return body;
    }

    private static Map<String, Object> toEdgeMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

        if (r == null)
            return body;

        Relationship edge = r.get(alias).asRelationship();

        for(String key : edge.keys()) {
            Object value = edge.get(key).asObject();
            body.put(key, value);
        }

        // put_ $id
        // put_ $type

        body.put(ID, toId(edge.id()));
        body.put(TYPE, edge.type());

        // put_ properties
        body.putAll(edge.asMap());

        return body;
    }

    private static Map<String, Object> toResultMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

        if (r == null)
            return body;

        body.putAll(r.asMap());

        return body;
    }

    // ----------------------------------------------------------------------
    
    private static Long asId(String id) {
        return Long.valueOf(id);
    }

    private static Set<Long> asId(Collection<String> ids) {
        return ids
            .stream()
            .map(Long::valueOf)
            .collect(Collectors.toSet());
    }

    private static String toId(long id) {
        return Long.toString(id);
    }

    // ----------------------------------------------------------------------

    /**
     * Create the block:
     *
     *      { param: $param, ... }
     *
     */
    private static String pblock(Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder("{");
        for(String param : params.keySet()) {
            if (sb.length() > 1)
                sb.append(",");
            sb.append(param).append(": $").append(param);
        }
        return sb.append("}").toString();
    }

    /**
     * Create the block:
     *
     *      { param: value, ... }
     *
     */
    private static String tblock(Map<String, Object> params){
        if (params == null || params.size() == 0)
            return "{}";

        StringBuilder sb = new StringBuilder("{");
        for(String param : params.keySet()) {
            if (sb.length() > 1)
                sb.append(",");

            sb.append(param).append(": ");

            if(params.get(param) instanceof String || params.get(param) instanceof Character)
                sb.append("\"");

            sb.append(params.get(param));

            if(params.get(param) instanceof String || params.get(param) instanceof Character)
                sb.append("\"");
        }
        return sb.append("}").toString();
    }

    /**
     * Create the block
     *
     *      SET n.param = $param, ...
     *
     */
    private static String sblock(String alias, Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder("SET ");
        for(String param : params.keySet()) {
            if (sb.length() > 4)
                sb.append(",");
            sb.append(String.format("%s.%s = $%s", alias, param, param));
        }
        return sb.toString();
    }

    /**
     * Create the blocks
     *
     *      WHERE n.param = $param AND ...
     *
     * or
     *
     *      AND   n.param = $param AND ...
     *
     * @param wparams parameters
     * @param where if to include the WHERE keyword or AND
     * @return
     */
    private static String wblock(String alias, Map<String, Object> wparams, boolean where) {
        if (wparams == null || wparams.size() == 0)
            return "";

        int cond = 0;
        StringBuilder sb = new StringBuilder();
        if(where)
            sb.append("WHERE ");
        else
            sb.append(" AND ");
        for(String param : wparams.keySet()) {
            if (cond > 0)
                sb.append(" AND ");
            sb.append(String.format("%s.%s = $%s", alias, param, param));
            ++cond;
        }

        return sb.toString();
    }

    /**
     * Create the edge syntax block
     *
     *       -[e:edgeType {...}]-         Any
     *       -[e:edgeType {...}]->        Output
     *      <-[e:edgeType {...}]-         Input
     *
     */
    private static String eblock(String alias, String edgeType, Direction direction, boolean recursive,
                                 Map<String, Object> eparams) {
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

        String pblock = pblock(eparams);
        String etype = edgeType != null ? ":" + edgeType : "";

        return String.format(eblock,
            alias,
            etype,
            recursive ? "*" : "",
            pblock);
    }

    /**
     * Create the degree syntax block
     *
     *      WITH n, size( (n)  -[:ETYPE]-> () ) AS outdegree
     *      WITH n, size( (n) <-[:ETYPE]-  () ) AS indegree
     *      WITH n, size( (n) <-[:ETYPE]-  () ) AS indegree, size( (n)  -[:ETYPE]-> () ) AS outdegree
     *      WITH n, size( (n)  -[:ETYPE]-  () ) AS degree
     *
     *      WHERE ??degree > ${}
     *
     */
    private static String dblock(String alias, String edgeType, NodeDegree ndegree, Map<String, Object> edgeProps) {

        String dblock;
        String eprops = pblock(edgeProps);

        Parameters dparams = Parameters.params(
            "nalias", alias,
            "etype", edgeType, "eprops", eprops,
            "idwblock", dwblock("indegree",  ndegree.minInDegree,  ndegree.maxInDegree),
            "odwblock", dwblock("outdegree", ndegree.minOutDegree, ndegree.maxOutDegree),
            "dwblock",  dwblock("degree", ndegree.minInDegree, ndegree.maxInDegree));

        if (ndegree == null ) {
            return "";
        }
        else if (ndegree.isDegree) {

            dblock = StringUtils.format("WITH ${nalias}, " +
                "size( (${nalias}) -[:${etype} ${eprops}]- () ) AS degree " +
                "WHERE ${dwblock}", dparams);

        }
        else {

            if (ndegree.hasInDegree() && ndegree.hasOutDegree()) {

                dblock = StringUtils.format("WITH ${nalias}, " +
                    "size( (${nalias}) -[:${etype} ${eprops}]->() ) AS outdegree, " +
                    "size( (${nalias})<-[:${etype} ${eprops}]- () ) AS indegree " +
                    "WHERE ${idwblock} AND ${odwblock}", dparams);

            }
            else if (ndegree.hasInDegree()) {

                dblock = StringUtils.format("WITH ${nalias}, " +
                    "size( (${nalias})<-[:${etype} ${eprops}]- () ) AS indegree " +
                    "WHERE ${idwblock}", dparams);

            }
            else if (ndegree.hasOutDegree()) {

                dblock = StringUtils.format("WITH ${nalias}, " +
                    "size( (${nalias}) -[:${etype} ${eprops}]->() ) AS outdegree " +
                    "WHERE ${odwblock}", dparams);

            }
            else {

                dblock = StringUtils.format("", dparams);

            }

        }

        return dblock;
    }


    /**
     * Create the degree where block
     *
     *      ??degree  = deg
     *      ??degree >= minDegree
     *      ??degree <= maxDegree
     *      ??degree >= minDegree AND ??degree <= maxDegree
     *
     */
    private static String dwblock(String alias, long minDegree, long maxDegree) {
        if (minDegree == NodeDegree.MIN_DEGREE && maxDegree == NodeDegree.MAX_DEGREE)
            return "";
        if (minDegree == maxDegree)
            return String.format("%s = %d", alias, minDegree);
        if (maxDegree == NodeDegree.MAX_DEGREE)
            return String.format("%s >= %d", alias, minDegree);
        if (minDegree == NodeDegree.MIN_DEGREE)
            return String.format("%s <= %d", alias, maxDegree);
        else
            return String.format("%s >= %d and %s <= %d", alias, minDegree, alias, maxDegree);
    }

}
