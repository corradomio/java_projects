package jext.configuration;

import java.util.List;
import java.util.Properties;

public class AbstractConfiguration implements HierarchicalConfiguration {
    @Override
    public boolean isChanged() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getProperty(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Properties getProperties(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInt(String key, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(String key, long defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(String key, String defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getList(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setProperty(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Configuration configurationAt(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Configuration> configurationsAt(String key) {
        throw new UnsupportedOperationException();
    }
}
