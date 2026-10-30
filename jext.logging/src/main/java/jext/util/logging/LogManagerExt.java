package jext.util.logging;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jext.exception.InvalidParameterException;


public class LogManagerExt extends LogManager {

    public static void configure(String loggingConfig) {
        configure(new File(loggingConfig));
    }

    public static void configure(File loggingConfig) {
        if (manager != null)
            return;

        try {
            synchronized (LogManager.class) {
                manager = java.util.logging.LogManager.getLogManager();
            }

            if (loggingConfig.getName().endsWith(".properties")) {
                try(InputStream is =  new FileInputStream(loggingConfig)) {
                    manager.readConfiguration(is);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

            if (Log4J1Parser.isLog4J1(loggingConfig)) {
                Properties properties = Log4J1Parser.parser(loggingConfig);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                properties.store(bytes, null);
                manager.readConfiguration(new ByteArrayInputStream(bytes.toByteArray()));
                return;
            }

            if (Log4J2Parser.isLog4J2(loggingConfig)) {
                Properties properties = Log4J2Parser.parser(loggingConfig);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                properties.store(bytes, null);
                manager.readConfiguration(new ByteArrayInputStream(bytes.toByteArray()));
                return;
            }

            throw new InvalidParameterException("loggingConfig", "Unsupported configuration file " + loggingConfig);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
