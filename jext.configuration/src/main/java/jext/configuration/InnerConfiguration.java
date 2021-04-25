package jext.configuration;

import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * This class permit to have a partial view on the configuration
 * file.
 */
public class InnerConfiguration implements Configuration {
    private Configuration root;
    private String ikey;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InnerConfiguration(String ikey, Configuration root) {
        this.ikey = ikey;
        this.root = root;
    }

    // ----------------------------------------------------------------------
    // IO Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean isChanged() {
        return root.isChanged();
    }

    @Override
    public void load() throws IOException {
        root.load();
    }

    @Override
    public void save() throws IOException {
        root.save();
    }

    // ----------------------------------------------------------------------
    // Read Operations
    // ----------------------------------------------------------------------

    @Override
    public Configuration configurationAt(String key) {
        return new InnerConfiguration(pkeyOf(key), root);
    }

    @Override
    public boolean containsKey(String key) {
        return root.containsKey(pkeyOf(key));
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return root.getBoolean(pkeyOf(key), defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return root.getInt(pkeyOf(key), defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return root.getLong(pkeyOf(key), defaultValue);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return root.getString(pkeyOf(key), defaultValue);
    }

    // ----------------------------------------------------------------------
    // Write Operations
    // ----------------------------------------------------------------------

    @Override
    public void setProperty(String key, Object value) {
        root.setProperty(pkeyOf(key), value);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private String pkeyOf(String key) {
        if (ikey.isEmpty())
            return key;
        if (key.isEmpty())
            return ikey;
        else
            return String.format("%s.%s", ikey, key);
    }
}
