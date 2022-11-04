package jext.graph.neo4j;

import jext.graph.Direction;
import jext.graph.Op;
import jext.graph.Param;
import jext.graph.Value;
import jext.util.Assert;
import jext.util.Parameters;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import static jext.graph.neo4j.Neo4JOnlineSession.AND_BLOCK;
import static jext.graph.neo4j.Neo4JOnlineSession.END_BLOCK;
import static jext.graph.neo4j.Neo4JOnlineSession.N;
import static jext.graph.neo4j.Neo4JOnlineSession.NONE;
import static jext.graph.neo4j.Neo4JOnlineSession.WHERE_BLOCK;

public class WhereFormatter {

    private static final String NONE = "";
    private static final String ID = "id";
    private static final String LABEL = "$label";
    private static final String REVISION = "revision";
    private static final String IN_REVISION = "inRevision";
    private static final String FALSE = "false";
    private static final String TRUE = "true";

    private static final String DEGREE = "degree";
    private static final String IN_DEGREE = "indegree";
    private static final String OUT_DEGREE = "outdegree";

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------
    // cypher template:
    //
    //      MATCH (n:label {slot: $param, ...})
    //      WHERE n.slot        OP $param
    //         OR n.slot[index] OP $param
    //         OR sslot(n),     OP $param
    //      SET   n.slot = $param,
    //            n.slot = function(n.slot, index, $param),
    //            n.slot = function(n.slot, $param)
    //
    //  There are some 'special' (s)slots:
    //
    //      n.$label     ==  labels(n)[0]
    //      n.$degree    ==  apoc.node.degree(n)
    //      n.$outdegree ==  apoc.node.degree(n, '>')
    //      n.$indegree  ==  apoc.node.degree(n, '<')
    //
    //      n.count
    //      e.count
    //  .

    public static String label(String type) {
        if (type == null)
            return NONE;
        else if (type.isEmpty())
            return NONE;
        else
            return String.format(":%s", type);
    }

    // ----------------------------------------------------------------------
    // blocks

    /**
     * Create the PROPERTIES block:
     *
     *      { param: $[n]param, ... }
     *
     * Note: this block has a different meaning if used ina MATCH or CREATE clause:
     *   - in MATCH  clause is a WHERE clause
     *   - in CREATE clause is a SET   clause
     *
     * However, we can limit the 'pblock' only to 'properties with simple values'
     */
    public static String pblock(String alias, Map<String,Object> params) {
        // (... { ... })
        // if (params.isEmpty())
        //     return NONE;

        StringBuilder sb = new StringBuilder();
        for (String key : new HashSet<>(params.keySet())) {
            Param param = Param.of(alias, key);
            Value value = Value.of(params.get(key));

            // skip if special handling
            if (isSpecial(param, value))
                continue;

            // ... ,
            comma(sb);
            sb.append(formatProp(param, value));
        }

        // { ... }
        return brackets(sb);
    }

    /**
     * Create a WHERE block
     *      WHERE n.param = $param AND ...
     * or
     *        AND n.param = $param AND ...
     *
     * @param params parameters
     * @param and if to include the WHERE keyword or AND
     * @param pblock if to exclude simple (name, value)
     */
    public static String wblock(String alias, Map<String,Object> params, boolean and, boolean pblock) {
        // WHERE ...
        //   AND ...
        // if (params.isEmpty())
        //     return NONE;

        StringBuilder sb = new StringBuilder();
        for (String key : new HashSet<>(params.keySet())) {
            Param param = Param.of(alias, key);
            Value value = Value.of(params.get(key));

            // skip if already used in pblock
            if (pblock && !isSpecial(param, value))
                continue;

            and(sb);
            sb.append(formatClause(param, value));
        }

        return where(sb, and);
    }

    /**
     * Create a SET block
     *
     *      SET n.param = $nparam, ...
     *
     * @param pblock specify if some parameters are already used in 'pblock'
     */
    public static String sblock(String alias, Map<String,Object> params, boolean pblock) {
        // SET ...
        // if (params.isEmpty())
        //     return NONE;

        StringBuilder sb = new StringBuilder();
        for (String key : new HashSet<>(params.keySet())) {
            Param param = Param.of(alias, key);
            Value value = Value.of(params.get(key));

            // skip if already used in pblock
            if (pblock && !isSpecial(param, value))
                continue;
            // skip if special
            if (isSetSpecial(param))
                continue;

            comma(sb);
            sb.append(formatAssign(param, value));
        }

        return update(sb);
    }

    /**
     * Create the EDGE block
     *
     *       -[e:etype {...}]-      Any
     *       -[e:etype {...}]->     Output
     *      <-[e:etype {...}]-      Input
     *      <-[e:type *]->          recursive
     */
    public static String eblock(String alias, String edgeType,
                                Direction direction, boolean recursive,
                                Map<String,Object> edgeProps) {
        String eblock;

        switch (direction) {
            case Input:
                eblock = "<-[%s%s%s%s]-";
                break;
            case Output:
                eblock = "-[%s%s%s%s]->";
                break;
            default:
                eblock = "-[%s%s%s%s]-";
                break;
        }

        return String.format(eblock,
            alias,
            label(edgeType),
            recursive ? "*" : NONE,
            pblock(alias, edgeProps));
    }


    // ----------------------------------------------------------------------
    // special cases

    // ----------------------------------------------------------------------
    // ${and:  [alias:][name]}
    // ${where:[alias:][name]}

    /**
     * Create a USER WHERE block
     */
    public static String ublock(String stmt, Parameters params, boolean and) {
        if (params.isEmpty())
            return NONE;

        params = Parameters.params(params);

        // split ${where:[alias:][name]} in the parts
        String prefix = and ? AND_BLOCK : WHERE_BLOCK;
        int b = stmt.indexOf(prefix) ;
        int e = stmt.indexOf(END_BLOCK, b);
        String name = stmt.substring(b+prefix.length(), e);
        String alias;

        // name | alias:name
        int sep = name.indexOf(':');
        if (sep == -1)
            alias = N;
        else {
            alias = name.substring(0, sep);
            name  = name.substring(sep+1);
        }

        //
        // params['name'] is ANOTHER map
        //
        Map<String,Object> uparams = (Map<String,Object>) params.getOrDefault(name, Collections.emptyMap());
        params.remove(name);
        params.add(alias, uparams);

        String wblock = wblock(alias, uparams, and, false);

        // replace '${and...}' or '${where...}' with 'wblock'
        stmt = stmt.substring(0, b) + wblock + stmt.substring(e+END_BLOCK.length());

        return stmt;
    }

    // ----------------------------------------------------------------------
    // special cases

    private static boolean isSpecial(Param param, Value value) {
        return param.isSpecial() || value.isCollection() || !value.isAssign();
    }

    private static boolean isSetSpecial(Param param) {
        return param.param.startsWith("$");
    }

    // ----------------------------------------------------------------------
    // wrappers

    private static String where(StringBuilder sb, boolean and) {
        if (sb.length() == 0)
            return NONE;

        return sb.insert(0, and ? " AND ": " WHERE ").toString();
    }

    private static void and(StringBuilder sb) {
        if (sb.length() > 0)
            sb.append(" AND ");
    }

    private static void comma(StringBuilder sb) {
        if (sb.length() > 0)
            sb.append(", ");
    }

    private static String brackets(StringBuilder sb) {
        if (sb.length() == 0)
            return NONE;
        else
            return sb.insert(0," {").append("}").toString();
    }

    private static String update(StringBuilder sb) {
        if (sb.length() == 0)
            return NONE;
        else
            return sb.insert(0," SET ").toString();
    }

    // ----------------------------------------------------------------------
    // format

    public static String formatProp(Param param, Value value) {

        if (param.isSpecial() || !value.isAssign() || value.isCollection())
            return "";
        else
            return String.format("%s:%s", param.name, param.pname);
    }

    public static String formatClause(Param param, Value value) {
        // set/assignment
        if (Op.ASSIGN == value.op) {
            return formatAssign(param, value);
        }
        else if (Op.INCR == value.op) {
            return formatIncr(param, value);
        }
        else if (Op.LIST_ADD == value.op) {
            return String.format("%1$s = apocx.coll.listAdd(%1$s, %2$s)", param.aname, param.pname);
        }
        else if (Op.SET_ADD == value.op) {
            return String.format("%1$s = apocx.coll.setAdd(%1$s, %2$s)", param.aname, param.pname);
        }

        //
        // predicates
        //

        // [id, value | {...}]
        else if (ID.equals(param.param)) {
            return formatId(param, value);
        }
        // [$label, label | {...}]
        else if (LABEL.equals(param.param)) {
            return formatLabel(param, value);
        }
        // [revision, rev | {...}]
        else if (REVISION.equals(param.name)) {
            return formatRevision(param, value);
        }
        // [$[in|out|]degree, value]
        else if (isDegree(param)) {
            return formatDegree(param, value);
        }

        // [name|name[i], value | {...}]
        else if (Op.EQ == value.op || Op.IN == value.op) {
            return formatEq(param, value);
        }
        else if (Op.NEQ == value.op || Op.NOT_IN == value.op) {
            return formatEq(param, value);
        }
        // else if (Op.IN == value.op) {
        //     return String.format("%1$s IN %2$s", param.aname, param.pname);
        // }
        // else if (Op.NOT_IN == value.op) {
        //     return String.format("NOT(%1$s IN %2$s)", param.aname, param.pname);
        // }

        // else if (Op.GT == value.op) {
        //     return String.format("%1$s > %2$s", param.aname, param.pname);
        // }
        // else if (Op.GEQ == value.op) {
        //     return String.format("%1$s >= %2$s", param.aname, param.pname);
        // }
        // else if (Op.LT == value.op) {
        //     return String.format("%1$s < %2$s", param.aname, param.pname);
        // }
        // else if (Op.LEQ == value.op) {
        //     return String.format("%1$s <= %2$s", param.aname, param.pname);
        // }

        else if (Op.CONTAINS == value.op) {
            return String.format("%1$s CONTAINS %2$s", param.aname, param.pname);
        }
        else if (Op.NOT_CONTAINS == value.op) {
            return String.format("NOT(%1$s CONTAINS %2$s)", param.aname, param.pname);
        }
        else if (Op.STARTS_WITH == value.op) {
            return String.format("%1$s STARTS WITH %2$s", param.aname, param.pname);
        }
        else if (Op.ENDS_WITH == value.op) {
            return String.format("%1$s ENDS WITH %2$s", param.aname, param.pname);
        }

        else {
            return String.format("%1$s %3$s %2$s", param.aname, param.pname, op(value.op));
        }
    }

    private static String formatAssign(Param param, Value value) {
        // [inRevision[rev], value]
        if (IN_REVISION.equals(param.name)) {
            // n.inRevision = apocx.coll.arraySet(n.inRevision, rev, $ninRevision)
            return String.format("%1$s = apocx.coll.arraySet(%1$s, %3$d, %2$s)", param.sname, param.pname, param.index);
        }
        // [revision, rev] == [inRevision[rev], true]
        else if (REVISION.equals(param.name)) {
            // n.inRevision = apocx.coll.arraySet(n.inRevision, $nrevision, true)
            return String.format("%1$s.%2$s = apocx.coll.arraySet(%1$s.%2$s, %3$s, true)", param.alias, IN_REVISION, param.pname);
        }
        // [name[index], value]
        else if (param.index != -1) {
            return String.format("%1$s = apoc.coll.set(%1$s, %3$d, %2$s)", param.sname, param.pname, param.index);
        }
        // [name, value]
        else {
            return String.format("%1$s = %2$s", param.sname, param.pname);
        }
    }

    // ----------------------------------------------------------------------
    // format clauses

    // [name, value | {...}]
    // [name,  in, [value | {...}]]
    // [name, !in, [value | {...}]]
    private static String formatEq(Param param, Value value) {
        if (Op.EQ == value.op || Op.IN == value.op) {
            if (value.isCollection())
                return String.format("%1$s IN %2$s", param.aname, param.pname);
            else
                return String.format("%1$s = %2$s", param.aname, param.pname);
        }
        else if (Op.NEQ == value.op || Op.NOT_IN == value.op) {
            if (value.isCollection())
                return String.format("NOT(%1$s IN %2$s)", param.aname, param.pname);
            else
                return String.format("%1$s <> %2$s", param.aname, param.pname);
        }
        else return FALSE;
    }

    // [name, incr(value)]
    private static String formatIncr(Param param, Value value) {
        if (param.index != -1)
            // n.name[index] += 1
            return String.format("%1$s = apocx.coll.arrayIncr(%1$s, %3$d, %2$s)", param.sname, param.pname, param.index);
        else
            // n.name += 1
            return String.format("%1$s = %1$s + %2$s", param.aname, param.pname);
    }

    // [id, value | {...}]
    private static String formatId(Param param, Value value) {
        if (value.isCollection())
            return String.format("id(%1$s) IN %2$s", param.alias, param.pname);
        else
            return String.format("id(%1$s) = %2$s", param.alias, param.pname);
    }

    // [$label, label | {l1, ...}]
    private static String formatLabel(Param param, Value value) {
        if (value.isCollection())
            return String.format("labels(%1$s)[0] IN %2$s", param.alias, param.pname);
        else
            return String.format("labels(%1$s)[0] = %2$s", param.alias, param.pname);
    }

    // [revision, rev | [] | [r] | [r1,...]
    private static String formatRevision(Param param, Value value) {
        if (!value.isCollection())
            // n.inRevision[rev]
            return String.format("%1$s.%2$s[%3$s]", param.alias, IN_REVISION, param.pname);

        int[] revs = value.intArray();
        if (revs.length == 0)
            return TRUE;

        StringBuilder sb = new StringBuilder("(")
                .append(String.format("%1$s.%2$s[%3$d]", param.alias, IN_REVISION, revs[0]));
        for (int i=1; i<revs.length; ++i)
            sb.append(" OR ")
                .append(String.format("%1$s.%2$s[%3$d]", param.alias, IN_REVISION, revs[i]));
        return sb.append(")").toString();
    }

    // [$[int|out|]degree[edge], value]  ==, !=, <, <=, >, >=
    private static boolean isDegree(Param param) {
        return param.param.startsWith("$") && param.param.contains("degree");
    }

    private static String formatDegree(Param param, Value value) {
        if (IN_DEGREE.equals(param.name)) {
            if (param.key == null)
                return String.format("apoc.node.degree(%1$s,<) %3$s %2$s", param.alias, param.pname, op(value.op));
            else
                return String.format("apoc.node.degree(%1$s,%4$s<) %3$s %2$s", param.alias, param.pname, op(value.op), param.key);
        }
        else if (OUT_DEGREE.equals(param.name)) {
            if (param.key == null)
                return String.format("apoc.node.degree(%1$s,>) %3$s %2$s", param.alias, param.pname, op(value.op));
            else
                return String.format("apoc.node.degree(%1$s,%4$s>) %3$s %2$s", param.alias, param.pname, op(value.op), param.key);
        }
        else if (DEGREE.equals(param.name)) {
            if (param.key == null)
                return String.format("apoc.node.degree(%1$s) %3$s %2$s", param.alias, param.pname, op(value.op));
            else
                return String.format("apoc.node.degree(%1$s,%4$s) %3$s %2$s", param.alias, param.pname, op(value.op), param.key);
        }
        else {
            return "true";
        }
    }

    private static String op(Op op) {
        switch(op) {
            case EQ: return "=";
            case NEQ: return "<>";
            default:
                return op.toString();
        }
    }
}
