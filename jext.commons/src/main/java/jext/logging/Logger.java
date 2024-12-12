package jext.logging;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Logger {

    private static boolean configured = false;
    private static int timeout = 3000;

    // timed logs
    private static transient long warnts;
    private static transient long warncnt;
    private static transient long infots;
    private static transient long infocnt;

    private static transient long timestamp;
    private static transient long count;

    // configuration

    public static void configure() {
        if (!configured) {
            File log4j = new File("log4j2.xml");
            if (!log4j.exists())
                log4j = new File("config/log4j2.xml");
            if (!log4j.exists())
                log4j = new File("log4j.xml");
            if (!log4j.exists())
                log4j = new File("config/log4j.xml");
            if (log4j.exists()) {
                configure(log4j);
                Logger.getLogger(Logger.class).infof("Configured using %s", log4j.getAbsolutePath());
            }
            else {
                BasicConfigurator.configure();
                Logger.getLogger(Logger.class).infof("Configured using basic configuration");
            }
            configured = true;
        }
    }

    public static void configure(File configFile) {
        if (!configured) {
            // support for Log4j v2
            System.setProperty("log4j.configurationFile", configFile.getAbsolutePath());

            // support for Log4j v1
            if (configFile != null && configFile.exists()) {
                DOMConfigurator.configureAndWatch(configFile.getAbsolutePath(), 15000);
            }
            else if (configFile == null) {
                BasicConfigurator.configure();
                Logger.getLogger(Logger.class).error("Configuration file is null");
            }
            else {
                BasicConfigurator.configure();
                Logger.getLogger(Logger.class).error("Configuration file not existent " + configFile.getAbsolutePath());
            }
            configured = true;
        }
    }

    public static void setTimeout(int delta) {
        timeout = delta;
    }

    // factory methods

    public static Logger getLogger() {
        return getLogger("$");
    }

    public static Logger getLogger(String name) {
        if (name.contains("/"))
            name = name.replace('.', '_').replace('/', '.');
        return new Logger(LogManager.getLogger(name));
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

    // private fields and constructor

    private org.apache.log4j.Logger logger;

    private Logger(org.apache.log4j.Logger logger) {
        this.logger = logger;
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

    // print

    public void println(String message) {
        info(message);
    }

    public void println(Object object) {
        info(object != null ? object.toString() : "null");
    }

    public void printf(String message, Object ... args) {
        infof(message, args);
    }
}
