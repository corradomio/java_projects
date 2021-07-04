package org.example.m2;

public class Module2 {

    private String iAmHere = "I am here";

    public Module2() { }

    public void test() {
        System.out.printf("%s.test()", getClass().getName());
    }

}
