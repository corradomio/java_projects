package jext.graph;

import jext.util.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

public class ConnectTest {

    GraphDatabase gdb;

    @BeforeSuite
    void setUp() {
        gdb = GraphDatabases.create(new File("data/neo4j.properties"));
    }

    @Test
    void testVersion() {
        GraphVersion gv = gdb.getVersion();
        Assert.assertEquals(gv.getMajorVersion(), 4);
        Assert.assertEquals(gv.getVersion(), "4.3.10");
    }

    @Test
    void testCreateNode() {
        String nodeId;
        try(GraphSession s = gdb.connect()) {
            s.deleteNodes("test", Parameters.empty());

            nodeId = s.createNode("test", Parameters.empty());
            Assert.assertNotNull(nodeId);
        }

        try(GraphSession s = gdb.connect()) {
            Assert.assertTrue(s.existsNode(nodeId));

            Map<String, Object> nv = s.getNodeProperties(nodeId);
            Assert.assertNotNull(nv);
            // $labels
            // $id
            // $type
            Assert.assertEquals(nv.size(), 3);
        }

        try(GraphSession s = gdb.connect()) {
            Assert.assertTrue(s.deleteNode(nodeId));

            Map<String, Object> nv = s.getNodeProperties(nodeId);
            Assert.assertNull(nv);
            Assert.assertFalse(s.existsNode(nodeId));
        }

        Parameters params = Parameters.params(
            "name", "test",
            "fullname", "org.hls.test"
        );

        Parameters query = Parameters.params(
            "fullname", "org.hls.test"
        );

        try(GraphSession s = gdb.connect()) {
            nodeId = s.createNode("test", params);

            Assert.assertNotNull(nodeId);
        }

        try(GraphSession s = gdb.connect()) {
            Assert.assertTrue(s.existsNode("test", query));
            Assert.assertEquals(s.countNodes("test", query), 1);

            Assert.assertEquals(s.findNode("test", query), nodeId);
            Map<String, Object> nv = s.findNodeProperties("test", query);
            Assert.assertNotNull(nv);
            Assert.assertTrue(nv.containsKey("name"));
            Assert.assertTrue(nv.containsKey("fullname"));
        }

    }

    @AfterSuite
    void tearDown() {
        gdb.destroy();
    }
}
