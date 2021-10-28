package org.example.m0;

public class Implementation implements Interface {

    @Override
    public void m() {
        Interface i = new Implementation();
        i.m();
    }
}
