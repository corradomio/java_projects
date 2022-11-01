package jext.graph.neo4j;

import jext.graph.Param;
import jext.graph.Query;
import jext.graph.schema.EdgeSchema;
import jext.graph.schema.GraphSchema;
import jext.graph.schema.ModelSchema;
import jext.graph.schema.NodeSchema;
import jext.graph.schema.PropertySchema;
import jext.util.Parameters;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongConsumer;

import static jext.graph.NodeId.asId;
import static jext.graph.neo4j.CypherFormatter.label;
import static jext.graph.neo4j.CypherFormatter.pblock;
import static jext.graph.neo4j.CypherFormatter.wblock;
import static jext.graph.schema.NodeSchema.NO_REV;

/*
                   (1)              (2)
    MATCH  (n:type {p:v,...}) WHERE n.p=v AND ... RETURN n
                   (1)              (3)
    CREATE (n:type {p:v,...}) SET   n.p=v,... RETURN id(n)

    ... RETURN n.p, ...
    ... DELETE n.p, ...
 */

public class VNeo4JOnlineSession extends Neo4JOnlineSession implements VGraphSession {

    private final String refId;
    private final String model;
    private final int rev;
    private final GraphSchema schema;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public VNeo4JOnlineSession(VNeo4JOnlineDatabase graphdb, String refId, String model, int rev) {
        super(graphdb);
        this.refId = refId;
        this.model = model;
        this.rev = rev;
        this.schema = graphdb.getGraphSchema();
    }

    // ----------------------------------------------------------------------
    // Query Nodes
    // ----------------------------------------------------------------------
    //  (revision, rev) ->
    //      set:    inRevision[rev] = true
    //      where:  inRevision[rev]

    // add 'refId' is not present
    private Map<String, Object> check(Map<String,Object> props) {
        // add 'refId'
        if (refId == null)
            return props;

        // can be a unmodifiable map
        if (props.isEmpty())
            props = new HashMap<>();
        if (!props.containsKey(REF_ID))
            props.put(REF_ID, refId);

        return props;
    }

    // query + revision
    private Map<String, Object> check(String nodeType, Map<String,Object> props, boolean create) {
        // FIRST: add refId is available
        props = check(props);

        // SECOND: normal processing IF no revision specified
        if (rev == NO_REV)
            return props;

        ModelSchema mschema = schema.modelSchema(model);
        NodeSchema  nschema = schema.nodeSchema(nodeType);

        if (mschema.isReference(nodeType))
            props.put(REVISIONS, Collections.singleton(rev));
        if (nschema.isRevisioned())
            props.put(REVISION, rev);

        if (!nschema.hasRevisionedProperties())
            return props;

        if (create)
            nschema.normalizeCreate(props, rev);
        else
            nschema.normalizeQuery(props, rev);

        return props;
    }

    private Map<String, Object> qcheck(Map<String,Object> props) {
        // add 'refId'
        if (refId == null)
            return props;

        // can be a unmodifiable map
        if (props.isEmpty())
            props = new HashMap<>();
        if (!props.containsKey(REF_ID))
            props.put(REF_ID, refId);
        if (!props.containsKey(REVISION))
            props.put(REVISION, rev);

        return props;
    }

    // ----------------------------------------------------------------------

    @Override
    public Query queryNodes(@Nullable String nodeType, @Nullable Map<String,Object> nodeProps) {
        nodeProps = check(nodeType, nodeProps, false);
        return super.queryNodes(nodeType, nodeProps);
    }

    // ----------------------------------------------------------------------
    // Operations on a single node
    // ----------------------------------------------------------------------

    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String,Object> nodeProps) {
        // check if the node is already present
        Map<String, Object> prevProps = findPrevious(nodeType, nodeProps);
        if (prevProps.isEmpty())
            return super.createNode(nodeType, check(nodeType, nodeProps, true));
        else
            return updateNode(nodeType, nodeProps, prevProps);
    }

    private Map<String, Object> findPrevious(String nodeType, Map<String,Object> nodeProps) {
        if (rev <= 0)
            return Collections.emptyMap();

        NodeSchema nschema = schema.nodeSchema(nodeType);
        Map<String,Object> findProps = nschema.uniqueProperties(nodeProps);
        String nodeId = super.queryNodes(nodeType, check(findProps)).id();
        return getNodeProperties(nodeId);
    }

    private String/*nodeId*/ updateNode(String nodeType,  Map<String,Object> nodeProps, Map<String,Object> prevProps) {
        String nodeId = (String) prevProps.get(GRAPH_ID);
        NodeSchema nschema = schema.nodeSchema(nodeType);
        Map<String,Object> updateProps = nschema.normalizeUpdate(nodeProps, prevProps, rev);
        if (nschema.isRevisioned())
            updateProps.put(REVISION, rev);
        super.setNodeProperties(nodeId, updateProps);
        return nodeId;
    }

    // ----------------------------------------------------------------------
    // Operations on node list
    // ----------------------------------------------------------------------

    @Override
    public boolean deleteNode(String nodeId) {
        Map<String, Object> nodeProps = super.getNodeProperties(nodeId);
        String nodeType = (String)nodeProps.get(GRAPH_TYPE);
        return deleteNode(nodeType, nodeId);
    }

    @Override
    public boolean deleteNode(String nodeType, String nodeId) {
        if (rev == NO_REV)
            return super.deleteNode(nodeId);

        NodeSchema nschema = schema.nodeSchema(nodeType);
        if (!nschema.isRevisioned())
            return false;

        deleteIncidentEdges(nodeId);
        setNodeProperty(nodeId, Param.at(IN_REVISION, rev), false);
        return true;
    }

    private void deleteIncidentEdges(String nodeId) {
        // MATCH (n)-[e]->()
        // WHERE id(n)=$id AND e.inRevision[rev]
        // SET e.inRevision = apocx.coll.arraySet(e.inRevision, $revision, false)
        // RETURN count(e)

        String s = String.format("MATCH (n)-[e]-() " +
                "WHERE id(n)=$id AND e.%1$s[$%2$s] " +
                "SET e.%1$s = apocx.coll.arraySet(e.%1$s, $%2$s, false) " +
                "RETURN COUNT(e)", IN_REVISION, REVISION);

        Parameters params = Parameters.params().add(ID, asId(nodeId), REVISION, rev);

        this.execute(s, params);
    }

    @Override
    public long deleteNodes(@Nullable String nodeType, Map<String,Object> nodeProps, LongConsumer callback) {
        if (rev == NO_REV)
            return super.deleteNodes(nodeType, check(nodeType, nodeProps, false), callback);

        NodeSchema nschema = schema.nodeSchema(nodeType);
        if (!nschema.isRevisioned())
            return 0;

        nodeProps = check(nodeType, nodeProps, false);

        long count = countNodes(nodeType, nodeProps);
        deleteIncidentEdges(nodeType, nodeProps);
        setNodesProperty(nodeType, nodeProps, Param.at(IN_REVISION, rev), false);
        return count;
    }

    private void deleteIncidentEdges(@Nullable String nodeType, Map<String,Object> nodeProps) {
        // MATCH (n:type {...})-[e]->()
        // WHERE n.inRevision[rev] AND e.inRevision[rev]
        //   AND ...
        // SET e.inRevision = apox.coll.arraySet(e.inRevision, rev, false)
        // RETURN count(e)

        String pblock = pblock(N, nodeProps);
        String wblock = wblock(N, nodeProps, true, true);

        String s = String.format("MATCH (n%3$s %4$s)-[e]-() " +
                "WHERE n.%1$s[$%2$s] AND e.%1$s[$%2$s] " +
                "%5$s " +
                "SET e.%1$s = apocx.coll.arraySet(e.%1$s, $%2$s, false) " +
                "RETURN COUNT(e)",
                IN_REVISION, REVISION,
                label(nodeType),
                pblock,
                wblock
        );

        Parameters params = Parameters.params()
            .add(nodeProps)
            .add(REVISION, rev);

        this.execute(s, params);
    }

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    private Map<String,Object> echeck(Map<String,Object> props) {
        if (props.isEmpty())
            props = new HashMap<>();
        return props;
    }

    private Map<String, Object> echeck(String edgeType, Map<String,Object> props, boolean create) {
        // FIRST: check is props is empty
        props = echeck(props);

        // SECOND: normal processing if no revision
        if (rev == NO_REV)
            return props;

        EdgeSchema eschema = schema.edgeSchema(edgeType);
        if (eschema.isRevisioned())
            props.put(REVISION, rev);
        if (!eschema.hasRevisionedProperties())
            return props;
        if (create)
            eschema.normalizeCreate(props, rev);
        else
            eschema.normalizeQuery(props, rev);
        return props;
    }

    // ----------------------------------------------------------------------

    @Nullable
    @Override
    public String findEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps) {
        if (rev == NO_REV)
            return super.findEdge(edgeType, fromId, toId, edgeProps);

        edgeProps = echeck(edgeType, edgeProps, false);
        return super.findEdge(edgeType, fromId, toId, edgeProps);
    }

    // ----------------------------------------------------------------------

    @Override
    public String/*edgeId*/ createEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps) {
        if (rev == NO_REV)
            return super.createEdge(edgeType, fromId, toId, edgeProps);

        String edgeId = super.findEdge(edgeType, fromId, toId);
        if (edgeId == null)
            return super.createEdge(edgeType, fromId, toId, echeck(edgeType, edgeProps, true));

        setEdgeProperty(edgeId, Param.at(IN_REVISION, rev), true);
        return edgeId;
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean deleteEdge(String edgeId) {
        if (rev == NO_REV)
            return super.deleteEdge(edgeId);

        Map<String, Object> edgeProps = super.getEdgeProperties(edgeId);
        String edgeType = (String)edgeProps.get(GRAPH_TYPE);
        return deleteEdge(edgeType, edgeId);
    }

    @Override
    public boolean deleteEdge(String edgeType, String edgeId) {
        if (rev == NO_REV)
            return super.deleteEdge(edgeId);

        EdgeSchema eschema = schema.edgeSchema(edgeType);
        if (!eschema.isRevisioned())
            return false;

        setEdgeProperty(edgeId, Param.at(IN_REVISION, rev), false);
        return true;
    }

    // ----------------------------------------------------------------------

    @Override
    public Query queryEdges(
        @Nullable String edgeType,
        @Nullable String fromType, Map<String,Object> fromProps,
        @Nullable String toType,   Map<String,Object> toProps,
        Map<String,Object> edgeProps) {
        return super.queryEdges(edgeType,
            fromType, check(fromType, fromProps, false),
            toType,   check(toType,   toProps, false),
            echeck(edgeType, edgeProps, false));
    }

    @Override
    public long deleteEdges(
        @Nullable String edgeType,  // can be null
        String fromType, Map<String,Object> fromProps,
        String toType, Map<String,Object> toProps,
        Map<String,Object> edgeProps,
        LongConsumer callback) {
        return super.deleteEdges(edgeType,
            fromType, check(fromType, fromProps, false),
            toType,   check(toType,   toProps, false),
            echeck(edgeType, edgeProps, false),
            callback);
    }

    // ----------------------------------------------------------------------
    // Query using named queries
    // Query suing full text
    // ----------------------------------------------------------------------

    @Override
    public Query queryUsing(String queryName, Map<String,Object> queryParams) {
        return super.queryUsing(queryName, qcheck(queryParams));
    }

    @Override
    public void executeUsing(String queryName, Map<String,Object> queryParams) {
        super.executeUsing(queryName, qcheck(queryParams));
    }

    @Override
    public Query queryUsingFullText(String query,  Map<String,Object> params) {
        return super.queryUsingFullText(query, qcheck(params));
    }

    // ----------------------------------------------------------------------
    // Post processing
    // ----------------------------------------------------------------------

    private static final String TYPE = GRAPH_TYPE;
    private static final String LABELS = GRAPH_LABELS;

    private static boolean isSpecial(String name) {
        return name.startsWith("$");
    }

    @Override
    protected Map<String, Object> postProcess(Map<String, Object> map) {
        if (rev == NO_REV)
            return super.postProcess(map);
        if (map.containsKey(LABELS))
            return processNode(map);
        if (map.containsKey(TYPE))
            return processEdge(map);
        else
            return super.postProcess(map);
    }

    private Map<String, Object> processNode(Map<String, Object> map) {
        String nodeType = (String) map.get(TYPE);
        NodeSchema nschema = schema.nodeSchema(nodeType);

        if (!nschema.hasRevisionedProperties())
            return map;

        for (String name : map.keySet()) {
            if (isSpecial(name))
                continue;

            PropertySchema pschema = nschema.propertySchema(name);
            if (pschema.isRevisioned())
                map.put(name, pschema.atRevision(map.get(name), rev));
        }

        return map;
    }

    private Map<String, Object> processEdge(Map<String, Object> map) {
        String edgeType = (String) map.get(TYPE);
        EdgeSchema eschema = schema.edgeSchema(edgeType);

        if (!eschema.hasRevisionedProperties())
            return map;

        for (String name : map.keySet()) {
            if (isSpecial(name))
                continue;

            PropertySchema pschema = eschema.propertySchema(name);
            if (pschema.isRevisioned())
                map.put(name, pschema.atRevision(map.get(name), rev));
        }

        return map;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
