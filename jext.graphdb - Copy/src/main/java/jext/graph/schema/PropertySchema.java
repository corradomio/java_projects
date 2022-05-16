package jext.graph.schema;

public class PropertySchema {

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
