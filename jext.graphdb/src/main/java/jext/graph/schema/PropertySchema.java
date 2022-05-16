package jext.graph.schema;

public class PropertySchema {

    private String name;
    private String type;
    private boolean array;
    private boolean revisioned;

    public PropertySchema() {

    }

    // public PropertySchema(String pname, String ptype, boolean previsioned) {
    //     this.name = pname;
    //     this.type = ptype;
    //     this.revisioned = previsioned;
    //     int p = ptype.indexOf("[");
    //     this.array = p >= 0;
    //     if (this.array)
    //         this.type = ptype.substring(0, p);
    // }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRevisioned(boolean revisioned) {
        this.revisioned = revisioned;
    }

}
