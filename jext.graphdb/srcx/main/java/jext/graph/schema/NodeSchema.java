package jext.graph.schema;

import jext.graph.util.PropertyUtils;
import jext.logging.Logger;
import jext.util.MapUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeSchema {

    static final String REVISION = "revision";
    static final String REVISIONS = "revisions";
    static final String IN_REVISION = "inRevision";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(NodeSchema.class);

    private String name = "";
    private boolean revisioned;

    private Map<String, PropertySchema> properties = new HashMap<>();
    private List<String> unique = new ArrayList<>();

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

    public boolean isRevisioned(String name) {
        if (properties.containsKey(name))
            return properties.get(name).isRevisioned();
        else
            return false;
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

    public void addProperty(PropertySchema pschema) {
        properties.put(pschema.getName(), pschema);
        if (pschema.isUnique())
            unique.add(pschema.getName());
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    public PropertySchema propertySchema(String name) {
        PropertySchema pschema = properties.get(name);
        if (pschema == null) {
            logger.errorf("Invalid property %s.%s", this.name, name);
            pschema = PropertySchema.of(name, null);
        }
        return pschema;
    }

    public Map<String,Object> uniqueProperties(Map<String,Object> props) {
        if (props.isEmpty() || unique.isEmpty())
            return Collections.emptyMap();

        Map<String,Object> uprops = new HashMap<>();
        for (String uname : unique)
            uprops.put(uname, props.get(uname));

        return uprops;
    }

    public Map<String,Object> normalizeCreate(Map<String,Object> cprops) {
        int rev = MapUtils.getOrDefault(cprops, REVISION, -1);
        Map<String,Object> nprops = new HashMap<>();
        for (String name : cprops.keySet()) {
            Object value = cprops.get(name);
            if (REVISION.equals(name))
                continue;

            PropertySchema pschema = propertySchema(name);
            nprops.put(name, pschema.asRevisioned(rev, value));
        }
        if (isRevisioned()) {
            nprops.put(IN_REVISION, PropertyUtils.boolArray(rev, true));
        }
        return nprops;
    }

    public Map<String,Object> normalizeUpdate(Map<String,Object> cprops, Map<String,Object> uprops) {
        int rev = MapUtils.getOrDefault(uprops, REVISION, -1);
        Map<String,Object> nprops = new HashMap<>();
        for (String name : uprops.keySet()) {
            Object uvalue = uprops.get(name);
            Object cvalue = cprops.get(name);
            if (REVISION.equals(name))
                continue;

            PropertySchema pschema = propertySchema(name);
            nprops.put(name, pschema.asRevisioned(cvalue, rev, uvalue));
        }
        if (isRevisioned()) {
            Object revs = cprops.get(IN_REVISION);
            nprops.put(IN_REVISION, PropertyUtils.boolArray(revs, rev, true));
        }
        return nprops;
    }

    public Map<String,Object> normalizeQuery(Map<String,Object> qprops) {
        int rev = MapUtils.getOrDefault(qprops, REVISION, -1);
        Map<String,Object> nprops = new HashMap<>();
        for (String name : qprops.keySet()) {
            Object value = qprops.get(name);
            if (REVISION.equals(name))
                continue;

            PropertySchema pschema = propertySchema(name);
            nprops.put(pschema.atRevision(name, rev), value);
        }
        return nprops;
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
