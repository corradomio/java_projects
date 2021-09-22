package jext.util;

public class Assert {

    public static void check(boolean condition, String message, Object... args) {
        if (!condition)
            throw new AssertionError(String.format(message, args));
    }
}
