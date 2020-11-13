package jext.jgrapht.nio.clustering;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Export a clustering in JSON format
 *
 * @param <V>
 */
public class JSONClusteringExporter<V> {

    public void exportClustering(ClusteringAlgorithm.Clustering<V> clustering, File file) {

        try(Writer w = new FileWriter(file)) {
            new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(w, clustering);
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

}
