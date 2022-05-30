package jext.graph.schema;

import jext.graph.util.PropertyUtils;

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

    public Object asRevisioned(Object values, int rev, Object value) {
        if (!revisioned || rev == -1)
            return value;
        if (values == null)
            return asRevisioned(rev, value);
        return values;
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
    // End
    // ----------------------------------------------------------------------

}
