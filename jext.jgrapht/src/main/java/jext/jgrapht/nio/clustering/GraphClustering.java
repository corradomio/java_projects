package jext.jgrapht.nio.clustering;

import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GraphClustering<V> implements ClusteringAlgorithm.Clustering<V> {

    private List<Set<V>> clusters;

    @Override
    public int getNumberClusters() {
        return clusters.size();
    }

    @Override
    public List<Set<V>> getClusters() {
        return clusters;
    }

    public void setClusters(List<Set<V>> clusters) {
        this.clusters = clusters;
    }

    @Override
    public Iterator<Set<V>> iterator() {
        return clusters.iterator();
    }
}
