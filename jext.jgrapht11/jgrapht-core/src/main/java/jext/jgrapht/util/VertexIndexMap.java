package jext.jgrapht.util;

import org.jgrapht.util.VertexToIntegerMapping;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class VertexIndexMap<V> extends VertexToIntegerMapping<V> {

    public VertexIndexMap(Set vertices) {
        super(vertices);
    }

    public VertexIndexMap(List vertices) {
        super(vertices);
    }

    public VertexIndexMap(Collection vertices) {
        super(vertices);
    }

    public V getVertex(int index) {
        return getIndexList().get(index);
    }

    public int getIndex(V vertex) {
        return getVertexMap().get(vertex);
    }
}
