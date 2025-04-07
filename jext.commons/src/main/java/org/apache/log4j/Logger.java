package org.apache.log4j;

public class Logger extends jext.util.logging.Logger {

    protected Logger(java.util.logging.Logger logger) {
        super(logger);
    }

    public static Logger getLogger(Class<?> clazz) {
        java.util.logging.Logger jull = java.util.logging.Logger.getLogger(clazz.getName());
        return new Logger(jull);
    }

}
