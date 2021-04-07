package jext.jgrapht.nio.neo4j;

import org.neo4j.driver.Record;
import org.neo4j.driver.Result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Neo4JResultSet<T> implements GraphIterator<T> {

    private Result result;

    Neo4JResultSet(Result result) {
        this.result = result;
    }

    @Override
    public boolean hasNext() {
        return result.hasNext();
    }

    @Override
    public T next() {
        return compose(result.next());
    }

    @Override
    public List<T> toList() {
        List<T> l = new ArrayList<>();
        while(hasNext())
            l.add(next());
        return l;
    }

    @Override
    public Set<T> toSet() {
        Set<T> s = new HashSet<>();
        while(hasNext())
            s.add(next());
        return s;
    }

    protected T compose(Record r) {
        return null;
    }

}
