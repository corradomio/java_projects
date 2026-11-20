package jext.apache.logging.log4j;


import java.io.File;


// --------------------------------------------------------------------------
//
// --------------------------------------------------------------------------

public class Logger extends org.apache.logging.log4j.core.Logger {

    private static int timeout = 3000;

    private static transient long timestamp;
    private static transient long count;

    static Logger of(org.apache.logging.log4j.core.Logger logger) {
        return new Logger(logger);
    }

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static void configure() {
        LogManager.configure();
    }


    public static void configure(String loggingConfig) {
        LogManager.configure(loggingConfig);
    }

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
        return LogManager.getLogger(clazz);
    }

    public static void setTimeout(int timeout) {
        Logger.timeout = timeout;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // Simplify the conversion of 'System.out.print..' and 'System.err.print..'
    // with logging

    public static SystemLog getSystemLog(String format, Object... args) {
        return LogManager.getSystemLog(String.format(format, args));
    }

    public static SystemLog getSystemLog(Class<?> clazz) {
        return LogManager.getSystemLog(clazz.getName());
    }

    
    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private org.apache.logging.log4j.core.Logger logger;

    Logger(org.apache.logging.log4j.core.Logger logger) {
        super(logger.getContext(), logger.getName(), logger.getMessageFactory(), logger.getFlowMessageFactory());
        this.logger = logger;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void debugf(String format, Object... args) {
        debug(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void infof(String format, Object... args) {
        info(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void warnf(String format, Object... args) {
        warn(String.format(format, args));
    }

    public void warning(Object message) {
        logger.warn(toString(message));
    }

    public void warningf(String format, Object... args) {
        warning(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void errorf(String format, Object... args) {
        error(String.format(format, args));
    }

    // ----------------------------------------------------------------------

    public void fatalf(String format, Object... args) {
        fatal(String.format(format, args));
    }

    // ----------------------------------------------------------------------

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
