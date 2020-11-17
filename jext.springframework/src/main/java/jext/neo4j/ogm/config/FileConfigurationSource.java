package jext.neo4j.ogm.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class FileConfigurationSource extends org.neo4j.ogm.config.FileConfigurationSource {

    private static Logger logger = LogManager.getLogger(FileConfigurationSource.class);

    private Properties ref;


    /**
     * Create {@link FileConfigurationSource}
     *
     * @param propertiesFile relative or absolute path to the configuration file
     */
    public FileConfigurationSource(File propertiesFile, String prefix) {
        this(propertiesFile.getAbsolutePath(), prefix);
    }

    /**
     * Create {@link FileConfigurationSource}
     *
     * @param propertiesFilePath relative or absolute path to the configuration file
     */
    public FileConfigurationSource(String propertiesFilePath, String prefix) {
        super(propertiesFilePath);

        List<String> toRemove = new ArrayList<>();

        Field field;
        try {

            field = org.neo4j.ogm.config.FileConfigurationSource.class.getDeclaredField("properties");
            field.setAccessible(true);
            ref = (Properties) field.get(this);

            if (!prefix.endsWith(".")) prefix += ".";

            Set<String> names = new HashSet<>(ref.stringPropertyNames());
            for (String name : names) {
                if (name.startsWith(prefix)) {
                    String value = getProperty(ref, name);
                    String suffix = name.substring(prefix.length());

                    // uri & uris are in UPPERCASE!!!!
                    if (suffix.startsWith("uri"))
                        suffix = suffix.toUpperCase();
                    ref.put(suffix, value);
                }
                toRemove.add(name);
            }

            for (String name : toRemove)
                ref.remove(name);

        } catch (Exception e) {

        }

    }

    private  static String getProperty(Properties properties, String name) {
        if (!properties.containsKey(name))
            return null;

        int index = 0; // just to break recursive definitions
        String value = properties.getProperty(name);
        while(value.contains("${") && index < 128) {
            int bgn = value.indexOf("${");
            int end = value.indexOf("}", bgn+2);
            String placeholderName = value.substring(bgn+2, end);
            String placehlderValue = getProperty(properties, placeholderName);
            value = value.substring(0, bgn) + placehlderValue + value.substring(end+1);
            index += 1;
        }

        if (index >= 128)
            logger.error(String.format("Recursive definitions in %s: %s", name, properties.getProperty(name)));

        return value;
    }
}
