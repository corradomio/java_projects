package org.hls.check;

import jext.graph.schema.GraphSchema;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReadSchema {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        GraphSchema gschema = GraphSchema.load(new File("config/dbschema.xml"));
    }
}
