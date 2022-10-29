package jext.graph.neo4j;

import jext.graph.Query;
import jext.graph.schema.GraphSchema;
import jext.graph.schema.ModelSchema;
import jext.graph.schema.NodeSchema;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongConsumer;

/*
                   (1)              (2)
    MATCH  (n:type {p:v,...}) WHERE n.p=v AND ... RETURN n
                   (1)              (3)
    CREATE (n:type {p:v,...}) SET   n.p=v,... RETURN id(n)

    ... RETURN n.p, ...
    ... DELETE n.p, ...
 */

public class VNeo4JOnlineSession extends Neo4JOnlineSession {

    private static final String REVISION = "revision";
    public static final int NO_REV = -1;

    private final String refId;
    private final String model;
    private final int rev;
    private final GraphSchema schema;

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
    
    // create
    private Map<String, Object> check(String nodeType, Map<String,Object> props) {
        if (props != null && refId != null) {
            if (props.isEmpty())
                props = new HashMap<>();
            if (!props.containsKey(REF_ID))
                props.put(REF_ID, refId);
        }
        ModelSchema mschema = schema.modelSchema(model);
        NodeSchema  nschema = schema.nodeSchema(nodeType);

        if (mschema.isReference(nodeType))
        if (nschema.isRevisioned() && rev != NO_REV)
            props.put(REVISION, rev);

        return props;
    }

    // query
    private Map<String,Object> check(Map<String,Object> props) {
        if (props != null && refId != null) {
            if (props.isEmpty())
                props = new HashMap<>();
            if (!props.containsKey(REF_ID))
                props.put(REF_ID, refId);
            if (!props.containsKey(REVISION) && rev != NO_REV)
                props.put(REVISION, rev);
        }
        return props;
    }
    
    @Override
    public Query queryNodes(@Nullable String nodeType, @Nullable Map<String,Object> nodeProps) {
        return super.queryNodes(nodeType, check(nodeProps));
    }

    @Nullable
    @Override
    public String/*nodeId*/ findNode(@Nullable String nodeType, Map<String,Object> nodeProps) {
        return super.findNode(nodeType, check(nodeProps));
    }

    @Override
    public boolean existsNode(@Nullable String nodeType, Map<String,Object> nodeProps) {
        return super.existsNode(nodeType, check(nodeProps));
    }

    // ----------------------------------------------------------------------
    // Operations on a single node
    // ----------------------------------------------------------------------

    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String,Object> nodeProps) {
        return super.createNode(nodeType, check(nodeType, nodeProps));
    }

    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String,Object> findProps, Map<String,Object> updateProps) {
        return super.createNode(nodeType, check(nodeType, findProps), updateProps);
    }

    // ----------------------------------------------------------------------
    // Operations on node list
    // ----------------------------------------------------------------------

    @Override
    public long countNodes(String nodeType, Map<String,Object> nodeProps) {
        return super.countNodes(nodeType, check(nodeProps));
    }

    @Override
    public long deleteNodes(@Nullable String nodeType, Map<String,Object> nodeProps) {
        return super.deleteNodes(nodeType, check(nodeProps));
    }

    @Override
    public long deleteNodes(@Nullable String nodeType, Map<String,Object> nodeProps, LongConsumer callback) {
        return super.deleteNodes(nodeType, check(nodeProps), callback);
    }

    // ----------------------------------------------------------------------
    // Edge queries
    // ----------------------------------------------------------------------

    @Override
    public Query queryEdges(
        @Nullable String edgeType,
        @Nullable String fromType, Map<String,Object> fromProps,
        @Nullable String toType,   Map<String,Object> toProps,
        Map<String,Object> edgeProps) {
        return super.queryEdges(edgeType,
            fromType, check(fromProps),
            toType, check(toProps),
            edgeProps);
    }

    // ----------------------------------------------------------------------
    // Edge
    // ----------------------------------------------------------------------

    @Override
    public long deleteEdges(
        @Nullable String edgeType,  // can be null
        String fromType, Map<String,Object> fromProps,
        String toType, Map<String,Object> toProps,
        Map<String,Object> edgeProps,
        LongConsumer callback) {
        return super.deleteEdges(edgeType,
            fromType, check(fromProps),
            toType, check(toProps),
            edgeProps, callback);
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
    public Query queryUsingFullText(String query,  Map<String,Object> queryParams) {
        return super.queryUsingFullText(query, check(queryParams));
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
