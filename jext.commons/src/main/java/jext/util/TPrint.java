package jext.util;

import java.util.Calendar;

public class TPrint {

    private static long timestamp = System.currentTimeMillis();
    public static long DELAY = 3000;

    public static void println(Object o) {
        long now = System.currentTimeMillis();
        if (now - timestamp > DELAY) {
            timestamp = now;
            tsprint();
            System.out.println(o);
        }
    }

    public static void printf(String fmt, Object... o) {
        long now = System.currentTimeMillis();
        if (now - timestamp > DELAY) {
            timestamp = now;
            tsprint();
            System.out.printf(fmt, o);
        }
    }

    private static void tsprint() {
        Calendar c = Calendar.getInstance();
        System.out.print(String.format("[%tH:%tM:%tS] ", c, c, c));
    }
}
