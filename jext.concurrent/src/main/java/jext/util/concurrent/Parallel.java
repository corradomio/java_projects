package jext.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
    In theory it is possible to use 'parallelStream()' a job in multiple threads using the ForkJoin pool.

    We have observed, unfortunately, that IF the threads need to wait for an external resource, the number
    of threads in the ForkJoin pool increase. But there is also another problem: the pool is not reused.
    The result is that at the end of a run, the application contains a lot of unused ForkJoin pools and each
    pool contains a lot of unused threads.

    This class resolve the problem: it uses the SAME pool of 'availableProcessors()-1' threads.
    The drawbacks are:

    1) it is NOT possible to use Parallel recursively. But this is reasonable
    2) obviously, the application wait until ALL threads have terminated
    3) it is necessary to SHUTDOWN the pool at the end of the application. And also this is reasonable.

    Note: this class must be improved to support exceptions in some thread!
 */

public class Parallel {

    static class Task<T> implements Callable<Boolean> {
        private T t;
        private Consumer<T> body;

        Task(T t, Consumer<T> body) {
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            body.accept(t);
            return true;
        }
    }

    static class IntTask implements Callable<Boolean> {
        private int t;
        private IntConsumer body;

        IntTask(int t, IntConsumer body) {
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            body.accept(t);
            return true;
        }
    }

    private static ExecutorService parallels;

    private static boolean inUse;

    // ----------------------------------------------------------------------

    public static void forEach(int first, int last, IntConsumer body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(int t=first; t<last; ++t) tasks.add(new IntTask(t, body));
        invokeAll(tasks);
    }

    public static <T> void forEach(Iterable<T> it, Consumer<T> body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(T t : it) tasks.add(new Task<>(t, body));
        invokeAll(tasks);
    }

    public static <T> void forEach(Stream<T> s, Consumer<T> body) {
        List<Callable<Boolean>> tasks = s.map(t -> new Task<>(t, body)).collect(Collectors.toList());
        invokeAll(tasks);
    }

    public static void forEach(IntStream s, IntConsumer body) {
        List<Callable<Boolean>> tasks = s.mapToObj(t -> new IntTask(t, body)).collect(Collectors.toList());
        invokeAll(tasks);
    }

    // ----------------------------------------------------------------------

    public static void invokeAll(List<Callable<Boolean>> tasks) {
        checkUsage(true);

        List<Future<Boolean>> results = Collections.emptyList();
        try {
            results = parallels.invokeAll(tasks);
        } catch (InterruptedException e) {

        }
        while (results.stream().anyMatch(f -> !f.isDone())) {
            sleep(500);
        }

        inUse = false;
    }

    public static void setup() {
        checkUsage(false);
    }

    public static void shutdown() {
        inUse = false;
        if (parallels == null)
            return;

        parallels.shutdownNow();
        parallels = null;
    }

    private static synchronized void checkUsage(boolean toUse) {
        if (toUse && inUse)
            throw new RuntimeException("Parallel in use");
        if (toUse)
            inUse = true;

        if (parallels != null)
            return;

        int nthreads = Runtime.getRuntime().availableProcessors() - 1;
        parallels = Executors.newFixedThreadPool(nthreads);
    }

    private static void sleep(long millis) {
        if (millis > 0)
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) { }
    }
}