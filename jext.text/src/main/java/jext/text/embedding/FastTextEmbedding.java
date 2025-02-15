package jext.text.embedding;

import jext.compress.Archives;
import jext.util.logging.Logger;

import java.io.File;
import java.io.IOException;

public class FastTextEmbedding extends WordEmbeddingLoader {

    static {
        logger = Logger.getLogger(FastTextEmbedding.class);
    }

    public static WordEmbeddingLoader loader() {
        return new FastTextEmbedding();
    }

    public static WordEmbedding load(File embeddingFile) throws IOException {
        return load(embeddingFile, true);
    }

    public static WordEmbedding load(File embeddingFile, boolean lowercase) throws IOException {
        if (Archives.isCompressed(embeddingFile))
            return load(embeddingFile, changeExt(embeddingFile.getName()), 1, lowercase);
        else
            return loadText(embeddingFile, 1, lowercase);
    }

    public static WordEmbedding load(File zippedFile, String path, boolean lowercase) throws IOException {
        return load(zippedFile, path, 1, lowercase);
    }

    private static String changeExt(String name) {
        int p = name.lastIndexOf('.');
        name = name.substring(0, p);
        if (!name.endsWith(".vec"))
            return name + ".vec";
        else
            return name;
    }
}
