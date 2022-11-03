package jext.graph;

import java.util.Objects;

public class Param {

    public static Param of(String alias, String param) {
        int p = param.indexOf('[');
        int index = -1;
        String key = null;
        if (p != -1) {
            int e = param.indexOf(']');
            key = param.substring(p + 1, e);
            try {
                index = Integer.parseInt(key);
            }
            catch (NumberFormatException ex) {
                //
            }
            param = param.substring(0, p);
        }
        return new Param(alias, param, index, key);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // $name | $name[index] | $name[key]
    // name | name[index]

    public final String alias;
    public final String name;
    public final int index;
    public final String key;

    // set:   [alias].[name]
    public final String sname;
    // get:   [alias].[name][index]
    public final String aname;
    // param: $[alias][name]
    public final String pname;


    private Param(String alias, String name, int index, String key) {
        // $name
        this.name = name;
        this.index = index;
        this.key = key;
        this.alias = alias;
        this.sname = String.format("%s.%s", alias, name);

        if (name.startsWith("$"))
            this.pname = String.format("$`%s%s`", alias, name);
        else
            this.pname = String.format("$%s%s", alias, name);

        if (index == -1)
            this.aname = this.sname;
        else
            this.aname = String.format("%s.%s[%d]", alias, name, index);
    }

    // ----------------------------------------------------------------------

    // compatibility
    public static String at(String name, int index) {
        return String.format("%s[%d]", name, index);
    }

    // ----------------------------------------------------------------------
    // param ->
    // ----------------------------------------------------------------------
    // == != < <= > >=
    // in !in
    // contains !contains


    // name[index]  -> name
    // name{op}     -> name
    // $name        -> name
    public static String nameOf(String param) {
        if (param.startsWith("$"))
            param = param.substring(1);

        int pos = param.indexOf('[');
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

    @Override
    public String toString() {
        if (index == -1)
            return name;
        else
            return String.format("%s[%d]", name, index);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Param))
            return false;

        Param that = (Param) obj;
        return this.name.equals(that.name)
            && this.index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index);
    }
}
