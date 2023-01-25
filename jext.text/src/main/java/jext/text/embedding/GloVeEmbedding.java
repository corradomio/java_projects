package jext.text.embedding;

import jext.compress.Archives;
import jext.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GloVeEmbedding extends WordEmbeddingLoader {

    static {
        logger = Logger.getLogger(GloVeEmbedding.class);
    }

    public static WordEmbeddingLoader loader() {
        return new GloVeEmbedding();
    }

    public static WordEmbedding load(File embeddingFile) throws IOException {
        if (Archives.isCompressed(embeddingFile))
            return load(embeddingFile, changeExt(embeddingFile.getName(), ".txt"), 0, false);
        else
            return loadText(embeddingFile, 0, false);
    }

    public static WordEmbedding load(File zippedFile, int length) throws IOException {
        String path = changeExt(zippedFile.getName(), length);
        return load(zippedFile, path, 0, false);
    }

    public static WordEmbedding load(File zippedFile, String path) throws IOException {
        return load(zippedFile, path, 0, false);
    }

    private static String changeExt(String name, int length) {
        int p = name.lastIndexOf('.');
        return name.substring(0, p) + String.format(".%dd.txt", length);
    }

    private static String changeExt(String name, String ext) {
        int p = name.lastIndexOf('.');
        return name.substring(0, p) + ext;
    }

}
