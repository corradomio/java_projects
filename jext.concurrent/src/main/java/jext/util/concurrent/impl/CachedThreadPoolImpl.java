package jext.util.concurrent.impl;

import jext.util.concurrent.ExecutorService;
import jext.util.concurrent.Executors;
import jext.util.concurrent.Sleep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class CachedThreadPoolImpl extends ThreadPoolExecutor implements ExecutorService {

    private List<Future<?>> futures = new ArrayList<>();

    public CachedThreadPoolImpl(int maximumPoolSize) {
        super(maximumPoolSize, maximumPoolSize, 3, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Future<T> future = super.submit(task);
        futures.add(future);
        return future;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        Future<T> future = super.submit(task, result);
        futures.add(future);
        return future;
    }

    @Override
    public Future<?> submit(Runnable task) {
        Future<?> future = super.submit(task);
        futures.add(future);
        return future;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        List<Future<T>> flist = super.invokeAll(tasks);
        futures.addAll(flist);
        return flist;
    }

    @Override
    public List<Future<Object>> invokeAll(List<? extends Runnable> tasks) throws InterruptedException {
        List<Callable<Object>> callables = tasks
            .stream()
            .map(Executors::callable)
            .collect(Collectors.toList());

        return invokeAll(callables);
    }

    @Override
    public int countWaiting() {
        return getQueue().size();
    }

    @Override
    public int countRunning() {
        return getActiveCount();
    }

    @Override
    public boolean isCompleted() {
        return countWaiting() == 0 && countRunning() == 0;
    }

    @Override
    public void waitForCompletion() {
        // try {
        //     shutdown();
        //     awaitTermination(timeout, timeUnit);
        // } catch (InterruptedException e) { }
        while (!isCompleted())
            Sleep.sleep();

        shutdown();
        futures = new ArrayList<>();
    }

    @Override
    public <V> List<V> waitForResults() {
        List<Future<?>> futures = this.futures;

        waitForCompletion();

        List<V> results = new ArrayList<>();
        for(Future<?> future : futures) {
            try {
                results.add((V)future.get());
            }
            catch(InterruptedException | ExecutionException e) { }
        }
        return results;
    }

    @Override
    public void dump() {
        System.out.printf("------------------------\n");
        System.out.printf("       getActiveCount %d\n", this.getActiveCount());
        System.out.printf("             getQueue %d\n", this.getQueue().size());
        System.out.printf("          getPoolSize %d\n", this.getPoolSize());
        System.out.printf("      getCorePoolSize %d\n", this.getCorePoolSize());
        System.out.printf("getCompletedTaskCount %d\n", this.getCompletedTaskCount());
        System.out.printf("     getKeepAliveTime %d\n", this.getKeepAliveTime(TimeUnit.SECONDS));
        System.out.printf("   getLargestPoolSize %d\n", this.getLargestPoolSize());
        System.out.printf("   getMaximumPoolSize %d\n", this.getMaximumPoolSize());
        System.out.printf("         getTaskCount %d\n", this.getTaskCount());
    }
}
