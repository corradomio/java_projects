package jext.graph.schema;

import jext.graph.GraphDatabaseException;
import jext.graph.util.PropertyUtils;
import jext.util.MapUtils;

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
    static final String REVISION = "revision";
    static final String REVISIONS = "revisions";
    static final String IN_REVISION = "inRevision";

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

    public String name() {
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

    public PropertySchema propertySchema(String pname) {
        PropertySchema pschema = properties.get(pname);
        if (pschema == null)
            throw new GraphDatabaseException(String.format("Invalid property %s.%s", name, pname));
        return pschema;
    }

    public Map<String, Object> uniqueProps(Map<String, Object> nprops) {
        if (nprops.isEmpty() || unique.isEmpty())
            return Collections.emptyMap();

        Map<String, Object> uprops = new HashMap<>();
        for (String uname : unique)
            uprops.put(uname, nprops.get(uname));

        return uprops;
    }

    public Map<String, Object> normalizeCreate(Map<String, Object> props) {
        Map<String, Object> nprops = new HashMap<>();
        int rev = MapUtils.getOrDefault(nprops, REVISION, -1);
        for (String pname : props.keySet()) {
            Object value = props.get(pname);
            if (REVISION.equals(pname))
                continue;

            PropertySchema pschema = propertySchema(pname);
            nprops.put(pname, pschema.asRevisioned(rev, value));
        }
        if (isRevisioned()) {
            nprops.put(IN_REVISION, PropertyUtils.boolArray(rev, true));
        }
        return nprops;
    }

    public Map<String, Object> normalizeUpdate(Map<String, Object> cprops, Map<String, Object> uprops) {
        Map<String, Object> nprops = new HashMap<>();
        int rev = MapUtils.getOrDefault(nprops, REVISION, -1);
        for (String pname : uprops.keySet()) {
            Object uvalue = uprops.get(pname);
            Object cvalue = cprops.get(pname);
            if (REVISION.equals(pname))
                continue;

            PropertySchema pschema = propertySchema(pname);
            nprops.put(pname, pschema.asRevisioned(cvalue, rev, uvalue));
        }
        if (isRevisioned()) {
            Object revs = cprops.get(IN_REVISION);
            nprops.put(IN_REVISION, PropertyUtils.boolArray((boolean[])revs, rev, true));
        }
        return nprops;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
