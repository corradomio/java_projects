package org.hls.check;

import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.NamedQueries;
import jext.logging.Logger;
import jext.util.PropertiesUtils;

import java.util.Map;
import java.util.Properties;

public class CheckNamedQueries {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        Map<String/*version*/, Map<String/*name*/, String/*body*/>> namedQueries = NamedQueries.load("config/namedqueries.xml");

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);
        gdb.registerVersionedQueries(namedQueries);


    }
}
