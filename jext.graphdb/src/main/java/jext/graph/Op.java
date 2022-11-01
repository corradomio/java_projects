package jext.graph;

public enum Op {

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

    // deprecated
    APPEND("listAdd"),
    APPEND_DISTINCT("setAdd")
    ;

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
            case "!in": return NOT_IN;
            case "startsWith": return STARTS_WITH;
            case "endsWith": return ENDS_WITH;
            case "contains": return CONTAINS;
            case "!contains": return NOT_CONTAINS;
            case "append": return LIST_ADD;
            case "!append": return SET_ADD;
            default:
                throw new IllegalArgumentException("Unknown '" + op + "'");
        }
    }
}