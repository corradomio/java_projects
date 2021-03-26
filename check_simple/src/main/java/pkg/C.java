package pkg;

import java.util.Random;

public class C {

    static class StaticI implements I {

        @Override
        public void m(int i) { }
    }

    class InnerI implements I {
        @Override
        public void m(int i) { }
    }

    public static void main(String[] args) { }

    I field1 = new InnerI() {
        @Override
        public void m(int i) { }
    };

    void run() {

        class MethodI implements I {

            @Override
            public void m(int i) { }
        }

        StaticI si;
        InnerI ii = new InnerI();
        MethodI mi = new MethodI();

        I anonI = new I(/*new Random()*/) {

            @Override
            public void m(int i) {
                Random r;
            }
        };

        I anonI2 = new I(/*new Random()*/) {

            @Override
            public void m(int i) {
                //Random r;
                C c;
            }
        };

        B anonI3 = new B() {

        };

        B anon4 = new B();


    }
}
