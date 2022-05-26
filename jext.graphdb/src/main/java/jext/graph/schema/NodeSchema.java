package jext.graph.schema;

import jext.graph.GraphDatabaseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeSchema {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    static NodeSchema NO_SCHEMA = new NodeSchema();

    private String name = "";
    private List<String> unique = new ArrayList<>();
    private boolean revisioned;

    private Map<String, PropertySchema> properties = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NodeSchema() {

    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public boolean isRevisioned() {
        return revisioned;
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setRevisioned(boolean revisioned) {
        this.revisioned = revisioned;
    }

    public void addProperty(PropertySchema pschema) {
        properties.put(pschema.getName(), pschema);
        if (pschema.isUnique())
            unique.add(pschema.getName());
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    public Map<String, Object> getUnique(Map<String, Object> nprops) {
        if (nprops.isEmpty() || unique.isEmpty())
            return Collections.emptyMap();

        Map<String, Object> uprops = new HashMap<>();
        for (String uname : unique)
            uprops.put(uname, nprops.get(uname));

        return uprops;
    }

    public Map<String, Object> normalizeCreate(Map<String, Object> props) {
        Map<String, Object> nprops = new HashMap<>();
        for (String pname : props.keySet()) {
            Object value = props.get(pname);
            PropertySchema pschema = properties.get(pname);
            if ("revision".equals(pname))
                continue;
            if (pschema == null)
                throw new GraphDatabaseException(String.format("Invalid property %s.%s", name, pname));
            if (pschema.isRevisioned())
                pname = String.format("%s[$revision]", pname);
            nprops.put(pname, value);
        }
        if (isRevisioned()) {
            Object rev = props.get("revision");
            nprops.put("revisions", new Object[]{rev});
        }
        return nprops;
    }

    public Map<String, Object> normalizeUpdate(Map<String, Object> props) {
        Map<String, Object> nprops = new HashMap<>();
        for (String pname : props.keySet()) {
            Object value = props.get(pname);
            PropertySchema pschema = properties.get(pname);
            if (pschema == null)
                throw new GraphDatabaseException(String.format("Invalid property %s.%s", name, pname));
            if (pschema.isRevisioned())
                pname = String.format("%s[$revision]", pname);
            nprops.put(pname, value);
        }
        if (isRevisioned()) {
            Object rev = props.get("revision");
            nprops.put("revisions", new Object[]{rev});
        }
        return nprops;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
