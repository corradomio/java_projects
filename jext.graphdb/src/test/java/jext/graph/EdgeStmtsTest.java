package jext.graph;

import jext.util.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;

public class EdgeStmtsTest {

    int N = 10;
    GraphDatabase gdb;
    String[] ids = new String[N];
    String id1, id2;

    @BeforeSuite
    void setUp() {
        gdb = GraphDatabases.create(new File("data/neo4j.properties"));
        deleteNodes();
        createNodes();
    }

    @AfterSuite
    void tearDown() {
        // deleteNodes();
        gdb.destroy();
    }

    void createNodes() {
        try(GraphSession s = gdb.connect()) {
            for (int i=0; i<10; ++i) {
                String id = s.createNode("test", Parameters.params("index", i));
                ids[i] = id;
            }
        }
        id1 = ids[1];
        id2 = ids[2];
    }

    void deleteNodes() {
        try(GraphSession s = gdb.connect()) {
            s.deleteNodes("test", Parameters.empty());
        }
    }

    @Test
    void createEdge() {
        try(GraphSession s = gdb.connect()) {
            String edgeId = s.createEdge("edge", id1, id2);
            Assert.assertNotNull(edgeId);

            String neweId = s.findEdge("edge", id1, id2);
            Assert.assertEquals(edgeId, neweId);
        }
    }

}
