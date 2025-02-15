package jext.util.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LogManager {

    private static java.util.logging.LogManager manager;

    public static void configure(File loggingConfig) {
        if (manager != null)
            return;

        manager = java.util.logging.LogManager.getLogManager();
        try(FileInputStream fis = new FileInputStream(loggingConfig)) {
            manager.readConfiguration(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        return Logger.of(java.util.logging.Logger.getLogger(name));
    }
}
