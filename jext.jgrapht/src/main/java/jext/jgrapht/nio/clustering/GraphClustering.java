package jext.jgrapht.nio.clustering;

import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphClustering<V> implements ClusteringAlgorithm.Clustering<V> {

    private List<Set<V>> clusters = new ArrayList<>();;

    public GraphClustering() {

    }

    public GraphClustering(List<Set<V>> clusters) {
        setClusters(clusters);
    }

    @Override
    public int getNumberClusters() {
        return clusters.size();
    }

    @Override
    public List<Set<V>> getClusters() {
        return clusters;
    }

    public GraphClustering<V> setClusters(List<Set<V>> clusters) {
        this.clusters.addAll(clusters);
        return this;
    }

    public <K> GraphClustering<V> setClusters(Map<K, Set<V>> clusterMap) {
        this.clusters.addAll(clusterMap.values());
        return this;
    }

    @Override
    public Iterator<Set<V>> iterator() {
        return clusters.iterator();
    }
}
