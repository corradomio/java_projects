package jext.graph.neo4j;

import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Limit;
import jext.graph.Query;
import jext.util.MapUtils;
import jext.util.Parameters;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static jext.graph.neo4j.Neo4JOnlineSession.E;
import static jext.graph.neo4j.WhereFormatter.sblock;

/*
    WARNING the alias used for nodes and edges are 'n' and 'e'
 */

public class Neo4JQuery implements Query {

    private final Neo4JOnlineSession session;
    private final String stmt;
    private final String alias;
    private final Map<String,Object> params;
    private final Map<String,Object> assign;
    private boolean edge;

    private Limit limit;
    private boolean distinct;

    public Neo4JQuery(GraphSession session, String alias, String stmt, Map<String,Object> p) {
        this.session = (Neo4JOnlineSession) session;
        this.stmt = stmt;
        this.alias = alias;
        this.params = p;
        this.assign = new HashMap<>();
        this.limit = null;
    }

    Neo4JQuery edge() {
        edge = true;
        return this;
    }

    @Override
    public Query assign(Map<String,Object> values) {
        assign.putAll(values);
        return this;
    }

    @Override
    public Query limit(Limit limit) {
        this.limit = limit;
        return this;
    }

    public Query limit(long limit) {
        this.limit = new Limit(limit);
        return this;
    }

    @Override
    public Query distinct() {
        this.distinct = true;
        return this;
    }

    @Override
    public long execute() {
        String s = String.format("%1$s RETURN count(%2$s)", setAssign(stmt), alias);
        return session.execute(s, params);
    }


    @Override
    public long count() {
        String s = String.format("%s RETURN count(%s)", stmt, alias);
        s = setLimit(s);
        return session.count(s, params);
    }

    @Override
    public boolean exists() {
        return count() > 0;
    }

    @Override
    public long delete() {
        String s;
        if (!edge)
            s = String.format("%s DETACH DELETE %s", stmt, alias);
        else
            s = String.format("%s DELETE %s", stmt, alias);

        // LIMIT not supported

        return session.execute(s, params);
    }

    @Override
    public String id() {
        String s = String.format("%s RETURN id(%s)", stmt, alias);
        return session.find(s, params);
    }

    @Override
    public GraphIterator<String> ids() {
        String s;
        if (distinct)
            s = String.format("%s RETURN DISTINCT id(%s)", stmt, alias);
        else
            s = String.format("%s RETURN id(%s)", stmt, alias);
        s = setLimit(s);
        return session.findAllIter(s, params);
    }

    @Override
    public Map<String,Object> values() {
        String s = String.format("%s RETURN %s", stmt, alias);
        s = setLimit(s);
        return session.retrieve(alias, s, params);
    }

    @Override
    public GraphIterator<Map<String,Object>> allValues() {
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

    // @Override
    // public GraphIterator<Map<String,Object>> result() {
    //     String s = setLimit(stmt);
    //
    //     GraphIterator<Map<String,Object>> git = session.resultIter(alias, s, params, edge);
    //
    //     return new GraphIterator<Map<String,Object>>() {
    //
    //         @Override
    //         public boolean hasNext() {
    //             return git.hasNext();
    //         }
    //
    //         @Override
    //         public Map<String,Object> next() {
    //             Map<String,Object> tmp = git.next();
    //             Map<String,Object> nv = Neo4JOnlineSession.toNodeMap(MapUtils.get(tmp, alias));
    //
    //             for (String key : tmp.keySet()) {
    //                 if (key.equals(alias))
    //                     continue;
    //
    //                 Object value = asMap(tmp.get(key));
    //                 nv.put(key, value);
    //             }
    //
    //             return session.postProcess(nv);
    //         }
    //
    //         @Override
    //         public List<Map<String,Object>> toList() {
    //             List<Map<String,Object>> l = new ArrayList<>();
    //             while(hasNext())
    //                 l.add(next());
    //             return l;
    //         }
    //
    //         @Override
    //         public Set<Map<String,Object>> toSet() {
    //             Set<Map<String,Object>> s = new HashSet<>();
    //             while(hasNext())
    //                 s.add(next());
    //             return s;
    //         }
    //
    //     };
    // }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private String setLimit(String s) {
        if (limit == null || limit.isAll())
            return s;

        if (limit.start == 0)
            s = String.format("%s LIMIT %d", s, limit.count);
        else
            s = String.format("%s SKIP %d LIMIT %d", s, limit.start, limit.count);
        return s;
    }

    private String setAssign(String s) {
        if (assign.isEmpty())
            return s;

        String sblock = sblock(alias, assign, false);

        Parameters nparams = Parameters.params().add(params).add(alias, assign);
        params.clear();
        params.putAll(nparams);
        return String.format("%s%s", s, sblock);
    }
}