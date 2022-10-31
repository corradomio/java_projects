package jext.graph;

import java.util.Objects;

public class Value {

    public static Value of(Object value) { return of(Op.EQ, value); }
    public static Value of(String op, Object value) { return of(Op.valueOf(op), value); }
    public static Value of(Op op, Object value) { return new Value(op, value); }

    public static Value eq(Object value) { return of(Op.EQ, value); }
    public static Value neq(Object value) { return of(Op.NEQ, value); }
    public static Value gt(Object value) { return of(Op.GT, value); }
    public static Value geq(Object value) { return of(Op.GEQ, value); }
    public static Value lt(Object value) { return of(Op.LT, value); }
    public static Value leq(Object value) { return of(Op.LEQ, value); }

    public static Value append(Object value) { return of(Op.APPEND, value); }
    public static Value appendDistinct(Object value) { return of(Op.APPEND_DISTINCT, value); }

    public static Value incr(Object value) { return of(Op.INCR, value); }

    public static Value in(Object value) { return of(Op.IN, value); }
    public static Value nin(Object value) { return of(Op.NIN, value); }
    public static Value contains(Object value) { return of(Op.CONTAINS, value); }
    public static Value ncontains(Object value) { return of(Op.NCONTAINS, value); }

    public static Value startsWith(Object value) { return of(Op.STARTS_WITH, value); }
    public static Value endsWith(Object value) { return of(Op.ENDS_WITH, value); }

    // ----------------------------------------------------------------------

    public final Op op;
    public final Object value;

    // ----------------------------------------------------------------------

    private Value(Op op, Object value) {
        this.op = op;
        this.value = value;
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        if (op == Op.EQ)
            return String.valueOf(value);
        else
            return String.format("%s %s", op, value);
    }

    @Override
    public int hashCode() {
        if (op == Op.EQ)
            return Objects.hash(value);
        else
            return Objects.hash(op, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Value)) {
            if (op == Op.EQ)
                return eq(value, obj);
            else
                return false;
        }
        else {
            Value that = (Value) obj;
            return this.op.equals(that.op) && eq(this.value, that.value);
        }
    }

    private static boolean eq(Object o1, Object o2) {
        if (o1 == o2)
            return true;
        if (o1 == null || o2 == null)
            return false;
        else
            return o1.equals(o2);
    }

    // ----------------------------------------------------------------------

}
