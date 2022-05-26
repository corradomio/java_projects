package jext.graph;

import java.util.Collection;
import java.util.Objects;

public class Param {

    // compatibility
    public static String at(String name, int index) {
        return of(name, index, Op.EQ);
    }


    public static String of(String name) {
        return of(name, -1, Op.EQ);
    }

    public static String of(String name, int index) {
        return of(name, index, Op.EQ);
    }

    public static String of(String name, String op) {
        return of(name, -1, Op.of(op));
    }

    public static String of(String name, Op op) {
        return of(name, -1, op);
    }

    public static String of(String name, int index, String op) {
        return of(name, index, Op.of(op));
    }

    public static String of(String name, int index, Op op) {
        if (index < 0 && isEq(op))
            return name;
        else if (isEq(op))
            return String.format("%s[%d]", name, index);
        else if (index < 0)
            return String.format("%s %s", name, op);
        else
            return String.format("%s[%d] %s", name, index, op);
    }

    // ----------------------------------------------------------------------

    public static String appendDistinct(String name) {
        return name + "[!]";
    }

    public static String append(String name) {
        return name + "[+]";
    }

    public static String appendDistinct(String name, int index) {
        return String.format("%s[%d,!]", name, index);
    }


    private static boolean isEq(Op op) {
        return op == Op.EQ;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // public static boolean isSpecial(String name, Object value) {
    //     if (name.startsWith("$") || name.contains("[") || name.equals("revision"))
    //         return true;
    //     if (name.contains("<") || name.contains(">") || name.contains("With") || name.contains("append"))
    //         return true;
    //     if (value == null)
    //         return true;
    //     else
    //         return false;
    // }

    public static boolean isSpecialOrArray(String name, Object value) {
        if (name.startsWith("$") || name.contains("[") || name.equals("revision"))
            return true;
        if (value == null || value instanceof Collection || value.getClass().isArray())
            return true;
        if (name.contains("<") || name.contains(">") || name.contains("With") || name.contains("append"))
            return true;
        else
            return false;
    }

    public static boolean isExists(Object value) {
        return value == null;
    }

    public static boolean isRevision(String name) {
        return "revision".equals(name);
    }

    public static boolean isDegree(String name) {
        return name.startsWith("$degree") || name.startsWith("$indegree") || name.startsWith("$outdegree");
    }

    public static boolean isSpecial(String name) {
        return name.startsWith("$");
    }

    public static boolean isArray(String name, Object value) {
        if (name.contains("["))
            return true;
        if (value == null)
            return false;
        // if (value instanceof Collection || value.getClass().isArray())
        //     return true;
        else
            return false;
    }

    public static boolean isAppendDistinct(String name) {
        return name.endsWith("[!]");
    }

    public static boolean isAppend(String name) {
        return name.endsWith("[+]");
    }

    public static boolean isAppendDistinct2(String name) {
        return name.endsWith(",!]");
    }

    public static boolean isAppend2(String name) {
        return name.endsWith(",+]");
    }

    public static boolean isCollection(Object value) {
        return value instanceof Collection || value.getClass().isArray();
    }

    public static boolean isFunction(String name) {
        if (name.contains("<") || name.contains(">") || name.contains("With") || name.contains("append"))
            return true;
        else
            return false;
    }

    // ----------------------------------------------------------------------
    // param ->
    // ----------------------------------------------------------------------

    // name[index] -> name
    public static String nameOf(String param) {
        if (param.startsWith("$"))
            param = param.substring(1);

        int pos = param.indexOf('[');
        if (pos == -1)
            pos = param.indexOf(" ");
        if (pos == -1)
            pos = param.indexOf("==");
        if (pos == -1)
            pos = param.indexOf("<");
        if (pos == -1)
            pos = param.indexOf(">");
        if (pos == -1)
            pos = param.indexOf("!");
        if (pos != -1)
            return param.substring(0, pos);
        else
            return param;
    }

    // [name[index], 0] -> index
    // name[idx1,idx2], 1] -> idx1
    // name[idx1,idx2], 2] -> idx2
    public static int indexOf(String indexed, int at) {
        if (at == 0) {
            int pos = indexed.indexOf('[');
            int end = indexed.indexOf(']');
            String index = indexed.substring(pos + 1, end);
            return Integer.parseInt(index);
        }
        if (at == 1) {
            int pos = indexed.indexOf('[');
            int end = indexed.indexOf(',', pos);
            String index = indexed.substring(pos + 1, end);
            return Integer.parseInt(index);
        }
        if (at == 2) {
            int pos = indexed.indexOf(',');
            int end = indexed.indexOf(']', pos);
            String index = indexed.substring(pos + 1, end);
            return Integer.parseInt(index);
        }
        else
            throw new IllegalArgumentException();
    }

    // name[key] -> key
    public static String keyOf(String keyed) {
        int pos = keyed.indexOf('[');
        int end = keyed.indexOf(']');
        if (pos == -1)
            return "";
        else
            return keyed.substring(pos + 1, end);
    }


    // name        -> name
    // $name       -> name
    // name[index] -> nameIndex
    // name[!]     -> name_a
    // name[+]     -> name_a
    // name[index,!]    -> nameIndex_a
    // name[index,+]    -> nameIndex_a
    // name[idx1,idx2]  -> nameIdx1_Idx2
    public static String pnameOf(String param) {
        if (param.startsWith("$"))
            param = param.substring(1);
        if (param.indexOf(' ') != -1)
            param = param.substring(0, param.indexOf(" "));

        if (!param.contains("["))
            return param;

        if (param.endsWith("[!]"))
            return param.replace("[!]", "_a");
        if (param.endsWith("[+]"))
            return param.replace("[+]", "_a");
        if (param.endsWith(",!]"))
            return param.replace("[", "").replace(",!]", "a");
        if (param.endsWith(",+]"))
            return param.replace("[", "").replace(",+]", "a");
        if (param.contains("["))
            return param.replace("[", "")
                .replace(",", "_")
                .replace("]", "");
        else
            return param;
    }

    public static String opOf(String param) {
        if (param.contains(Op.EQ.name()))
            return "=";
        if (param.contains(Op.NEQ.name()))
            return "<>";
        if (param.contains(Op.GEQ.name()))
            return ">=";
        if (param.contains(Op.LEQ.name()))
            return "<=";
        if (param.contains(Op.LT.name()))
            return "<";
        if (param.contains(Op.GT.name()))
            return ">";

        if (param.contains(Op.NOT_CONTAINS.name()))
            return Op.NOT_CONTAINS.name();
        if (param.contains(Op.CONTAINS.name()))
            return Op.CONTAINS.name();

        if (param.contains(Op.NIN.name()))
            return Op.NIN.name();
        if (param.contains(Op.IN.name()))
            return Op.IN.name();

        if (param.contains(Op.STARTS_WITH.name()))
            return Op.STARTS_WITH.name();
        if (param.contains(Op.ENDS_WITH.name()))
            return Op.ENDS_WITH.name();

        if (param.contains(Op.APPEND_DISTINCT.name()))
            return Op.APPEND_DISTINCT.name();
        if (param.contains(Op.APPEND.name()))
            return Op.APPEND.name();

        else
            return Op.EQ.name();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private final String name;
    private final int index;
    private final Op op;

    private Param(String name, int index, Op op) {
        this.name = name;
        this.index = index;
        this.op = op;
    }

    public String name() { return name; }
    public int index() { return index; }
    public boolean hasIndex() { return index != -1; }
    public Op op() { return op; }

    @Override
    public String toString() {
        if (index == -1 && op == Op.EQ)
            return name;
        if (index != -1 && op == Op.EQ)
            return String.format("%s[%d]", name, index);
        if (index == -1)
            return String.format("%s %s", name, op);
        else
            return String.format("%s[%d] %s", name, index, op);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Param))
            return false;

        Param that = (Param) obj;
        return this.name.equals(that.name)
            && this.index == that.index
            && this.op == that.op;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index, op);
    }
}
