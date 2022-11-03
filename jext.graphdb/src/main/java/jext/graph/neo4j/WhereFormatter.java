package jext.graph.neo4j;

import jext.graph.Op;
import jext.graph.Param;
import jext.graph.Value;

public class WhereFormatter {

    static final String ID = "id";
    static final String LABEL = "$label";
    static final String REVISION = "revision";
    static final String IN_REVISION = "inRevision";

    static final String DEGREE = "$degree";
    static final String IN_DEGREE = "$indegree";
    static final String OUT_DEGREE = "$outdegree";

    /*
    EQ("=="),       // ==
    NEQ("!="),      // !=
    GT(">"),        // >
    GEQ(">="),      // >=
    LT("<"),        // <
    LEQ("<="),      // <=

    IN("in"),       // IN
    NOT_IN("!in"),     // NOT IN
    CONTAINS("contains"),      // string, collection (c CONTAINS x)
    NOT_CONTAINS("!contains"),    // string, collection (NOT c CONTAINS x)

    INCR("incr"),

    STARTS_WITH("startsWith"), // string
    ENDS_WITH("endsWith"),     // string

    LIST_ADD("listAdd"),
    SET_ADD("setAdd"),
     */
    public static String format(Param param, Value value) {
        // set/assignment
        if (Op.ASSIGN == value.op) {

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
            else if (param.index == -1) {
                return String.format("%1$s = apoc.coll.set(%1$s, %3$d, %2$s)", param.sname, param.pname, param.index);
            }
            // [name, value]
            else {
                return String.format("%1$s = %2$s", param.sname, param.pname);
            }
        }
        else if (Op.INCR == value.op) {
            return formatIncr(param, value);
        }
        else if (Op.LIST_ADD == value.op) {
            return String.format("%1$s = apocx.coll.listAdd(%1$s, %3$d, %2$s)", param.aname, param.pname, param.index);
        }
        else if (Op.SET_ADD == value.op) {
            return String.format("%1$s = apocx.coll.setAdd(%1$s, %3$d, %2$s)", param.aname, param.pname, param.index);
        }

        //
        // predicates
        //

        // [id, value | {...}]
        else if (ID.equals(param.name)) {
            return formatId(param, value);
        }
        // [$label, label | {...}]
        else if (LABEL.equals(param.name)) {
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
        else if (Op.EQ == value.op) {
            if (value.isCollection())
                return String.format("%1$s IN %2$s", param.aname, param.pname);
            else
                return String.format("%1$s = %2$s", param.aname, param.pname);
        }

        else if (Op.NEQ == value.op) {
            return String.format("%1$s <> %2$s", param.aname, param.pname);
        }
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

        else if (Op.IN == value.op) {
            return String.format("%1$s IN %2$s", param.aname, param.pname);
        }
        else if (Op.NOT_IN == value.op) {
            return String.format("NOT(%1$s IN %2$s)", param.aname, param.pname);
        }

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
            return String.format("%1$s %3$s %2$s", param.aname, param.pname, value.op.toString());
        }
    }

    private static String formatIncr(Param param, Value value) {
        if (param.index != -1)
            // n.name[index] += 1
            return String.format("%1$s = apocx.coll.arrayIncr(%1$s, %3$d, %2$s)", param.aname, param.pname, param.index);
        else
            // n.name += 1
            return String.format("%1$s = %1$s+1", param.aname);
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
            return String.format("%1$s.%2$s[%3$s]", param.aname, IN_REVISION, param.pname);

        int[] revs = value.intArray();
        if (revs.length == 0)
            return "true";

        if (revs.length == 1)
            return String.format("%1$s.%2$s[%3$d]", param.aname, IN_REVISION, revs[0]);

        StringBuilder sb = new StringBuilder("(")
                .append(String.format("%1$s.%2$s[%3$d]", param.aname, IN_REVISION, revs[0]));
        for (int i=1; i<revs.length; ++i)
            sb.append(" OR ").append(String.format("%1$s.%2$s[%3$d]", param.aname, IN_REVISION, revs[i]));
        return sb.append(")").toString();
    }

    // [$[int|out|]degree[edge], value]  ==, !=, <,                                                              <=, >, >=
    private static boolean isDegree(Param param) {
        return param.name.startsWith("$") && param.name.contains("degree");
    }

    private static String formatDegree(Param param, Value value) {
        if (IN_DEGREE.equals(param.name)) {
            if (param.key == null)
                return String.format("apoc.node.degree(%1$s,<) %3$s %2$s", param.alias, param.pname, value.op.toString());
            else
                return String.format("apoc.node.degree(%1$s,%4$s<) %3$s %2$s", param.alias, param.pname, value.op.toString(), param.key);
        }
        else if (OUT_DEGREE.equals(param.name)) {
            if (param.key == null)
                return String.format("apoc.node.degree(%1$s,>) %3$s %2$s", param.alias, param.pname, value.op.toString());
            else
                return String.format("apoc.node.degree(%1$s,%4$s>) %3$s %2$s", param.alias, param.pname, value.op.toString(), param.key);
        }
        else if (DEGREE.equals(param.name)) {
            if (param.key == null)
                return String.format("apoc.node.degree(%1$s) %3$s %2$s", param.alias, param.pname, value.op.toString());
            else
                return String.format("apoc.node.degree(%1$s,%4$s) %3$s %2$s", param.alias, param.pname, value.op.toString(), param.key);
        }
        else {
            return "true";
        }
    }
}
