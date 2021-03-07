package org.hls.check;

public class App {
    static class StaticInner { }
    class Inner { }

    interface Itfc {

    }

    public static void main(String[] args) {

        Itfc i = new Itfc() { };

    }
}
