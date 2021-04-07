package jext.jgrapht.nio.neo4j;

import jext.util.Parameters;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Relationship;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Neo4JSession {
    private static final String N = "n";
    private static final String E = "e";
    private static final String ID = "$id";
    private static final String TYPE = "$type";

    public enum Direction {
        Any,
        Input,
        Output
    }

    private enum WhereType {
        NONE,
        WHERE,
        AND
    }

    private Session session;

    public Neo4JSession(Session session) {
        this.session = session;
    }

    public Neo4JQuery queryEdges(
        String edgeType,
        String fromType, Map<String, Object> fromProps,
        String toType,   Map<String, Object> toProps,
        Map<String, Object> edgeProps) {

        String fblock = pblock(fromProps);
        String eblock = eblock(E, edgeType, Direction.Output, false, edgeProps);
        String tblock = pblock(toProps);

        Parameters allProps = new Parameters();
        allProps.putAll(fromProps);
        allProps.putAll(edgeProps);
        allProps.putAll(toProps);

        String s = String.format("MATCH (from:%s %s) %s (to:%s %s) RETURN id(from) AS idfrom, id(to) AS idto, e AS edge",
            fromType,
            fblock,
            eblock,
            toType,
            tblock
        );

        if(edgeType.equals("uses"))
            s = s +", e.uses as uses";

        return new Neo4JQuery(this, N, s, allProps);
    }

    /**
     * Create the block:
     *
     *      { param: $param, ... }
     *
     */
    private static String pblock(Map<String, Object> params) {
        return pblock("", params);
    }

    private static String pblock(String prefix, Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder("{");
        for(String param : params.keySet()) {

            if (params.get(param) == null)
                continue;
            if (params.get(param) instanceof Collection)
                continue;

            if (sb.length() > 1)
                sb.append(",");
            sb.append(param).append(": $").append(prefix).append(param);
        }
        return sb.append("}").toString();
    }

    /**
     * Create the block:
     *
     *      { param: value, ... }
     *
     */
    private static String tblock(Map<String, Object> params){
        if (params == null || params.size() == 0)
            return "{}";

        StringBuilder sb = new StringBuilder("{");
        for(String param : params.keySet()) {
            if (sb.length() > 1)
                sb.append(",");

            sb.append(param).append(": ");

            if(params.get(param) instanceof String || params.get(param) instanceof Character)
                sb.append("\"");

            sb.append(params.get(param));

            if(params.get(param) instanceof String || params.get(param) instanceof Character)
                sb.append("\"");
        }
        return sb.append("}").toString();
    }

    /**
     * Create the block
     *
     *      SET n.param = $param, ...
     *
     */
    private static String sblock(String alias, Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder("SET ");
        for(String param : params.keySet()) {
            if (sb.length() > 4)
                sb.append(",");
            sb.append(String.format("%s.%s = $%s%s", alias, param, alias, param));
        }
        return sb.toString();
    }

    /**
     * Create the blocks
     *
     *      WHERE n.param = $param AND ...
     *
     * or
     *
     *      AND  n.param = $param AND ...
     *
     * @param params parameters
     * @param where if to include the WHERE keyword or AND
     * @return
     */
    private static String wblock(String alias, Map<String, Object> params, WhereType where) {
        if (params == null || params.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();

        for (String key : params.keySet()) {
            Object value = params.get(key);

            if (value != null && !(value instanceof Collection))
                continue;

            if (sb.length() > 0) sb.append(" AND ");

            if (value != null)
                sb.append(alias).append(".").append(key).append(" in $").append(alias).append(key);
            else
                sb.append("EXISTS(").append(alias).append(".").append(key).append(")");
        }

        if (sb.length() == 0)
            return sb.toString();

        switch (where) {
            case NONE: return sb.toString();
            case AND: return sb.insert(0, " AND ").toString();
            case WHERE: return sb.insert(0, " WHERE ").toString();
            default: return sb.toString();
        }
    }

    private static String wblock(String alias, Map<String, Object> wparams, boolean where) {
        if (wparams == null || wparams.size() == 0)
            return "";

        int cond = 0;
        StringBuilder sb = new StringBuilder();
        if(where)
            sb.append("WHERE ");
        else
            sb.append(" AND ");
        for(String param : wparams.keySet()) {
            if (cond > 0)
                sb.append(" AND ");

            if (wparams.get(param) instanceof Collection)
                sb.append(String.format("%s.%s in $%s%s", alias, param, alias, param));
            else
                sb.append(String.format("%s.%s = $%s%s", alias, param, alias, param));
            ++cond;
        }

        return sb.toString();
    }

    /**
     * Create the edge syntax block
     *
     *       -[e:edgeType {...}]-         Any
     *       -[e:edgeType {...}]->        Output
     *      <-[e:edgeType {...}]-         Input
     *
     */
    private static String eblock(String alias, String edgeType, Direction direction, boolean recursive,
                                 Map<String, Object> edgeProps) {
        String eblock;

        switch (direction) {
            case Input:
                eblock = "<-[%s%s%s %s]-";
                break;
            case Output:
                eblock = "-[%s%s%s %s]->";
                break;
            default:
                eblock = "-[%s%s%s %s]-";
                break;
        }

        String pblock = pblock(alias, edgeProps);
        String etype = edgeType != null ? ":" + edgeType : "";

        return String.format(eblock,
            alias,
            etype,
            recursive ? "*" : "",
            pblock);
    }

    // ----------------------------------------------------------------------

    protected GraphIterator<Map<String, Object>> resultIter(String alias, String s, Map<String, Object> params, boolean edge) {
        // logStmt(s, params);

        try {
            Result result = session.run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                protected Map<String, Object> compose(Record r) {
                    return toResultMap(alias, r);
                }
            };
        }
        catch (Throwable t) {
            // logStmt(s, params, t);
            throw t;
        }
    }

    protected GraphIterator<Map<String, Object>> retrieveAllIter(String alias, String s, Map<String, Object> params, boolean edge) {
        // logStmt(s, params);

        try {
            Result result = session.run(s, params);

            return new Neo4JResultSet<Map<String, Object>>(result) {
                protected Map<String, Object> compose(Record r) {
                    return toEdgeMap(alias, r);
                }
            };
        }
        catch (Throwable t) {
            // logStmt(s, params, t);
            throw t;
        }
    }

    private static Map<String, Object> toEdgeMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

        if (r == null)
            return body;

        Relationship edge = r.get(alias).asRelationship();

        // for(String key : edge.keys()) {
        //     Object value = edge.get(key).asObject();
        //     body.put(key, value);
        // }

        // put_ properties
        body.putAll(edge.asMap());

        // put_ $id
        // put_ $type

        body.put(ID, toId(edge.id()));
        body.put(TYPE, edge.type());

        return body;
    }

    private static Map<String, Object> toResultMap(String alias, Record r) {
        Map<String, Object> body = new HashMap<>();

        if (r == null)
            return body;

        body.putAll(r.asMap());

        Set<String> keys = new HashSet<>(body.keySet());
        for(String k : keys) {
            Object v = body.get(k);
            if (v instanceof Relationship)
                body.put(k, toEdgeMap(k, r));
        }

        return body;
    }

    private static Long asId(String id) {
        return Long.valueOf(id);
    }

    private static String toId(long id) {
        return Long.toString(id);
    }

    private static Set<Long> asIds(Collection<String> ids) {
        return ids
            .stream()
            .map(Long::valueOf)
            .collect(Collectors.toSet());
    }

}
