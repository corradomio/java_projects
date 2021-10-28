package org.example.m6;

import org.example.m5.T4;

public class S1 {

    public S1() { }

    void m() {
        S2 s2 = new S2();
        s2.m();
        S3 s3 = new S3();
        s3.m();
        // T4 t4 = new T4();
        // t4.m();
    }
}
