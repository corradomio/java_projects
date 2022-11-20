package org.hls.check;

import jext.text.TokensCounter;
import jext.text.TokensScanner;

import java.io.File;
import java.io.IOException;

public class TokenizeFiles {

    public static void main(String[] args) throws IOException {
        TokensScanner.scan(new File(
            // "D:\\Projects.github.2\\elasticsearch-8.5.1"
            // "D:\\Projects.github.2\\deeplearning4j-1.0.0-M2.1"
            "D:\\Projects.github.2\\hibernate-orm-5.2.0"
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
