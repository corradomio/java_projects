package jext.graph.neo4j;

import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Limit;
import jext.graph.Query;
import jext.util.MapUtils;

import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
    WARNING the alias used for nodes and edges are 'n' and 'e'
 */

public class Neo4JQuery implements Query {

    private Neo4JOnlineSession session;
    private String stmt;
    private String alias;
    private Map<String,Object> params;
    private boolean edge;

    private Limit limit;
    private boolean distinct;

    public Neo4JQuery(GraphSession session, String alias, String stmt, Map<String,Object> p) {
        this.session = (Neo4JOnlineSession) session;
        this.stmt = stmt;
        this.alias = alias;
        this.params = p;
        this.limit = null;
    }

    Neo4JQuery edge() {
        edge = true;
        return this;
    }

    @Override
    public Query limit(Limit limit) {
        this.limit = limit;
        return this;
    }

    public Query limit(int limit) {
        this.limit = new Limit(limit);
        return this;
    }

    @Override
    public Query distinct() {
        this.distinct = true;
        return this;
    }


    @Override public long count() { return count(alias); }
    @Override public boolean exists() { return exists(alias); }
    @Override public long delete() { return delete(alias); }
    @Override public String id(){ return id(alias); }
    @Override public GraphIterator<String> ids() { return ids(alias); }
    @Override public Map<String,Object> values() { return values(alias); }
    @Override public GraphIterator<Map<String,Object>> allValues() { return allValues(alias); }


    @Override
    public long count(String alias) {
        String s = String.format("%s RETURN count(%s)", stmt, alias);
        s = setLimit(s);
        return session.count(s, params);
    }

    @Override
    public boolean exists(String alias) {
        return count(alias) > 0;
    }

    @Override
    public long delete(String alias) {
        String s;
        if (!edge)
            s = String.format("%s DETACH DELETE %s", stmt, alias);
        else
            s = String.format("%s DELETE %s", stmt, alias);

        session.execute(s, params);
        return 0;
    }

    @Override
    public String id(String alias) {
        String s = String.format("%s RETURN id(%s)", stmt, alias);
        return session.find(s, params);
    }

    @Override
    public GraphIterator<String> ids(String alias) {
        String s;
        if (distinct)
            s = String.format("%s RETURN DISTINCT id(%s)", stmt, alias);
        else
            s = String.format("%s RETURN id(%s)", stmt, alias);
        s = setLimit(s);
        return session.findAllIter(s, params);
    }

    @Override
    public Map<String,Object> values(String alias) {
        String s = String.format("%s RETURN %s", stmt, alias);
        return session.retrieve(alias, s, params);
    }

    @Override
    public GraphIterator<Map<String,Object>> allValues(String alias) {
        String s;
        if (distinct)
            s = String.format("%s RETURN DISTINCT %s", stmt, alias);
        else
            s = String.format("%s RETURN %s", stmt, alias);
        s = setLimit(s);
        return session.retrieveAllIter(alias, s, params, edge);
    }

    @Override
    public GraphIterator<Map<String,Object>> result() {
        String s = setLimit(stmt);
        return session.resultIter(alias, s, params, edge);
    }

    private static Object asMap(Object value) {
        if (value == null)
            return null;
        if (value instanceof Node) {
            return Neo4JOnlineSession.toNodeMap((Node)value);
        }
        if (value instanceof List)
            return ((List)value).stream()
                .map(v -> asMap(v))
                .collect(Collectors.toList());
        else
            return value;
    }

    @Override
    public GraphIterator<Map<String,Object>> result(String alias) {
        String s = setLimit(stmt);

        GraphIterator<Map<String,Object>> git = session.resultIter(alias, s, params, edge);

        return new GraphIterator<Map<String,Object>>() {

            @Override
            public boolean hasNext() {
                return git.hasNext();
            }

            @Override
            public Map<String,Object> next() {
                Map<String,Object> tmp = git.next();
                Map<String,Object> nv = Neo4JOnlineSession.toNodeMap(MapUtils.get(tmp, alias));

                for (String key : tmp.keySet()) {
                    if (key.equals(alias))
                        continue;

                    Object value = asMap(tmp.get(key));
                    nv.put(key, value);
                }

                return session.postProcess(nv);
            }

            @Override
            public List<Map<String,Object>> toList() {
                List<Map<String,Object>> l = new ArrayList<>();
                while(hasNext())
                    l.add(next());
                return l;
            }

            @Override
            public Set<Map<String,Object>> toSet() {
                Set<Map<String,Object>> s = new HashSet<>();
                while(hasNext())
                    s.add(next());
                return s;
            }
        };
    }

    private String setLimit(String s) {
        if (limit == null || limit.isAll())
            return s;

        if (limit.start == 0)
            s = String.format("%s LIMIT %d", s, limit.count);
        else
            s = String.format("%s SKIP %d LIMIT %d", s, limit.start, limit.count);
        return s;
    }
}