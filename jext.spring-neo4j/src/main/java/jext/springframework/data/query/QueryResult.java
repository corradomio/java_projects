package jext.springframework.data.query;

import org.neo4j.ogm.model.Result;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public class QueryResult {

    private Result result;

    public QueryResult(Result result) {
        this.result = result;
    }

    public Iterable<Map<String, Object>> queryResults() {
        return result.queryResults();
    }

    public Iterator<Map<String, Object>> iterator() {
        return result.iterator();
    }

    public void forEach(Consumer<? super Map<String, Object>> action) {
        result.forEach(action);
    }

    public Spliterator<Map<String, Object>> spliterator() {
        return result.spliterator();
    }

}
