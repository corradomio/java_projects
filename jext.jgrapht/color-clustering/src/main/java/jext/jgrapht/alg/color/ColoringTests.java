package jext.jgrapht.alg.color;

import jext.jgrapht.util.VertexInfo;
import jext.util.concurrent.ConcurrentBitSet;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class ColoringTests {

    public static <V> long countColors(VertexColoringAlgorithm.Coloring<V> coloring) {
        return coloring.getColors().values().parallelStream().collect(Collectors.toSet()).size();
    }

    public static <V, E> long countConflicts(Graph<V, E> g, VertexColoringAlgorithm.Coloring<V> coloring) {
        return countConflicts(g, coloring.getColors());
    }

    public static <V, E> long countConflicts(Graph<V, E> g, Map<V, Integer> colors) {
        return g.edgeSet().parallelStream()
                .filter(e -> {
                    int sourceColor = colors.get(g.getEdgeSource(e));
                    int targetColor = colors.get(g.getEdgeTarget(e));
                    return sourceColor == targetColor;
                }).count();
    }

    public static <V> int countDominantColors(Collection<VertexInfo<V>> vinfos) {
        int maxColors = vinfos.stream().map(vi -> vi.color).collect(Collectors.toSet()).size() - 1;
        ConcurrentBitSet dcolors = new ConcurrentBitSet();
        vinfos.parallelStream().forEach(vi -> {
            if(vi.ncolors.cardinality() == maxColors)
                dcolors.set(vi.color);
        });
        return dcolors.cardinality();
    }
}
