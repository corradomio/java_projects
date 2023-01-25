package jext.text.embedding;

import jext.logging.Logger;

import java.io.File;
import java.io.IOException;

public class Word2VecEmbedding extends WordEmbeddingLoader {

    static {
        logger = Logger.getLogger(Word2VecEmbedding.class);
    }

    public static Word2VecEmbedding loader() {
        return new Word2VecEmbedding();
    }

    public static WordEmbedding load(File embeddingFile) throws IOException {
        return load(embeddingFile, "model.txt", 1, false);
    }

}
