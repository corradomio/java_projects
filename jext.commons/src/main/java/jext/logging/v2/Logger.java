package jext.logging.v2;

import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Logger implements jext.logging.ILogger {

    private static boolean configured = false;
    private static int timeout = 3000;

    // timed logs
    private static transient long warnts;
    private static transient long warncnt;
    private static transient long infots;
    private static transient long infocnt;

    private static transient long timestamp;
    private static transient long count;

    public static void configure() {
        File configFile = new File("log4j2.xml");
        if (!configFile.exists())
            configFile = new File("config/log4j2.xml");
        if (!configFile.exists())
            configFile = new File("WEB-INF/log4j2.xml");

        // compatibility with log4jv1
        if (!configFile.exists())
            configFile = new File("log4j.xml");
        if (!configFile.exists())
            configFile = new File("config/log4j.xml");
        if (!configFile.exists())
            configFile = new File("WEB-INF/log4j.xml");

        if (configFile.exists())
            configure(configFile);
    }

    public static void configure(File configurationFile) {
        //log4j.configurationFile=path/to/log4j2.xml
        System.setProperty("log4j.configurationFile", configurationFile.getAbsolutePath());
    }

    public static Logger getLogger() {
        return getLogger("$");
    }

    public static Logger getLogger(String name) {
        if (name.contains("/"))
            name = name.replace('.', '_').replace('/', '.');
        org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(name);
        return new Logger(log);
    }

    public static Logger getLogger(String format, Object... args) {
        return getLogger(String.format(format, args));
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getCanonicalName());
    }

    public static Logger getLogger(Class<?> clazz, String name) {
        if (name.contains("/"))
            name = name.replace('.', '_').replace('/', '.');
        if (name.length() > 0)
            return getLogger("%s.%s", clazz.getCanonicalName(), name);
        else
            return getLogger("%s.$", clazz.getCanonicalName(), name);
    }

    // public static Logger getLogger(Class<?> clazz) {
    //     org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(clazz);
    //     return new Logger(log);
    // }

    private final org.apache.logging.log4j.Logger logger;

    protected Logger(org.apache.logging.log4j.Logger log) {
        this.logger = log;
    }

    // query

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    // debug/debugf

    public void debug(Object message) {
        logger.debug(message);
    }

    public void debug(Object message, Throwable t) {
        logger.debug(message, t);
    }

    public void debugf(String format, Object... args) {
        logger.debug(String.format(format, args));
    }

    // info/infof

    public void info(Object message) {
        logger.info(message);
    }

    public void info(Object message, Throwable t) {
        logger.info(message, t);
    }

    public void infof(String format, Object... args) {
        logger.info(String.format(format, args));
    }

    // warn/warnf

    public void warn(Object message) {
        logger.warn(message);
    }

    public void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    public void warnf(String format, Object... args) {
        logger.warn(String.format(format, args));
    }

    // error/errorf

    public void error(Object message) {
        logger.error(message);
    }

    public void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    public void errorf(String format, Object... args) {
        logger.error(String.format(format, args));
    }

    // fatal

    public void fatal(Object message) {
        logger.fatal(message);
    }

    public void fatal(Object message, Throwable t) {
        logger.fatal(message, t);
    }

    // timed debug/info
    // it write a log ONLY each 'timeout' milliseconds

    public void debugt(String format, Object... args) {
        long now = System.currentTimeMillis();
        ++count;
        if ((now - timestamp) > timeout/* || level != Level.DEBUG*/) {
            String message = String.format(format, args);
            logger.debug(String.format("%s (%d) ...", message, count));
            timestamp = now;
            count = 0;
        }
    }

    public void debugft(String format, Object... args) {
        debugt(String.format(format, args));
    }

    public void infoft(String format, Object... args) {
        long now = System.currentTimeMillis();
        ++infocnt;
        if ((now - infots) > timeout/* || level != Level.INFO*/) {
            String message = String.format(format, args);
            logger.info(String.format("%s (%d) ...", message, count));
            infots = now;
            infocnt = 0;
        }
    }

    public void warnft(String format, Object... args) {
        long now = System.currentTimeMillis();
        ++warncnt;
        if ((now - warnts) > timeout/* || level != Level.WARN*/) {
            String message = String.format(format, args);
            logger.warn(String.format("%s (%d) ...", message, count));
            warnts = now;
            warncnt = 0;
        }
    }

    // counted warn/error
    // it generate ONLY a specified number of messages

    // counted logs
    private static int minCount = 1;
    private static int maxCount = 100;
    private Map<Level, Map<String, AtomicInteger>> lcounts;

    public void warnc(String category, String format, Object... args) {
        int count = incrCategory(Level.WARN, category);
        if (count <= minCount) {
            String message = String.format(format, args);
            logger.warn(message);
        }
        else if (count % maxCount == 0) {
            String message = String.format(format, args);
            logger.warn(message + String.format(" (%d times)", count));
        }
    }

    public void errorc(String category, String format, Object... args) {
        int count = incrCategory(Level.ERROR, category);
        if (count <= minCount) {
            String message = String.format(format, args);
            logger.error(message);
        }
        else if (count % maxCount == 0) {
            String message = String.format(format, args);
            logger.error(message + String.format(" (%d times)", count));
        }
    }

    private synchronized int incrCategory(Level level, String category) {
        if (lcounts == null)
            lcounts = new HashMap<>();
        if (!lcounts.containsKey(level))
            lcounts.put(level, new HashMap<>());
        Map<String, AtomicInteger> counts = lcounts.get(level);
        if (!counts.containsKey(category))
            counts.put(category, new AtomicInteger());
        return counts.get(category).incrementAndGet();
    }

    // query

    public void println(String message) {
        info(message);
    }

    public void printf(String message, Object ... args) {
        infof(message, args);
    }
}
