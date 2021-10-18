package jext.debug;

import jext.logging.Logger;

public class Debug {

    private static final Logger logger = Logger.getLogger("[[DEBUGGING]]");

    public static Logger logger() {
        return Debug.logger;
    }

    public static void nop() {

    }

    public static void breakpoint() {

    }
}
