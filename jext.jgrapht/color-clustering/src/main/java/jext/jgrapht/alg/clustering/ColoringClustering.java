package jext.jgrapht.alg.clustering;

import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Clustering using acoloring algorithm
 */
public class ColoringClustering<V, E> implements ClusteringAlgorithm<V> {

    private final VertexColoringAlgorithm<V> coloringAlgorithm;

    public ColoringClustering(VertexColoringAlgorithm<V> ca) {
        this.coloringAlgorithm = ca;
    }

    @Override
    public Clustering<V> getClustering() {
        // get the coloring
        // vertex -> color
        VertexColoringAlgorithm.Coloring<V> coloring =
                coloringAlgorithm.getColoring();
        return getClustering(coloring);
    }

    public static <V> Clustering<V> getClustering(VertexColoringAlgorithm.Coloring<V> coloring) {
        // create the clusters based on the colors
        List<Set<V>> clusters = new ArrayList<>();
        // color -> set[vertices]
        Map<Integer, Set<V>> cvsetMap = new HashMap<>();
        coloring.getColors().forEach((v, c) -> {
            if (!cvsetMap.containsKey(c)) {
                Set<V> vset = new HashSet<>();
                cvsetMap.put(c, vset);
                clusters.add(vset);
            }
            cvsetMap.get(c).add(v);
        });

        return new ClusteringAlgorithm.ClusteringImpl<>(clusters);
    }
}
