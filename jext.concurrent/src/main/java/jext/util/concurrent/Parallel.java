package jext.util.concurrent;

import jext.exception.AbortedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
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

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static int nthreads = 0;
    private static List<ExecutorService> running;
    private static Queue<WaitingExecutorService> waiting;
    private static long TIMEOUT = 60000;

    static class DefaultThreadFactory implements ThreadFactory {
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

    public static void forEach(IntStream s, IntConsumer body) {
        List<Callable<Boolean>> tasks = s.mapToObj(t -> new IntTask(t, body))
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

    private static <T> List<T> collectExceptions(List<Future<T>> futures, ParallelException pe) {
        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
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
