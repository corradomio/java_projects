package jext.text.embedding;

import jext.compress.Archives;
import jext.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GloVeEmbedding {

    private static Logger logger = Logger.getLogger(GloVeEmbedding.class);

    public static WordEmbedding load(File embeddingFile) throws IOException {
        if (Archives.isCompressed(embeddingFile))
            return load(embeddingFile, changeExt(embeddingFile.getName(), ".txt"));
        else
            return loadText(embeddingFile);
    }

    public static WordEmbedding load(File zippedFile, int length) throws IOException {
        String ext = String.format(".%dd.txt", length);
        return load(zippedFile, changeExt(zippedFile.getName(), ext));
    }

    public static WordEmbedding load(File zippedFile, String path) throws IOException {
        WordEmbedding emb;
        logger.infof("Loading GloVe embedding from '%s[%s]'", zippedFile, path);
        try(BufferedReader rdr = Archives.openText(zippedFile, path)) {
            emb = load(rdr);
        }
        logger.infof("done (%d/%d)", emb.size(), emb.length());
        return emb;
    }

    private static WordEmbedding loadText(File embeddingFile) throws IOException {
        WordEmbedding emb;
        logger.infof("Loading GloVe embedding from '%s'", embeddingFile);
        try (BufferedReader rdr = new BufferedReader(new FileReader(embeddingFile))) {
            emb = load(rdr);
        }
        logger.infof("done (%d/%d)", emb.size(), emb.length());
        return emb;
    }

    private static WordEmbedding load(BufferedReader rdr) throws IOException {
        WordEmbedding emb = new WordEmbedding();

        for(String line = rdr.readLine(); line != null; line = rdr.readLine()) {
            String[] parts = line.split(" ");
            int n = parts.length;;
            double[] vec = new double[n-1];

            String word = parts[0];
            for (int i=1; i<n; ++i)
                vec[i-1] = Double.parseDouble(parts[i]);

            emb.put(word, vec);
            logger.debugft("... loaded %6d tokens", emb.size());
        }
        return emb;
    }

    private static String changeExt(String name, String ext) {
        int p = name.lastIndexOf('.');
        return name.substring(0, p) + ext;
    }

}
