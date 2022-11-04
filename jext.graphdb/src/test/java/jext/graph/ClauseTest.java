package jext.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static jext.graph.neo4j.WhereFormatter.formatClause;
import static jext.graph.neo4j.WhereFormatter.formatProp;

public class ClauseTest {

    @Test
    void testAssignment() {

        Assert.assertEquals(formatClause(Param.of("p","name", 1), Value.of("incr",1)),
            "p.name = apocx.coll.arrayIncr(p.name, 1, $pname)");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("incr",1)),
            "p.name = p.name + $pname");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("add",false)),
            "p.name = apocx.coll.listAdd(p.name, $pname)");

        Assert.assertEquals(formatClause(Param.of("p","name", 1), Value.of("=",1)),
            "p.name = apoc.coll.set(p.name, 1, $pname)");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("=",1)),
            "p.name = $pname");

        Assert.assertEquals(formatClause(Param.of("p","revision"), Value.of("=",1)),
            "p.inRevision = apocx.coll.arraySet(p.inRevision, $prevision, true)");

        Assert.assertEquals(formatClause(Param.of("p","inRevision", 1), Value.of("=",false)),
            "p.inRevision = apocx.coll.arraySet(p.inRevision, 1, $pinRevision)");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("!add",false)),
            "p.name = apocx.coll.setAdd(p.name, $pname)");
    }

    @Test
    void testSimple() {
        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of(1)),
            "p.name = $pname");

        Assert.assertEquals(formatClause(Param.of("p","name", 1), Value.of(1)),
            "p.name[1] = $pname");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of(">",1)),
            "p.name > $pname");
        Assert.assertEquals(formatClause(Param.of("p","name", 1), Value.of(">",1)),
            "p.name[1] > $pname");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("!=",1)),
            "p.name <> $pname");
        Assert.assertEquals(formatClause(Param.of("p","name", 1), Value.of("!=",1)),
            "p.name[1] <> $pname");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("!=",Arrays.asList(1))),
            "NOT(p.name IN $pname)");
        Assert.assertEquals(formatClause(Param.of("p","name", 1), Value.of("!=",Arrays.asList(1))),
            "NOT(p.name[1] IN $pname)");

        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of(Arrays.asList(1,2))),
            "p.name IN $pname");
    }

    @Test
    void testId() {
        Assert.assertEquals(formatClause(Param.of("p","id"), Value.of(1)),
            "id(p) = $pid");
        Assert.assertEquals(formatClause(Param.of("p","id"), Value.of(Arrays.asList(1,2))),
            "id(p) IN $pid");
    }

    @Test
    void testLabel() {
        Assert.assertEquals(formatClause(Param.of("p","$label"), Value.of(1)),
            "labels(p)[0] = $plabel");
        Assert.assertEquals(formatClause(Param.of("p","$label"), Value.of(Arrays.asList(1,2))),
            "labels(p)[0] IN $plabel");
    }

    @Test
    void testRevision() {

        Assert.assertEquals(formatClause(Param.of("p","revision"), Value.of(Arrays.asList(1))),
            "(p.inRevision[1])");

        Assert.assertEquals(formatClause(Param.of("p","revision"), Value.of(1)),
            "p.inRevision[$prevision]");

        Assert.assertEquals(formatClause(Param.of("p","revision"), Value.of(Arrays.asList())),
            "true");

        Assert.assertEquals(formatClause(Param.of("p","revision"), Value.of(Arrays.asList(1,2))),
            "(p.inRevision[1] OR p.inRevision[2])");
    }

    @Test
    void testDegree() {
        Assert.assertEquals(formatClause(Param.of("p","$degree", "uses"), Value.of(1)),
            "apoc.node.degree(p,uses) = $pdegree");
        Assert.assertEquals(formatClause(Param.of("p","$degree"), Value.of(1)),
            "apoc.node.degree(p) = $pdegree");

        Assert.assertEquals(formatClause(Param.of("p","$indegree", "uses"), Value.of(1)),
            "apoc.node.degree(p,uses<) = $pindegree");
        Assert.assertEquals(formatClause(Param.of("p","$indegree"), Value.of(1)),
            "apoc.node.degree(p,<) = $pindegree");

        Assert.assertEquals(formatClause(Param.of("p","$outdegree", "uses"), Value.of(1)),
            "apoc.node.degree(p,uses>) = $poutdegree");
        Assert.assertEquals(formatClause(Param.of("p","$outdegree"), Value.of(1)),
            "apoc.node.degree(p,>) = $poutdegree");
    }


    @Test
    void testCollection() {
        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("in", Arrays.asList(1,2))),
            "p.name IN $pname");
        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("!in", Arrays.asList(1,2))),
            "NOT(p.name IN $pname)");
    }

    @Test
    void testStrings() {
        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("startsWith", "$")),
            "p.name STARTS WITH $pname");
        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("endsWith", "$")),
            "p.name ENDS WITH $pname");
        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("contains", "$")),
            "p.name CONTAINS $pname");
        Assert.assertEquals(formatClause(Param.of("p","name"), Value.of("!contains", "$")),
            "NOT(p.name CONTAINS $pname)");
    }


    @Test
    void testProp() {
        Assert.assertEquals(formatProp(Param.of("p","name"), Value.of(1)),
            "name:$pname");
        Assert.assertEquals(formatProp(Param.of("p","name"), Value.of(Arrays.asList(1,2))),
            "");
    }

}
