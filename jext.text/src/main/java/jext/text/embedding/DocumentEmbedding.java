package jext.text.embedding;

import jext.text.tokens.TokensCounter;
import jext.text.tokens.TokensScanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentEmbedding {

    public static void computeEmbedding(TokensScanner toks, WordEmbedding wemb) throws IOException {
        Map<String, double[]> embeddings = calculate(toks, wemb);
        save(embeddings, toks.getOutputDirectory());
    }

    private static Map<String, double[]> calculate(TokensScanner toks, WordEmbedding wemb) {
        TokensCounter gtc = toks.getGlobalCounter();
        List<TokensCounter> ftcs = toks.getFileCounters();
        Map<String, double[]> embeddings = new HashMap<>();

        for (TokensCounter ftc : ftcs) {
            String path = ftc.path();

            double[] vec = wemb.embedding(ftc.words(), gtc);
            embeddings.put(path, vec);
        }

        return embeddings;
    }

    private static void save( Map<String, double[]> embeddings, File outputFile) throws IOException {
        if (outputFile == null)
            return;

        File saveFile = new File(outputFile, "embeddings.txt");
        try(Writer wrt = new FileWriter(saveFile)) {
            for(String path : embeddings.keySet()) {
                double[] vec = embeddings.get(path);

                wrt.write("\"");
                wrt.write(path);
                wrt.write("\" ");
                for(int i=0; i<vec.length; ++i) {
                    wrt.write(" ");
                    wrt.write(Double.toString(vec[i]));
                }
                wrt.write("\n");
            }
        }
    }
}
