package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.named.NamedQueries;
import jext.logging.Logger;
import jext.util.PropertiesUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CheckNamedQueries {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Logger.configure();
        CacheManager.configure();

        Properties props = PropertiesUtils.load("config/neo4j.properties");
        NamedQueries namedQueries = NamedQueries.load(new File("config/nqueries.xml"));

        GraphDatabase gdb = GraphDatabases.create(props)
            .setNamedQueries(namedQueries);

    }
}
