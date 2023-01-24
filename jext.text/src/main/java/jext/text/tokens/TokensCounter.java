package jext.text.tokens;

import jext.util.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TokensCounter {

    public static class Count {
        public String token;
        public int documents;
        public long count;

        Count(String token) {
            this.token = token;
            this.documents = 1;
        }

        boolean isValid(int tlen, int tcount) {
            return count >= tcount && token.length() >= tlen;
        }
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String path;
    private final boolean lowercase;
    private final List<TokensCounter> processed = new ArrayList<>();
    private final Map<String, Count> counters = new HashMap<>();
    private final List<String> tokens = new ArrayList<>();
    private int documents;
    private int count;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public TokensCounter() {
        path = null;
        lowercase = true;
    }

    public TokensCounter(String path) {
        this.path = path;
        this.lowercase = true;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Relative path of the processed file
     */
    public String path() {
        return path;
    }

    /**
     * Number of processed file (used in the global counter)
     */
    public int size() {
        return processed.size();
    }

    /**
     * Number of documents processed
     */
    public int documents() {
        return documents;
    }

    /**
     * Number of words/tokens processed
     */
    public int count() {
        return count;
    }

    /**
     * Unique tokens
     */
    public Set<String> tokens() {
        return counters.keySet();
    }

    /**
     * Sequence of tokens
     */
    public List<String> words() {
        return tokens;
    }

    public List<TokensCounter> fileCounters() {
        return processed;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * List of counters for all tokens
     */
    public List<Count> counters() {
        return counters(0, 0);
    }

    /**
     * List of counters for tokens satisfying conditions
     *
     *      token_length >= toklen
     *      token_count  >= tokcount
     *
     * @param toklen minimum token length
     * @param tokcount minimum token count
     * @return list of counters
     */
    public List<Count> counters(int toklen, int tokcount) {
        return counters.values().stream()
                .filter(c -> c.isValid(toklen, tokcount))
                .sorted((c1, c2) -> (int)(c2.count - c1.count))
                .collect(Collectors.toList());
    }

    /**
     * Counter for the token
     */
    public Count count(String token) {
        if (!counters.containsKey(token))
            return new Count(token);
        else
            return counters.get(token);
    }

    /**
     * tfidf of the token
     */
    public double tfidf(String token) {
        Count c = count(token);
        double v = tfidf(c);
        if (v < 0 || v > 1 || Double.isNaN(v) || Double.isInfinite(v))
            Assert.nop();
        return v;
    }

    /**
     * tfidf for the specific counter
     */
    public double tfidf(Count c) {
        // note: check for the following cases
        //  1) the word doesn't exists (documents = 0, count = 0)
        //  2) no file has been processed (this.count == 0)
        if (this.count == 0 || c.count == 0) return 0;
        return (0.+c.count)/(0.+this.count) * Math.log((0.+this.documents)/(0.+c.documents));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void add(String token) {
        add(token, 1, 0);
    }

    public void add(String token, int count) {
        add(token, count, 0);
    }

    public void add(String token, int c, int docs) {
        if (lowercase)
            token = token.toLowerCase();
        if(!counters.containsKey(token))
            counters.put(token, new Count(token));

        counters.get(token).count += c;
        counters.get(token).documents += docs;
        count += c;

        for(int i=0; i<c; ++i)
            tokens.add(token);
    }

    public void add(TokensCounter tokc) {
        this.processed.add(tokc);
        this.documents += 1;

        for (Count that : tokc.counters.values()) {
            if(!counters.containsKey(that.token)) {
                counters.put(that.token, that);
            }
            else {
                Count self = counters.get(that.token);
                self.documents += that.documents;
                self.count += that.count;
            }
            this.count += that.count;
        }

        this.tokens.addAll(tokc.tokens);
    }

    public TokensCounter set(int count, int documents) {
        Assert.check(this.count == count, "Invalid count");
        this.documents = documents;
        return this;
    }

}
