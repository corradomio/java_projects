package jext.graph.schema;

import jext.graph.Param;
import jext.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public abstract class ObjectSchema {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final int NO_REV = -1;
    public static final String REVISION = "revision";
    public static final String REVISIONS = "revisions";
    public static final String IN_REVISION = "inRevision";

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected final Logger logger = Logger.getLogger(getClass());

    protected String  name;
    protected boolean revisioned;
    protected boolean revisionedProperties;
    protected final Map<String, PropertySchema> properties = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String name() {
        return name;
    }

    public boolean isRevisioned() {
        return revisioned;
    }

    public boolean hasRevisionedProperties() {
        return revisionedProperties;
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

    protected void addProperty(PropertySchema pschema) {
        properties.put(pschema.name(), pschema);
        if (pschema.isRevisioned())
            revisionedProperties = true;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    private boolean skip(String name) {
        return REVISION.equals(name) || REVISIONS.equals(name);
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

            nprops.put(Param.at(name, rev), value);
        }
        return nprops;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
