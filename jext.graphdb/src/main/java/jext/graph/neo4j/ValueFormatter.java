package jext.graph.neo4j;

import jext.graph.Op;
import jext.graph.Value;

public class ValueFormatter {

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
    public static String format(String alias, String name, Value value) {
        if (Op.EQ == value.op && value.isCollection()) {
            return String.format("%1$s.%2$s IN %1$s%2$s", alias, name);
        }
        else if (Op.EQ == value.op) {
            return String.format("%1$s.%2$s = %1$s%2$s", alias, name);
        }
        else if (Op.NEQ == value.op) {
            return String.format("%1$s.%2$s <> %1$s%2$s", alias, name);
        }
        else if (Op.GT == value.op) {
            return String.format("%1$s.%2$s > %1$s%2$s", alias, name);
        }
        else if (Op.GEQ == value.op) {
            return String.format("%1$s.%2$s >= %1$s%2$s", alias, name);
        }
        else if (Op.LT == value.op) {
            return String.format("%1$s.%2$s < %1$s%2$s", alias, name);
        }
        else if (Op.LEQ == value.op) {
            return String.format("%1$s.%2$s <= %1$s%2$s", alias, name);
        }

        else if (Op.IN == value.op) {
            return String.format("%1$s.%2$s IN %1$s%2$s", alias, name);
        }
        else if (Op.NOT_IN == value.op) {
            return String.format("NOT(%1$s.%2$s IN %1$s%2$s)", alias, name);
        }

        else if (Op.CONTAINS == value.op) {
            return String.format("%1$s.%2$s CONTAINS %1$s%2$s", alias, name);
        }
        else if (Op.NOT_CONTAINS == value.op) {
            return String.format("NOT(%1$s.%2$s CONTAINS %1$s%2$s)", alias, name);
        }

        else if (Op.STARTS_WITH == value.op) {
            return String.format("%1$s.%2$s STARTS WITH %1$s%2$s", alias, name);
        }
        else if (Op.ENDS_WITH == value.op) {
            return String.format("NOT(%1$s.%2$s ENDS WITH %1$s%2$s)", alias, name);
        }

    }
}
