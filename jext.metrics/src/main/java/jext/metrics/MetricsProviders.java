package jext.metrics;

import jext.exception.InvalidValueException;
import jext.logging.Logger;
import jext.util.PropertiesUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MetricsProviders {

    private static final Logger logger = Logger.getLogger(MetricsProviders.class);

    private static final Map<String, Class<MetricsProvider>> providers = new HashMap<>();

    static {
        try (InputStream stream = MetricsProviders.class.getResourceAsStream("providers.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            for(String name : properties.stringPropertyNames()) {
                String providerClassName = properties.getProperty(name);
                register(name, providerClassName);
            }
        }
        catch (IOException e) {
            // in theory never happen
            logger.error(e.getMessage());
        }
    }

    public static void register(String name, String providerClassName) {
        try {
            Class<?> providerClass = Class.forName(providerClassName);
            providers.put(name, (Class<MetricsProvider>) providerClass);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public static List<String> getProviders() {
        return Arrays.asList("scitools");
    }

    public static <T extends MetricsProvider> T getProvider(String name) {
        return getProvider(name, PropertiesUtils.empty());
    }

    public static <T extends MetricsProvider> T getProvider(String name, Properties properties) {
        if (!providers.containsKey(name))
            throw new InvalidValueException("provider name", name);

        Class<MetricsProvider> providerClass = providers.get(name);
        try {
            T provider = (T) providerClass.getConstructor().newInstance();
            provider.initialize(properties);
            return provider;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // in theory never happen, only in development
            throw new NoSuchProviderException(name, e);
        }
    }
}
