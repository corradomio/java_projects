package jext.graph;

import jext.util.Parameters;
import org.testng.Assert;
import org.testng.annotations.Test;

import static jext.graph.neo4j.WhereFormatter.eblock;
import static jext.graph.neo4j.WhereFormatter.pblock;
import static jext.graph.neo4j.WhereFormatter.sblock;
import static jext.graph.neo4j.WhereFormatter.ublock;
import static jext.graph.neo4j.WhereFormatter.wblock;

public class BlockTest {

    @Test
    void testPblock() {
        Parameters params;
        params = Parameters.params("name", 1);
        Assert.assertEquals(pblock("n", params), " {name:$nname}");

        params = Parameters.params("name", 1, "$label", "l", "revision", 1);
        Assert.assertEquals(pblock("n", params), " {name:$nname}");

        Assert.assertEquals(wblock("n", params, false, true),
            " WHERE labels(n)[0] = $nlabel AND n.inRevision[$nrevision]");
        Assert.assertEquals(wblock("n", params, true, true),
            " AND labels(n)[0] = $nlabel AND n.inRevision[$nrevision]");

        Assert.assertEquals(sblock("n", params, true),
            " SET n.inRevision = apocx.coll.arraySet(n.inRevision, $nrevision, true)");
    }

    @Test
    void testPblockTwo() {
        Parameters params;

        params = Parameters.params(
            "name", 1,
            "$label", "l",
            "revision", 1,
            "digest[3]", 123
        );
        Assert.assertEquals(pblock("n", params), " {name:$nname}");

        Assert.assertEquals(wblock("n", params, false, true),
            " WHERE labels(n)[0] = $nlabel AND n.digest[3] = $ndigest AND n.inRevision[$nrevision]");

        Assert.assertEquals(sblock("n", params, true),
            " SET n.digest = apoc.coll.set(n.digest, 3, $ndigest), n.inRevision = apocx.coll.arraySet(n.inRevision, $nrevision, true)");
    }

    @Test
    void testEmptyBlocks() {
        Parameters params = Parameters.empty();

        Assert.assertEquals(pblock("n", params), "");
        Assert.assertEquals(wblock("n", params, false, false), "");
        Assert.assertEquals(wblock("n", params, true, false), "");
        Assert.assertEquals(sblock("n", params, true), "");
    }

    @Test
    void testEblock() {
        Parameters params = Parameters.empty();

        Assert.assertEquals(eblock("e", null, Direction.Any, false, params),
            "-[e]-");
        Assert.assertEquals(eblock("e", "", Direction.Any, true, params),
            "-[e*]-");

        Assert.assertEquals(eblock("e", "uses", Direction.Any, false, params),
            "-[e:uses]-");
        Assert.assertEquals(eblock("e", "uses", Direction.Input, false, params),
            "<-[e:uses]-");
        Assert.assertEquals(eblock("e", "uses", Direction.Output, false, params),
            "-[e:uses]->");
    }


    @Test
    void testUblock() {
        Parameters params;

        params = Parameters.empty();
        Assert.assertEquals(ublock("${where:name}", params, false), "");
        Assert.assertEquals(ublock("${and:name}", params, true), "");

        params = Parameters.params("name", Parameters.params("name", 1));
        Assert.assertEquals(ublock("${where:name}", params, false),
            " WHERE n.name = $nname");
        Assert.assertEquals(ublock("${and:name}", params, true),
            " AND n.name = $nname");
    }

    @Test
    void testAliasUblock() {
        Parameters params;

        params = Parameters.empty();
        Assert.assertEquals(ublock("${where:u:name}", params, false), "");
        Assert.assertEquals(ublock("${and:u:name}", params, true), "");

        params = Parameters.params("name", Parameters.params("name", 1));
        Assert.assertEquals(ublock("${where:u:name}", params, false),
            " WHERE u.name = $uname");
        Assert.assertEquals(ublock("${and:u:name}", params, true),
            " AND u.name = $uname");
    }
}
