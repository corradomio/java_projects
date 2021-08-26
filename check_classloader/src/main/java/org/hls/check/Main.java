package org.hls.check;

/*
    ClassLoader
        SecureClassLoader
            URLClassLoader
                AppClassLoader
                ExtClassLoader

    class sun.misc.Launcher$ExtClassLoader          <== root classLoader
        class sun.misc.Launcher$AppClassLoader      <== system classLoader
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class C<T> implements Iterable<T> {

    private List<T> l = new ArrayList<>();

    public C(List<T> ll) {
        l.addAll(ll);
    }

    @Override
    public Iterator<T> iterator() {
        return l.iterator();
    }

    public Iterable<T> iterateOn() {
        return this;
    }
}

public class Main {

    public static void main(String[] args) {
        C<String> c = new C<>(Arrays.asList("1","2","3"));

        for (String s : c.iterateOn()) {
            System.out.println(s);
        }


    }

    public static void main1(String[] args) {
        ClassLoader cl = Main.class.getClassLoader();
        int i=0;
        while (cl != null) {
            i++;
            System.out.printf("%d) %s\n", i, cl.getClass().toString());
            cl = cl.getParent();
        }
        System.out.printf("s) %s\n", ClassLoader.getSystemClassLoader().getClass().toString());

    }
}
