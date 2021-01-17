package jext.util.concurrent.impl;

import jext.util.concurrent.Executors;
import jext.util.concurrent.QueueExecutorService;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;


public class QueueExecutorServiceImpl extends CachedThreadPoolImpl implements QueueExecutorService {

    private Map<String, Queue<Callable<?>>> waitingQueues = new ConcurrentHashMap<>();

    private class ProcessingQueueTask implements Runnable {

        private String queue;

        ProcessingQueueTask(String queue) {
            this.queue = queue;
        }

        public void run() {
            Optional<Callable<?>> task = QueueExecutorServiceImpl.this.dequeue(this.queue);
            if (!task.isPresent())
                return;

            try {
                Callable<?> callable = task.get();
                Object result = callable.call();
            } catch (Exception e) { }

            QueueExecutorServiceImpl.this.submit(new ProcessingQueueTask(this.queue));
        }
    }

    public QueueExecutorServiceImpl(int maximumPoolSize) {
        super(maximumPoolSize);
    }

    @Override
    public int countWaiting() {
        int[] total = new int[1];
        synchronized (waitingQueues) {
            waitingQueues.values()
                .stream()
                .map(Collection::size)
                .forEach(count -> {
                    total[0] += count;
                });

            total[0] += super.countWaiting();
        }
        return total[0];
    }

    @Override
    public <T> void submit(String queue, Callable<T> task) {
        if (queue == null)
            submit(task);
        else
            enqueue(queue, task);
    }

    @Override
    public <T> void submit(String queue, Runnable task, T result) {
        submit(queue, Executors.callable(task, result));
    }

    @Override
    public void submit(String queue, Runnable task) {
        submit(queue, Executors.callable(task));
    }

    @Override
    public <T> void invokeAll(String queue, Collection<? extends Callable<T>> tasks) {
        for (Callable<T> task : tasks)
            this.submit(queue, task);
    }

    private <T> void enqueue(String queue, Callable<T> task) {
        synchronized (waitingQueues) {
            if (!waitingQueues.containsKey(queue))
                waitingQueues.put(queue, new LinkedList<>());

            Queue<Callable<?>> waitingQueue = waitingQueues.get(queue);
            if (waitingQueue.isEmpty()) {
                waitingQueue.add(task);
                super.submit(new ProcessingQueueTask(queue));
            }
            else {
                waitingQueue.add(task);
            }
        }
    }

    private Optional<Callable<?>> dequeue(String queue) {
        synchronized (waitingQueues) {
            if (!waitingQueues.containsKey(queue))
                return Optional.empty();

            Queue<Callable<?>> waitingQueue = waitingQueues.get(queue);
            if (waitingQueue.isEmpty())
                return Optional.empty();
            else
                return Optional.of(waitingQueue.remove());
        }
    }

    // @Override
    // public <T> List<Future<T>> invokeAll(String queue, Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) {
    //     return null;
    // }
    //
    // @Override
    // public <T> T invokeAny(String queue, Collection<? extends Callable<T>> tasks) {
    //     return null;
    // }
    //
    // @Override
    // public <T> T invokeAny(String queue, Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) {
    //     return null;
    // }
}
