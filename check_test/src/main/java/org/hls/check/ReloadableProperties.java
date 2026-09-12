package org.hls.check;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ReloadableProperties extends Properties {

    public static Properties load(File propertiesFile) {
        ReloadableProperties props = new ReloadableProperties(propertiesFile);
        props.load();
        return props;
    }

    private File propertiesFile;
    private long lastModified;

    private ReloadableProperties(File propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    private void load() {
        if (this.lastModified == this.propertiesFile.lastModified())
            return;

        Properties saved = new Properties(this);
        try (Reader reader = new FileReader(this.propertiesFile)) {
            this.clear();
            this.load(reader);
        }
        catch (IOException e) {
            this.putAll(saved);
            throw new RuntimeException(e);
        }
        this.lastModified = this.propertiesFile.lastModified();
    }

    @Override
    public String getProperty(String key) {
        this.load();
        return super.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        this.load();
        return super.getProperty(key, defaultValue);
    }

    @Override
    public Enumeration<?> propertyNames() {
        this.load();
        return super.propertyNames();
    }

    @Override
    public Set<String> stringPropertyNames() {
        this.load();
        return super.stringPropertyNames();
    }

    @Override
    public int size() {
        this.load();
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        this.load();
        return super.isEmpty();
    }

    @Override
    public Enumeration<Object> keys() {
        this.load();
        return super.keys();
    }

    @Override
    public Enumeration<Object> elements() {
        this.load();
        return super.elements();
    }

    @Override
    public boolean contains(Object value) {
        this.load();
        return super.contains(value);
    }

    @Override
    public boolean containsValue(Object value) {
        this.load();
        return super.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        this.load();
        return super.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        this.load();
        return super.get(key);
    }

    @Override
    public Set<Object> keySet() {
        this.load();
        return super.keySet();
    }

    @Override
    public Collection<Object> values() {
        this.load();
        return super.values();
    }

    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        this.load();
        return super.entrySet();
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        this.load();
        return super.getOrDefault(key, defaultValue);
    }
}
