package jext.util.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
