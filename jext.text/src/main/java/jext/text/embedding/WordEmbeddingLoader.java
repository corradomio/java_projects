package jext.text.embedding;

import jext.compress.Archives;
import jext.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class WordEmbeddingLoader {

    protected static Logger logger = Logger.getLogger(GloVeEmbedding.class);

    private boolean lowercase;
    private boolean normalize;
    private File file;
    private String path;

    public static WordEmbedding load(File zippedFile, String path, int skip, boolean lowercase) throws IOException {
        WordEmbedding emb;
        logger.infof("Loading words embedding from '%s[%s]'", zippedFile, path);
        try(BufferedReader rdr = Archives.openText(zippedFile, path)) {
            emb = load(rdr, skip, lowercase);
        }
        logger.infof("done (%d/%d)", emb.size(), emb.length());
        return emb;
    }

    protected static WordEmbedding loadText(File embeddingFile, int skip, boolean lowercase) throws IOException {
        WordEmbedding emb;
        logger.infof("Loading words embedding from '%s'", embeddingFile);
        try (BufferedReader rdr = new BufferedReader(new FileReader(embeddingFile))) {
            emb = load(rdr, skip, lowercase);
        }
        logger.infof("done (%d/%d)", emb.size(), emb.length());
        return emb;
    }

    protected static WordEmbedding load(BufferedReader rdr, int skip, boolean lowercase) throws IOException {
        WordEmbedding emb = new WordEmbedding();

        for(String line = rdr.readLine(); line != null; line = rdr.readLine()) {
            if (skip > 0) { --skip; continue; }
            String[] parts = line.split("[ \t]");
            int n = parts.length;
            double[] vec = new double[n-1];

            String word = parts[0];
            for (int i=1; i<n; ++i)
                vec[i-1] = Double.parseDouble(parts[i]);

            if (lowercase)
                word = word.toLowerCase();

            emb.put(word, vec);
            logger.debugft("... loaded %8d tokens", emb.size());
        }
        return emb;
    }

    protected WordEmbeddingLoader() {

    }

    public WordEmbeddingLoader file(File file) {
        this.file = file;
        return this;
    }

    public WordEmbeddingLoader file(File file, String path) {
        this.file = file;
        this.path = path;
        return this;
    }

    public WordEmbeddingLoader lowercase() {
        lowercase=true;
        return this;
    }

    public WordEmbeddingLoader normalize() {
        normalize=true;
        return this;
    }

}
