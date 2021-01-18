Extensions
----------

    ExecutorService extends java.util.concurrent.ExecutorService
        - invokeAll(List<? extends Runnable> tasks)
        - countWaiting()
        - countRunning()
        - isComplete()
        - waitForCompletion()
        - List<V> waitForResults()

    Executors


Parallel
--------

    Parallel.setup()
    Parallel.shutdown()

    Parallel.forEach(int first, int last, IntConsumer body)
    Parallel.forEach(Iterable<T> it, Consumer<T> body)
    Parallel.forEach(Stream<T> s, Consumer<T> body)
    Parallel.forEach(IntStream s, IntConsumer body)

    Parallel.invokeAll(List<Callable<Boolean>> tasks)


Function
--------




Java
----

    java.lang.Thread
    java.lang.ThreadGroup

    java.lang.Runnable
        - void run()

    java.util.concurrent.Callable<V>
        - V call()

    java.util.concurrent.Future<V>
        - boolean cancel(boolean mayInterruptIfRunning)
        - boolean isCancelled();
        - boolean isDone();
        - V get()
        - V get(long timeout, TimeUnit unit)
        java.util.concurrent.RunnableFuture<V> extends Runnable,Future<V>

        java.util.concurrent.FutureTask

    Executor
        - execute(Runnable command)

        ExecutorService
            shutdown()
            shutdownNow() -> List<Runnable>
            isShutdown()
            isTerminated()
            awaitTermination(long timeout, TimeUnit unit)

            Future<T> submit(Callable<T> task);
            Future<T> submit(Runnable task, T result);
            Future<?> submit(Runnable task);
            List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)

            List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            T invokeAny(Collection<? extends Callable<T>> tasks)
            T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)


    Executors
        - ExecutorService newFixedThreadPool(int nThreads)
        - ExecutorService newWorkStealingPool(int parallelism)
        - ExecutorService newWorkStealingPool()
        - ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory)
        - ExecutorService newSingleThreadExecutor()
        - ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory)
        - ExecutorService newCachedThreadPool()
        - ExecutorService newCachedThreadPool(ThreadFactory threadFactory)

        - ScheduledExecutorService newSingleThreadScheduledExecutor()
        - ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory)
        - ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
        - ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory)

        - ExecutorService unconfigurableExecutorService(ExecutorService executor)
        - ScheduledExecutorService unconfigurableScheduledExecutorService(ScheduledExecutorService executor)

        - ThreadFactory defaultThreadFactory()
        - ThreadFactory privilegedThreadFactory()

        // adapters: <something> -> Callable

        - Callable<T> callable(Runnable task, T result)
        - Callable<Object> callable(Runnable task)
        - Callable<Object> callable(final PrivilegedAction<?> action)
        - Callable<Object> callable(final PrivilegedExceptionAction<?> action)

        - Callable<T> privilegedCallable(Callable<T> callable)
        - Callable<T> privilegedCallableUsingCurrentClassLoader(Callable<T> callable)

    ThreadFactory
        - Thread newThread(Runnable r)
