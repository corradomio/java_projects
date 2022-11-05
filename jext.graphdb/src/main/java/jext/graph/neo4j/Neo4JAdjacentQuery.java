package jext.graph.neo4j;

import jext.graph.Direction;
import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Limit;
import jext.graph.Query;
import jext.util.SetUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Neo4JAdjacentQuery implements Query {

    private static final String N = "n";

    private final Neo4JOnlineSession session;

    private Limit limit;
    private String alias;
    private boolean distinct;

    private final Collection<String> fromIds;
    private final String edgeType;
    private final Direction direction;
    private final String nodeType;
    private final Map<String, Object> nodeProps;
    private final Map<String, Object> edgeProps;

    public Neo4JAdjacentQuery(
        GraphSession session,
        Collection<String> fromIds, String edgeType, Direction direction,
        String nodeType, Map<String, Object> nodeProps,
        Map<String, Object> edgeProps)
    {
        this.session = (Neo4JOnlineSession) session;

        this.fromIds = fromIds;
        this.edgeType = edgeType;
        this.direction = direction;
        this.nodeType = nodeType;
        this.nodeProps = nodeProps;
        this.edgeProps = edgeProps;
    }

    @Override
    public long update(Map<String, Object> values) {

        return execute();
    }

    @Override
    public long execute() {
        return 0;
    }

    @Override
    public Query limit(Limit limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public Query limit(long count) {
        this.limit = new Limit(count);
        return this;
    }

    @Override
    public Query distinct() {
        distinct = true;
        return this;
    }

    @Override
    public long count() {
        return scanAdjacentNodes().count();
    }

    @Override
    public boolean exists() {
        return scanAdjacentNodes().exists();
    }

    @Override
    public long delete() {
        return scanAdjacentNodes().delete();
    }

    @Override
    public String id() {
        return scanAdjacentNodes().id();
    }

    @Override
    public Map<String, Object> values() {
        return scanAdjacentNodes().values();
    }

    @Override
    public GraphIterator<String> ids() {
        return scanAdjacentNodes().ids();
    }

    @Override
    public GraphIterator<Map<String, Object>> allValues() {
        return scanAdjacentNodes().allValues();
    }

    @Override
    public GraphIterator<Map<String, Object>> result() {
        return allValues();
    }

    private Query scanAdjacentNodes() {
        Set<String> visited = new HashSet<>();

        // initial list of nodes to visit
        Queue<String> toVisit = new LinkedList<>(this.fromIds);

        while(!toVisit.isEmpty()) {
            Set<String> adjIds = session.queryAdjacentNodesStep(toVisit, edgeType, direction, edgeProps)
                .distinct().ids().toSet();

            visited.addAll(toVisit);

            // visit only nodes not already visited
            toVisit.clear();
            toVisit.addAll(SetUtils.difference(adjIds, visited));
        }

        return session.selectNodes(visited, nodeType, nodeProps);
    }

}
