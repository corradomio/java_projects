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
import java.util.function.Function;
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

    // private static int nthreads;
    // private static List<ExecutorService> running;
    // private static Queue<ExecutorService> waiting;

    // ----------------------------------------------------------------------

    public static void forEach(int first, int last, IntConsumer body) {

        new Parallelize().forEach(first, last, body);

        // List<Callable<Boolean>> tasks = new ArrayList<>();
        // for(int t=first; t<last; ++t) tasks.add(new IntTask(t, body));
        // invokeAll(tasks);
    }

    public static <T> void forEach(Iterable<T> it, Consumer<T> body) {

        new Parallelize().forEach(it, body);

        // List<Callable<Boolean>> tasks = new ArrayList<>();
        // for(T t : it) tasks.add(new Task<>(t, body));
        // invokeAll(tasks);
    }

    public static <T> void forEach(Stream<T> s, Consumer<T> body) {

        new Parallelize().forEach(s, body);

        // List<Callable<Boolean>> tasks = s.map(t -> new Task<>(t, body)).collect(Collectors.toList());
        // invokeAll(tasks);
    }

    public static void forEach(IntStream s, IntConsumer body) {

        new Parallelize().forEach(s, body);

        // List<Callable<Boolean>> tasks = s.mapToObj(t -> new IntTask(t, body)).collect(Collectors.toList());
        // invokeAll(tasks);
    }


    // public static <T, U> List<U> mapEach(Iterable<T> it, Function<T, U> body) {
    //     List<Callable<U>> tasks = new ArrayList<>();
    //     for(T t : it) tasks.add(new TaskFunction<>(t, body));
    //     return invokeAll(tasks);
    // }

    // ----------------------------------------------------------------------

    public static <T> List<T> invokeAll(List<Callable<T>> tasks) {

        return new Parallelize().invokeAll(tasks);

        // checkUsage(true);
        //
        // ExecutorService executor = null;
        // List<Future<T>> futures = Collections.emptyList();
        // try {
        //     executor = newExecutorService();
        //
        //     futures = executor.invokeAll(tasks);
        // } catch (InterruptedException e) {
        //
        // }
        // while (futures.stream().anyMatch(f -> !f.isDone())) {
        //     sleep(500);
        // }
        // {
        //     parkExecutorService(executor);
        // }
        //
        // // check for exceptions
        // ParallelException pe = new ParallelException();
        // List<T> results = new ArrayList<>();
        // for (Future<T> future : futures) {
        //     try {
        //         results.add(future.get());
        //     }
        //     catch (Throwable t) {
        //         pe.add(t);
        //     }
        // }
        //
        // if (!pe.isEmpty())
        //     throw pe;
        //
        // return results;
    }

    // private static synchronized ExecutorService newExecutorService() {
    //     ExecutorService executor;
    //     if (waiting.isEmpty()) {
    //         waiting.add(Executors.newFixedThreadPool(nthreads));
    //     }
    //
    //     executor = waiting.remove();
    //
    //     running.add(executor);
    //     return executor;
    // }

    // private static synchronized void parkExecutorService(ExecutorService executor) {
    //     if (executor == null) return;
    //     running.remove(executor);
    //     waiting.add(executor);
    // }

    // public static void setup() {
    //     checkUsage(false);
    // }

    public static synchronized void shutdown() {
        // if (running != null)
        //     running.forEach(ExecutorService::shutdownNow);
        // if (waiting != null)
        //     waiting.forEach(ExecutorService::shutdownNow);
        // running = null;
        // waiting = null;
        Parallelize.shutdown();
    }

    // private static synchronized void checkUsage(boolean toUse) {
    //     if (running != null)
    //         return;
    //
    //     nthreads = Runtime.getRuntime().availableProcessors() - 1;
    //     if (nthreads < 3) nthreads = 3;
    //     running = new LinkedList<>();
    //     waiting = new LinkedList<>();
    // }

    // private static void sleep(long millis) {
    //     if (millis > 0)
    //         try {
    //             Thread.sleep(millis);
    //         } catch (InterruptedException e) { }
    // }
}
