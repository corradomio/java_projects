package jext.graph;

import jext.util.MapUtils;

import java.util.Map;

public class Value<T> {

    public enum Op {
        EQ,     // ==
        NEQ,    // !=
        GT,     // >
        GEQ,    // >=
        LT,     // <
        LEQ,    // <=

        IN,     // IN
        NIN,    // NOT IN

        STARTS_WITH,    // string
        ENDS_WITH,      // string
        CONTAINS,       // string, collection (c CONTAINS x)
        NOT_CONTAINS,   // string, collection (NOT c CONTAINS x)

        APPEND,
        APPEND_DISTINCT,
    };

    public static <T> Value of(T value) {
        return new Value<T>(value);
    }

    public static <T> Value of(String op, T value) {
        return new Value<T>(op(op), value);
    }

    public static <T> Value of(Op op, T value) {
        return new Value<T>(op, value);
    }

    private static Map<String, Op> opMap = MapUtils.asMap(
        "", Op.EQ,
        "=", Op.EQ,
        "==", Op.EQ,
        "!=", Op.NEQ,
        "<>", Op.NEQ,
        "<", Op.LT,
        "<=", Op.LEQ,
        ">=", Op.GEQ,
        ">", Op.GT,
        "in", Op.IN,
        "IN", Op.IN,
        "!in", Op.NIN,
        "!IN", Op.NIN
    );

    private static Op op(String op) {
        return opMap.get(op);
    }

    private T value;
    private Op op;

    public Value(T v) {
        value = v;
        op = Op.EQ;
    }

    public Value(Op op, T v) {
        value = v;
        this.op = op;
    }

    public T value() {
        return value;
    }

    public Op op() {
        return op;
    }

}
