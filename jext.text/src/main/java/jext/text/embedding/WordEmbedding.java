package jext.text.embedding;

import jext.text.tokens.TokensCounter;
import jext.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

/**
 *
 */
public class WordEmbedding {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final Map<String, double[]> embeddings;
    private double[] ZERO = new double[0];
    private double[][] E_MIN_MAX = new double[0][0];
    private double[] D_MIN_MAX = new double[2];
    private int length;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public WordEmbedding() {
        this.embeddings = new HashMap<>();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int length() {
        return length;
    }

    public int size() {
        return embeddings.size();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void put(String word, double[] embedding) {
        if (length == 0) {
            length = embedding.length;
            ZERO = new double[length];
            E_MIN_MAX = new double[2][];
            E_MIN_MAX[0] = new double[length];
            E_MIN_MAX[1] = new double[length];
        }

        if (embedding.length != length)
            Assert.check(this.length == embedding.length, "Embedding length for %s is not compatibe: %d != %d",
                    word, embedding.length, length);

        this.embeddings.put(word, embedding);

        for(int i=0; i<length; ++i) {
            double v = embedding[i];
            if (v < E_MIN_MAX[0][i]) E_MIN_MAX[0][i] = v;
            if (v > E_MIN_MAX[1][i]) E_MIN_MAX[1][i] = v;
            if (v < D_MIN_MAX[0]) D_MIN_MAX[0] = v;
            if (v > D_MIN_MAX[1]) D_MIN_MAX[1] = v;
        }
    }

    public double[/*min,max*/] dimensionsMinMax() {
        return D_MIN_MAX;
    }

    public double[/*min,max*/][/*length*/] embeddingMinMax() {
        return E_MIN_MAX;
    }

    public double[] embedding(String word) {
        return normOne(this.embeddings.getOrDefault(word, ZERO), true);
    }

    public double[] embedding(List<String> words) {
        double[] emb = new double[length];
        for(String word: words) {
            addTo(emb, 1., embedding(word));
        }
        return normOne(emb, false);
    }

    public double[] embedding(List<String> words, TokensCounter gtc) {
        double[] emb = new double[length];
        for(String word: words) {
            addTo(emb, gtc.tfidf(word), embedding(word));
        }
        return normOne(emb, false);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private static final double EPS = 10^-6;

    private void addTo(double[] sum, double weight, double[] v) {

        for (int i=0; i<length; ++i)
            sum[i] += weight*v[i];
    }

    private double[] normOne(double[] v, boolean copy) {
        double sum = 0;
        for(int i=0; i<length; ++i)
            sum += v[i];

        if (sum == 0)
            return ZERO;

        double[] norm;
        if (copy)
            norm = new double[length];
        else
            norm = v;

        for (int i=0; i<length; ++i)
            norm[i] = v[i]/sum;
        return norm;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
