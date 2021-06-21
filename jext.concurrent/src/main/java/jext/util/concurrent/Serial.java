package jext.util.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Serial {

    public static void forEach(int first, int last, IntConsumer body) {
        for(int i=first; i<last; ++i)
            body.accept(i);
    }

    public static <T> void forEach(Iterable<T> it, Consumer<T> body) {
        it.forEach(body);
    }

    public static <T> void forEach(Stream<T> s, Consumer<T> body) {
        s.forEach(body);
    }

    public static void forEach(IntStream s, IntConsumer body) {
        s.forEach(body);
    }

    public static <T> List<T> invokeAll(List<Callable<T>> tasks) {
        ParallelException pe = new ParallelException();
        List<T> results = new ArrayList<>();
        tasks.forEach(body -> {
            try {
                results.add(body.call());
            } catch (Exception e) {
                pe.add(e);
            }
        });

        if (!pe.isEmpty())
            throw pe;

        return results;
    }

    public static void setup() { }

    public static void shutdown() { }
}
