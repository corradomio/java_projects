package jext.graph.schema;

public class EdgeSchema extends ObjectSchema {

    public static EdgeSchema of(String name) {
        EdgeSchema eschema = new EdgeSchema();
        eschema.name = name;
        return eschema;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public EdgeSchema() {

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
