package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.named.NamedIndices;
import jext.graph.named.NamedQueries;
import jext.graph.neo4j.VGraphDatabase;
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
        VGraphDatabase gdb = (VGraphDatabase)GraphDatabases.create(dbprops);
        gdb.setGraphSchema(graphSchema)
            .setNamedIndices(namedIndices)
            .setNamedQueries(namedQueries)
            ;

        try(GraphSession s = gdb.connect("123456")) {
            String nid = s.createNode("splproject", MapUtils.asMap(
                "name", "myName",
                "repository", "myRepository",
                "fullname", "myRepository/myName"
            ));
        }

        try(GraphSession s = gdb.connect("123456", "source", 0)) {
            String nid = s.createNode("msource", MapUtils.asMap(
                "name", "myName",
                "repository", "myRepository",
                "fullname", "myRepository/myName"
            ));
        }

        try(GraphSession s = gdb.connect("123456", "dependency", 0)) {
            String nid = s.createNode("module", MapUtils.asMap(
                "name", "test",
                "fullname", "base/test",
                "digest", 0
            ));
        }

    }
}
