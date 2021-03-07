package org.hls.check;

public class App {
    static class StaticInner { }
    class Inner { }

    static abstract class Abstract { }

    interface Itfc {
        void print();
    }

    enum E {
        ONE,
        TWO
    }

    static {
        String helloWorld = "Ciccio";
    }

    public static void main(String[] args) {

        Itfc i = new Itfc() {
            public void print() {

            }
        };

        Abstract a = new Abstract(){
            void print() { }
        };

    }
}
