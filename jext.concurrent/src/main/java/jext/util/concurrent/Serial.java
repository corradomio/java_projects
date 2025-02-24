package jext.util.concurrent;

import jext.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/// As Parallel but the code is executed sequentially
/// Used in debugging
public class Serial {

    private static final Logger logger = Logger.getLogger(Serial.class);

    // ----------------------------------------------------------------------
    // forEach
    // ----------------------------------------------------------------------

    public static void forEach(int first, int last, IntConsumer body) {
        if (first <= last)
            for(int i=first; i<last; ++i)
                body.accept(i);
        else
            for(int i=last; i>first; --i)
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

    // ----------------------------------------------------------------------
    // invokeAll
    // ----------------------------------------------------------------------

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

    // ----------------------------------------------------------------------
    // apply
    // ----------------------------------------------------------------------

    public static <V, R> List<R> map(List<V> list, Function<V, R> function) {
        List<R> results = new ArrayList<>();
        for(V v : list)
            results.add(function.apply(v));
        return results;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public static int threads() {
        return 1;
    }

    // ----------------------------------------------------------------------
    // Setup/shutdown
    // ----------------------------------------------------------------------

    public static void setup() { }
    public static void setup(int nth) { }
    public static void shutdown() { }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
