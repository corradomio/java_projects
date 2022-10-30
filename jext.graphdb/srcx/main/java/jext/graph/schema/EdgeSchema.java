package jext.graph.schema;

public class EdgeSchema {

    public static EdgeSchema of(String name) {
        EdgeSchema eschema = new EdgeSchema();
        eschema.name = name;
        return eschema;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String  name;
    private boolean revisioned;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public EdgeSchema() {

    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public String name() {
        return name;
    }

    public boolean isRevisioned() {
        return revisioned;
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------
    // Called from the digester

    public void setName(String name) {
        this.name = name;
    }

    public void setRevisioned(boolean revisioned) {
        this.revisioned = revisioned;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
