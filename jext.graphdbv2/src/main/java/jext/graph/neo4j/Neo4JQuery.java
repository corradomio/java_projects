package jext.graph.neo4j;

import jext.graph.GraphIterator;
import jext.graph.GraphSession;
import jext.graph.Limit;
import jext.graph.Query;
import jext.util.Parameters;

import java.util.HashMap;
import java.util.Map;

import static jext.graph.neo4j.WhereFormatter.sblock;

/*
    WARNING the alias used for nodes and edges are 'n' and 'e'
 */

public class Neo4JQuery implements Query {

    private final Neo4JOnlineSession session;
    private final String stmt;
    private final String alias;
    private final Map<String, Object> params;
    private final Map<String, Object> update;
    private boolean edge;

    private Limit limit;
    private boolean distinct;

    public Neo4JQuery(GraphSession session, String alias, String stmt, Map<String, Object> p) {
        this.session = (Neo4JOnlineSession) session;
        this.stmt = stmt;
        this.alias = alias;
        this.params = p;
        this.update = new HashMap<>();
        this.limit = null;
    }

    Neo4JQuery edge() {
        edge = true;
        return this;
    }

    @Override
    public long update(Map<String, Object> values) {
        update.putAll(values);
        return execute();
    }

    public Query limit(long limit) {
        this.limit = new Limit(0, limit);
        return this;
    }

    public Query limit(long start, long limit) {
        this.limit = new Limit(start, limit);
        return this;
    }

    @Override
    public Query distinct() {
        this.distinct = true;
        return this;
    }

    @Override
    public long execute() {
        String s = String.format("%1$s RETURN count(%2$s)", setUpdate(stmt), alias);
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
    public Map<String, Object> values() {
        String s = String.format("%s RETURN %s", stmt, alias);
        s = setLimit(s);
        return session.retrieve(s, params);
    }

    @Override
    public GraphIterator<Map<String, Object>> allValues() {
        String s;
        if (distinct)
            s = String.format("%s RETURN DISTINCT %s", stmt, alias);
        else
            s = String.format("%s RETURN %s", stmt, alias);
        s = setLimit(s);
        return session.retrieveAllIter(s, params);
    }

    @Override
    public GraphIterator<Map<String, Object>> result() {
        String s = setLimit(stmt);
        return session.retrieveAllIter(s, params);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private String setLimit(String s) {
        if (limit == null || limit.isAll())
            return s;

        if (limit.start == 0)
            s = String.format("%s LIMIT %d", s, limit.limit);
        else
            s = String.format("%s SKIP %d LIMIT %d", s, limit.start, limit.limit);
        return s;
    }

    private String setUpdate(String s) {
        if (update.isEmpty())
            return s;

        String sblock = sblock(alias, update, false);

        Parameters nparams = Parameters.params().add(params).prefix(alias, update);
        params.clear();
        params.putAll(nparams);
        return String.format("%s%s", s, sblock);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}