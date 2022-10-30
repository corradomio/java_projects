package jext.graph.schema;

import jext.logging.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeSchema {

    public static NodeSchema of(String name) {
        NodeSchema nschema = new NodeSchema();
        nschema.name = name;
        return nschema;
    }

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final int NO_REV = -1;
    public static final String REVISION = "revision";
    public static final String REVISIONS = "revisions";
    public static final String IN_REVISION = "inRevision";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(NodeSchema.class);

    private String  name;
    private boolean revisioned;

    private final Map<String, PropertySchema> properties = new HashMap<>();
    private final List<String> unique = new ArrayList<>();

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
    // Called from the digester

    public void setName(String name) {
        this.name = name;
    }

    public void setRevisioned(boolean revisioned) {
        this.revisioned = revisioned;
    }

    public void addProperty(PropertySchema pschema) {
        properties.put(pschema.name(), pschema);
        if (pschema.isUnique())
            unique.add(pschema.name());
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    private boolean skip(String name) {
        return REVISION.equals(name) || REVISIONS.equals(name);
    }

    public PropertySchema propertySchema(String name) {
        PropertySchema pschema = properties.get(name);
        if (pschema == null) {
            logger.warnf("Unknown property %s.%s", this.name, name);
            pschema = PropertySchema.of(name, null);
            addProperty(pschema);
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

    public Map<String,Object> normalizeCreate(Map<String,Object> cprops, int rev) {
        for (String name : cprops.keySet()) {
            // skip special names
            if (skip(name))
                continue;

            Object cvalue = cprops.get(name);
            PropertySchema pschema = propertySchema(name);
            cprops.put(name, pschema.asRevisioned(cvalue, rev));
        }
        return cprops;
    }

    public Map<String,Object> normalizeUpdate(Map<String,Object> cprops, Map<String,Object> pprops, int rev) {
        Map<String,Object> uprops = new HashMap<>();
        for (String name : cprops.keySet()) {
            // skip special names
            if (skip(name))
                continue;

            PropertySchema pschema = propertySchema(name);

            // skip not revisioned vields
            if (!pschema.isRevisioned())
                continue;

            Object cvalue = cprops.get(name);
            Object pvalue = pprops.get(name);

            uprops.put(name, pschema.asRevisioned(cvalue, pvalue, rev));
        }

        return uprops;
    }

    public Map<String,Object> normalizeQuery(Map<String,Object> qprops, int rev) {
        Map<String,Object> nprops = new HashMap<>();
        for (String name : qprops.keySet()) {
            Object value = qprops.get(name);
            if (skip(name))
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
