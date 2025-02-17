package jext.util;

import java.util.Calendar;

public class TPrint {

    private static long timestamp = System.currentTimeMillis();
    public static long DELAY = 3000;

    public static void println(Object obj) {
        println(true, obj);
    }

    public static void tprintln(Object obj) {
        println(false, obj);
    }

    private static void println(boolean force, Object obj) {
        long now = System.currentTimeMillis();
        if (force || now - timestamp > DELAY) {
            timestamp = now;
            tsprint();
            System.out.println(obj);
        }
    }

    public static void printf(String fmt, Object... objs) {
        printf(true, fmt, objs);
    }

    public static void tprintf(String fmt, Object... objs) {
        printf(false, fmt, objs);
    }

    private static void printf(boolean force, String fmt, Object... objs) {
        long now = System.currentTimeMillis();
        if (force || now - timestamp > DELAY) {
            timestamp = now;
            tsprint();
            System.out.printf(fmt, objs);
        }
    }

    private static void tsprint() {
        Calendar c = Calendar.getInstance();
        System.out.print(String.format("[%tH:%tM:%tS] ", c, c, c));
    }
}
