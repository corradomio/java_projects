package jext.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Value {

    public static Value of(Object value) {
        if (value instanceof Value)
            return (Value) value;
        else
            return of(Op.EQ, value);
    }
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
    public static Value ladd(Object value) { return of(Op.LIST_ADD, value); }
    public static Value sadd(Object value) { return of(Op.SET_ADD, value); }

    public static Value incr(Object value) { return of(Op.INCR, value); }

    public static Value in(Object value) { return of(Op.IN, value); }
    public static Value nin(Object value) { return of(Op.NOT_IN, value); }
    public static Value contains(Object value) { return of(Op.CONTAINS, value); }
    public static Value ncontains(Object value) { return of(Op.NOT_CONTAINS, value); }

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

    public boolean isCollection() {
        if (value == null)
            return false;
        else
            return value instanceof Collection || value.getClass().isArray();
    }

    public int[] intArray() {
        int[] a;
        int i=0;
        if (value instanceof Collection) {
            Collection<Number> c = (Collection<Number>) value;
            a = new int[c.size()];
            for(Number e : c)
                a[i++] = e.intValue();
        }
        if (value.getClass().equals(long[].class)) {
            long[] l = (long[])value;
            a = new int[l.length];
            for(i=0; i<l.length; ++i)
                a[i] = (int)l[i];
        }
        else {
            a = (int[]) value;
        }
        return a;
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
