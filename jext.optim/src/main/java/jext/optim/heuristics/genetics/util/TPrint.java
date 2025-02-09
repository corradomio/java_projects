package jext.optim.heuristics.genetics.util;

import java.util.Calendar;
import java.util.Date;

public class TPrint {

    private static long timestamp = System.currentTimeMillis();
    private static long delta = 3000;

    public static void println(Object o) {
        long now = System.currentTimeMillis();
        if (now - timestamp > delta) {
            timestamp = now;
            tsprint();
            System.out.println(o);
        }
    }

    public static void printf(String fmt, Object... o) {
        long now = System.currentTimeMillis();
        if (now - timestamp > delta) {
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
