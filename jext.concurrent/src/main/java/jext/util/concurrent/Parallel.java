package jext.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
    In theory, it is possible to use 'parallelStream()' for a job in multiple threads using the ForkJoin pool.

    We have observed, unfortunately, that IF the threads need to wait for an external resource, the number
    of threads in the ForkJoin pool increase. But there is also another problem: the pool is not reused.
    The result is that at the end of a run, the application contains a lot of unused ForkJoin pools and each
    pool contains a lot of unused threads.

    This class resolve the problem: it uses the SAME pool of 'availableProcessors()-1' threads.
    The drawbacks are:

    1) it is NOT possible to use Parallel recursively. But this is reasonable
    2) obviously, the application wait until ALL threads have terminated
    3) it is necessary to "shutdown" the pool at the end of the application. And also this is reasonable.

    Note: this class must be improved to support exceptions in some thread!

    The current implementation intercept the exception raised in some thread and, at the end of all executions,
    it is raised a ParallelException.
    In theory, it could be useful to decide IF to raise the exception or to ignore the thread result.
    But this, at the moment, seems to introduce an unnecessary complication. It is more simple to intercept
    the exceptions inside the thread body (implemented by the user)


Mathematica
-----------

    Support for Parallel executione:

    Mathematica (Parallel*):
        ParallelArray   [f, n] [f, dims]
        ParallelCases   [{e1, ...}, pattern]
        ParallelCombine ?
        ParallelEvaluate[expr]
        Parallelize     [expr]
        ParallelMap     [f, expr]
        ParallelSelect  [data, crit]
        ParallelTry     [f, {arg1, ...}]

        ParallelDo      [expr, {imax} | {i, imin, imax}]
        ParallelTable   [expr, {imax} | {i, imin, imax}]
        ParallelProduct [expr, {imax} | {i, imin, imax}]
        ParallelSum     [expr, {imax} | {i, imin, imax}]
 */

public class Parallel {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    /// Factor used to compute the maximum list size used to apply the parallelism
    /// to each element of the list. Otherwise, the list is split in sublist and
    /// each thread is applied on each sublist.
    /// It theory, it is possible to change it.
    /// If it is 0, the sublists asre not used
    public static int MAX_TASK_LIST_FACTOR = 0;

    /// Number of threads used in the pool
    /// Default: the number of physical threads minus one
    private static int nthreads = Runtime.getRuntime().availableProcessors() - 1;

    /// List of running tasks
    private static List<ExecutorService> running;
    /// List of waiting tasks (in waiting queue)
    private static Queue<WaitingExecutorService> waiting;

    /// Timeout used to park unused threads in thread pool
    /// It theory, it is possible to change it.
    public static long TIMEOUT = 20000;

    /// Timeout used to check is it is necessary to cleanup some
    /// thread pool
    public static long CLEANUP_TIMEOUT = 3000;

    /// Thread used to cleanup the the unused thread pools
    private static class CleanupThread extends Thread {
        public CleanupThread() {
            super("Parallel-cleanup");
        }

        public void run() {
            while (running != null) {
                Parallel.sleep(CLEANUP_TIMEOUT);
                Parallel.cleanup();
            }
        }
    }

    private static CleanupThread cleanupThread;

    /// Thread factory.
    /// It is a copy&past of java.util.concurrent.Executors.DefaultThreadFactory
    /// It is used to create threads with a priority lower than the standard priority.
    /// This is necessary to keep the computer 'reactive' also when all CPU threads are
    /// used in the computation
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
            namePrefix = "Parallel-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
            // if (t.isDaemon())
            //     t.setDaemon(false);
            // if (t.getPriority() != Thread.NORM_PRIORITY)
            //     t.setPriority(Thread.NORM_PRIORITY);
            t.setPriority(Thread.NORM_PRIORITY - 2);
            return t;
        }
    }

    /// Thread factory used to create thread with lower priority than the thread's default
    /// when created with 'new Thread(...)'
    // private static ThreadFactory threadFactory = new DefaultThreadFactory();

    // ----------------------------------------------------------------------
    // Task implementation
    // ----------------------------------------------------------------------
    // A 'task' is a Runnable executed by each tread in the pool.
    // There are two main object:
    //
    //      Runnable { void run() }
    //      Callable<T> { T call() }
    //
    // To use Callable<T> is more flexible, because it permits to implement
    // threads returning some result
    //

    private static class Task<T> implements Callable<Boolean> {
        private final T t;
        private final Consumer<T> body;

        Task(T t, Consumer<T> body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            body.accept(t);
            return Boolean.TRUE;
        }
    }

    private static class IntTask implements Callable<Boolean> {
        private final int t;
        private final IntConsumer body;

        IntTask(int t, IntConsumer body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            body.accept(t);
            return Boolean.TRUE;
        }
    }

    private static class LongTask implements Callable<Boolean> {
        private final long t;
        private final LongConsumer body;

        LongTask(long t, LongConsumer body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            body.accept(t);
            return Boolean.TRUE;
        }
    }

    private static class DoubleTask implements Callable<Boolean> {
        private final double t;
        private final DoubleConsumer body;

        DoubleTask(double t, DoubleConsumer body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            body.accept(t);
            return Boolean.TRUE;
        }
    }

    /// Convert a Runnable in a Callable[Boolean]
    private static class RunnableTask implements Callable<Boolean> {
        private final Runnable runnable;

        RunnableTask(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public Boolean call() {
            runnable.run();
            return Boolean.TRUE;
        }
    }

    // ----------------------------------------------------------------------
    // forEach
    // ----------------------------------------------------------------------

    public static void forEach(int first, int last, IntConsumer body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int t = first; t < last; ++t) tasks.add(new IntTask(t, body));
        invokeAll(tasks);
    }

    public static <T> void forEach(Iterable<T> it, Consumer<T> body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(T t : it) tasks.add(new Task<>(t, body));
        invokeAll(tasks);
    }

    public static <T> void forEach(Stream<T> s, Consumer<T> body) {
        List<Callable<Boolean>> tasks = s.map(t -> new Task<>(t, body))
            .collect(Collectors.toList());
        invokeAll(tasks);
    }

    public static <T> void forEach(T[] array, Consumer<T> body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(T t : array) tasks.add(new Task<>(t, body));
        invokeAll(tasks);
    }

    // ----------------------------------------------------------------------
    // forEach native arrays
    // ----------------------------------------------------------------------

    public static void forEach(int[] array, IntConsumer body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(int t : array) tasks.add(new IntTask(t, body));
        invokeAll(tasks);
    }

    public static void forEach(long[] array, LongConsumer body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(long t : array) tasks.add(new LongTask(t, body));
        invokeAll(tasks);
    }

    public static void forEach(double[] array, DoubleConsumer body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(double t : array) tasks.add(new DoubleTask(t, body));
        invokeAll(tasks);
    }

    // ----------------------------------------------------------------------
    // forEach native streams
    // ----------------------------------------------------------------------

    public static void forEach(IntStream s, IntConsumer body) {
        List<Callable<Boolean>> tasks = s.mapToObj(t -> new IntTask(t, body))
            .collect(Collectors.toList());
        invokeAll(tasks);
    }

    public static void forEach(LongStream s, LongConsumer body) {
        List<Callable<Boolean>> tasks = s.mapToObj(t -> new LongTask(t, body))
            .collect(Collectors.toList());
        invokeAll(tasks);
    }

    public static void forEach(DoubleStream s, DoubleConsumer body) {
        List<Callable<Boolean>> tasks = s.mapToObj(t -> new DoubleTask(t, body))
            .collect(Collectors.toList());
        invokeAll(tasks);
    }

    // ----------------------------------------------------------------------
    // invokeAll
    // ----------------------------------------------------------------------

    public static void invokeAll(Runnable ... tasks) {
        List<Callable<Boolean>> callables = new ArrayList<>();

        for (Runnable runnable : tasks)
            callables.add(new RunnableTask(runnable));

        invokeAll(callables);
    }

    public static <T> List<T> invokeAll(List<Callable<T>> tasks) {
        // If the list is very long, and the task is very simple, it is not very
        // efficient to run each single task in the threads of the pool.
        // It is possible the time of execution is greater that to execute each
        // task sequentially.
        // The trich is to split the long list in nthread parts and each thread
        // in the pool process sequentially each list.

        if (MAX_TASK_LIST_FACTOR == 0 || tasks.size() <= MAX_TASK_LIST_FACTOR*nthreads)
            return invokeOnList(tasks);
        else
            return invokeOnSublists(tasks);
    }

    private static <T> List<T> invokeOnList(List<Callable<T>> tasks) {
        ExecutorService executor = null;
        List<Future<T>> futures = Collections.emptyList();
        try {
            executor = newExecutorService();

            futures = executor.invokeAll(tasks);
        } catch (InterruptedException e) {

        } catch (Throwable t) {

        }
        while (futures.stream().anyMatch(f -> !f.isDone())) {
            sleep(500);
        }
        {
            parkExecutorService(executor);
        }

        ParallelException pe = new ParallelException();
        List<T> results = collectExceptions(futures, pe);
        if (!pe.isEmpty())
            throw pe;

        return results;
    }

    // ----------------------------------------------------------------------

    private static class ListCallableTask<T> implements Callable<List<T>> {

        private final List<Callable<T>> callableList;

        ListCallableTask(List<Callable<T>> callableList) {
            this.callableList = callableList;
        }

        @Override
        public List<T> call() throws Exception {
            List<T> results = new ArrayList<>();
            for (Callable<T> callable : callableList)
                results.add(callable.call());
            return results;
        }

    }

    private static <T> List<T> invokeOnSublists(List<Callable<T>> tasks) {
        List<List<Callable<T>>> tasksList = split(tasks, MAX_TASK_LIST_FACTOR*nthreads);

        List<Callable<List<T>>> callableList = new ArrayList<>();
        for (List<Callable<T>> taskList : tasksList)
            callableList.add(new ListCallableTask<>(taskList));

        List<List<T>> resultsList = invokeOnList(callableList);

        return flatten(resultsList);
    }

    private static <T> List<List<Callable<T>>> split(List<Callable<T>> tasks, int nParts) {
        int ntasks = tasks.size();
        int ptasks = (ntasks + nParts - 1) / nParts;
        List<List<Callable<T>>> parts = new ArrayList<>();
        for (int s = 0; s < ntasks; s += ptasks) {
            int e = Math.min(ntasks, s+ptasks);
            List<Callable<T>> part = tasks.subList(s, e);
            parts.add(part);
        }
        return parts;
    }

    private static <T> List<T> flatten(List<List<T>> ll) {
        // if the result is 'null' it is skipped
        List<T> flatten = new ArrayList<>();
        for (List<T> l : ll)
            for (T e : l)
                if (e != null)
                    flatten.add(e);
        return flatten;
    }

    // ----------------------------------------------------------------------
    // Parallel.map
    // ----------------------------------------------------------------------

    private static class CallableTask<V, R> implements Callable<R> {

        private final V value;
        private final Function<V, R> function;

        CallableTask(V value, Function<V, R> function) {
            this.value = value;
            this.function = function;
        }

        @Override
        public R call() throws Exception {
            R r;
            r = function.apply(value);
            return r;
        }
    }

    private static class IntCallableTask<R> implements Callable<R> {

        private final int value;
        private final IntFunction<R> function;

        IntCallableTask(int value, IntFunction<R> function) {
            this.value = value;
            this.function = function;
        }

        @Override
        public R call() throws Exception {
            R r;
            r = function.apply(value);
            return r;
        }
    }

    private static class LongCallableTask<R> implements Callable<R> {

        private final long value;
        private final LongFunction<R> function;

        LongCallableTask(long value, LongFunction<R> function) {
            this.value = value;
            this.function = function;
        }

        @Override
        public R call() throws Exception {
            R r;
            r = function.apply(value);
            return r;
        }
    }

    private static class DoubleCallableTask<R> implements Callable<R> {

        private final double value;
        private final DoubleFunction<R> function;

        DoubleCallableTask(double value, DoubleFunction<R> function) {
            this.value = value;
            this.function = function;
        }

        @Override
        public R call() throws Exception {
            R r;
            r = function.apply(value);
            return r;
        }
    }

    // ----------------------------------------------------------------------

    public static <V, R> List<R> map(Collection<V> collection, Function<V, R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        collection.forEach(t -> tasks.add(new CallableTask<>(t, function)));

        return invokeAll(tasks);
    }

    public static <V, R> List<R> map(Iterable<V> iterable, Function<V, R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        iterable.forEach(t -> tasks.add(new CallableTask<>(t, function)));

        return invokeAll(tasks);
    }

    // ----------------------------------------------------------------------

    public static <V, R> List<R> map(int first, int last, IntFunction<R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        for(int i = first; i < last; ++i) tasks.add(new IntCallableTask<>(i, function));
        return invokeAll(tasks);
    }

    public static <R> List<R> map(int[] array, IntFunction<R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        for(int v : array) tasks.add(new IntCallableTask<>(v, function));
        return invokeAll(tasks);
    }

    public static <R> List<R> map(long[] array, LongFunction<R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        for(long v : array) tasks.add(new LongCallableTask<>(v, function));

        return invokeAll(tasks);
    }

    public static <R> List<R> map(double[] array, DoubleFunction<R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        for(double v : array) tasks.add(new DoubleCallableTask<>(v, function));

        return invokeAll(tasks);
    }

    public static <V, R> List<R> map(V[] array, Function<V, R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        for(V t : array) tasks.add(new CallableTask<>(t, function));

        return invokeAll(tasks);
    }

    // ----------------------------------------------------------------------
    // Parallel.map on dictionary
    // ----------------------------------------------------------------------

    private static class EntryResult<K, R> {
        private final K k;
        private final R r;

        EntryResult(K k, R r) {
            this.k = k;
            this.r = r;
        }
    }

    private static class EntryCallableTask<K, V, R> implements Callable<EntryResult<K, R>> {

        private final K key;
        private final V value;
        private Function<V, R> function;

        EntryCallableTask(K key, V value, Function<V, R> function) {
            this.key = key;
            this.value = value;
            this.function = function;
        }

        @Override
        public EntryResult<K, R> call() {
            R r;
            r = function.apply(value);
            return new EntryResult<>(key, r);
        }
    }

    // ----------------------------------------------------------------------

    public static <K, V, R> Map<K, R> map(Map<K, V> dictionary, Function<V, R> function) {
        List<Callable<EntryResult<K, R>>> tasks = new ArrayList<>();
        dictionary.forEach((k, t) -> tasks.add(new EntryCallableTask<>(k, t, function)));

        List<EntryResult<K, R>> results = invokeAll(tasks);

        Map<K, R> mapResult = new HashMap<>();
        results.forEach(e -> mapResult.put(e.k, e.r));

        return mapResult;
    }

    // ----------------------------------------------------------------------
    // Setup/shutdown
    // ----------------------------------------------------------------------

    public static void setup() {
        setup(Runtime.getRuntime().availableProcessors() - 1);
    }

    public static synchronized void setup(int nth) {
        if (nth <= 0)
            throw new IllegalArgumentException("Number of threads in a thread pool must be > 0");

        nthreads = nth;

        if (running == null) {
            running = new LinkedList<>();
            waiting = new LinkedList<>();

            cleanupThread = new CleanupThread();
            cleanupThread.start();
        }
    }

    public static synchronized void shutdown() {
        if (running != null) {
            running.forEach(ExecutorService::shutdownNow);
            running = null;
        }
        if (waiting != null) {
            waiting.forEach(WaitingExecutorService::shutdownNow);
            waiting = null;
        }
        if (cleanupThread != null) {
            // note CleanupThread checks if 'waiting'
            join(cleanupThread);
            cleanupThread = null;
        }
    }

    private static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public static int threads() {
        return nthreads;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private static <T> List<T> collectExceptions(List<Future<T>> futures, ParallelException pe) {
        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            try {
                T value = future.get();
                if (value != null)
                    results.add(value);
            }
            catch (ExecutionException e) {
                Throwable t = e.getCause();
                // if (!(t instanceof AbortedException))
                pe.add(t);
            }
            catch (Throwable t) {
                pe.add(t);
            }
        }
        return results;
    }

    private static class WaitingExecutorService {
        long timestamp;
        ExecutorService executorService;

        WaitingExecutorService(ExecutorService executorService) {
            this.timestamp = System.currentTimeMillis();
            this.executorService = executorService;
        }

        public static void shutdownNow(WaitingExecutorService waitingExecutorService) {
            waitingExecutorService.get().shutdownNow();
        }

        ExecutorService get() {
            return executorService;
        }

        long waitingTime() {
            return System.currentTimeMillis() - timestamp;
        }
    }

    private static synchronized ExecutorService newExecutorService() {
        ExecutorService executor;

        setup();

        if (waiting.isEmpty()) {
            waiting.add(new WaitingExecutorService(
                Executors.newFixedThreadPool(nthreads, new DefaultThreadFactory())
                // Executors.newFixedThreadPool(nthreads, threadFactory)
                // Executors.newDynamicThreadPool(nthreads)
            ));
        }

        executor = waiting.remove().get();

        // remove timeout executors
        long now = System.currentTimeMillis();
        waiting.removeIf(wexec -> (now - wexec.timestamp) > TIMEOUT);

        running.add(executor);
        return executor;
    }

    private static synchronized void parkExecutorService(ExecutorService executor) {
        if (executor == null) return;
        if (running != null)
            running.remove(executor);
        if (waiting == null)
            waiting = new LinkedList<>();
        waiting.add(new WaitingExecutorService(executor));

        waiting.removeIf(wes -> wes.waitingTime() > TIMEOUT);
    }

    private static synchronized void cleanup() {
        if (running == null)
            return;

        List<WaitingExecutorService> toRemove = new ArrayList<>();
        for (WaitingExecutorService wes : waiting) {
            if (wes.waitingTime() > TIMEOUT)
                toRemove.add(wes);
        }

        // remove & shotdows services
        for (WaitingExecutorService wes : toRemove) {
            waiting.remove(wes);
            wes.get().shutdownNow();
        }
    }

    private static void sleep(long millis) {
        if (millis > 0)
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) { }
    }


}
