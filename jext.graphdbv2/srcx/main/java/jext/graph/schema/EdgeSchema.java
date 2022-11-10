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
        super.setName(name);
    }

    public void setRevisioned(boolean revisioned) {
        super.setRevisioned(revisioned);
    }

    @Override
    public void setCounted(boolean counted) {
        super.setCounted(counted);
    }

    @Override
    public void addProperty(PropertySchema pschema) {
        super.addProperty(pschema);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
