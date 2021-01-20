package jext.util.concurrent;

import jext.util.concurrent.impl.CachedThreadPoolImpl;
import jext.util.concurrent.impl.DynamicThreadPoolImpl;
import jext.util.concurrent.impl.QueueExecutorServiceImpl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class Executors  {

    private static java.util.concurrent.Executors executors;

    private Executors() { }

    // ----------------------------------------------------------------------

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return java.util.concurrent.Executors.newFixedThreadPool(nThreads);
    }

    public static ExecutorService newWorkStealingPool(int parallelism) {
        return java.util.concurrent.Executors.newWorkStealingPool(parallelism);
    }

    public static ExecutorService newWorkStealingPool() {
        return java.util.concurrent.Executors.newWorkStealingPool();
    }

    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return java.util.concurrent.Executors.newFixedThreadPool(nThreads, threadFactory);
    }

    public static ExecutorService newSingleThreadExecutor() {
        return java.util.concurrent.Executors.newSingleThreadExecutor();
    }

    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
        return java.util.concurrent.Executors.newSingleThreadExecutor(threadFactory);
    }

    public static ExecutorService newCachedThreadPool() {
        return java.util.concurrent.Executors.newCachedThreadPool();
    }

    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
        return java.util.concurrent.Executors.newCachedThreadPool(threadFactory);
    }

    public static ExecutorService unconfigurableExecutorService(ExecutorService executor) {
        return java.util.concurrent.Executors.unconfigurableExecutorService(executor);
    }

    public static ThreadFactory defaultThreadFactory() {
        return java.util.concurrent.Executors.defaultThreadFactory();
    }

    // ----------------------------------------------------------------------

    public static ExecutorService newCachedThreadPoolExt(int maximumPoolSize) {
        return new CachedThreadPoolImpl(maximumPoolSize);
    }

    public static QueueExecutorService newQueueThreadPool(int maximumPoolSize) {
        return new QueueExecutorServiceImpl(maximumPoolSize);
    }

    public static ExecutorService newDynamicThreadPool(int maximumPoolSize) {
        return new DynamicThreadPoolImpl(maximumPoolSize);
    }

    // ----------------------------------------------------------------------

    public static <T> Callable<T> callable(Runnable task, T result) {
        return java.util.concurrent.Executors.callable(task, result);
    }

    public static Callable<Object> callable(Runnable task) {
        return java.util.concurrent.Executors.callable(task);
    }

    public static List<Callable<Object>> callable(List<? extends Runnable> runnables) {
        return runnables.stream()
            .map(Executors::callable)
            .collect(Collectors.toList());
    }

    public static <T> List<Callable<T>> callable(List<? extends Runnable> runnables, T result) {
        return runnables.stream()
            .map(task -> callable(task, result))
            .collect(Collectors.toList());
    }

    // public static Callable<Object> callable(final PrivilegedAction<?> action) {
    //     return java.util.concurrent.Executors.callable(action);
    // }

    // public static Callable<Object> callable(final PrivilegedExceptionAction<?> action) {
    //     return java.util.concurrent.Executors.callable(action);
    // }
}
