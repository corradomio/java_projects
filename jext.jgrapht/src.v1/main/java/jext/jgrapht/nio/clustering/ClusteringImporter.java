package jext.jgrapht.nio.clustering;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ClusteringImporter<V> {

    public ClusteringAlgorithm.Clustering<V> importClustering(File file) {
        try(Reader r = new FileReader(file)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(r,  GraphClustering.class);
        }
        catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
