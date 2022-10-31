package jext.graph;

public enum Op {

    EQ("=="),       // ==
    NEQ("!="),      // !=
    GT(">"),        // >
    GEQ(">="),      // >=
    LT("<"),        // <
    LEQ("<="),      // <=

    IN("in"),       // IN
    NIN("!in"),     // NOT IN

    STARTS_WITH("startsWith"), // string
    ENDS_WITH("endsWith"),     // string
    CONTAINS("contains"),      // string, collection (c CONTAINS x)
    NCONTAINS("!contains"),    // string, collection (NOT c CONTAINS x)

    APPEND("append"),
    APPEND_DISTINCT("!append");

    private String op;

    Op(String op) {
        this.op = op;
    }

    public String toString() {
        return op;
    }


    public static Op of(String op) {
        if (op == null || op.isEmpty())
            return EQ;

        switch(op) {
            case "==": return EQ;
            case "!=": return NEQ;
            case "<" : return LT;
            case "<=": return LEQ;
            case ">" : return GT;
            case ">=": return GEQ;
            case "in": return IN;
            case "!in": return NIN;
            case "startsWith": return STARTS_WITH;
            case "endsWith": return ENDS_WITH;
            case "contains": return CONTAINS;
            case "!contains": return NCONTAINS;
            case "append": return APPEND;
            case "!append": return APPEND_DISTINCT;
            default:
                throw new IllegalArgumentException("Unknown '" + op + "'");
        }
    }
}