package jext.springframework.data.cypherdsl;

import jext.springframework.data.query.Direction;
import jext.springframework.data.query.Edge;

import java.util.Collection;
import java.util.Map;

public interface AdjacentQueryStatementExecutor<T, ID> {

    Iterable<T> queryAdjacentNodes(
        ID fromId,
        String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);

    Iterable<T> queryAdjacentNodes(
        Collection<ID> fromIds,
        String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps);

    Iterable<Edge> queryEdges(String edgeType, Collection<ID> fromIds, Map<String, Object> edgeProps);
}
