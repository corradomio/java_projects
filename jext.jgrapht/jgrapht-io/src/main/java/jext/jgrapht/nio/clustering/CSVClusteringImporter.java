package jext.jgrapht.nio.clustering;

import jext.jgrapht.alg.clustering.MapClustering;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

/**
 * Import a clustering from a CSV file with the structure
 *
 *          vertex,cluster      (header: optional)
 *          v1, c1 \n
 *          ...
 *
 * @param <V>
 */
public class CSVClusteringImporter<V> {

    private boolean header;
    private Function<String, V> toVertex;

    public CSVClusteringImporter() {

    }

    /**
     * If the file has a header to skip
     * @param hdr if it is present a header
     * @return self
     */
    public CSVClusteringImporter<V> withHeader(boolean hdr) {
        this.header = header;
        return this;
    }

    /**
     * Function used to convert a string in a vertex of type V
     * @param toVertex Function String->V
     * @return self
     */
    public CSVClusteringImporter<V> withToVertex(Function<String, V> toVertex) {
        this.toVertex = toVertex;
        return this;
    }

    /**
     * Import the clustering from the specified file
     *
     * @param file file to read
     * @return the clustering
     */
    public ClusteringAlgorithm.Clustering<V> importClustering(File file) {
        MapClustering<String, V> clustering = new MapClustering<>();

        try(BufferedReader rdr = new BufferedReader(new FileReader(file))) {
            int iLine = 0;

            for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
                if (iLine == 0 && header) {
                    iLine += 1;
                    continue;
                }

                String[] parts = line.split(",");

                V vertex = toVertex.apply(parts[0]);
                String c = parts[1];

                clustering.addVertex(c, vertex);
            }

            return clustering;
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
