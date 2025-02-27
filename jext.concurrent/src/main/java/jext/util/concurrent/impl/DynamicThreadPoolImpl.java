package jext.util.concurrent.impl;

import jext.util.concurrent.ExecutorService;
import jext.util.concurrent.Executors;
import jext.util.concurrent.Sleep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;


public class DynamicThreadPoolImpl implements ExecutorService {

    private class ProcessTask implements Runnable {

        @Override
        public void run() {
            Thread thisThread = Thread.currentThread();
            try {
                DynamicThreadPoolImpl.this.runningThreads.put(thisThread.getId(), thisThread);

                FutureTask<?> future = DynamicThreadPoolImpl.this.waitingQueue.remove();
                future.run();
            }
            finally {
                DynamicThreadPoolImpl.this.runningThreads.remove(thisThread.getId());
            }

        }
    }

    private final int maximumPoolSize;
    private final BlockingQueue<FutureTask<?>> waitingQueue = new LinkedBlockingQueue<>();
    private final Map<Long, Thread> runningThreads = new ConcurrentHashMap<>();
    private final ThreadFactory threadFactory;

    public DynamicThreadPoolImpl(int maximumPoolSize) {
        this(maximumPoolSize, Executors.defaultThreadFactory());
    }

    public DynamicThreadPoolImpl(int maximumPoolSize, ThreadFactory threadFactory) {
        this.maximumPoolSize = maximumPoolSize;
        this.threadFactory = threadFactory;
    }


    @Override
    public int countWaiting() {
        return waitingQueue.size();
    }

    @Override
    public int countRunning() {
        return runningThreads.size();
    }

    @Override
    public boolean isCompleted() {
        return waitingQueue.isEmpty() && runningThreads.isEmpty();
    }

    @Override
    public boolean isTerminated() {
        return isCompleted();
    }

    // ----------------------------------------------------------------------

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        FutureTask<T> future = futureOf(task);
        waitingQueue.add(future);
        return future;
    }

    @Override
    public <T> Future<T> submit(Runnable runnable, T result) {
        Callable<T> task = Executors.callable(runnable, result);
        return submit(task);
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        Callable<Object> task = Executors.callable(runnable);
        return submit(task);
    }

    @Override
    public List<Future<Object>> invokeAll(List<? extends Runnable> runnables) throws InterruptedException {
        List<Callable<Object>> tasks = Executors.callable(runnables);
        return invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        List<FutureTask<T>> futures = futureOf(tasks);
        waitingQueue.addAll(futures);
        return new ArrayList<>(futures);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return invokeAny(tasks);
    }

    private static <T> FutureTask<T> futureOf(Callable<T> task) {
        return new FutureTask<>(task);
    }

    private static <T> List<FutureTask<T>> futureOf(Collection<? extends Callable<T>> tasks) {
        return tasks.stream().map(DynamicThreadPoolImpl::futureOf).collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------


    @Override
    public void waitForCompletion() {
        while(!isCompleted()) {
            startThreads();
            Sleep.sleep();
        }
    }

    private void startThreads() {
        while (countRunning() < maximumPoolSize && countWaiting() > 0)
            threadFactory
                .newThread(new ProcessTask())
                .start();
    }

    @Override
    public <V> List<V> waitForResults() {
        waitForCompletion();

        return null;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return Collections.emptyList();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void execute(Runnable command) {

    }


    @Override
    public void dump() {

    }

}
