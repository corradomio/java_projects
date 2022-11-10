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

    @AfterSuite
    void tearDown() {
        gdb.destroy();
    }

    @Test
    void testVersion() {
        GraphVersion gv = gdb.getVersion();
        Assert.assertEquals(gv.getMajorVersion(), 4);
        Assert.assertEquals(gv.getVersion(), "4.3.10");
    }

}
