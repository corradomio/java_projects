package jext.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ValueTest {

    @Test
    void testValue() {
        Value v = Value.of(1);
        Assert.assertEquals(v.value, Integer.valueOf(1));
        Assert.assertEquals(v.op, Op.EQ);
        Assert.assertFalse(v.isCollection());
    }

    @Test
    void testIntArray() {
        Value v = Value.of(new int[]{1,2,3});
        Assert.assertEquals(v.value, new int[]{1,2,3});
        Assert.assertEquals(v.op, Op.EQ);
        Assert.assertTrue(v.isCollection());
        Assert.assertEquals(v.intArray(), new int[]{1,2,3});
    }

    @Test
    void testLongArray() {
        Value v = Value.of(new long[]{1,2,3});
        Assert.assertEquals(v.value, new long[]{1,2,3});
        Assert.assertEquals(v.op, Op.EQ);
        Assert.assertTrue(v.isCollection());
        Assert.assertEquals(v.intArray(), new int[]{1,2,3});
    }

    @Test
    void testCollection() {
        Value v = Value.of(Arrays.asList(1,2,3));
        Assert.assertEquals(v.value, Arrays.asList(1,2,3));
        Assert.assertEquals(v.op, Op.EQ);
        Assert.assertTrue(v.isCollection());
        Assert.assertEquals(v.intArray(), new int[]{1,2,3});
    }


    @Test
    void testOp() {
        Assert.assertEquals(Value.of("", 1).op, Op.EQ);
        Assert.assertEquals(Value.of("=", 1).op, Op.ASSIGN);
        Assert.assertEquals(Value.of("==", 1).op, Op.EQ);
        Assert.assertEquals(Value.of("!=", 1).op, Op.NEQ);
        Assert.assertEquals(Value.of("<>", 1).op, Op.NEQ);
        Assert.assertEquals(Value.of(">", 1).op, Op.GT);
        Assert.assertEquals(Value.of(">=", 1).op, Op.GEQ);
        Assert.assertEquals(Value.of("<=", 1).op, Op.LEQ);
        Assert.assertEquals(Value.of("<", 1).op, Op.LT);
        Assert.assertEquals(Value.of("in", Arrays.asList(1, 2)).op, Op.IN);
        Assert.assertEquals(Value.of("!in", Arrays.asList(1, 2)).op, Op.NOT_IN);
        Assert.assertEquals(Value.of("contains", Arrays.asList(1, 2)).op, Op.CONTAINS);
        Assert.assertEquals(Value.of("!contains", Arrays.asList(1, 2)).op, Op.NOT_CONTAINS);
        Assert.assertEquals(Value.of("incr", Arrays.asList(1, 2)).op, Op.INCR);
        Assert.assertEquals(Value.of("startsWith", "$").op, Op.STARTS_WITH);
        Assert.assertEquals(Value.of("endsWith", "$").op, Op.ENDS_WITH);
        Assert.assertEquals(Value.of("add", "$").op, Op.LIST_ADD);
        Assert.assertEquals(Value.of("!add", "$").op, Op.SET_ADD);
        Assert.assertEquals(Value.of("append", "$").op, Op.LIST_ADD);
        Assert.assertEquals(Value.of("!append", "$").op, Op.SET_ADD);
    }
}
