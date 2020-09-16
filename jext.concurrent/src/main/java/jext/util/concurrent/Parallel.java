package jext.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    private static class Task<T> implements Callable<Boolean> {
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

    private static class IntTask implements Callable<Boolean> {
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

    private static int nthreads;
    private static List<ExecutorService> running;
    private static Queue<ExecutorService> waiting;

    // ----------------------------------------------------------------------

    // IntConsumer === Consumer<int>

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



    // ----------------------------------------------------------------------

    public static void invokeAll(List<Callable<Boolean>> tasks) {
        checkUsage(true);

        List<Future<Boolean>> results = Collections.emptyList();
        ExecutorService executor = newExecutorService();
        try {
            results = executor.invokeAll(tasks);
            while (results.stream().anyMatch(f -> !f.isDone())) {
                sleep(500);
            }
        } catch (InterruptedException e) {

        } finally {
            parkExecutorService(executor);
        }

    }

    private static synchronized ExecutorService newExecutorService() {
        ExecutorService executor;
        if (waiting.isEmpty())
            waiting.add(Executors.newFixedThreadPool(nthreads));

        executor = waiting.remove();
        running.add(executor);

        return executor;
    }

    private static synchronized void parkExecutorService(ExecutorService executor) {
        if (executor == null) return;
        running.remove(executor);
        waiting.add(executor);
    }

    public static void setup() {
        checkUsage(false);
    }

    public static synchronized int shutdown() {
        int usedThreads = 0;

        if (running != null) {
            running.forEach(ExecutorService::shutdownNow);
            usedThreads += running.size();
            running = null;
        }
        if (waiting != null) {
            waiting.forEach(ExecutorService::shutdownNow);
            usedThreads += waiting.size();
            waiting = null;
        }

        return usedThreads*nthreads;
    }

    private static synchronized void checkUsage(boolean toUse) {
        if (toUse && running == null)
            throw new RuntimeException("Parallel not initialized. Call 'Parallel.setup()'");

        if (running != null)
            return;

        nthreads = Runtime.getRuntime().availableProcessors() - 1;
        if (nthreads < 3) nthreads = 3;
        running = new LinkedList<>();
        waiting = new LinkedList<>();
    }

    private static void sleep(long millis) {
        if (millis > 0)
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) { }
    }
}
