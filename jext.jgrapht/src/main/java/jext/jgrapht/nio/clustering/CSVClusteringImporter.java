package jext.jgrapht.nio.clustering;

import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CSVClusteringImporter<V> {

    private int skipLines;
    private boolean header;

    public CSVClusteringImporter() {

    }

    public CSVClusteringImporter<V> withHeader(boolean hdr) {
        this.header = header;
        return this;
    }

    public ClusteringAlgorithm.Clustering<V> importClustering(File file) {
        Map<String, Set<V>> clusters = new HashMap<>();

        try(BufferedReader rdr = new BufferedReader(new FileReader(file))) {
            int iLine = 0;

            for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
                if (iLine == 0 && header) {
                    iLine += 1;
                    continue;
                }

                String[] parts = line.split(",");

                V vertex = toVertex(parts[0]);
                String c = parts[1];

                if (!clusters.containsKey(c))
                    clusters.put(c, new HashSet<>());

                clusters.get(c).add(vertex);
            }

            return new GraphClustering<V>().setClusters(clusters);
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    protected abstract V toVertex(String v);
}
