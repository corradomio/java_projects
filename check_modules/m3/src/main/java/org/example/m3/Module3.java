package org.example.m3;

import org.example.m2.Module2;

public class Module3 {

    public void test(Module2 m2) {
        System.out.printf("%s.test()", getClass().getName());
        m2.test();
    }

}
