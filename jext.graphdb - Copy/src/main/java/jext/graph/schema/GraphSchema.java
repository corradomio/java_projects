package jext.graph.schema;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.ObjectCreateRule;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphSchema {

    // ----------------------------------------------------------------------
    // Load from file
    // ----------------------------------------------------------------------

    public static GraphSchema load(File schemaFile) throws ParserConfigurationException, IOException, SAXException {
        Digester d = new Digester();
        GraphSchema gschema = new GraphSchema();

        addRules(d, gschema);

        d.push(gschema);

        d.parse(schemaFile);

        return gschema;
    }

    private static void addRules(Digester d, GraphSchema gschema) {

        d.addObjectCreate("graphdb/schema/nodes/node/property", PropertySchema.class);
        d.addSetProperties("graphdb/schema/nodes/node/property");
        d.addSetNext( "graphdb/schema/nodes/node/property", "addProperty" );
        d.addObjectCreate("graphdb/schema/nodes/node", NodeSchema.class);
        d.addSetProperties("graphdb/schema/nodes/node");
        d.addSetNext( "graphdb/schema/nodes/node", "addNodeSchema" );

        ObjectCreateRule msc = new ObjectCreateRule(ModelSchema.class);
        msc.setConstructorArgumentTypes(GraphSchema.class);
        msc.setDefaultConstructorArguments(gschema);
        d.addRule("graphdb/schema/models/model", msc);
        d.addSetProperties("graphdb/schema/models/model");
        d.addSetNext( "graphdb/schema/models/model", "addModelSchema" );

        d.addObjectCreate("graphdb/schema/models/model/node", ModelNode.class);
        d.addSetProperties("graphdb/schema/models/model/node");
        d.addSetNext( "graphdb/schema/models/model/node", "addNode" );
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Map<String, NodeSchema>  nodeSchemas  = new HashMap<>();
    private Map<String, ModelSchema> modelSchemas = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GraphSchema() {

    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public NodeSchema getNodeSchema(String node) {
        return nodeSchemas.getOrDefault(node, NodeSchema.NO_SCHEMA);
    }

    public ModelSchema getModelSchema(String model) {
        return modelSchemas.get(model);
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void addNodeSchema(NodeSchema nschema) {
        nodeSchemas.put(nschema.getName(), nschema);
    }

    public void addModelSchema(ModelSchema mschema) {
        modelSchemas.put(mschema.getName(), mschema);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
