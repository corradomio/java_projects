package jext.util.concurrent;

import java.util.List;
import java.util.concurrent.Future;

/// Extends Java ExecutorService with some extra useful information:
/// the number of tasks running, waiting, and it permits to wait for
/// the completion of all tasks.
public interface ExecutorService extends java.util.concurrent.ExecutorService {

    // void shutdown();
    // List<Runnable> shutdownNow();
    // boolean isShutdown();
    // boolean isTerminated();
    // boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;
    //
    // <T> Future<T> submit(Callable<T> task);
    // <T> Future<T> submit(Runnable task, T result);
    //     Future<?> submit(Runnable task);
    //
    // <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks);
    // <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit);
    //
    // <T> T invokeAny(Collection<? extends Callable<T>> tasks);
    // <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit);

    List<Future<Object>> invokeAll(List<? extends Runnable> tasks) throws InterruptedException;

    /**
     * Number of waiting tasks
     */
    int countWaiting();

    /**
     * Number of running tasks
     */
    int countRunning();

    /**
     * Wait the completion of all tasks
     */
    void waitForCompletion();

    /**
     * Wait for completion of all tasks and
     * return the collected results
     */
    <V> List<V> waitForResults();

    /**
     * Check is all tasks are completed
     */
    boolean isCompleted();

    /**
     * Dump the executor status
     */
    void dump();
}
