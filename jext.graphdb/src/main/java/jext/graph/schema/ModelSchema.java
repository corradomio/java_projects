package jext.graph.schema;

import jext.graph.util.PropertyUtils;
import jext.util.ArrayUtils;
import jext.util.MapUtils;

import java.util.HashMap;
import java.util.Map;

import static jext.graph.schema.NodeSchema.REVISION;
import static jext.graph.schema.NodeSchema.REVISIONS;

public class ModelSchema {

    public static ModelSchema NO_SCHEMA = new ModelSchema(null) {
        @Override
        public Map<String, Object> normalizeCreate(NodeSchema nschema, Map<String, Object> nprops) {
            return nprops;
        }
    };

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String name = "";
    private NodeSchema refNode = null;
    private GraphSchema gschema;

    private Map<String, NodeSchema> nodes = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ModelSchema(GraphSchema gschema) {
        this.gschema = gschema;
    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public String name() {
        return name;
    }

    public NodeSchema referenceNode() {
        return refNode;
    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public Map<String, Object> normalizeCreate(NodeSchema nschema, Map<String, Object> nprops) {
        if (refNode == nschema) {
            int rev = MapUtils.getInt(nprops, REVISION);
            nprops.put(REVISIONS, new int[]{rev});
        }
        return nprops;
    }

    public Map<String, Object> normalizeUpdate(NodeSchema nschema, Map<String, Object> nprops) {
        if (refNode == nschema) {
            int rev = MapUtils.getInt(nprops, REVISION);
            int[] revs = MapUtils.getIntArray(nprops, REVISIONS);
            revs = PropertyUtils.appendUnique(revs, rev);
            nprops.put(REVISIONS, revs);
        }
        return nprops;
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setRef(String ref) {
        this.refNode = gschema.nodeSchema(ref);
    }

    public void addNode(ModelNode mnode) {
        NodeSchema nschema = gschema.nodeSchema(mnode.ref());
        nodes.put(nschema.name(), nschema);
    }
}