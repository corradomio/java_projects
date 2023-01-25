package org.hls.check;

import jext.logging.v2.Logger;
import jext.text.embedding.FastTextEmbedding;
import jext.text.embedding.GloVeEmbedding;
import jext.text.embedding.Word2VecEmbedding;
import jext.text.embedding.WordEmbedding;

import java.io.File;
import java.io.IOException;

public class LoadEmbeddingVectors {

    static void printMinMax(WordEmbedding we) {
        System.out.printf(">>> MinMax: %f,%f\n", we.dimensionsMinMax()[0], we.dimensionsMinMax()[1]);
    }

    public static void main(String[] args) throws IOException {
        Logger.configure();
        WordEmbedding we;

        we = Word2VecEmbedding.load(new File("E:\\Datasets\\NLP\\word2vec\\English CoNLL17 corpus.zip"));
        printMinMax(we);

        we = GloVeEmbedding.load(new File("E:\\Datasets\\NLP\\GloVe\\glove.6B.zip"), 50);
        printMinMax(we);

        we = FastTextEmbedding.load(new File("E:\\Datasets\\NLP\\fastText\\crawl-300d-2M-subword.zip"));
        printMinMax(we);

        we = FastTextEmbedding.load(new File("E:\\Datasets\\NLP\\fastText\\wiki-news-300d-1M-subword.vec"));
        printMinMax(we);

    }
}
