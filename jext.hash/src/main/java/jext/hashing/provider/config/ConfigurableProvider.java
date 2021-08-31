package jext.hashing.provider.config;

import java.util.Map;

public interface ConfigurableProvider
{
    void setParameter(String parameterName, Object parameter);

    void addAlgorithm(String key, String value);

    boolean hasAlgorithm(String type, String name);

    void addAttributes(String key, Map<String, String> attributeMap);
}