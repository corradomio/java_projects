package jext.graph;

import jext.util.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NodeStmtsTest {

    int N = 10;
    GraphDatabase gdb;
    List<String> ids = new ArrayList<>();

    @BeforeSuite
    void setUp() {
        gdb = GraphDatabases.create(new File("data/neo4j.properties"));
        deleteNodes();
    }

    @AfterSuite
    void tearDown() {
        // deleteNodes();
        gdb.destroy();
    }

    void createNodes() {
        try(GraphSession s = gdb.connect()) {
            for (int i=0; i<N; ++i) {
                String id = s.createNode("test", Parameters.params("index", i));
                ids.add(id);
            }
        }
    }

    void deleteNodes() {
        try(GraphSession s = gdb.connect()) {
            s.deleteNodes("test", Parameters.empty());
        }
    }

    @Test
    void testCreateEdge() {
        String nodeId;
        try(GraphSession s = gdb.connect()) {
            s.deleteEdges("edge", null, null, Parameters.empty());
        }

    }

    @Test
    void testInvalidNode() {
        try(GraphSession s = gdb.connect()) {
            Assert.assertFalse(s.existsNode(null));

            Assert.assertFalse(s.deleteNode(null));

            Assert.assertNull(s.getNodeProperties(null));
        }
    }

    @Test
    void testEmptyIdList() {
        try(GraphSession s = gdb.connect()) {
            Assert.assertEquals(s.deleteNodes(Collections.emptyList()), 0);
        }
    }

    @Test
    void testIdList() {
        try(GraphSession s = gdb.connect()) {
            createNodes();
            List<Map<String,Object>> values = s.getNodesProperties(ids);

            Assert.assertEquals(values.size(), ids.size());
        }
    }

    @Test
    void setSetProperties() {
        try(GraphSession s = gdb.connect()) {
            createNodes();
            long n = s.setNodesProperties(ids, Parameters.params("example", true));
            List<Map<String,Object>> values = s.getNodesProperties(ids);

            Assert.assertEquals(values.size(), ids.size());
            Assert.assertEquals(values.size(), n);
        }
    }

    @Test
    void testCreateOrUpdateNode() {
        try(GraphSession s = gdb.connect()) {
            s.deleteNodes("test", Parameters.params("index", 11));
            Assert.assertFalse(s.existsNode("test", Parameters.params("index", 11)));
            String id1 = s.createNode("test",
                Parameters.params("index", 11),
                Parameters.params("update", 1));
            Assert.assertTrue(s.existsNode("test", Parameters.params("index", 11)));
            Assert.assertEquals(((Number)s.getNodeProperties(id1).get("update")).intValue(), 1);

            String id2 = s.createNode("test",
                Parameters.params("index", 11),
                Parameters.params("update", 2));

            Assert.assertEquals(id1, id2);
            Assert.assertEquals(((Number)s.getNodeProperties(id1).get("update")).intValue(), 2);
        }
    }

    @Test
    void testSetNodeProperties() {
        try(GraphSession s = gdb.connect()) {
            s.setNodesProperty("test", Parameters.params("index", 11), "update", 12);
            Assert.assertEquals(((Number)s.getNodeProperties("test", Parameters.params("index", 11))
                .get("update")).intValue(), 12);
        }
    }

}
