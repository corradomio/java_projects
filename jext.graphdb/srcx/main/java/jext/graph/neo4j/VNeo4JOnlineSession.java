package jext.graph.neo4j;

import jext.graph.Param;
import jext.graph.Query;
import jext.graph.schema.EdgeSchema;
import jext.graph.schema.GraphSchema;
import jext.graph.schema.ModelSchema;
import jext.graph.schema.NodeSchema;
import jext.graph.schema.PropertySchema;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongConsumer;

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

    // query
    private Map<String, Object> check(Map<String,Object> props) {
        // add 'refId'
        if (refId != null) {
            if (props.isEmpty())
                props = new HashMap<>();
            if (!props.containsKey(REF_ID))
                props.put(REF_ID, refId);
        }

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

    // ----------------------------------------------------------------------

    @Override
    public Query queryNodes(@Nullable String nodeType, @Nullable Map<String,Object> nodeProps) {
        return super.queryNodes(nodeType, check(nodeType, nodeProps, false));
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

    private String/*nodeId*/ updateNode(String nodeType,
                                        Map<String,Object> nodeProps,
                                        Map<String,Object> prevProps) {
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
    public long deleteNodes(@Nullable String nodeType, Map<String,Object> nodeProps, LongConsumer callback) {
        if (rev == NO_REV)
            return super.deleteNodes(nodeType, check(nodeType, nodeProps, false), callback);
        else
            return resetRevision(nodeType, nodeProps, callback);
    }

    // remove nodes from revision
    private long resetRevision(@Nullable String nodeType, Map<String,Object> nodeProps, LongConsumer callback) {
        NodeSchema nschema = schema.nodeSchema(nodeType);
        if (!nschema.isRevisioned())
            return 0;

        nodeProps = check(nodeProps);

        setNodesProperty(nodeType, nodeProps, Param.at(IN_REVISION, rev), false);
        return 1;
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

    @Override
    public String/*edgeId*/ createEdge(String edgeType, String fromId, String toId, Map<String,Object> edgeProps) {
        return super.createEdge(edgeType, fromId, toId, echeck(edgeType, edgeProps, true));
    }

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
    // ----------------------------------------------------------------------

    @Override
    public Query queryUsing(String queryName, Map<String,Object> queryParams) {
        return super.queryUsing(queryName, check(queryParams));
    }

    @Override
    public void executeUsing(String queryName, Map<String,Object> queryParams) {
        super.executeUsing(queryName, check(queryParams));
    }

    // ----------------------------------------------------------------------
    // Query using named queries
    // ----------------------------------------------------------------------

    @Override
    public Query queryUsingFullText(String query,  Map<String,Object> params) {
        return super.queryUsingFullText(query, check(params));
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

        Map<String, Object> nmap = new HashMap<>();
        for (String name : map.keySet()) {
            if (isSpecial(name)) {
                nmap.put(name, map.get(name));
                continue;
            }

            PropertySchema pschema = nschema.propertySchema(name);
            if (!pschema.isRevisioned()) {
                nmap.put(name, map.get(name));
            }
            else {
                nmap.put(name, pschema.atRevision(map.get(name), rev));
            }
        }

        return nmap;
    }

    private Map<String, Object> processEdge(Map<String, Object> map) {
        String edgeType = (String) map.get(TYPE);
        EdgeSchema eschema = schema.edgeSchema(edgeType);
        if (!eschema.hasRevisionedProperties())
            return map;

        Map<String, Object> nmap = new HashMap<>();
        for (String name : map.keySet()) {
            if (isSpecial(name)) {
                nmap.put(name, map.get(name));
                continue;
            }

            PropertySchema pschema = eschema.propertySchema(name);
            if (!pschema.isRevisioned()) {
                nmap.put(name, map.get(name));
            }
            else {
                nmap.put(name, pschema.atRevision(map.get(name), rev));
            }
        }

        return nmap;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
