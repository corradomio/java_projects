package jext.jgrapht.nio.clustering;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ClusteringExporter<V> {

    public void exportClustering(ClusteringAlgorithm.Clustering<V> clustering, File file) {

        try(Writer w = new FileWriter(file)) {
            new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(w, clustering);
            // Jsonb jsonb = JsonbBuilder.create();
            // jsonb.toJson(clustering);
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

}
