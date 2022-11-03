package jext.graph.neo4j;

import jext.graph.GraphIterator;
import jext.graph.Limit;
import jext.graph.Query;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmptyQuery implements Query {

    public static final EmptyQuery EMPTY = new EmptyQuery();

    public static EmptyQuery empty() { return EMPTY; }

    private static class EmptyIterator<T> implements GraphIterator<T> {

        @Override
        public List<T> toList() {
            return Collections.emptyList();
        }

        @Override
        public Set<T> toSet() {
            return Collections.emptySet();
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

    @Override
    public Query limit(Limit limit) {
        return this;
    }

    @Override
    public Query limit(long limit) {
        return this;
    }

    @Override
    public Query distinct() {
        return this;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long count(String alias) {
        return 0;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean exists(String alias) {
        return false;
    }

    @Override
    public long delete() {
        return 0;
    }

    @Override
    public long delete(String alias) {
        return 0;
    }

    @Nullable
    @Override
    public String id() {
        return null;
    }

    @Nullable
    @Override
    public String id(String alias) {
        return null;
    }

    @Nullable
    @Override
    public Map<String, Object> values() {
        return null;
    }

    @Nullable
    @Override
    public Map<String, Object> values(String alias) {
        return null;
    }

    @Override
    public GraphIterator<String> ids() {
        return null;
    }

    @Override
    public GraphIterator<String> ids(String alias) {
        return new EmptyIterator<>();
    }

    @Override
    public GraphIterator<Map<String, Object>> allValues() {
        return new EmptyIterator<>();
    }

    @Override
    public GraphIterator<Map<String, Object>> allValues(String alias) {
        return new EmptyIterator<>();
    }

    @Override
    public GraphIterator<Map<String, Object>> result() {
        return new EmptyIterator<>();
    }

    @Override
    public GraphIterator<Map<String, Object>> result(String alias) {
        return new EmptyIterator<>();
    }
}
