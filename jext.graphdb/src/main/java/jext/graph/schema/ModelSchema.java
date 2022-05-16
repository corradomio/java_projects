package jext.graph.schema;

import java.util.HashMap;
import java.util.Map;

public class ModuleSchema {

    private String name;
    private NodeSchema refnode;

    private Map<String, NodeSchema> nodes = new HashMap<>();

    public ModuleSchema() {

    }

    public ModuleSchema(String name, NodeSchema refnode) {
        this.name = name;
        this.refnode = refnode;
    }

    public String getName() {
        return name;
    }

    public void addNode(NodeSchema mnode) {
        nodes.put(mnode.getName(), mnode);
    }
}
