package jext.graph;

import java.util.Objects;

public class Param {

    // compatibility
    public static String at(String name, int index) {
        return String.format("%s[%d]", name, index);
    }

    // ----------------------------------------------------------------------
    // param ->
    // ----------------------------------------------------------------------

    // name[index]  -> name
    // name{op}     -> name
    // $name        -> name
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

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private final String name;
    private final int index;

    private Param(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String name() { return name; }
    public int index() { return index; }

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
