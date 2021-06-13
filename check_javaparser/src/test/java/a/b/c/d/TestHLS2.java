package a.b.c.d;

import java.util.ArrayList;
import java.util.List;

public class TestHLS2 {

    static class C {
        void print(String s) { }
    }

    public static void main(String[] args) {
        a.b.c.d.TestHLS2.C c = new a.b.c.d.TestHLS2.C();
        List<String> l = new ArrayList<>();
        l.forEach(c::print);
    }
}
