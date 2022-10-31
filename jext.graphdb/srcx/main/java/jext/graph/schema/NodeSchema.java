package jext.graph.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeSchema extends ObjectSchema {

    public static NodeSchema of(String name) {
        NodeSchema nschema = new NodeSchema();
        nschema.name = name;
        return nschema;
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private final List<String> unique = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NodeSchema() {

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
    // Utilities
    // ----------------------------------------------------------------------

    protected void addProperty(PropertySchema pschema) {
        super.addProperty(pschema);
        if (pschema.isUnique())
            unique.add(pschema.name());
    }

    public Map<String,Object> uniqueProperties(Map<String,Object> props) {
        if (props.isEmpty() || unique.isEmpty())
            return Collections.emptyMap();

        Map<String,Object> uprops = new HashMap<>();
        for (String uname : unique)
            uprops.put(uname, props.get(uname));

        return uprops;
    }

    // ----------------------------------------------------------------------
    // Debug
    // ----------------------------------------------------------------------

    public void dump() {
        if (revisioned)
            System.out.printf("    %s: revisioned\n", name);
        else
            System.out.printf("    %s\n", name);

        properties.forEach((name, schema) -> {
            schema.dump();
        });
        System.out.println();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
