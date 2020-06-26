package jext.util.concurrent;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Sequential {

    public static void forEach(int first, int last, IntConsumer body) {
        for(int i=first; i<last; ++i)
            body.accept(i);
    }

    public static <T> void forEach(Iterable<T> it, Consumer<T> body) {
        for(Iterator<T> i = it.iterator(); i.hasNext(); ) {
            body.accept(i.next());
        }
    }

}
