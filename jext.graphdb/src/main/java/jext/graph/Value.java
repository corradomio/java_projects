package jext.graph;

public class Value<T> {

    public enum Op {
        EQ,     // ==
        NEQ,    // !=
        GT,     // >
        GEQ,    // >=
        LT,     // <
        LEQ,    // <=
        IN,     // IN
        NIN     // NOT IN
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

    public Value(String op, T v) {
        value = v;
        if ("=".equals(op) ||"==".equals(op))
            this.op = Op.EQ;
        else if ("!=".equals(op) || "<>".equals(op))
            this.op = Op.NEQ;
        else if (">".equals(op))
            this.op = Op.GT;
        else if (">=".equals(op))
            this.op = Op.GEQ;
        else if ("<".equals(op))
            this.op = Op.LT;
        else if ("<=".equals(op))
            this.op = Op.LEQ;
        else if ("in".equals(op))
            this.op = Op.IN;
        else if ("!in".equals(op))
            this.op = Op.NIN;
        else
            this.op = Op.EQ;
    }
}
