package jext.util.logging;

import java.io.File;

public class Logger {

    private static int timeout = 3000;

    private static transient long timestamp;
    private static transient long count;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static class Level extends java.util.logging.Level {
        protected Level(String name, int value) {
            super(name, value);
        }
    }

    private static final Level DEBUG = new Level("DEBUG",500);
    private static final Level ERROR = new Level("ERROR",950);
    private static final Level FATAL = new Level("FATAL",1000);

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // static Logger of(java.util.logging.Logger logger) {
    //     return new Logger(logger);
    // }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private java.util.logging.Logger logger;

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static void configure(File loggingConfig) {
        LogManager.configure(loggingConfig);
    }

    public static Logger getLogger() {
        return LogManager.getLogger("$");
    }

    public static Logger getLogger(String format, Object... args) {
        return LogManager.getLogger(String.format(format, args));
    }

    public static Logger getLogger(Class<?> clazz) {
        // return  new Logger(java.util.logging.LogManager.getLogManager().getLogger(clazz.getName()));
        return new Logger(java.util.logging.Logger.getLogger(clazz.getName()));
    }

    public static Logger getLogger(String name){
        return LogManager.getLogger(name);
    }

    public static void setTimeout(int timeout) {
        Logger.timeout = timeout;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected Logger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public boolean isDebugEnabled() {
        return logger.isLoggable(DEBUG);
    }

    public boolean isInfoEnabled() {
        return logger.isLoggable(java.util.logging.Level.INFO);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void debug(Object message) {
        logger.log(DEBUG, toString(message));
    }

    public void debugf(String format, Object... args) {
        debug(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void info(Object message) {
        logger.log(java.util.logging.Level.INFO, toString(message));
    }

    public void infof(String format, Object... args) {
        info(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void warn(Object message) {
        warning(message);
    }

    public void warnf(String format, Object... args) {
        warn(String.format(format, args));
    }

    public void warning(Object message) {
        logger.log(java.util.logging.Level.WARNING, toString(message));
    }

    public void warningf(String format, Object... args) {
        warning(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void error(Object message) {
        logger.log(ERROR, toString(message));
    }

    public void error(Object message, Throwable t) {
        logger.log(ERROR, toString(message), t);
    }

    public void errorf(String format, Object... args) {
        error(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void fatal(Object message) {
        logger.log(FATAL, toString(message));
    }

    public void fatal(Object message, Throwable t) {
        logger.log(FATAL, toString(message), t);
    }

    public void fatalf(String format, Object... args) {
        fatal(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void severe(Object message) {
        logger.log(java.util.logging.Level.SEVERE, toString(message));
    }

    public void severe(Object message, Throwable t) {
        logger.log(java.util.logging.Level.SEVERE, toString(message), t);
    }

    public void severef(String format, Object... args) {
        severe(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void debugft(String format, Object... args) {
        long now = System.currentTimeMillis();
        ++count;
        if ((now - timestamp) > timeout/* || level != Level.DEBUG*/) {
            String message = String.format(format, args);
            debug(String.format("%s (%d) ...", message, count));
            timestamp = now;
            count = 0;
        }
    }

    // ----------------------------------------------------------------------

    public void println(Object object) {
        info(toString(object));
    }

    public void printf(String message, Object ... args) {
        infof(message, args);
    }

    // ----------------------------------------------------------------------

    private static String toString(Object object) {
        if (object == null)
            return "null";
        else
            return object.toString();
    }
}
