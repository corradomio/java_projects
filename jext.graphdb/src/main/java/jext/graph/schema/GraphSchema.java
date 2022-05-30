package jext.graph.schema;

import jext.graph.GraphDatabaseException;
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

    public Map<String, Object> uniqueProps(String nodeType, Map<String, Object> nprops) {
        NodeSchema nodeSchema = nodeSchema(nodeType);
        return nodeSchema.uniqueProps(nprops);
    }

    public NodeSchema nodeSchema(String node) {
        NodeSchema nschema = nodeSchemas.get(node);
        if (nschema == null)
            throw new GraphDatabaseException(String.format("Node '%s' not defined", node));
        else
            return nschema;
    }

    public ModelSchema modelSchema(String model) {
        if (model == null)
            return ModelSchema.NO_SCHEMA;
        else
            return modelSchemas.get(model);
    }

    // public boolean isModelReference(String model, String nodeType) {
    //     if (isEmpty(model) || isEmpty(nodeType))
    //         return false;
    //     ModelSchema modelSchema = modelSchemas.get(model);
    //     if (modelSchema == null)
    //         return false;
    //     NodeSchema nodeSchema = modelSchema.referenceNode();
    //     if (nodeSchema == null)
    //         return false;
    //     else
    //         return nodeSchema.name().equals(nodeType);
    // }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void addNodeSchema(NodeSchema nschema) {
        nodeSchemas.put(nschema.name(), nschema);
    }

    public void addModelSchema(ModelSchema mschema) {
        modelSchemas.put(mschema.name(), mschema);
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    private static boolean isEmpty(String s){
        return s == null || s.length() == 0;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
