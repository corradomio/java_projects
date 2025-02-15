package jext.graph.schema;

import jext.graph.Param;
import jext.util.logging.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static jext.graph.neo4j.Neo4JOnlineSession.REVISION;
import static jext.graph.neo4j.Neo4JOnlineSession.REVISIONS;

public abstract class ObjectSchema {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final int NO_REV = -1;

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected final Logger logger = Logger.getLogger(getClass());

    protected String  name;
    protected boolean revisioned;
    protected boolean counted;
    protected final Set<String> revisionedProperties = new HashSet<>();
    protected final Set<String> revisionedArrays = new HashSet<>();
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
        return !revisionedProperties.isEmpty();
    }

    public boolean hasRevisionedArrayProperties() {
        return !revisionedArrays.isEmpty();
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

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    public Map<String, Object> revisionedArrays(Map<String, Object> props) {
        if (props.isEmpty() || revisionedArrays.isEmpty())
            return Collections.emptyMap();

        Map<String, Object> aprops = new HashMap<>();
        for (String param : props.keySet()) {
            String aname = Param.anameOf(param);
            if (revisionedArrays.contains(param))
                aprops.put(param, props.get(param));
        }
        return aprops;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setRevisioned(boolean revisioned) {
        this.revisioned = revisioned;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    public void addProperty(PropertySchema pschema) {
        properties.put(pschema.name(), pschema);
        if (pschema.isRevisioned()) {
            revisionedProperties.add(pschema.name());
            if (pschema.isArray())
                revisionedArrays.add(pschema.name());
        }
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    private boolean skip(String name) {
        return REVISION.equals(name) || REVISIONS.equals(name);
    }

    public Map<String, Object> normalizeCreate(Map<String, Object> props, int rev) {
        for (String name : new ArrayList<>(props.keySet())) {
            // skip special names
            if (skip(name))
                continue;

            PropertySchema pschema = propertySchema(name);
            if (!pschema.isRevisioned())
                continue;

            Object value = props.get(name);
            props.remove(name);
            props.put(Param.at(name, rev), value);
            // props.put(name, pschema.asRevisioned(value, rev));
        }
        return props;
    }

    public Map<String, Object> normalizeUpdate(Map<String, Object> cprops, Map<String, Object> pprops, int rev) {
        Map<String, Object> uprops = new HashMap<>();
        for (String name : cprops.keySet()) {
            // skip special names
            if (skip(name))
                continue;

            PropertySchema pschema = propertySchema(name);
            if (!pschema.isRevisioned())
                continue;

            // Object cvalue = cprops.get(name);
            // Object pvalue = pprops.get(name);
            // uprops.put(name, pschema.asRevisioned(cvalue, pvalue, rev));
            uprops.put(Param.at(name, rev), cprops.get(name));
        }

        return uprops;
    }

    public Map<String, Object> normalizeQuery(Map<String, Object> qprops, int rev) {
        Map<String, Object> nprops = new HashMap<>();
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
