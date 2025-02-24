package jext.util.concurrent;

import jext.exception.AbortedException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

/*
    In theory, it is possible to use 'parallelStream()' a job in multiple threads using the ForkJoin pool.

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

    Mathematica:
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

    public static final int MAX_PARALLELISM = Runtime.getRuntime().availableProcessors() - 1;
    public static int MAX_TASKS_LIST_SIZE = 10*MAX_PARALLELISM;

    private static int nthreads = MAX_PARALLELISM;
    private static List<ExecutorService> running;
    private static Queue<WaitingExecutorService> waiting;
    private static long TIMEOUT = 60000;

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
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

    // decrease the thread priority used in Parallel
    private static ThreadFactory threadFactory = new DefaultThreadFactory();

    // ----------------------------------------------------------------------
    // Task implementation
    // ----------------------------------------------------------------------

    private static class Ordered<T> {
        int order;
        T value;

        Ordered(int order, T value) {
            this.order = order;
            this.value = value;
        }
    }

    private static class TaskBase {

        TaskBase() { }

        protected void running() {

        }

        protected void done() {

        }

    }

    private static class TaskFunction<T, U> extends TaskBase implements Callable<U> {
        private T t;
        private Function<T, U> body;

        TaskFunction(T t, Function<T, U> body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public U call() {
            try {
                running();
                return body.apply(t);
            }
            finally {
                done();
            }

        }
    }

    private static class Task<T> extends TaskBase implements Callable<Boolean> {
        private T t;
        private Consumer<T> body;

        Task(T t, Consumer<T> body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            try {
                running();
                body.accept(t);
            }
            finally {
                done();
            }
            return true;
        }
    }

    private static class IntTask extends TaskBase implements Callable<Boolean> {
        private int t;
        private IntConsumer body;

        IntTask(int t, IntConsumer body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            try {
                running();
                body.accept(t);
            }
            finally {
                done();
            }
            return true;
        }
    }

    private static class LongTask extends TaskBase implements Callable<Boolean> {
        private long t;
        private LongConsumer body;

        LongTask(long t, LongConsumer body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            try {
                running();
                body.accept(t);
            }
            finally {
                done();
            }
            return true;
        }
    }

    private static class DoubleTask extends TaskBase implements Callable<Boolean> {
        private double t;
        private DoubleConsumer body;

        DoubleTask(double t, DoubleConsumer body/*, Counters todo*/) {
            // super(todo);
            this.t = t;
            this.body = body;
        }

        @Override
        public Boolean call() {
            try {
                running();
                body.accept(t);
            }
            finally {
                done();
            }
            return true;
        }
    }

    private static class RunnableTask extends TaskBase implements Callable<Boolean> {

        private Runnable runnable;

        RunnableTask(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public Boolean call() {
            try {
                running();
                runnable.run();
            }
            finally {
                done();
            }
            return true;
        }
    }

    private static class OrderedTask<T> implements Callable<Ordered<T>> {

        private final int order;
        private Callable<T> callable;

        OrderedTask(int order, Callable<T> callable) {
            this.order = order;
            this.callable = callable;
        }

        @Override
        public Ordered<T> call() throws Exception {
            T value = callable.call();
            return new Ordered<>(order, value);
        }
    }

    // ----------------------------------------------------------------------
    // forEach
    // ----------------------------------------------------------------------

    public static void forEach(int first, int last, IntConsumer body) {
        List<Callable<Boolean>> tasks = new ArrayList<>();

        if (first <= last)
            for (int t = first; t < last; ++t) tasks.add(new IntTask(t, body));
        else
            for (int t = last; t > first; --t) tasks.add(new IntTask(t, body));

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
    // forEach native data
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
        int order = 0;
        List<Callable<Ordered<T>>> orderedTasks = new ArrayList<>();
        for (Callable<T> task : tasks)
            orderedTasks.add(new OrderedTask(order++, task));

        if (tasks.size() <= MAX_TASKS_LIST_SIZE)
            return invokeOnList(orderedTasks);
        else
            return invokeOnSplittedLists(orderedTasks);
    }

    private static <T> List<T> invokeOnList(List<Callable<Ordered<T>>> tasks) {
        ExecutorService executor = null;
        List<Future<Ordered<T>>> futures = Collections.emptyList();
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

    private static <T> List<T> invokeOnSplittedLists(List<Callable<Ordered<T>>> tasks) {
        List<List<Callable<Ordered<T>>>> splittedTasks = splitTasks(tasks);
        List<Callable<Ordered<List<T>>>> callables = new ArrayList<>();

        for (List<Callable<Ordered<T>>> taskList : splittedTasks)
            callables.add(new RunTaskList(taskList));

        List<List<T>> results = invokeOnList(callables);

        return flatten(results);
    }

    private static <T> List<List<Callable<T>>> splitTasks(List<Callable<T>> tasks) {
        int ntasks = tasks.size();
        int ptasks = (ntasks + nthreads - 1) / nthreads;
        List<List<Callable<T>>> parts = new ArrayList<>();
        for (int s = 0; s < ntasks; s += ptasks) {
            int e = Math.min(ntasks, s+ptasks);
            List<Callable<T>> part = tasks.subList(s, e);
            parts.add(part);
        }
        return parts;
    }

    private static <T> List<T> flatten(List<List<T>> ll) {
        List<T> flatten = new ArrayList<>();
        for (List<T> l : ll)
            flatten.addAll(l);
        return flatten;
    }

    private static class RunTaskList<T> implements Callable<List<T>> {

        private final List<Callable<T>> tasks;

        RunTaskList(List<Callable<T>> tasks) {
            this.tasks = tasks;
        }

        @Override
        public List<T> call() throws Exception {
            List<T> results = new ArrayList<>();

            for (Callable<T> task : tasks) {
                T resul = task.call();
                results.add(resul);
            }

            return results;
        }
    }

    // ----------------------------------------------------------------------
    // Parallel.map
    // ----------------------------------------------------------------------

    private static class CallableTask<V, R> implements Callable<R> {

        private V v;
        private Function<V, R> function;

        CallableTask(V v, Function<V, R> function) {
            this.v = v;
            this.function = function;
        }

        @Override
        public R call() throws Exception {
            return function.apply(v);
        }
    }

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

    public static <V, R> List<R> map(V[] array, Function<V, R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        for(V t : array) tasks.add(new CallableTask<>(t, function));

        return invokeAll(tasks);
    }

    public static <R> List<R> map(int start, int end, Function<Integer, R> function) {
        List<Callable<R>> tasks = new ArrayList<>();
        for(int t=start; t<end; ++t) tasks.add(new CallableTask<>(t, function));

        return invokeAll(tasks);
    }

    // ----------------------------------------------------------------------
    // Parallel.map(Map<K, V> map, R f(V))
    // ----------------------------------------------------------------------

    private static class EntryResult<K, R> {
        private K k;
        private R r;

        EntryResult(K k, R r) {
            this.k = k;
            this.r = r;
        }
    }

    private static class CallableEntryTask<K, V, R> implements Callable<EntryResult<K, R>> {

        private K k;
        private V v;
        private Function<V, R> function;

        CallableEntryTask(K k, V v, Function<V, R> function) {
            this.k = k;
            this.v = v;
            this.function = function;
        }

        @Override
        public EntryResult<K, R> call() {
            R r = function.apply(v);
            return new EntryResult<>(k, r);
        }
    }

    public static <K, V, R> Map<K, R> map(Map<K, V> dictionary, Function<V, R> function) {
        List<Callable<EntryResult<K, R>>> tasks = new ArrayList<>();
        dictionary.forEach((k, t) -> tasks.add(new CallableEntryTask<>(k, t, function)));

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

    public static void setup(int nth) {
        if (nthreads == 0) {
            nthreads = nth;
            if (nthreads < 3) nthreads = 3;
        }

        if (running == null) {
            running = new LinkedList<>();
            waiting = new LinkedList<>();
        }
    }

    public static synchronized void shutdown() {
        if (running != null)
            running.forEach(ExecutorService::shutdownNow);
        if (waiting != null)
            waiting.forEach(WaitingExecutorService::shutdownNow);
        running = null;
        waiting = null;
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

    private static <T> List<T> collectExceptions(List<Future<Ordered<T>>> futures, ParallelException pe) {
        List<Ordered<T>> results = new ArrayList<>();
        for (Future<Ordered<T>> future : futures) {
            try {
                results.add(future.get());
            }
            catch (ExecutionException e) {
                Throwable t = e.getCause();
                if (!(t instanceof AbortedException))
                    pe.add(t);
            }
            catch (Throwable t) {
                pe.add(t);
            }
        }

        results.sort((o1, o2) -> Integer.compare(o1.order, o2.order));

        return results.stream().map(o -> o.value).collect(Collectors.toList());
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
                Executors.newFixedThreadPool(nthreads, threadFactory)
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

    private static void sleep(long millis) {
        if (millis > 0)
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) { }
    }
}
