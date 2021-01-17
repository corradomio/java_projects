package jext.util.concurrent;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Tasks submitted inside a specified queue are executed sequentially
 */
public interface QueueExecutorService extends ExecutorService {

    <T> void submit(String queue, Callable<T> task);
    <T> void submit(String queue, Runnable task, T result);
        void submit(String queue, Runnable task);

    <T> void invokeAll(String queue, Collection<? extends Callable<T>> tasks);

    // <T> List<Future<T>> invokeAll(String queue, Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit);
    // <T> T invokeAny(String queue, Collection<? extends Callable<T>> tasks);
    // <T> T invokeAny(String queue, Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit);

}
