package jext.graph.schema;

import jext.graph.GraphDatabaseException;
import jext.util.logging.Logger;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.ObjectCreateRule;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class GraphSchema {

    // ----------------------------------------------------------------------
    // Load from file
    // ----------------------------------------------------------------------

    public static GraphSchema load(File schemaFile) throws GraphDatabaseException {
        Digester d = new Digester();
        GraphSchema gschema = new GraphSchema();

        addRules(d, gschema);

        d.push(gschema);

        try {
            d.parse(schemaFile);
        } catch (IOException | SAXException e) {
            throw new GraphDatabaseException(e);
        }

        return gschema;
    }

    private static void addRules(Digester d, GraphSchema gschema) {

        // node property
        d.addObjectCreate("graphdb/schema/nodes/node/property", PropertySchema.class);
        d.addSetProperties("graphdb/schema/nodes/node/property");
        // property -> node
        d.addSetNext( "graphdb/schema/nodes/node/property", "addProperty" );

        // node
        d.addObjectCreate("graphdb/schema/nodes/node", NodeSchema.class);
        d.addSetProperties("graphdb/schema/nodes/node");
        // node -> graph
        d.addSetNext( "graphdb/schema/nodes/node", "addNodeSchema" );

        // edge property
        d.addObjectCreate("graphdb/schema/edges/edge/property", PropertySchema.class);
        d.addSetProperties("graphdb/schema/edges/edge/property");
        // property -> node
        d.addSetNext( "graphdb/schema/edges/edge/property", "addProperty" );

        // edge
        d.addObjectCreate("graphdb/schema/edges/edge", EdgeSchema.class);
        d.addSetProperties("graphdb/schema/edges/edge");
        // edge -> graph
        d.addSetNext( "graphdb/schema/edges/edge", "addEdgeSchema" );

        // model
        ObjectCreateRule msc = new ObjectCreateRule(ModelSchema.class);
        msc.setConstructorArgumentTypes(GraphSchema.class);
        msc.setDefaultConstructorArguments(gschema);
        d.addRule("graphdb/schema/models/model", msc);
        d.addSetProperties("graphdb/schema/models/model");
        // model -> graph
        d.addSetNext( "graphdb/schema/models/model", "addModelSchema" );

        // modelNode
        d.addObjectCreate("graphdb/schema/models/model/node", ModelNode.class);
        d.addSetProperties("graphdb/schema/models/model/node");
        // modelNode -> model
        d.addSetNext( "graphdb/schema/models/model/node", "addNode" );
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(GraphSchema.class);

    private Map<String, NodeSchema>  nodeSchemas  = new TreeMap<>();
    private Map<String, EdgeSchema>  edgeSchemas  = new TreeMap<>();
    private Map<String, ModelSchema> modelSchemas = new TreeMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GraphSchema() {

    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public NodeSchema nodeSchema(String node) {
        NodeSchema nschema = nodeSchemas.get(node);
        if (nschema == null) {
            logger.errorf("Unknown node %s", node);
            nschema = NodeSchema.of(node);
            addNodeSchema(nschema);
        }
        return nschema;
    }

    public EdgeSchema edgeSchema(String edge) {
        EdgeSchema eschema = edgeSchemas.get(edge);
        if (eschema == null) {
            logger.errorf("Unknown edge %s", edge);
            eschema = EdgeSchema.of(edge);
            addEdgeSchema(eschema);
        }
        return eschema;
    }

    public ModelSchema modelSchema(String model) {
        if (model == null)
            return ModelSchema.NO_SCHEMA;

        ModelSchema mschema = modelSchemas.get(model);
        if (mschema == null) {
            logger.errorf("Unknown model %s", model);
            mschema = ModelSchema.NO_SCHEMA;
            addModelSchema(mschema);
        }
        return mschema;
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void addNodeSchema(NodeSchema nschema) {
        nodeSchemas.put(nschema.name(), nschema);
    }

    public void addEdgeSchema(EdgeSchema eschema) {
        edgeSchemas.put(eschema.name(), eschema);
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
    // Debug
    // ----------------------------------------------------------------------

    public void dump() {
        System.out.println("Graph schema");
        System.out.println("  Nodes:");
        nodeSchemas.forEach((name, schema) -> {
            schema.dump();
        });
        System.out.println("  Models:");
        modelSchemas.forEach((name, schema) -> {
            schema.dump();
        });
        System.out.println("done");
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
