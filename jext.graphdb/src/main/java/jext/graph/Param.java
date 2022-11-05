package jext.graph;

import java.util.Objects;

public class Param {

    public static Param of(String alias, Object param) {
        if (param instanceof Param)
            return (Param) param;
        else
            return new Param(alias, (String)param);
    }

    public static Param of(String alias, String param) {
        return new Param(alias, (String)param);
    }

    public static Param of(String alias, String param, int index) {
        return new Param(alias, at(param, index));
    }

    public static Param of(String alias, String param, String key) {
        return new Param(alias, at(param, key));
    }

    // ----------------------------------------------------------------------
    // compatibility

    public static String at(String name, int index) {
        return String.format("%s[%d]", name, index);
    }

    public static String at(String name, String key) {
        return String.format("%s[%s]", name, key);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // $name | $name[index] | $name[key]
    // name | name[index]

    public final String alias;
    public final String param;

    // name
    public final String name;
    public final int index;
    public final String key;

    // set:   [alias].[name]
    public final String sname;
    // get:   [alias].[name][index]
    public final String aname;
    // param: $[alias][name]
    public final String pname;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private Param(String alias, String param) {
        this.alias = alias;
        this.param = param;

        int index = -1;
        String key = null;

        if (param.startsWith("$"))
            param = param.substring(1);

        // param[i] | param[k]
        if (param.contains("[")) {
            int b = param.indexOf('[');
            int e = param.indexOf(']');
            key = param.substring(b+1, e);
            param = param.substring(0, b);
            try {
                index = Integer.parseInt(key);
                key = null;
            }
            catch (NumberFormatException ex) {
                //
            }
        }

        this.index = index;
        this.key = key;

        // $name
        this.name = param;
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
    //
    // ----------------------------------------------------------------------

    public boolean isArray() {
        return index != -1;
    }

    public boolean isSpecial() {
        return index != -1 || param.startsWith("$") || "revision".equals(param) || "id".equals(param);
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return param;
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
