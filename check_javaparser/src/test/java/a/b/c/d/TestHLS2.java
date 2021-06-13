import java.util.ArrayList;
import java.util.List;

public class testHLS2 {

    static class A {
        static class B {
            static class C {
                void print(String s) { }
            }
        }
    }

    // static class C {
    //     void print(String s) { }
    // }

    public static void main(String[] args) {
        A.B.C c = new A.B.C();
        List<String> l = new ArrayList<>();
        l.forEach(c::print);

        for (String s : l) {
            c.print(s);
        }
    }
}
