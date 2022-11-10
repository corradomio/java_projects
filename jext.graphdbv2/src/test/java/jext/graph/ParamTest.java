package jext.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ParamTest {

    @Test
    void testParam() {
        Param q = Param.of("q", "name");
        Param p = Param.of("n", q);

        Assert.assertEquals(p.param, "name");
        Assert.assertEquals(p.alias, "q");

        Object o = "name";
        p = Param.of("n", o);

        Assert.assertEquals(p.param, "name");
        Assert.assertEquals(p.alias, "n");

    }

    @Test
    void testName() {
        Param p = Param.of("n", "name");
        Assert.assertEquals(p.alias, "n");
        Assert.assertEquals(p.param, "name");
        Assert.assertEquals(p.name, "name");
        Assert.assertEquals(p.aname, "n.name");
        Assert.assertEquals(p.pname, "$nname");
        Assert.assertEquals(p.index, -1);
        Assert.assertNull(p.key);
    }

    @Test
    void testSpecial() {
        Param p = Param.of("n", "$name");

        Assert.assertEquals(p.alias, "n");
        Assert.assertEquals(p.param, "$name");

        Assert.assertEquals(p.name, "name");
        Assert.assertEquals(p.aname, "n.name");
        Assert.assertEquals(p.pname, "$nname");
        Assert.assertEquals(p.index, -1);
        Assert.assertNull(p.key);
    }

    @Test
    void testArray() {
        String param = Param.at("name", 3);
        Param p = Param.of("n", param);

        Assert.assertEquals(p.alias, "n");
        Assert.assertEquals(p.param, "name[3]");

        Assert.assertEquals(p.name, "name");
        Assert.assertEquals(p.aname, "n.name[3]");
        Assert.assertEquals(p.pname, "$nname");
        Assert.assertEquals(p.index, 3);
        Assert.assertNull(p.key);
    }

    @Test
    void testKey() {
        String param = Param.at("name", "key");
        Param p = Param.of("n", param);

        Assert.assertEquals(p.alias, "n");
        Assert.assertEquals(p.param, "name[key]");

        Assert.assertEquals(p.name, "name");
        Assert.assertEquals(p.aname, "n.name");
        Assert.assertEquals(p.pname, "$nname");
        Assert.assertEquals(p.index, -1);
        Assert.assertEquals(p.key, "key");
    }


    // LIST_ADD("listAdd"),
    // SET_ADD("setAdd"),
    //
    // // deprecated
    // APPEND("listAdd"),
    // APPEND_DISTINCT("setAdd")
    //     ;

}
