package jext.configuration;

import java.io.IOException;

public interface Configuration {

    boolean isChanged();

    void load() throws IOException;
    void save() throws IOException;

    boolean containsKey(String key);

    default boolean getBoolean(String key) { return getBoolean(key, false); }
    default int  getInt(String key) { return getInt(key, 0); }
    default long getLong(String key) { return getLong(key, 0); }
    default String getString(String key) { return getString(key, null); }


    boolean getBoolean(String key, boolean defaultValue);
    int     getInt(String key, int defaultValue);
    long    getLong(String key, long defaultValue);
    String  getString(String key, String defaultValue);

    Configuration configurationAt(String key);

    void setProperty(String key, Object value);


}
