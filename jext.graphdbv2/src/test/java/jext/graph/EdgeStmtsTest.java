package jext.graph;

import jext.util.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class EdgeStmtsTest {

    String TEST = "test2";
    String EDGE = "edge";
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
        gdb.destroy();
    }

    void createNodes() {
        try(GraphSession s = gdb.connect()) {
            for (int i=0; i<N; ++i) {
                String id = s.createNode(TEST, Parameters.params("index", i));
                ids[i] = id;
            }
        }
        id1 = ids[1];
        id2 = ids[2];
    }

    void deleteNodes() {
        try(GraphSession s = gdb.connect()) {
            s.deleteNodes(TEST, Parameters.empty());
        }
    }

    @Test
    void testCreateEdge() {
        try(GraphSession s = gdb.connect()) {
            Assert.assertTrue(s.existsNode(id1));
            Assert.assertTrue(s.existsNode(id2));

            String edgeId = s.createEdge(EDGE, id1, id2);
            Assert.assertNotNull(edgeId);

            String neweId = s.findEdge(EDGE, id1, id2);
            Assert.assertEquals(edgeId, neweId);
        }
    }

    @Test
    void testCreateEdges() {
        try(GraphSession s = gdb.connect()) {
            List<String> nids = Arrays.asList(ids).subList(5, 10);
            Assert.assertEquals(s.createEdges(EDGE, id1, nids, Parameters.params("check", 22)), 5);

            String edgeId = s.createEdge(EDGE,
                Parameters.params("index", 3),
                Parameters.params("index", 4),
                Parameters.empty(),
                Parameters.params("check", 22));
        }
    }
}
