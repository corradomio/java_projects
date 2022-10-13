package jext.util;

import jext.logging.Logger;

public class Assert {

    private static final Logger logger = Logger.getLogger(Assert.class);

    public static void error(String message, Object... args) {
        logger.errorf(message, args);
    }

    public static boolean check(boolean condition, String message, Object... args) {
        if (!condition)
            logger.errorf("AssertionError: %s ", String.format(message, args));
        return condition;
    }

    public static void verify(boolean condition, String message, Object... args) {
        if (!condition)
            throw new AssertionError(String.format(message, args));
    }

    public static void notNull(Object value, String name) {
        if (value == null)
            throw new AssertionError(String.format("%s is null", name));
    }

    public static void nop() {

    }

}
