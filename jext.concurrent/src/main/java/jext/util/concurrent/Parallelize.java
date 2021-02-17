package jext.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Split a long queue in chunks of the specified size
 * and pass each chunk to a different thread
 */
public class Parallelize {

    // private AtomicInteger running = new AtomicInteger();
    // private AtomicInteger waiting = new AtomicInteger();

    private class TaskFunction<T, U> implements Callable<U> {
        private T t;
        private Function<T, U> body;

        TaskFunction(T t, Function<T, U> body) {
            this.t = t;
            this.body = body;
        }

        @Override
        public U call() {
            // try {
            //     waiting.decrementAndGet();
            //     running.incrementAndGet();
                return body.apply(t);
            // }
            // finally {
            //     running.decrementAndGet();
            // }
        }
    }

    private class Task<T> implements Callable<Boolean> {
        private T t;
        private Consumer<T> body;

        Task(T t, Consumer<T> body) {
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            // try {
            //     waiting.decrementAndGet();
            //     running.incrementAndGet();
                body.accept(t);
            // }
            // finally {
            //     running.decrementAndGet();
            // }
            return true;
        }
    }

    private class IntTask implements Callable<Boolean> {
        private int t;
        private IntConsumer body;

        IntTask(int t, IntConsumer body) {
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            // try {
            //     waiting.decrementAndGet();
            //     running.incrementAndGet();
                body.accept(t);
            // }
            // finally {
            //     running.decrementAndGet();
            // }
            return true;
        }
    }

    private int chunkSize;

    public Parallelize() {
        this.chunkSize = 128;
    }

    public Parallelize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public void forEach(int first, int last, IntConsumer body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(int t=first; t<last; ++t) tasks.add(new IntTask(t, body));
        invokeAll(tasks);
    }

    public <T> void forEach(Iterable<T> it, Consumer<T> body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(T t : it) tasks.add(new Task<>(t, body));
        invokeAll(tasks);
    }

    public <T> void forEach(Stream<T> s, Consumer<T> body) {
        List<Callable<Boolean>> tasks = s.map(t -> new Task<>(t, body)).collect(Collectors.toList());
        invokeAll(tasks);
    }

    public void forEach(IntStream s, IntConsumer body) {
        List<Callable<Boolean>> tasks = s.mapToObj(t -> new IntTask(t, body)).collect(Collectors.toList());
        invokeAll(tasks);
    }

    public <T> List<T> invokeAll(List<Callable<T>> tasks) {
        setup();

        // waiting.set(tasks.size());

        int fromIndex = 0;
        int toIndex, endIndex = tasks.size();

        List<T> results = new ArrayList<>();

        while (fromIndex < endIndex) {
            toIndex = Math.min(fromIndex + chunkSize, endIndex);
            List<Callable<T>> tasksChunk = tasks.subList(fromIndex, toIndex);

            beginChunk();

            List<T> chunkResults = invokeChunk(tasksChunk);
            results.addAll(chunkResults);

            endChunk();

            fromIndex = toIndex;
        }

        return results;
    }

    protected void beginChunk() {

    }

    protected void endChunk() {

    }

    private <T> List<T> invokeChunk(List<Callable<T>> tasks) {

        // checkUsage(true);
        //
        java.util.concurrent.ExecutorService executor = null;
        List<Future<T>> futures = Collections.emptyList();
        try {
            executor = newExecutorService();

            futures = executor.invokeAll(tasks);
        } catch (InterruptedException e) {

        }
        while (futures.stream().anyMatch(f -> !f.isDone())) {
            sleep(500);
        }
        {
            parkExecutorService(executor);
        }

        // check for exceptions
        ParallelException pe = new ParallelException();
        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            try {
                results.add(future.get());
            } catch (ExecutionException | InterruptedException e) {
                pe.add(e);
            }
        }

        if (!pe.isEmpty())
            throw pe;

        return results;
    }

    private static int nthreads;
    private static List<java.util.concurrent.ExecutorService> runningExecutors;
    private static Queue<java.util.concurrent.ExecutorService> waitingExecutors;

    private static synchronized java.util.concurrent.ExecutorService newExecutorService() {
        java.util.concurrent.ExecutorService executor;
        if (waitingExecutors.isEmpty()) {
            waitingExecutors.add(Executors.newFixedThreadPool(nthreads));
        }

        executor = waitingExecutors.remove();

        runningExecutors.add(executor);
        return executor;
    }

    private static synchronized void parkExecutorService(java.util.concurrent.ExecutorService executor) {
        if (executor == null) return;
        runningExecutors.remove(executor);
        waitingExecutors.add(executor);
    }


    private static void sleep(long millis) {
        if (millis > 0)
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) { }
    }

    public static synchronized void setup() {
        if (runningExecutors != null) return;
        nthreads = Runtime.getRuntime().availableProcessors() - 1;
        if (nthreads < 3) nthreads = 3;
        runningExecutors = new LinkedList<>();
        waitingExecutors = new LinkedList<>();
    }

    public static synchronized void shutdown() {
        if (runningExecutors != null)
            runningExecutors.forEach(java.util.concurrent.ExecutorService::shutdownNow);
        if (waitingExecutors != null)
            waitingExecutors.forEach(ExecutorService::shutdownNow);
        runningExecutors = null;
        waitingExecutors = null;
    }
}
