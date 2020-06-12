package jext.util;

import java.util.concurrent.TimeUnit;

public class Sleep {

    private static final long SLEEP = 0000;

    public static void sleep() {
        sleep(SLEEP);
    }

    public static void sleep(long millis) {
        if (millis > 0)
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) { }
    }

    public static void sleep(long timeout, TimeUnit timeUnit) {
        switch (timeUnit) {
            case NANOSECONDS:
                sleep(timeout/1000000L);
                break;
            case MICROSECONDS:
                sleep(timeout/1000L);
                break;
            case MILLISECONDS:
                sleep(timeout);
                break;
            case SECONDS:
                sleep(timeout*1000L);
                break;
            case MINUTES:
                sleep(timeout*60L*1000L);
                break;
            case HOURS:
                sleep(timeout*60L*60L*1000L);
                break;
            case DAYS:
                sleep(timeout*24L*60L*60L*1000L);
                break;
        }

    }
}
