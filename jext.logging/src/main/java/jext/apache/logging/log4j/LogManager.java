package jext.apache.logging.log4j;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class LogManager {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static void configure() {
        Configurator.initialize(new DefaultConfiguration());
    }

    public static void configure(String configurationFile) {
        configure(new File(configurationFile));
    }

    public static void configure(File configurationFile) {
        // LoggerContext context = (LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
        // context.setConfigLocation(configurationFile.toURI());

        configurationFile = checkLog4j2V1propertiesFile(configurationFile);

        try(FileInputStream fis = new FileInputStream(configurationFile)) {
            ConfigurationSource source = new ConfigurationSource(fis, configurationFile.toURL());
            Configurator.initialize(null, source);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File checkLog4j2V1propertiesFile(File configurationFile) {
        String name = configurationFile.getName();
        if (!name.endsWith(".properties"))
            return configurationFile;

        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(configurationFile)) {
            properties.load(fis);
        }
        catch (Exception e) {
            return configurationFile;
        }

        Set<Object> keys = properties.keySet();
        boolean found = false;
        for(Object key : keys) {
            String skey = (String) key;
            if (!skey.startsWith("log4j2."))
                continue;

            String nkey = skey.substring("log4j2.".length());
            properties.put(nkey, properties.getProperty(skey));
            properties.remove(skey);
            found = true;
        }

        if (!found)
            return configurationFile;

        int pos = configurationFile.getName().lastIndexOf(".");
        File configurationFileExt = new File(configurationFile.getParentFile(), name.substring(0, pos) + "-update.properties");
        try(FileOutputStream fos = new FileOutputStream(configurationFileExt)) {
            properties.store(fos, "original file: " + configurationFile.getAbsolutePath());
        }
        catch (Exception e) {
            return configurationFile;
        }

        return configurationFileExt;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static Logger getLogger() {
        return Logger.of(
            (org.apache.logging.log4j.core.Logger) org.apache.logging.log4j.LogManager.getLogger()
        );
    }

    public static Logger getLogger(final Class<?> clazz) {
        return Logger.of(
            (org.apache.logging.log4j.core.Logger) org.apache.logging.log4j.LogManager.getLogger(clazz)
        );
    }

    public static Logger getLogger(String format, Object... args) {
        String name = String.format(format, args);
        return Logger.of(
            (org.apache.logging.log4j.core.Logger) org.apache.logging.log4j.LogManager.getLogger(name)
        );
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Map<String, SystemLog> SYSTEM_LOG_MAP = new HashMap<>();

    public static SystemLog getSystemLog(Class<?> clazz) {
        return getSystemLog(clazz.getCanonicalName());
    }

    public static SystemLog getSystemLog(String format, Object... args) {
        String name = String.format(format, args);

        synchronized (SYSTEM_LOG_MAP) {
            if (!SYSTEM_LOG_MAP.containsKey(name)) {
                Logger logger = getLogger(name);
                SystemLog systemLog = new SystemLog(logger);
                SYSTEM_LOG_MAP.put(name, systemLog);
            }
            return SYSTEM_LOG_MAP.get(name);
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
