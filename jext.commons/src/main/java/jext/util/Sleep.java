package jext.util;

import java.util.concurrent.TimeUnit;

public class Sleep {

    private static final long SLEEP = 0000;

    public static void sleep() {
        sleep(SLEEP);
    }

    public static void sleep(long timeout, TimeUnit timeUnit) {
        long millis = timeUnit.toMillis(timeout);
        sleep(millis);
    }

    public static void sleep(long millis) {
        if (millis > 0)
        try {
            Thread.sleep(millis);
            } catch (InterruptedException e) { }
    }

}
