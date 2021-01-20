package jext.util.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Sleep {

    public static void sleep() {
        sleep(500);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {

        }
    }

    public static void sleep(long minTimeout, long maxTimeout, TimeUnit timeUnit) {
        java.util.concurrent.ThreadLocalRandom rnd = ThreadLocalRandom.current();
        rnd.setSeed(Thread.currentThread().getId());
        long timeout = rnd.nextInt((int)(maxTimeout - minTimeout)) + minTimeout;
        sleep(timeout, timeUnit);
    }

    public static void sleep(long timeout, TimeUnit timeUnit) {
        long millis = timeUnit.toMillis(timeout);
        sleep(millis);
    }

}
