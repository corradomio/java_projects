package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;
import jext.graph.schema.GraphSchema;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.PropertiesUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CheckGraphSchema {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Logger.configure();
        CacheManager.configure();

        NamedQueries namedQueries = NamedQueries.load(new File("config/nqueries.xml"));
        NamedIndices namedIndices = NamedIndices.load(new File("config/nindices.xml"));
        GraphSchema  graphSchema  = GraphSchema.load( new File("config/dbschema.xml"));
        Properties  dbprops = PropertiesUtils.load(   new File("config/neo4j.properties"));
        GraphDatabase gdb = GraphDatabases.create(dbprops)
            .setGraphSchema(graphSchema)
            .setNamedQueries(namedQueries)
            .setNamedIndices(namedIndices);

        try(GraphSession s = gdb.connect("test", "general", 0)) {
            String nid = s.createNode("splserver", MapUtils.asMap(

            ));
        }

    }
}
