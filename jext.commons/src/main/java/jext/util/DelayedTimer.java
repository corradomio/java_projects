package jext.util;

import jext.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class can be used to register callbacks that must be called each N milliseconds.
 *
 * The minimum resolution 1 second.
 *
 * Because there is only one thread used to process all callbacks, it is not possible
 * to ensure the respect of the timeout.
 *
 */
public class DelayedTimer extends Thread {

    public interface Callback {

        /**
         *
         * @param previous timestamp of the last call
         * @param now current timestamp
         */
        void call(long previous, long now);
    }

    // ----------------------------------------------------------------------
    // Factory method
    // ----------------------------------------------------------------------

    /**
     * Register a new callback
     * @param timeout (ms) eac
     * @param callback
     */
    public static void register(long timeout, Callback callback) {
        INSTANCE.submitJob(timeout, callback);
    }

    public static void unregister(Callback callback) {
        INSTANCE.deleteJob(callback);
    }

    public void terminate() {
        try {
            terminate = true;
            INSTANCE.join();
        }
        catch (InterruptedException e) {
            logger.error("Catched exception", e);
        }
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(DelayedTimer.class);
    private static DelayedTimer INSTANCE = new DelayedTimer();
    private static boolean terminate = false;

    private static class Job {
        Callback callback;
        long previous;
        long timeout;

        Job(long timeout, Callback callback) {
            this.timeout = timeout;
            this.callback = callback;
            this.previous = System.currentTimeMillis();
        }

        void call() {
            long now = System.currentTimeMillis();
            if ((now - previous) > timeout) {
                try {
                    callback.call(previous, now);
                }
                catch (Throwable e) {
                    logger.error("Catched exception", e);
                }
                finally {
                    previous = now;
                }
            }
        }
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private static long TIMESLICE = 1000;

    private List<Job> jobs = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Interface implementation
    // ----------------------------------------------------------------------

    private DelayedTimer() {
        super("DelayedTimer");
        start();
    }

    @Override
    public void run() {
        long delta, start;

        while (!terminate)
            try {
                start = System.currentTimeMillis();

                List<Job> jobs;
                synchronized (DelayedTimer.class) {
                    jobs = new ArrayList<>(this.jobs);
                }

                for (Job job : jobs)
                    if (!terminate)
                        job.call();
                    else
                        break;

                delta = System.currentTimeMillis() - start;
                if (!terminate && delta < TIMESLICE)
                    Thread.sleep(TIMESLICE - delta);
            }
            catch (Throwable t) {
                logger.error(t, t);
            }
    }

    private void submitJob(long timeout, Callback callback) {
        synchronized (DelayedTimer.class) {
            jobs.add(new Job(timeout, callback));
        }
    }

    private void deleteJob(Callback callback) {
        synchronized (DelayedTimer.class) {
            jobs.remove(callback);
        }
    }
}
