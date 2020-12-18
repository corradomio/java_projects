package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.query.Limit;
import jext.springframework.data.query.Query;

import java.io.Serializable;
import java.util.Map;

public class PartialQuery<T, ID extends Serializable> implements Query<T> {

    private static final String N = "n";

    private NamedNeo4JRepository<T, ID> repository;
    private String query;
    private Map<String, ?> params;
    private Limit limit = new Limit(0);
    private boolean distinct;
    private boolean edge;

    PartialQuery(NamedNeo4JRepository<T, ID> r, String query, Map<String, ?> p) {
        this.repository = r;
        this.query = query;
        this.params = p;
    }

    @Override
    public Query<T> limit(Limit limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public Query<T> limit(int limit) {
        this.limit = new Limit(limit);
        return this;
    }

    @Override
    public Query<T> distinct() {
        this.distinct = true;
        return this;
    }

    @Override
    public Query<T> edge() {
        this.edge = true;
        return this;
    }

    // ----------------------------------------------------------------------
    // count        count(n)
    // exists       count(n) > 0
    // delete       detach delete n
    // id           id(n) limit 1
    // ids          id(n)

    @Override
    public long count() {
        return count(N);
    }

    @Override
    public long count(String alias) {
        String s = String.format("%s RETURN count(%s)", query, alias);
        return repository.countUsingCypher(s, params);
    }

    @Override
    public boolean exists() {
        return exists(N);
    }

    @Override
    public boolean exists(String alias) {
        String s = String.format("%s RETURN count(%s)", query, alias);
        return repository.countUsingCypher(s, params) > 0;
    }

    @Override
    public long delete() {
        return delete(N);
    }

    @Override
    public long delete(String alias) {
        String s;
        if (!edge)
            s = String.format("%s DETACH DELETE %s", query, alias);
        else
            s = String.format("%s DELETE %s", query, alias);

        return repository.deleteUsingCypher(s, params);
    }

    @Override
    public String id() {
        return id(N);
    }

    @Override
    public String id(String alias) {
        String s = String.format("%s RETURN id(%s)", query, alias);
        return repository.findOneIdUsingCypher(s, params);
    }

    // @Override
    // public Map<String, Object> values() {
    //     return values(N);
    // }
    //
    // @Override
    // public Map<String, Object> values(String alias) {
    //     throw new UnsupportedOperationException();
    // }

    @Override
    public Iterable<String> ids() {
        return ids(N);
    }

    @Override
    public Iterable<String> ids(String alias) {
        String s;
        if (distinct)
            s = String.format("%s RETURN DISTINCT id(%s)", query, alias);
        else
            s = String.format("%s RETURN id(%s)", query, alias);
        s = setLimit(s);
        return repository.findAllIdUsingCypher(s, params);
    }

    @Override
    public Iterable<T> allValues() {
        return allValues(N);
    }

    @Override
    public Iterable<T> allValues(String alias) {
        String s;
        if (distinct)
            s = String.format("%s RETURN DISTINCT %s", query, alias);
        else
            s = String.format("%s RETURN %s", query, alias);
        s = setLimit(s);
        return repository.findAllUsingCypher(s, params);
    }

    @Override
    public Iterable<T> result() {
        throw new UnsupportedOperationException();
    }

    private String setLimit(String query) {
        if (limit == null || limit.isAll())
            return query;
        else if (limit.start == 0)
            return String.format("%s LIMIT %d", query, limit.count);
        else
            return String.format("%s LIMIT %d %d", query, limit.start, limit.count);
    }

}
