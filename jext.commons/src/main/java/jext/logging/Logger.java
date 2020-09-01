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

    // configuration

    public static void configure() {
        if (!configured) {
            File log4j = new File("log4j.xml");
            if (log4j.exists())
                configure(log4j);
            else
                BasicConfigurator.configure();
            configured = true;
        }
    }

    public static void configure(File configFile) {
        if (!configured) {
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

    public static Logger getLogger(String name) {
        if (name.contains("/"))
            name = name.replace('.', '_').replace('/', '.');
        return new Logger(LogManager.getLogger(name));
    }

    public static Logger getLogger(String format, Object... args) {
        return getLogger(String.format(format, args));
    }

    public static Logger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(Class<?> clazz, String name) {
        if (name.contains("/"))
            name = name.replace('.', '_').replace('/', '.');
        if (name.length() > 0)
            return getLogger("%s.%s", clazz.toString(), name);
        else
            return getLogger("%s.$", clazz.toString(), name);
    }

    // private fields and constructor

    private org.apache.log4j.Logger logger;

    private Logger(org.apache.log4j.Logger logger) {
        this.logger = logger;
    }

    // debugf

    public void debug(Object message) {
        logger.debug(message);
    }

    public void debug(Object message, Throwable t) {
        logger.debug(message, t);
    }

    public void debugf(String format, Object... args) {
        logger.debug(String.format(format, args));
    }

    // infof

    public void info(Object message) {
        logger.info(message);
    }

    public void info(Object message, Throwable t) {
        logger.info(message, t);
    }

    public void infof(String format, Object... args) {
        logger.info(String.format(format, args));
    }

    // warnf

    public void warn(Object message) {
        logger.warn(message);
    }

    public void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    public void warnf(String format, Object... args) {
        logger.warn(String.format(format, args));
    }

    // errorf

    public void error(Object message) {
        logger.error(message);
    }

    public void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    public void errorf(String format, Object... args) {
        logger.error(String.format(format, args));
    }

    // fatalf

    public void fatal(Object message) {
        logger.fatal(message);
    }

    public void fatal(Object message, Throwable t) {
        logger.fatal(message, t);
    }

    // timed debugf/infof

    private static transient long timestamp;
    private static transient long count;
    private static transient Level level = Level.ALL;

    public void debugt(String format, Object... args) {
        long now = System.currentTimeMillis();
        ++count;
        if ((now - timestamp) > timeout || level != Level.INFO) {
            String message = String.format(format, args);
            logger.debug(String.format("%s (%d) ...", message, count));
            timestamp = now;
            count = 0;
            level = Level.DEBUG;
        }
    }

    public void debugft(String format, Object... args) {
        debugt(String.format(format, args));
    }

    public void infoft(String format, Object... args) {
        long now = System.currentTimeMillis();
        ++count;
        if ((now - timestamp) > timeout || level != Level.INFO) {
            String message = String.format(format, args);
            logger.info(String.format("%s (%d) ...", message, count));
            timestamp = now;
            count = 0;
            level = Level.INFO;
        }
    }

    public void warnft(String format, Object... args) {
        long now = System.currentTimeMillis();
        ++count;
        if ((now - timestamp) > timeout || level != Level.WARN) {
            String message = String.format(format, args);
            logger.warn(String.format("%s (%d) ...", message, count));
            timestamp = now;
            count = 0;
            level = Level.WARN;
        }
    }

    // query

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
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

    private int incrCategory(Level level, String category) {
        if (lcounts == null)
            lcounts = new HashMap<>();
        if (!lcounts.containsKey(level))
            lcounts.put(level, new HashMap<>());
        Map<String, AtomicInteger> counts = lcounts.get(level);
        if (!counts.containsKey(category))
            counts.put(category, new AtomicInteger());
        return counts.get(category).incrementAndGet();
    }
}
