package org.example.m3;

import org.example.m2.Module2;

public class Module3 {

    Module2 m2;

    // public void test() {
    //     System.out.printf("%s.test()", getClass().getName());
    // }

    public String test3(String who) {
        m2.test();
        return "";
    }
}
