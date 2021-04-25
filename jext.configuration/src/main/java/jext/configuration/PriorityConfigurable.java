package jext.configuration;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationDecoder;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.ImmutableConfiguration;
import org.apache.commons.configuration2.ImmutableHierarchicalConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.Lookup;
import org.apache.commons.configuration2.sync.LockMode;
import org.apache.commons.configuration2.sync.Synchronizer;
import org.apache.commons.configuration2.tree.ExpressionEngine;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.configuration2.tree.NodeModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Stack;
import java.util.stream.Collectors;

public class PriorityConfigurable implements HierarchicalConfiguration<ImmutableNode> {

    private final Stack<FileHierarchicalConfigurable> configurations = new Stack<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public PriorityConfigurable() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void addConfiguration(Configuration config) {
        configurations.forEach(HierarchicalConfigurable::inner);
        configurations.add((FileHierarchicalConfigurable) config);
    }

    public List<? extends Configuration> getConfigurations() {
        return configurations;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private HierarchicalConfiguration<ImmutableNode> wrap(String key, HierarchicalConfiguration<ImmutableNode> config) {
        return new HierarchicalConfigurable(this, key, config);
    }

    private ImmutableHierarchicalConfiguration wrap(String key, ImmutableHierarchicalConfiguration config) {
        return new HierarchicalConfigurable(this, key, (HierarchicalConfiguration<ImmutableNode>) config);
    }

    protected FileHierarchicalConfigurable peek() {
        return configurations.peek();
    }

    protected FileHierarchicalConfigurable base() {
        return configurations.get(0);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public HierarchicalConfiguration<ImmutableNode> configurationAt(String key) {
        return configurationAt(key, false);
    }

    @Override
    public HierarchicalConfiguration<ImmutableNode> configurationAt(String key, boolean supportUpdates) {
        for (HierarchicalConfigurable configuration : configurations) {
            try {
                HierarchicalConfiguration<ImmutableNode> config = configuration.configurationAt(key, supportUpdates);
                return wrap(key, config);
            }
            catch (ConfigurationRuntimeException ex) { }
        }
        throw new ConfigurationRuntimeException(
            "Passed in key must select exactly one node (found %,d): %s", 0, key);
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> configurationsAt(String key) {
        return configurationsAt(key, false);
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> configurationsAt(String key, boolean supportUpdates) {
        for (HierarchicalConfigurable configuration : configurations) {
            try {
                return configuration.configurationsAt(key, supportUpdates).stream()
                    .map(config -> wrap(key, config))
                    .collect(Collectors.toList());
            }
            catch (ConfigurationRuntimeException ex) { }
        }
        throw new ConfigurationRuntimeException(
            "Passed in key must select exactly one node (found %,d): %s", 0, key);
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> childConfigurationsAt(String key) {
        return childConfigurationsAt(key, false);
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> childConfigurationsAt(String key, boolean supportUpdates) {
        for (HierarchicalConfigurable configuration : configurations) {
            try {
                return configuration.childConfigurationsAt(key, supportUpdates).stream()
                    .map(config -> wrap(key, config))
                    .collect(Collectors.toList());
            }
            catch (ConfigurationRuntimeException ex) { }
        }
        throw new ConfigurationRuntimeException(
            "Passed in key must select exactly one node (found %,d): %s", 0, key);
    }

    // --

    @Override
    public ImmutableHierarchicalConfiguration immutableConfigurationAt(String key) {
        return immutableConfigurationAt(key, false);
    }

    @Override
    public ImmutableHierarchicalConfiguration immutableConfigurationAt(String key, boolean supportUpdates) {
        for (HierarchicalConfigurable configuration : configurations) {
            try {
                return wrap(key, configuration.immutableConfigurationAt(key, supportUpdates));
            }
            catch (ConfigurationRuntimeException ex) { }
        }
        throw new ConfigurationRuntimeException(
            "Passed in key must select exactly one node (found %,d): %s", 0, key);
    }

    @Override
    public List<ImmutableHierarchicalConfiguration> immutableConfigurationsAt(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            try {
                return configuration.immutableConfigurationsAt(key).stream()
                    .map(config -> wrap(key, config))
                    .collect(Collectors.toList());
            }
            catch (ConfigurationRuntimeException ex) { }
        }
        throw new ConfigurationRuntimeException(
            "Passed in key must select exactly one node (found %,d): %s", 0, key);
    }

    @Override
    public List<ImmutableHierarchicalConfiguration> immutableChildConfigurationsAt(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            try {
                return configuration.immutableChildConfigurationsAt(key).stream()
                    .map(config -> wrap(key, config))
                    .collect(Collectors.toList());
            }
            catch (ConfigurationRuntimeException ex) { }
        }
        throw new ConfigurationRuntimeException(
            "Passed in key must select exactly one node (found %,d): %s", 0, key);
    }

    // --

    @Override
    public void setExpressionEngine(ExpressionEngine expressionEngine) {
        this.configurations.forEach( config -> config.setExpressionEngine(expressionEngine));
    }

    @Override
    public void addNodes(String key, Collection<? extends ImmutableNode> nodes) {
        this.peek().addNodes(key, nodes);
    }

    @Override
    public void clearTree(String key) {
        this.peek().clearTree(key);
    }

    @Override
    public Configuration subset(String prefix) {
        for (HierarchicalConfigurable configuration : configurations) {
            try {
                return configuration.subset(prefix);
            }
            catch (ConfigurationRuntimeException ex) { }
        }
        throw new ConfigurationRuntimeException(
            "Passed in key must select exactly one node (found %,d): %s", 0, prefix);
    }

    @Override
    public void addProperty(String key, Object value) {
        this.peek().addProperty(key, value);
    }

    @Override
    public void setProperty(String key, Object value) {
        this.peek().setProperty(key, value);
    }

    @Override
    public void clearProperty(String key) {
        this.peek().clearProperty(key);
    }

    @Override
    public void clear() {
        this.peek().clear();
    }

    @Override
    public ConfigurationInterpolator getInterpolator() {
        return this.base().getInterpolator();
    }

    @Override
    public void setInterpolator(ConfigurationInterpolator ci) {
        this.configurations.forEach( config -> config.setInterpolator(ci));
    }

    @Override
    public void installInterpolator(Map<String, ? extends Lookup> prefixLookups, Collection<? extends Lookup> defLookups) {
        this.configurations.forEach( config -> config.installInterpolator(prefixLookups, defLookups));
    }

    @Override
    public ExpressionEngine getExpressionEngine() {
        return this.base().getExpressionEngine();
    }

    @Override
    public int getMaxIndex(String key) {
        return this.base().getMaxIndex(key);
    }

    @Override
    public String getRootElementName() {
        return this.base().getRootElementName();
    }

    @Override
    public boolean isEmpty() {
        return configurations.isEmpty();
    }

    @Override
    public int size() {
        return configurations.size();
    }

    @Override
    public boolean containsKey(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return true;
        }
        return false;
    }

    @Override
    public Object getProperty(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getProperty(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public Iterator<String> getKeys(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<String> getKeys() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Properties getProperties(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getProperties(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public boolean getBoolean(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getBoolean(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        try {
            return getBoolean(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Boolean getBoolean(String key, Boolean defaultValue) {
        try {
            return getBoolean(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public byte getByte(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getByte(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public byte getByte(String key, byte defaultValue) {
        try {
            return getByte(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Byte getByte(String key, Byte defaultValue) {
        try {
            return getByte(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getDouble(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        try {
            return getDouble(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Double getDouble(String key, Double defaultValue) {
        try {
            return getDouble(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getFloat(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        try {
            return getFloat(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Float getFloat(String key, Float defaultValue) {
        try {
            return getFloat(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getInt(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        try {
            return getInt(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Integer getInteger(String key, Integer defaultValue) {
        try {
            return getInt(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getLong(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        try {
            return getLong(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Long getLong(String key, Long defaultValue) {
        try {
            return getLong(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public short getShort(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getShort(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public short getShort(String key, short defaultValue) {
        try {
            return getShort(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Short getShort(String key, Short defaultValue) {
        try {
            return getShort(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getBigDecimal(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        try {
            return getBigDecimal(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getBigInteger(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        try {
            return getBigInteger(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public String getString(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getString(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        try {
            return getString(key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public String getEncodedString(String key, ConfigurationDecoder decoder) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getEncodedString(key, decoder);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public String getEncodedString(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getEncodedString(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public String[] getStringArray(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getStringArray(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public List<Object> getList(String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getList(key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public List<Object> getList(String key, List<?> defaultValue) {
        try {
            return getList(key);
        }
        catch (NoSuchElementException e) {
            return (List<Object>) defaultValue;
        }
    }

    @Override
    public <T> T get(Class<T> cls, String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.get(cls, key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public <T> T get(Class<T> cls, String key, T defaultValue) {
        try {
            return get(cls, key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public Object getArray(Class<?> cls, String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getArray(cls, key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public Object getArray(Class<?> cls, String key, Object defaultValue) {
        try {
            return getArray(cls, key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public <T> List<T> getList(Class<T> cls, String key) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getList(cls, key);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public <T> List<T> getList(Class<T> cls, String key, List<T> defaultValue) {
        try {
            return getList(cls, key);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public <T> Collection<T> getCollection(Class<T> cls, String key, Collection<T> target) {
        for (HierarchicalConfigurable configuration : configurations) {
            if (configuration.containsKey(key))
                return configuration.getCollection(cls, key, target);
        }
        throw new NoSuchElementException(key);
    }

    @Override
    public <T> Collection<T> getCollection(Class<T> cls, String key, Collection<T> target, Collection<T> defaultValue) {
        try {
            return getCollection(cls, key, target);
        }
        catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    @Override
    public ImmutableConfiguration immutableSubset(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Synchronizer getSynchronizer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSynchronizer(Synchronizer sync) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void lock(LockMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unlock(LockMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NodeModel<ImmutableNode> getNodeModel() {
        throw new UnsupportedOperationException();
    }
}
