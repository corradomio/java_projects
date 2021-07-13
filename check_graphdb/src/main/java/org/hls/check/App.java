package org.hls.check;

import apoc.coll.CollExt;
import apoc.rev.Revision;
import jext.cache.CacheManager;
import jext.graph.GraphDatabase;
import jext.graph.GraphDatabases;
import jext.graph.GraphSession;
import jext.graph.Name;
import jext.graph.Value;
import jext.logging.Logger;
import jext.util.MapUtils;
import jext.util.Parameters;
import jext.util.PropertiesUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        CollExt collx = new CollExt();
        Revision rev = new Revision();
        rev.test(Arrays.asList(true,false,true), Arrays.asList(0L,1L));

        // System.out.println(collx.setOrExtend2(Arrays.asList("1,2", "2,3"), 1, 1, 22));
        // System.out.println(collx.setOrExtend2(Arrays.asList("1,2", "2,3"), 4, 4, 44));

        GraphDatabase gdb = GraphDatabases.newGraphDatabase(props);

        String nodeId;
        long count;
        List<Map<String, Object>> r;
        try(GraphSession s = gdb.connect()) {
            count = s.countNodes("method", MapUtils.asMap(
                Name.of("name"), "$classInitializer",
                "$outdegree", Value.of(">", 0)
            ));
        }
        System.out.println(count);

        gdb.destroy();
    }
}
