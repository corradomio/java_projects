package jext.jgrapht.nio;

import org.jgrapht.Graph;

import java.io.Reader;

public interface GraphImporter<V, E> extends org.jgrapht.nio.GraphImporter<V, E> {

    default void importGraph(Graph<V, E> g, Reader in) {
        importGraph(g);
    }

    void importGraph(Graph<V, E> graph);
}
