package jext.graph.schema;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModelSchema {

    public static ModelSchema NO_SCHEMA = new ModelSchema(null) {
        // @Override
        // public Map<String, Object> normalizeCreate(NodeSchema nschema, Map<String, Object> nprops) {
        //     return nprops;
        // }
    };

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String name = "";
    private NodeSchema ref = null;
    private GraphSchema gschema;

    private Map<String, NodeSchema> nodes = new LinkedHashMap<>();

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

    public NodeSchema reference() {
        return ref;
    }

    public boolean isReference(String name) {
        return ref != null && ref.name().equals(name);
    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    // public Map<String, Object> normalizeCreate(NodeSchema nschema, Map<String, Object> nprops) {
    //     if (nschema == ref) {
    //         int rev = MapUtils.getInt(nprops, REVISION);
    //         nprops.put(REVISIONS, new int[]{rev});
    //     }
    //     return nprops;
    // }

    // public Map<String, Object> normalizeUpdate(NodeSchema nschema, Map<String, Object> nprops) {
    //     if (nschema == ref) {
    //         int rev = MapUtils.getInt(nprops, REVISION);
    //         int[] revs = MapUtils.getIntArray(nprops, REVISIONS);
    //         revs = PropertyUtils.appendUnique(revs, rev);
    //         nprops.put(REVISIONS, revs);
    //     }
    //     return nprops;
    // }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setRef(String ref) {
        this.ref = gschema.nodeSchema(ref);
        nodes.put(ref, this.ref);
    }

    public void addNode(ModelNode mnode) {
        NodeSchema nschema = gschema.nodeSchema(mnode.ref());
        nodes.put(nschema.name(), nschema);
    }

    // ----------------------------------------------------------------------
    // Debug
    // ----------------------------------------------------------------------

    public void dump() {
        System.out.printf("    %s (%s)\n", name, ref.name());
        nodes.forEach((name, schema) -> {
            System.out.printf("      %s\n", schema.name());
        });
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
