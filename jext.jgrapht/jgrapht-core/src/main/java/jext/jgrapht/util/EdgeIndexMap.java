package jext.jgrapht.util;

import org.jgrapht.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EdgeIndexMap<E> {

    private final Map<E, Integer> edgeMap;
    private final List<E> indexList;

    public EdgeIndexMap(List<E> edges) {
        edgeMap = CollectionUtil.newHashMapWithExpectedSize(edges.size());
        indexList = edges;

        for (E e : edges) {
            if (edgeMap.put(e, edgeMap.size()) != null) {
                throw new IllegalArgumentException("edges are not distinct");
            }
        }
    }

    public EdgeIndexMap(Collection<E> edges) {
        this(new ArrayList<>(
            Objects
                .requireNonNull(edges, "the input collection of edges cannot be null")));
    }

    public Map<E, Integer> getEdgeMap()
    {
        return edgeMap;
    }

    public List<E> getIndexList()
    {
        return indexList;
    }
}
