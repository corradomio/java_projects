package org.example.m2;

public class Module2 {

    private String iAmHere = "I am here";
    private long timestamp = currentTimeMillis();

    public Module2() { }

    public void test() {
        System.out.printf("%s.test()", getClass().getName());
    }

    private static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    static {
        long globalTimestamp = currentTimeMillis();
    }

}
