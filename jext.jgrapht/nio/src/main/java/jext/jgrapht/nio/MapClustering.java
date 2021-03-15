package jext.jgrapht.clustering.nio;

import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Clustering data structured based on a map cluster->setOf[V]
 * used to compose a clustering
 *
 * @param <K>
 * @param <V>
 */
public class MapClustering<K, V> implements ClusteringAlgorithm.Clustering<V> {

    Map<K, Set<V>> clustering = new HashMap<>();

    public MapClustering() {

    }

    public MapClustering<K, V> addVertex(K cluster, V vertex) {
        if (!clustering.containsKey(cluster))
            clustering.put(cluster, new HashSet<>());
        clustering.get(cluster).add(vertex);
        return this;
    }

    @Override
    public int getNumberClusters() {
        return clustering.size();
    }

    @Override
    public List<Set<V>> getClusters() {
        return new ArrayList<>(clustering.values());
    }

    @Override
    public Iterator<Set<V>> iterator() {
        return clustering.values().iterator();
    }
}
