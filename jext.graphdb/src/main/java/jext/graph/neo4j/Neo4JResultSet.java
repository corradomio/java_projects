package jext.graph.neo4j;

import jext.graph.GraphIterator;
import jext.util.HashSet;
import jext.util.Set;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.List;


public class Neo4JResultSet<T> implements GraphIterator<T> {

    private StatementResult result;

    Neo4JResultSet(StatementResult result) {
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
        Set<T> s = new HashSet();
        while(hasNext())
            s.add(next());
        return s;
    }

    protected T compose(Record r) {
        return null;
    }

}
