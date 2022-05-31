package jext.graph.schema;

import jext.graph.util.PropertyUtils;

import java.util.List;

public class PropertySchema {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String BOOLEAN = "bool";
    private static final String INTEGER = "int";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String STRING = "string";


    private String name;
    private String type;
    private boolean array;
    private boolean revisioned;
    private boolean unique;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PropertySchema() {

    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isArray() {
        return array;
    }

    public boolean isRevisioned() {
        return revisioned;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Object asRevisioned(int rev, Object value) {
        if (!revisioned || rev == -1)
            return value;
        if (BOOLEAN.equals(type))
            return PropertyUtils.boolArray(rev, (Boolean) value);
        if (INTEGER.equals(type) || LONG.equals(type))
            return PropertyUtils.longArray(rev, ((Number)value).longValue());
        if (FLOAT.equals(type) || DOUBLE.equals(type))
            return PropertyUtils.doubleArray(rev, ((Number)value).doubleValue());
        if (STRING.equals(type))
            return PropertyUtils.stringArray(rev, (String)value);
        else
            return PropertyUtils.objectArray(rev, value);
    }

    public Object asRevisioned(Object prev, int rev, Object value) {
        if (!revisioned || rev == -1)
            return value;
        if (prev == null)
            return asRevisioned(rev, value);

        if (BOOLEAN.equals(type))
            return PropertyUtils.boolArray(prev, rev, (Boolean) value);
        if (INTEGER.equals(type) || LONG.equals(type))
            return PropertyUtils.longArray(prev, rev, ((Number)value).longValue());
        if (FLOAT.equals(type) || DOUBLE.equals(type))
            return PropertyUtils.doubleArray(prev, rev, ((Number)value).doubleValue());
        if (STRING.equals(type))
            return PropertyUtils.stringArray(prev, rev, (String)value);
        else
            return PropertyUtils.objectArray(prev, rev, value);
    }


    public boolean isUnique() {
        return unique;
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
        this.array = type.endsWith("[]");
    }

    public void setRevisioned(boolean revisioned) {
        this.revisioned = revisioned;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    // ----------------------------------------------------------------------
    // Debug
    // ----------------------------------------------------------------------

    public void dump() {
        if (revisioned)
            System.out.printf("      %15s:\t%s, revisioned\n", name, type);
        else if (unique)
            System.out.printf("      %15s:\t%s, unique\n", name, type);
        else
            System.out.printf("      %15s:\t%s\n", name, type);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
