package org.hls.check;

import jext.logging.Logger;
import jext.text.embedding.DocumentEmbedding;
import jext.text.tokens.TokensCounter;
import jext.text.tokens.TokensScanner;
import jext.text.embedding.GloVeEmbedding;
import jext.text.embedding.WordEmbedding;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Logger.configure();

        TokensScanner toks = TokensScanner.scan(new File(
                "D:\\Dropbox\\Software\\SPLGroup\\cocome\\cocome-maven-project"
        ), ".java");

        TokensCounter gtc = toks.getGlobalCounter();
        gtc.tokens().forEach(token -> {
            gtc.tfidf(token);
        });

        WordEmbedding wemb = GloVeEmbedding.load(new File("glove.6B.50d.zip"));
        // WordEmbedding wemb = GloVeEmbedding.load(new File("glove.6B.50d.txt"));
        // WordEmbedding wemb = GloVeEmbedding.load(new File("glove.6B.zip"), 50);

        DocumentEmbedding.computeEmbedding(toks, wemb);

        System.out.println("done");
    }

    public static void main1(String[] args) throws IOException {
        Logger.configure();
        // TokensScanner.scan(new File(
        //     // "D:\\Projects.github.2\\elasticsearch-8.5.1"
        //     // "D:\\Projects.github.2\\deeplearning4j-1.0.0-M2.1"
        //     // "D:\\Projects.github.2\\hibernate-orm-5.2.0"
        //         "D:\\Projects\\Java\\elasticsearch-8.6.0"
        // ), ".java");

        TokensScanner.scan(new File(
                "D:\\Dropbox\\Software\\SPLGroup\\cocome\\cocome-maven-project"
        ), ".java");
        System.out.println("done");

        // TokensCounter tc = TokensScanner.load(new File("D:\\Projects\\elasticsearch-8.5.1\\.tokens\\counts.csv"));
        //
        // double max = 0;
        // TokensCounter.Count maxc = null;
        //
        // for (TokensCounter.Count c : tc.counters()) {
        //     double tfidf = tc.tfidf(c);
        //     if (tfidf > max) {
        //         max = tfidf;
        //         maxc = c;
        //     }
        //     System.out.printf("%s: %f\n", c.token, tc.tfidf(c));
        // }
        //
        // System.out.printf("%s: %f\n", maxc.token, max);
    }
}
