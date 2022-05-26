package jext.graph.schema;

import java.util.HashMap;
import java.util.Map;

public class ModelSchema {

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

    public String getName() {
        return name;
    }

    public NodeSchema getReferenceNode() {
        return refNode;
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setRef(String ref) {
        this.refNode = gschema.getNodeSchema(ref);
    }

    public void addNode(ModelNode mnode) {
        NodeSchema nschema = gschema.getNodeSchema(mnode.getRef());
        nodes.put(nschema.getName(), nschema);
    }
}
