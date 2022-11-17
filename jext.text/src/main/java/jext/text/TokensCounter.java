package jext.text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final boolean lowercase;
    private final Map<String, Count> counters = new HashMap<>();
    private int documents;
    private int count;

    public TokensCounter() {
        lowercase = true;
    }

    public void add(String token) {
        add(token, 1);
    }

    public void add(String token, int count) {
        int index;
        if (lowercase)
            token = token.toLowerCase();
        if(!counters.containsKey(token)) {
            counters.put(token, new Count(token));
        }
        counters.get(token).count += count;
    }

    public void add(TokensCounter counter) {
        this.documents += 1;
        for (Count that : counter.counters.values()) {
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
    }

    public double tfidf(Count c) {
        return ((double)c.count)/this.count * Math.log(((double)this.documents)/c.documents);
    }

    public int documents() {
        return documents;
    }

    public List<Count> counters() {
       return counters(0, 0);
    }

    public List<Count> counters(int toklen, int tokcount) {
        return counters.values().stream()
            .filter(c -> c.isValid(toklen, tokcount))
            .sorted((c1, c2) -> (int)(c2.count - c1.count))
            .collect(Collectors.toList());
    }
}
