package jext.embedding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GloVe {

    private Map<String, Embedding> embeddings = new HashMap<>();
    private static Embedding NOT_AVAILABLE;

    public void load(File file){
        Embedding t;
        try(BufferedReader rdr = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            for(String line = rdr.readLine(); line != null; line = rdr.readLine()) {
                String[] parts = line.split(" ");
                String word = parts[0];
                Embedding embedding = Embedding.of(parts, 1);

                embeddings.put(word, t = embedding);
                if (NOT_AVAILABLE == null)
                    NOT_AVAILABLE = Embedding.of(t.size());
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Embedding getEmbedding(String word) {
        return embeddings.getOrDefault(word, NOT_AVAILABLE);
    }
}
