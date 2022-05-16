package jext.graph;

import jext.net.URL;

import java.util.Properties;

public interface GraphDatabaseFactory {

    GraphDatabase newGraphDatabase(URL url, Properties props);
}
