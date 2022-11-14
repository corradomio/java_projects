package jext.jgrapht.vertices;

import org.jgrapht.util.VertexToIntegerMapping;

import java.util.Collection;
import java.util.List;

public class VertexIndexMap<V> extends VertexToIntegerMapping<V> {

    public VertexIndexMap(List<V> vertices) {
        super(vertices);
    }

    public VertexIndexMap(Collection<V> vertices) {
        super(vertices);
    }

}
