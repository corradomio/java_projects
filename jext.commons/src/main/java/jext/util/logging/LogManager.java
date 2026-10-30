package jext.util.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LogManager {

    protected static java.util.logging.LogManager manager;

    public static void configure(String loggingConfig) {
        configure(new File(loggingConfig));
    }

    public static void configure(File loggingConfig) {
        if (manager != null)
            return;

        // WARNING: the file MUST BE in ".properties" format!!!
        if (!loggingConfig.getName().endsWith(".properties")) {
            throw new IllegalArgumentException("Log configuration file must be in '.properties' format");
        }

        synchronized (LogManager.class) {
            manager = java.util.logging.LogManager.getLogManager();
        }

        try(InputStream is =  new FileInputStream(loggingConfig)) {
            manager.readConfiguration(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // if (loggingConfig.getName().endsWith(".xml")) {
        //     File propertiesConfig = new File(loggingConfig.getParentFile(), loggingConfig.getName() + ".properties");
        //     if (!propertiesConfig.exists() || propertiesConfig.lastModified() < loggingConfig.lastModified()) {
        //         try {
        //             if (Log4J2Converted.isLog4J2(loggingConfig))
        //                 Log4J2Converted.convert(loggingConfig, propertiesConfig);
        //             else if (Log4J1Converted.isLog4J1(loggingConfig))
        //                 Log4J1Converted.convert(loggingConfig, propertiesConfig);
        //             else
        //                 throw new IOException("Unrecognized Log4j XML configuration: " + loggingConfig);
        //         } catch (IOException e) {
        //             throw new RuntimeException(e);
        //         }
        //     }
        //     loggingConfig = propertiesConfig;
        // }
        //
        // // WARNING: the file MUST BE in ".properties" format!!!
        // if (!loggingConfig.getName().endsWith(".properties")) {
        //     throw new IllegalArgumentException("Log configuration file must be in '.properties' format");
        // }
        //
        // manager = java.util.logging.LogManager.getLogManager();
        // try(FileInputStream fis = new FileInputStream(loggingConfig)) {
        //     manager.readConfiguration(fis);
        // } catch (FileNotFoundException e) {
        //     throw new RuntimeException(e);
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }
    }

    public static Logger getLogger() {
        return getLogger("$");
    }

    public static Logger getLogger(String format, Object... args) {
        return getLogger(String.format(format, args));
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getCanonicalName());
    }

    public static Logger getLogger(String name){
        return new Logger(java.util.logging.Logger.getLogger(name));
    }
}
