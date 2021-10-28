package org.example.m8;

import org.example.m5.T1;
import org.example.m5.T2;
import org.example.m5.T3;
import org.example.m5.T4;
import org.example.m9.B1;
import static org.example.m9.C1.set;

public class A1 {

    // private T4[][] field;
    //
    // public T3[] arrays(T1[] p1, T2 p2, T3[][][] p3) {
    //     return new T3[10];
    // }

    public static Object get() {
        return B1.get(A1.class);
    }

    public static void setName() {
        set("Name");
    }
}
