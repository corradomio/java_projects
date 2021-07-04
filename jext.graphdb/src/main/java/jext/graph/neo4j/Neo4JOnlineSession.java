package jext.graph.neo4j;

import jext.debug.Debug;
import jext.graph.Direction;
import jext.graph.GraphDatabase;
import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.NodeDegree;
import jext.graph.Query;
import jext.logging.Logger;
import jext.util.ArrayUtils;
import jext.util.MapUtils;
import jext.util.Parameters;
import jext.util.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
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
import java.util.Set;

import static jext.graph.NodeId.asId;
import static jext.graph.NodeId.asIds;
import static jext.graph.NodeId.toId;

public class Neo4JOnlineSession implements GraphSession {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private enum WhereType {
        NONE,
        WHERE,
        AND
    }

    private static final String FULLNAME = "fullname";
    private static final String NAMESPACE = "namespace";
    private static final String N = "n";
    private static final String E = "e";
    private static final String NONE = "";
    private static final int MAX_DELETE_NODES = 16*1024;
    private static final int MAX_DELETE_EDGES = 128*1024;

    // ----------------------------------------------------------------------
    // Special handled parameters
    // ----------------------------------------------------------------------

    private static final String LABELS = "labels";
    private static final String REFIDS = "refIds";

    // special parameters:   [revision, n]
    // is converted in       n.inRevision[$revision]
    // 'inRevision' is a boolean vector
    private static final String REVISION = "revision";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(Neo4JOnlineSession.class);

    private final Neo4JOnlineDatabase graphdb;
    private final Driver driver;
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

    public Neo4JOnlineSession connect(Map<String, Object> params) {
        if (params == null)
            params = Collections.emptyMap();
        session = driver.session();
        return this;
    }

    @Override
    public boolean isConnected() {
        return session != null && session.isOpen();
    }

    @Override
    public void close() {
        if (session== null)
            return;
        session.close();
        session = null;
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
    public Query queryUsing(String queryName,  Map<String,Object> params) {
        String query = graphdb.getQuery(queryName);

        // replace ${name} with 'name' value in 'params'
        String s = StringUtils.format(query, params);

        return new Neo4JQuery(this, N, s, params);
    }

    @Override
    public void executeUsing(String queryName, Map<String,Object> params) {
        String query = graphdb.getQuery(queryName);

        // replace ${name} with 'name' value in 'params'
        String s = StringUtils.format(query, params);

        try {
            session.run(s, params);
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    // ----------------------------------------------------------------------
    // Query using fulltext
    // ----------------------------------------------------------------------

    @Override
    public Query queryUsingFullText(String query,  Map<String,Object> queryParams) {
        // indexName
        // query
        // labels ONLY if size > 0
        // refIds ONLY if size > 0
        boolean hasLabels = queryParams.containsKey(LABELS) && !((Collection<String>)queryParams.get(LABELS)).isEmpty();
        boolean hasRefIds = queryParams.containsKey(REFIDS) && !((Collection<String>)queryParams.get(REFIDS)).isEmpty();

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
    // Nodes
    // ----------------------------------------------------------------------

    @Override
    public boolean existsNode(String nodeId) {
        if (nodeId == null)
            return false;

        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = "MATCH (n) WHERE id(n) = $id RETURN count(n)";
        return this.count(s, params) > 0;
    }

    @Override
    public String createNode(String nodeType, Map<String, Object> nodeProps) {
        Map<String, Object> arrayProps = null;
        // if (hasIndexedProperties(nodeProps))
        //     arrayProps = extractIndexedProperties(nodeProps);

        String pblock = pblock(NONE, nodeProps);
        String s = String.format("CREATE (n:%s %s) RETURN id(n)", nodeType, pblock);
        String nodeId = this.create(s, nodeProps);
        setNodeProperties(nodeId, arrayProps);
        return nodeId;
    }

    @Override
    public boolean deleteNode(String nodeId) {
        if (nodeId == null)
            return false;
        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = "MATCH (n) WHERE id(n) = $id DETACH DELETE n";
        this.delete(s, params, false);
        return true;
    }

    @Override
    public Map<String, Object> getNodeValues(String nodeId) {
        Parameters params = Parameters.params(
            "id", asId(nodeId));
        String s = "MATCH (n) WHERE id(n) = $id RETURN n";
        return this.retrieve(N, s, params);
    }

    // ----------------------------------------------------------------------
    // List of nodes
    // ----------------------------------------------------------------------

    @Override
    public long countNodes(String nodeType, Map<String,Object> nodeProps) {
        String pblock = pblock(NONE, nodeProps);
        String s;

        if (nodeType == null)
            s = String.format("MATCH (n %s) RETURN COUNT(n)", pblock);
        else
            s = String.format("MATCH (n:%s %s) RETURN COUNT(n)", nodeType, pblock);

        return this.count(s, nodeProps);
    }

    @Override
    public void deleteNodes(Collection<String> nodeIds) {
        if (nodeIds == null || nodeIds.isEmpty())
            return;

        if (nodeIds.size() > MAX_DELETE_NODES)
            nodeIds = new ArrayList<>(nodeIds);

        while(nodeIds.size() > MAX_DELETE_NODES) {
            int n = nodeIds.size();
            deleteSomeNodes(((List<String>)nodeIds).subList(0, MAX_DELETE_NODES));
            nodeIds = ((List<String>)nodeIds).subList(MAX_DELETE_NODES, n);
        }
        if (!nodeIds.isEmpty())
            deleteSomeNodes(nodeIds);
    }

    private void deleteSomeNodes(Collection<String> nodeIds) {
        Parameters params = Parameters.params(
            "ids", asIds(nodeIds));
        String s = "MATCH (n) WHERE id(n) IN $ids DETACH DELETE n";
        this.execute(s, params);
    }

    // ----------------------------------------------------------------------

    @Override
    public void deleteNodes(String nodeType, Map<String, Object> nodeProps, long count) {
        String pblock = pblock(NONE, nodeProps);
        String s;

        if (nodeType == null && count <= 0)
            s = String.format("MATCH (n %s) DETACH DELETE n", pblock);
        else if (nodeType == null)
            s = String.format("MATCH (n %s) WITH n LIMIT %d DETACH DELETE n", pblock, count);
        else if (count <= 0)
            s = String.format("MATCH (n:%s %s) DETACH DELETE n", nodeType, pblock);
        else
            s = String.format("MATCH (n:%s %s) WITH n LIMIT %d DETACH DELETE n", nodeType, pblock, count);

        this.delete(s, nodeProps, false);
    }

    // ----------------------------------------------------------------------

    @Override
    public List<Map<String, Object>> getNodesValues(List<String> nodeIds) {
        Parameters params = Parameters.params(
            "id", asIds(nodeIds));
        String s = "MATCH (n) WHERE id(n) IN $id RETURN n";

        return this.retrieveAllIter(N, s, params, false).toList();
    }

    // ----------------------------------------------------------------------
    // Indexed properties: name[index]
    // ----------------------------------------------------------------------

    private static boolean hasProperty(Map<String, Object> params, String text) {
        for(String key : params.keySet())
            if (key.contains(text))
                return true;
        return false;
    }

    // private static boolean isIndexedProperty(String name) {
    //     return name.contains("[");
    // }

    // private static boolean hasIndexedProperties(Map<String, Object> props) {
    //     for (String param : props.keySet())
    //         if (isIndexedProperty(param))
    //             return true;
    //     return false;
    // }

    // private static Map<String, Object> extractIndexedProperties(Map<String, Object> props) {
    //     Map<String, Object> aprops = new HashMap<>();
    //     for (String param : new HashSet<>(props.keySet()))
    //         if (isIndexedProperty(param)) {
    //             aprops.put(param, props.get(param));
    //             props.remove(param);
    //         }
    //     return aprops;
    // }

    // name,index -> name[index]
    private static String at(String name, int index) {
        return String.format("%s[%d]", name, index);
    }

    // name[index] -> name
    private static String nameOf(String indexed) {
        int pos = indexed.indexOf('[');
        return indexed.substring(0, pos);
    }

    // name[index] -> index
    private static int indexOf(String indexed, int at) {
        if (at == 0) {
            int pos = indexed.indexOf('[');
            int end = indexed.indexOf(']');
            String index = indexed.substring(pos + 1, end);
            return Integer.parseInt(index);
        }
        if (at == 1) {
            int pos = indexed.indexOf('[');
            int end = indexed.indexOf(',', pos);
            String index = indexed.substring(pos + 1, end);
            return Integer.parseInt(index);
        }
        if (at == 2) {
            int pos = indexed.indexOf(',');
            int end = indexed.indexOf(']', pos);
            String index = indexed.substring(pos + 1, end);
            return Integer.parseInt(index);
        }
        else
            throw new IllegalArgumentException();
    }

    // name        -> name
    // name[index] -> nameIndex
    // name[!]     -> name_a
    // name[+]     -> name_a
    // name[index,!]    -> nameIndex_a
    // name[index,+]    -> nameIndex_a
    // name[idx1,idx2]  -> nameIdx1_Idx2
    private static String pnameOf(String p) {
        if (!p.contains("["))
            return p;
        if (p.endsWith("[!]"))
            return p.replace("[!]", "_a");
        if (p.endsWith("[+]"))
            return p.replace("[+]", "_a");
        if (p.endsWith(",!]"))
            return p.replace("[", "").replace(",!]", "a");
        if (p.endsWith(",+]"))
            return p.replace("[", "").replace(",+]", "a");
        else
            return p.replace("[", "")
                .replace(",", "_")
                .replace("]", "");
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
        setNodeProperties(nodeId, MapUtils.asMap(at(name, index), value));
    }

    // @Override
    // public void appendNodePropertyValue(String nodeId, String name, Object value, boolean distinct) {
    //     if (nodeId == null)
    //         return;
    //
    //     String s;
    //
    //     Parameters params = Parameters.params(
    //         "id", asId(nodeId),
    //         "value", value,
    //         "name", name
    //     );
    //
    //     if (distinct)
    //         s = StringUtils.format("" +
    //             "MATCH (n) WHERE id(n) = $id " +
    //             "SET n.${name} = apoc.coll.appendDistinct(n.${name}, $value) " +
    //             "RETURN n", params);
    //     else
    //         s = StringUtils.format("" +
    //             "MATCH (n) WHERE id(n) = $id " +
    //             "SET n.${name} = apoc.coll.append(n.${name}, $value) " +
    //             "RETURN n", params);
    //
    //     this.execute(s, params);
    // }

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
        String pblock = pblock(NONE, nodeProps);
        String wblock = wblock(N, nodeProps, WhereType.WHERE);
        String s;

        if (StringUtils.isEmpty(nodeType))
            s = String.format("MATCH (n %s) %s", pblock, wblock);
        else
            s = String.format("MATCH (n:%s %s) %s", nodeType, pblock, wblock);

        Parameters params = Parameters.params(nodeProps).add(N, nodeProps);

        return new Neo4JQuery(this, N, s, params);
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

        return queryAdjacentNodes(Collections.singletonList(fromId), edgeType, direction, recursive,
            nodeType, nodeProps, edgeProps);
    }

    @Override
    public Query queryAdjacentNodes(
        List<String> fromIds, String edgeType, Direction direction, boolean recursive,
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

    @Override
    public Query queryAdjacentNodesAlgorithm(
            String fromId, String edgeType, Direction direction, boolean recursive,
            String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps
    ){
        String eblock = eblock(E, edgeType, direction, false, edgeProps); //recursive has to be false for algorithm
        String pblock = pblock(NONE, nodeProps);

        Parameters allParams = new Parameters();
        allParams.putAll(nodeProps);
        allParams.putAll(edgeProps);
        String tblock = tblock(allParams);
        String s;

        if(recursive){
            s = String.format("MATCH (from) WHERE id(from) = $id " +
                    "CALL algo.shortestPaths.stream(from, null,{nodeQuery:'Match(n:%s %s) return id(n) as id',relationshipQuery: 'Match (n1) %s (n2) RETURN id(n1) as source, id(n2) as target',graph:'cypher',params:%s}) " +
                    "YIELD nodeId as n,distance " +
                    "Where algo.isFinite(distance) = true return distinct n", nodeType, pblock, eblock,tblock);
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

        String pblock = pblock(NONE, nodeProps);
        String dblock = dblock(N, edgeType, ndegree, edgeProps);

        Parameters allProps = new Parameters();
        allProps.putAll(nodeProps);
        allProps.putAll(edgeProps);

        String s = String.format("MATCH (n:%s %s) %s",
            nodeType, pblock, dblock);

        return new Neo4JQuery(this, N, s, allProps);
    }

    // @Override
    // public Query queryNodesWithDegree(
    //     List<String> ids, String edgeType, NodeDegree ndegree,
    //     Map<String, Object> edgeProps) {
    //     String dblock = dblock(N, edgeType, ndegree, edgeProps);
    //
    //     Parameters params = Parameters.params(
    //         "ids", asIds(ids)
    //     );
    //
    //     String s = String.format("MATCH (n) %s AND id(n) IN $ids", dblock);
    //
    //     return new Neo4JQuery(this, N, s, params);
    // }

    // ----------------------------------------------------------------------
    // All Edges
    // ----------------------------------------------------------------------

    @Override
    public Query queryEdges(
        String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps) {

        String fblock = pblock(NONE, fromProps);
        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
        String tblock = pblock(NONE, toProps);

        Parameters allProps = new Parameters();
        allProps.putAll(fromProps);
        allProps.putAll(edgeProps);
        allProps.putAll(toProps);

        String s;
        if (fromType == null && toType == null)
            s = String.format("MATCH (from %s) %s (to %s) RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                fblock,
                eblock,
                tblock
            );
        else if (fromType == null)
            s = String.format("MATCH (from %s) %s (to:%s %s) RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                fblock,
                eblock,
                toType,
                tblock
            );
        else if (toType == null)
            s = String.format("MATCH (from:%s %s) %s (to %s) RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                fromType,
                fblock,
                eblock,
                tblock
            );
        else
            s = String.format("MATCH (from:%s %s) %s (to:%s %s) RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
                fromType,
                fblock,
                eblock,
                toType,
                tblock
            );

        if(edgeType != null && edgeType.equals("uses"))
            s = s +", e.uses as uses";

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

        String s = String.format(
            "MATCH (from) %s (to) WHERE id(from) IN $from AND id(to) IN $to " +
            "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
            eblock
        );

        Parameters allProps = new Parameters();
        allProps.put("from", asIds(fromIds));
        allProps.put("to", asIds(toIds));

        return new Neo4JQuery(this, N, s, allProps);
    }

    @Override
    public Query queryEdges(String edgeType, Collection<String> ids,
                            Map<String, Object> edgeProps) {

        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);

        String s = String.format(
            "MATCH (from) %s (to) WHERE id(from) IN $ids AND id(to) IN $ids " +
                "RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
            eblock
        );

        Parameters allProps = new Parameters();
        allProps.put("ids", asIds(ids));

        return new Neo4JQuery(this, N, s, allProps);
    }

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    //
    // SPL v2.4 COMPATIBILITY.
    // In SPL 2.4 there is an error: in the 'uses' edges it is used the property 'uses' to specify
    // the edge specialization, instead of 'type'.
    // To ensure the compatibility, the 'uses' edges will be created with BOTH properties ('uses' AND 'type')
    //
    private static String USES      = "uses";   // edge label/main type
    private static String EDGE_TYPE = "type";   // edge subtype  ('uses/type')

    private static Map<String, Object> createEdgeProps24(Map<String, Object> edgeProps) {
        if (edgeProps.containsKey(USES) && edgeProps.containsKey(EDGE_TYPE))
            return edgeProps;
        if (!edgeProps.containsKey(USES) && !edgeProps.containsKey(EDGE_TYPE))
            return edgeProps;

        Parameters newProps = Parameters.params(edgeProps);
        if (newProps.containsKey(USES))
            newProps.put(EDGE_TYPE, edgeProps.get(USES));
        else
            newProps.put(USES, edgeProps.get(EDGE_TYPE));
        return newProps;
    }

    // END

    @Override
    public String createEdge(String edgeType, String fromId, String toId, Map<String, Object> edgeProps) {

        //
        // SPL v2.4 COMPATIBILITY. See above
        //
        if (USES.equals(edgeType))
            edgeProps = createEdgeProps24(edgeProps);

        // END

        String pblock = pblock(E, edgeProps);
        String s = String.format("MATCH (from),(to) " +
            "WHERE id(from) = $fid AND id(to) = $tid " +
            "CREATE (from) -[e:%s %s]-> (to) " +
            "RETURN id(e)", edgeType, pblock);

        Parameters params = Parameters.params(
            "fid", asId(fromId),
            "tid", asId(toId))
            .add(E, edgeProps);

        return this.create(s, params);
    }

    @Override
    public void createEdges(String edgeType, String fromId, Collection<String> toIds, Map<String,Object> edgeProps) {

        //
        // SPL v2.4 COMPATIBILITY. See above
        //
        if (USES.equals(edgeType))
            edgeProps = createEdgeProps24(edgeProps);

        // END

        String pblock = pblock(E, edgeProps);
        String s = String.format("MATCH (from),(to) " +
            "WHERE id(from) = $from AND id(to) IN $to " +
            "CREATE (from) -[e:%s %s]-> (to) " +
            "RETURN id(e)", edgeType, pblock);

        Parameters params = Parameters.params(
            "from", asId(fromId),
            "to", asIds(toIds)
        ).add(E, edgeProps);

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
            .add(E, edgeProps);

        return new Neo4JQuery(this, E, s, params).edge();
    }

    @Override
    public String findEdge(String edgeType, String fromId, String toId,
                           Map<String, Object> edgeProps, Map<String, Object> createProps)
    {
        String edgeId = queryPath(edgeType, fromId, toId, Direction.Output, false, edgeProps).id();
        if (edgeId == null)
            edgeId = createEdge(edgeType, fromId, toId, createProps);

        return edgeId;
    }


    @Override
    public void deleteEdges(String edgeType,  // can be null
                     String fromType, Map<String, Object> fromProps,
                     String toType, Map<String, Object> toProps,
                     Map<String,Object> edgeProps) {

        String s = String.format("MATCH (f:%s %s) -[e:%s %s]- (t:%s %s) WITH e LIMIT %d DELETE e",
            fromType, pblock("f", fromProps),
            edgeType, pblock(E, edgeProps),
            toType, pblock("t", toProps),
            MAX_DELETE_EDGES);

        Parameters params = Parameters.params()
            .add("f", fromProps)
            .add(E, edgeProps)
            .add("t", toProps);

        this.delete(s, params, true);
    }

    // ----------------------------------------------------------------------

    @Override
    public void setEdgeProperties(String edgeId, Map<String, Object> updateProps) {
        if (updateProps == null || updateProps.size() == 0)
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
    public void setEdgeProperty(String edgeId, String name, Object value, boolean override) {
        if (!override && getEdgeProperties(edgeId).containsKey(name))
            return;

        Parameters params = new Parameters();
        params.put(name, value);
        setEdgeProperties(edgeId, params);
    }

    @Override
    public Map<String, Object> getEdgeProperties(String edgeId) {
        String s = String.format("MATCH ()-[e]-() WHERE id(e) = $id RETURN e");
        Parameters params = Parameters.params(
            "id", asId(edgeId));
        return this.retrieve("e", s, params, true);
    }

    // @Override
    // public void appendEdgePropertyValue(String edgeId, String name, Object value, boolean distinct) {
    //     if (edgeId == null)
    //         return;
    //
    //     String s;
    //     Parameters params = Parameters.params(
    //         "id", asId(edgeId),
    //         "value", value,
    //         "name", name
    //     );
    //
    //     if (distinct)
    //         s = StringUtils.format("" +
    //             "MATCH ()-[e]-() WHERE id(e) = $id " +
    //             "SET e.${name} = apoc.coll.appendDistinct(e.${name}, $value) " +
    //             "RETURN e", params);
    //     else
    //         s = StringUtils.format("" +
    //             "MATCH ()-[e]-() WHERE id(e) = $id " +
    //             "SET e.${name} = apoc.coll.append(e.${name}, $value) " +
    //             "RETURN e", params);
    //
    //     this.execute(s, params);
    // }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isDAG(String nodeType, Map<String,Object> nodeProps,
                         String edgeType, Map<String,Object> edgeProps)
    {
        String nblock = pblock(N, nodeProps);
        String eblock = pblock(E, edgeProps);
        String s = String.format("MATCH p=shortestPath( (n:%s %s) -[:%s* %s]-> (n) )",
            nodeType, nblock,
            edgeType, eblock);

        Parameters allParams = Parameters.params()
            .add(N, nodeProps)
            .add(E, edgeProps);

        return new Neo4JQuery(this, "p", s, allParams).count() == 0;
    }

    // ----------------------------------------------------------------------
    // Graph access
    // ----------------------------------------------------------------------

    protected String/*Id*/ create(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            Result result = session.run(s, params);
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
            Result result = session.run(s, params);
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
            Result result = session.run(s, params);
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
            Result result = session.run(s, params);
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
            Result result = session.run(s, params);

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
        logStmt(s, params);

        try {
            Result result = session.run(s, params);

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
        logStmt(s, params);

        try {
            Result result = session.run(s, params);

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
            sb.append(t.getMessage()).append("\n");

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

    void execute(String s, Map<String, Object> params) {
        logStmt(s, params);

        try {
            Result result = session.run(s, params);
        }
        catch (Throwable t) {
            logStmt(s, params, t);
            throw t;
        }
    }

    private long delete(String s, Map<String, Object> params, boolean edge) {
        logStmt(s, params);
        long count = -1, total = 0;

        try {
            if (edge){
                while (count != 0) {
                    count = session.writeTransaction(tx ->
                        tx.run(s, params)
                            .consume()
                            .counters()
                            .relationshipsDeleted())
                        .intValue();
                    total += count;
                    if (count > 0)
                        logger.debugft("Deleted %d edges %s", total, params);
                }
            }
            else {
                while(count != 0) {
                    count = session.writeTransaction(tx ->
                        tx.run(s, params)
                            .consume()
                            .counters()
                            .nodesDeleted())
                        .intValue();
                    total += count;
                    if (count > 0)
                        logger.debugft("Deleted %d nodes %s", total, params);
                }
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
        catch (Throwable t) {
            logStmt(s, params, t);
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

        // for(String key : edge.keys()) {
        //     Object value = edge.get(key).asObject();
        //     body.put(key, value);
        // }

        // put_ properties
        body.putAll(edge.asMap());

        // put_ $id
        // put_ $type

        body.put(ID, toId(edge.id()));
        body.put(TYPE, edge.type());

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

    /**
     * Create the block:
     *
     *      { param: $[prefix]param, ... }
     *
     */
    private static String pblock(String prefix, Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder("{");
        for(String param : params.keySet()) {
            Object value = params.get(param);

            // value is null
            if (value == null)
                continue;
            // value is a collection
            if (value instanceof Collection)
                continue;
            // param is "revision" or 'name[...]'
            if (param.equals(REVISION) || param.contains("["))
                continue;

            if (sb.length() > 1)
                sb.append(",");

            sb.append(param).append(":$").append(prefix).append(param);
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
            Object value = params.get(param);

            if (sb.length() > 1)
                sb.append(",");

            sb.append(param).append(": ");

            if (value instanceof String || value instanceof Character)
                sb.append("\"").append(value).append("\"");
            else
                sb.append(value);
        }
        return sb.append("}").toString();
    }

    /**
     * Create the block
     *
     *      SET n.param = $nparam, ...
     *
     */
    private static String sblock(String alias, Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder("SET ");
        for(String param : new HashSet<>(params.keySet())) {
            if (sb.length() > 4)
                sb.append(",");

            // name[!] -> appendDistinct(n.name, $pname)
            if (param.endsWith("[!]")) {
                String name = nameOf(param);
                String pname = pnameOf(param);

                sb.append(String.format("%1$s.%2$s = apoc.coll.appendDistinct(%1$s.%2$s, $%1$s%3$s)",
                    alias, name, pname));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[+] -> append(n.name, $pname)
            else if (param.endsWith("[+]")) {
                String name = nameOf(param);
                String pname = pnameOf(param);

                sb.append(String.format("%1$s.%2$s = apoc.coll.append(%1$s.%2$s, $%1$s%3$s)",
                    alias, name, pname));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[index,!] -> appendDistinct2(n.name, index, $pname)
            else if (param.endsWith(",!]")) {
                String name = nameOf(param);
                String pname = pnameOf(param);
                int index = indexOf(param, 1);

                sb.append(String.format("%1$s.%2$s = apoc.coll.appendDistinct2(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[index,+] -> append2(n.name, index, $pname)
            else if (param.endsWith(",+]")) {
                String name = nameOf(param);
                String pname = pnameOf(param);
                int index = indexOf(param, 1);

                sb.append(String.format("%1$s.%2$s = apoc.coll.append2(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[idx1,idx2] -> setOrExtend2(n.name, idx1, idx2, $pname)
            else if (param.contains("[") && param.contains(",")) {
                String name = nameOf(param);
                String pname = pnameOf(param);
                int index1 = indexOf(param, 1);
                int index2 = indexOf(param, 2);

                sb.append(String.format("%1$s.%2$s = apoc.coll.setOrExtend2(%1$s.%2$s, %4$d, %5$d, $%1$s%3$s)",
                    alias, name, pname, index1, index2));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name[index] -> setOrExtend2(n.name, index, $pname)
            else if (param.contains("[")) {
                String name = nameOf(param);
                String pname = pnameOf(param);
                int index = indexOf(param, 0);

                sb.append(String.format("%1$s.%2$s = apoc.coll.setOrExtend(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name -> name = $pname
            else {
                sb.append(String.format("%1$s.%2$s = $%1$s%2$s", alias, param));
            }
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
     *      AND  n.param = $param AND ...
     *
     * @param params parameters
     * @param where if to include the WHERE keyword or AND
     */
    private static String wblock(String alias, Map<String, Object> params, WhereType where) {
        if (params == null || params.isEmpty())
            return "";

        String s;
        StringBuilder sb = new StringBuilder();

        for (String param : new HashSet<>(params.keySet())) {
            Object value = params.get(param);

            String pname = pnameOf(param);
            if (!pname.equals(param))
                params.put(pname, value);

            // if (value != null && !(value instanceof Collection) && !REVISION.equals(param))
            //     continue;

            // append "... AND "
            if (sb.length() > 0) sb.append(" AND ");

            // convert [param, null] in "EXISTS(n.param)"
            if (value == null) {
                s = String.format("EXISTS(%s.%s)", alias, param);
            }
            // convert ["revision", n] in "inRevision[$revision]"
            else if (REVISION.equals(param)) {
                s = revisionCondition(alias, param, params);
            }
            else if (!(value instanceof Collection)) {
                s = String.format("%1$s.%2$s = $%1$s%3$s", alias, param, pname);
            }
            // convert [param, value: a collection] in "n.param IN $param"
            else {
                s = String.format("%1$s.%2$s IN $%1$s%s", alias, param, pname);
            }

            sb.append(s);
        }

        if (sb.length() == 0)
            return sb.toString();

        switch (where) {
            // case NONE: return sb.toString();
            case AND:  return sb.insert(0, " AND ").toString();
            case WHERE: return sb.insert(0, " WHERE ").toString();
            default: return sb.toString();
        }
    }

    /*
        [revision, 0 | [0,1,...]] ->
            n.inRevision[0]
            n.inRevision[0] OR n.inRevision[1] ...
     */
    private static String revisionCondition(String alias, String param, Map<String, Object> params) {
        Object value = params.get(param);

        // check if value is a simple integer
        if (value instanceof Integer) {
            int rev = (int) value;
            return String.format("%s.inRevision[%d]", alias, rev);
        }

        // convert a collection in a 'int[]'
        if (value instanceof Collection)
            value = ArrayUtils.asIntArray(value);

        int[] revs = (int[]) value;

        if (revs.length == 0)
            return "false";
        if (revs.length == 1)
            return String.format("%s.inRevision[%d]", alias, revs[0]);

        // revs.length > 1
        StringBuilder sb =  new StringBuilder("(");
        sb.append(String.format("%s.inRevision[%d]", alias, revs[0]));
        for (int i=1; i<revs.length; ++i) {
            sb.append(" OR ")
              .append(String.format("%s.inRevision[%d]", alias, revs[i]));
        }
        sb.append(")");

        return sb.toString();
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
        String etype = edgeType != null ? ":" + edgeType : "";

        return String.format(eblock,
            alias,
            etype,
            recursive ? "*" : "",
            pblock);
    }

    /**
     * Create the 'degree syntax' block
     *
     *      WITH n, size( (n)  -[:etype]-> () ) AS outdegree
     *      WITH n, size( (n) <-[:etype]-  () ) AS indegree
     *      WITH n, size( (n) <-[:etype]-  () ) AS indegree, size( (n)  -[:ETYPE]-> () ) AS outdegree
     *      WITH n, size( (n)  -[:etype]-  () ) AS degree
     *
     *      WHERE ??degree > ${}
     *
     */
    private static String dblock(String alias, String edgeType, NodeDegree ndegree, Map<String, Object> edgeProps) {

        String dblock;
        String eprops = pblock(NONE, edgeProps);

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
     * Create the 'degree where' block
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

    /**
     * Create 'n.att1, n.att2, ...' block
     */
    private static String ablock(String alias, Collection<String> attributes) {
        if (attributes.isEmpty())
            return "";

        Iterator<String> it = attributes.iterator();
        StringBuilder sb = new StringBuilder()
            .append(alias).append(".").append(it.next());

        while (it.hasNext()) {
            sb.append(",").append(alias).append(".").append(it.next());
        }
        return sb.toString();
    }

}
