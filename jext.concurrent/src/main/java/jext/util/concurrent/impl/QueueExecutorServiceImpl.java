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
import java.util.concurrent.RunnableFuture;


/**
 * Handle a primary queue, where the tasks are executed in parallel, and some
 * extra queues, where the tasks are executed sequentially.
 */
public class QueueExecutorServiceImpl extends CachedThreadPoolImpl implements QueueExecutorService {

    private Map<String, Queue<RunnableFuture<?>>> sequentialQueues = new ConcurrentHashMap<>();

    private class ProcessingQueueTask implements Runnable {

        private String queue;

        ProcessingQueueTask(String queue) {
            this.queue = queue;
        }

        public void run() {
            Optional<RunnableFuture<?>> task = QueueExecutorServiceImpl.this.dequeue(this.queue);
            if (!task.isPresent())
                return;

            RunnableFuture<?> callable = task.get();
            callable.run();

            QueueExecutorServiceImpl.this.submit(new ProcessingQueueTask(this.queue));
        }
    }

    public QueueExecutorServiceImpl(int maximumPoolSize) {
        super(maximumPoolSize);
    }

    @Override
    public int countWaiting() {
        int[] total = new int[1];
        synchronized (sequentialQueues) {
            sequentialQueues.values()
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

    private <T> void enqueue(String queue, Callable<T> callable) {
        synchronized (sequentialQueues) {
            if (!sequentialQueues.containsKey(queue))
                sequentialQueues.put(queue, new LinkedList<>());

            Queue<RunnableFuture<?>> waitingQueue = sequentialQueues.get(queue);
            RunnableFuture<?> task = newTaskFor(callable);

            if (waitingQueue.isEmpty()) {
                waitingQueue.add(task);
                super.submit(new ProcessingQueueTask(queue));
            }
            else {
                waitingQueue.add(task);
            }
        }
    }

    private Optional<RunnableFuture<?>> dequeue(String queue) {
        synchronized (sequentialQueues) {
            if (!sequentialQueues.containsKey(queue))
                return Optional.empty();

            Queue<RunnableFuture<?>> waitingQueue = sequentialQueues.get(queue);
            if (waitingQueue.isEmpty()) {
                sequentialQueues.remove(queue);
                return Optional.empty();
            }
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
