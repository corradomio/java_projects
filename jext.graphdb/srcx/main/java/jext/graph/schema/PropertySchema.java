package jext.graph.schema;

import jext.graph.util.PropertyUtils;

import static jext.graph.schema.NodeSchema.NO_REV;

public class PropertySchema {

    public static PropertySchema of(String name, Object value) {
        PropertySchema pschema = new PropertySchema();
        pschema.name = name;
        pschema.type = typeOf(value);
        return pschema;
    }

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String BOOLEAN = "bool";
    private static final String INTEGER = "int";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String STRING = "string";

    // special formats
    private static final String BYTES = "bytes";
    private static final String JSON = "json";
    private static final String OBJECT = "object";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

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

    public String name() {
        return name;
    }

    public String type() {
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

    public static String typeOf(Object value) {
        if (value == null)
            return OBJECT;
        if (value instanceof String)
            return STRING;
        if (value instanceof Boolean)
            return BOOLEAN;
        if (value instanceof Integer)
            return INTEGER;
        if (value instanceof Long)
            return LONG;
        if (value instanceof Float)
            return FLOAT;
        if (value instanceof Double)
            return DOUBLE;
        else
            return OBJECT;
    }

    public Object asRevisioned(Object value, int rev) {
        if (!revisioned || rev == NO_REV)
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

    // public Object asRevisioned(Object value, Object prev, int rev) {
    //     if (!revisioned || rev == NO_REV)
    //         return value;
    //     if (prev == null)
    //         return asRevisioned(value, rev);
    //
    //     // for revisioned properties, prev is an array
    //     if (BOOLEAN.equals(type))
    //         return PropertyUtils.boolArray(prev, rev, (Boolean) value);
    //     if (INTEGER.equals(type) || LONG.equals(type))
    //         return PropertyUtils.longArray(prev, rev, ((Number)value).longValue());
    //     if (FLOAT.equals(type) || DOUBLE.equals(type))
    //         return PropertyUtils.doubleArray(prev, rev, ((Number)value).doubleValue());
    //     if (STRING.equals(type))
    //         return PropertyUtils.stringArray(prev, rev, (String)value);
    //     else
    //         return PropertyUtils.objectArray(prev, rev, value);
    // }

    public Object atRevision(Object value, int rev) {
        if (!revisioned || rev == NO_REV)
            return value;
        if (BOOLEAN.equals(type))
            return PropertyUtils.atBoolArray(value, rev);
        if (INTEGER.equals(type) || LONG.equals(type))
            return PropertyUtils.atLongArray(value, rev);
        if (FLOAT.equals(type) || DOUBLE.equals(type))
            return PropertyUtils.atDoubleArray(value, rev);
        if (STRING.equals(type))
            return PropertyUtils.atStringArray(value, rev);
        else
            return PropertyUtils.atObjectArray(value, rev);
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
        this.array = type.contains("[");
        if (this.array)
            this.type = type.substring(0, type.indexOf("["));
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
