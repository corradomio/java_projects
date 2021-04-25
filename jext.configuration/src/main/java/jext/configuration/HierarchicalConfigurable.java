package jext.configuration;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationDecoder;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.ImmutableConfiguration;
import org.apache.commons.configuration2.ImmutableHierarchicalConfiguration;
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
import java.util.Properties;
import java.util.stream.Collectors;

public class HierarchicalConfigurable
    implements HierarchicalConfiguration<ImmutableNode>
{
    protected PriorityConfigurable root;
    protected String key;
    protected HierarchicalConfiguration<ImmutableNode> config;
    protected boolean leaf;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public HierarchicalConfigurable(PriorityConfigurable root) {
        this.root = root;
        this.leaf = true;
    }

    public HierarchicalConfigurable(PriorityConfigurable root,
                                    String key,
                                    HierarchicalConfiguration<ImmutableNode> config) {
        this.root = root;
        this.key = key;
        this.config = config;
        this.leaf = key.isEmpty();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void inner() {
        this.leaf = false;
    }

    protected String keyOf(String key) {
        if (this.key.isEmpty())
            return key;
        else if (key.isEmpty())
            return this.key;
        else
            return String.format("%s.%s", this.key, key);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public void setExpressionEngine(ExpressionEngine expressionEngine) {
        config.setExpressionEngine(expressionEngine);
    }

    @Override
    public void addNodes(String key, Collection<? extends ImmutableNode> nodes) {
        config.addNodes(key, nodes);
    }

    private HierarchicalConfiguration<ImmutableNode> wrap(String key, HierarchicalConfiguration<ImmutableNode> config) {
        return new HierarchicalConfigurable(root, key, config);
    }

    private ImmutableHierarchicalConfiguration iwrap(String key, HierarchicalConfiguration<ImmutableNode> config) {
        return new HierarchicalConfigurable(root, key, config);
    }

    // --

    @Override
    public HierarchicalConfiguration<ImmutableNode> configurationAt(String key, boolean supportUpdates) {
        return wrap(key, config.configurationAt(key, supportUpdates));
    }

    @Override
    public HierarchicalConfiguration<ImmutableNode> configurationAt(String key) {
        return wrap(key, config.configurationAt(key));
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> configurationsAt(String key) {
        return config.configurationsAt(key).stream()
            .map(config -> wrap(key, config))
            .collect(Collectors.toList());
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> configurationsAt(String key, boolean supportUpdates) {
        return config.configurationsAt(key, supportUpdates).stream()
            .map(config -> wrap(key, config))
            .collect(Collectors.toList());
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> childConfigurationsAt(String key) {
        return config.childConfigurationsAt(key).stream()
            .map(config -> wrap(key, config))
            .collect(Collectors.toList());
    }

    @Override
    public List<HierarchicalConfiguration<ImmutableNode>> childConfigurationsAt(String key, boolean supportUpdates) {
        return config.childConfigurationsAt(key, supportUpdates).stream()
            .map(config -> wrap(key, config))
            .collect(Collectors.toList());
    }

    // --

    @Override
    public ImmutableHierarchicalConfiguration immutableConfigurationAt(String key, boolean supportUpdates) {
        return iwrap(key, config.configurationAt(key, supportUpdates));
    }

    @Override
    public ImmutableHierarchicalConfiguration immutableConfigurationAt(String key) {
        return iwrap(key, config.configurationAt(key));
    }

    @Override
    public List<ImmutableHierarchicalConfiguration> immutableConfigurationsAt(String key) {
        return config.configurationsAt(key).stream()
            .map(config -> iwrap(key, config))
            .collect(Collectors.toList());
    }

    @Override
    public List<ImmutableHierarchicalConfiguration> immutableChildConfigurationsAt(String key) {
        return config.childConfigurationsAt(key).stream()
            .map(config -> iwrap(key, config))
            .collect(Collectors.toList());
    }

    // --

    @Override
    public void clearTree(String key) {
        config.clearTree(key);
    }

    @Override
    public Configuration subset(String prefix) {
        return config.subset(prefix);
    }

    @Override
    public void addProperty(String key, Object value) {
        if (leaf)
            config.addProperty(key, value);
        else
            root.addProperty(keyOf(key), value);
    }

    @Override
    public void setProperty(String key, Object value) {
        if (leaf)
            config.setProperty(key, value);
        else
            root.setProperty(keyOf(key), value);
    }

    @Override
    public void clearProperty(String key) {
        if (leaf)
            config.clearProperty(key);
        else
            root.clearProperty(keyOf(key));
    }

    @Override
    public void clear() {
        if (leaf)
            config.clear();
        else
            root.clear();
    }

    @Override
    public ConfigurationInterpolator getInterpolator() {
        return config.getInterpolator();
    }

    @Override
    public void setInterpolator(ConfigurationInterpolator ci) {
        config.setInterpolator(ci);
    }

    @Override
    public void installInterpolator(Map<String, ? extends Lookup> prefixLookups, Collection<? extends Lookup> defLookups) {
        config.installInterpolator(prefixLookups, defLookups);
    }

    @Override
    public boolean isEmpty() {
        return config.isEmpty();
    }

    @Override
    public int size() {
        return config.size();
    }

    @Override
    public boolean containsKey(String key) {
        return config.containsKey(key);
    }

    @Override
    public Object getProperty(String key) {
        return config.getProperty(key);
    }

    @Override
    public Iterator<String> getKeys(String prefix) {
        return config.getKeys(prefix);
    }

    @Override
    public Iterator<String> getKeys() {
        return config.getKeys();
    }

    @Override
    public Properties getProperties(String key) {
        return config.getProperties(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(String key, Boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    @Override
    public byte getByte(String key) {
        return config.getByte(key);
    }

    @Override
    public byte getByte(String key, byte defaultValue) {
        return config.getByte(key, defaultValue);
    }

    @Override
    public Byte getByte(String key, Byte defaultValue) {
        return config.getByte(key, defaultValue);
    }

    @Override
    public double getDouble(String key) {
        return config.getDouble(key);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return config.getDouble(key, defaultValue);
    }

    @Override
    public Double getDouble(String key, Double defaultValue) {
        return config.getDouble(key, defaultValue);
    }

    @Override
    public float getFloat(String key) {
        return config.getFloat(key);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return config.getFloat(key, defaultValue);
    }

    @Override
    public Float getFloat(String key, Float defaultValue) {
        return config.getFloat(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        return config.getInt(key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return config.getInt(key, defaultValue);
    }

    @Override
    public Integer getInteger(String key, Integer defaultValue) {
        return config.getInteger(key, defaultValue);
    }

    @Override
    public long getLong(String key) {
        return config.getLong(key);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return config.getLong(key, defaultValue);
    }

    @Override
    public Long getLong(String key, Long defaultValue) {
        return config.getLong(key, defaultValue);
    }

    @Override
    public short getShort(String key) {
        return config.getShort(key);
    }

    @Override
    public short getShort(String key, short defaultValue) {
        return config.getShort(key, defaultValue);
    }

    @Override
    public Short getShort(String key, Short defaultValue) {
        return config.getShort(key, defaultValue);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return config.getBigDecimal(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        return config.getBigDecimal(key, defaultValue);
    }

    @Override
    public BigInteger getBigInteger(String key) {
        return config.getBigInteger(key);
    }

    @Override
    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        return config.getBigInteger(key, defaultValue);
    }

    @Override
    public String getString(String key) {
        return config.getString(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }

    @Override
    public String getEncodedString(String key, ConfigurationDecoder decoder) {
        return config.getEncodedString(key, decoder);
    }

    @Override
    public String getEncodedString(String key) {
        return config.getEncodedString(key);
    }

    @Override
    public String[] getStringArray(String key) {
        return config.getStringArray(key);
    }

    @Override
    public List<Object> getList(String key) {
        return config.getList(key);
    }

    @Override
    public List<Object> getList(String key, List<?> defaultValue) {
        return config.getList(key, defaultValue);
    }

    @Override
    public <T> T get(Class<T> cls, String key) {
        return config.get(cls, key);
    }

    @Override
    public <T> T get(Class<T> cls, String key, T defaultValue) {
        return config.get(cls, key, defaultValue);
    }

    @Override
    public Object getArray(Class<?> cls, String key) {
        return config.getArray(cls, key);
    }

    @Override
    @Deprecated
    public Object getArray(Class<?> cls, String key, Object defaultValue) {
        return config.getArray(cls, key, defaultValue);
    }

    @Override
    public <T> List<T> getList(Class<T> cls, String key) {
        return config.getList(cls, key);
    }

    @Override
    public <T> List<T> getList(Class<T> cls, String key, List<T> defaultValue) {
        return config.getList(cls, key, defaultValue);
    }

    @Override
    public <T> Collection<T> getCollection(Class<T> cls, String key, Collection<T> target) {
        return config.getCollection(cls, key, target);
    }

    @Override
    public <T> Collection<T> getCollection(Class<T> cls, String key, Collection<T> target, Collection<T> defaultValue) {
        return config.getCollection(cls, key, target, defaultValue);
    }

    @Override
    public ImmutableConfiguration immutableSubset(String prefix) {
        return config.immutableSubset(prefix);
    }

    @Override
    public Synchronizer getSynchronizer() {
        return config.getSynchronizer();
    }

    @Override
    public void setSynchronizer(Synchronizer sync) {
        config.setSynchronizer(sync);
    }

    @Override
    public void lock(LockMode mode) {
        config.lock(mode);
    }

    @Override
    public void unlock(LockMode mode) {
        config.unlock(mode);
    }

    @Override
    public ExpressionEngine getExpressionEngine() {
        return config.getExpressionEngine();
    }

    @Override
    public int getMaxIndex(String key) {
        return config.getMaxIndex(key);
    }

    @Override
    public String getRootElementName() {
        return config.getRootElementName();
    }

    @Override
    public NodeModel<ImmutableNode> getNodeModel() {
        return config.getNodeModel();
    }

}
