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

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class CheckNeo4J {

    static final String REF_ID = "dde55a05";

    public static void main(String[] args) throws Exception {
        Logger.configure();
        CacheManager.configure();
        Properties props = PropertiesUtils.load("config/neo4j.properties");

        NamedIndices.load(new File("config/nindices.xml"));
        NamedQueries.load(new File("config/nqueries.xml"));
        GraphSchema.load(new File("config/dbschema.xml"));

        VGraphDatabase gdb = (VGraphDatabase) GraphDatabases.create(props);

        gdb.setGraphSchema(GraphSchema.load(new File("config/dbschema.xml")));
        gdb.setNamedQueries(NamedQueries.load(new File("config/nqueries.xml")));
        gdb.setNamedIndices(NamedIndices.load(new File("config/nindices.xml")));

        gdb.getNamedQueries().get().registerQueries(MapUtils.asMap("test", "MATCH (n:source {refId:$refId}) RETURN n"));

        try(GraphSession s = gdb.connect(REF_ID, "source", 2)) {
            s.queryUsing("test", Collections.emptyMap())
                .result().forEach(nv -> {
                    System.out.println(nv);
                });
        }

        gdb.destroy();
        CacheManager.shutdown();
    }
}
