package jext.graph.neo4j;

import jext.graph.Query;
import jext.graph.schema.GraphSchema;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongConsumer;

public class VNeo4JOnlineSession extends Neo4JOnlineSession {

    private final String refId;
    private final String model;
    private final int revision;
    private final GraphSchema schema;

    public VNeo4JOnlineSession(VNeo4JOnlineDatabase graphdb, String refId, String model, int revision) {
        super(graphdb);
        this.refId = refId;
        this.model = model;
        this.revision = revision;
        this.schema = graphdb.getGraphSchema();
    }

    // ----------------------------------------------------------------------
    // Query Nodes
    // ----------------------------------------------------------------------
    //

    private Map<String,Object> check(Map<String,Object> props) {
        if (props != null && !props.containsKey(REF_ID) && refId != null) {
            if (props.isEmpty())
                props = new HashMap<>();
            props.put(REF_ID, refId);
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
        return super.createNode(nodeType, check(nodeProps));
    }

    @Override
    public String/*nodeId*/ createNode(String nodeType, Map<String,Object> findProps, Map<String,Object> updateProps) {
        return super.createNode(nodeType, check(findProps), updateProps);
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
