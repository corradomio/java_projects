package jext.configuration;

import jext.logging.Logger;
import jext.xml.XPathUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class XMLConfiguration implements HierarchicalConfiguration {

    private static Logger logger = Logger.getLogger(XMLConfiguration.class);

    private Configuration parent;
    private File configurationFile;
    private long timestamp;
    private Document doc;
    private Element root;
    private Properties properties;
    private boolean updated;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public XMLConfiguration(File configurationFile) {
        this.configurationFile = configurationFile;
        this.timestamp = -1;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public File getFile() {
        return configurationFile;
    }

    // ----------------------------------------------------------------------
    // IO Operations
    // ----------------------------------------------------------------------

    @Override
    public File getHomeFolder() {
        if (parent != null)
            return parent.getHomeFolder();
        else
            return this.configurationFile.getParentFile();
    }

    @Override
    public boolean isChanged() {
        if (this.updated)
            return true;
        if (this.timestamp != 0 && !configurationFile.exists())
            return true;
        else if (this.timestamp == 0 && !configurationFile.exists())
            return false;
        else
            return this.timestamp != configurationFile.lastModified();
    }

    @Override
    public void load() {
        try {
            if (configurationFile.exists()) {
                Document tdoc = XPathUtils.parse(configurationFile);
                doc = tdoc;
                root = doc.getDocumentElement();
            } else {
                doc = XPathUtils.parse("<configuration/>");
                root = this.doc.getDocumentElement();
                timestamp = 0;
            }
            properties = XPathUtils.getProperties(root);
            updated = false;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.errorf("Unable to parse %s", configurationFile);
        }

        timestamp = configurationFile.lastModified();
    }

    @Override
    public void save() {
        if (updated)
        try {
            XPathUtils.serialize(doc, configurationFile);
            updated = false;
            timestamp = configurationFile.lastModified();
        } catch (TransformerException | IOException e) {
            logger.errorf("Unable to save %s", configurationFile);
        }
    }

    // ----------------------------------------------------------------------
    // Read Operations
    // ----------------------------------------------------------------------

    @Override
    public HierarchicalConfiguration configurationAt(String key) {
        check();
        return new InnerConfiguration(key, this);
    }

    @Override
    public List<Configuration> configurationsAt(String key) {
        check();
        int n = XPathUtils.selectNodes(root, xpathOf(key)).size();
        List<Configuration> configList = new ArrayList<>();
        for (int i=0; i<n; ++i) {
            String ikey = String.format("%s(%d)", key, i);
            configList.add(new InnerConfiguration(ikey, this));
        }
        return configList;
    }

    // ----------------------------------------------------------------------
    // Read Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean containsKey(String key) {
        check();
        Node node = XPathUtils.selectNode(root, xpathOf(key));
        return node != null;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        check();
        return XPathUtils.getValue(root, xpathOf(key), defaultValue, properties);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        check();
        return XPathUtils.getValue(root, xpathOf(key), defaultValue, properties);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        check();
        return XPathUtils.getValue(root, xpathOf(key), defaultValue, properties);
    }

    @Override
    public String getString(String key, String defaultValue) {
        check();
        return XPathUtils.getValue(root, xpathOf(key), defaultValue, properties);
    }

    @Override
    public List<String> getList(String key) {
        check();
        return XPathUtils.getValues(root, xpathOf(key), properties);
    }

    @Override
    public Properties getProperties(String key) {
        check();
        return XPathUtils.getProperties(root, xpathOf(key));
    }

    // ----------------------------------------------------------------------
    // Write Operations
    // ----------------------------------------------------------------------

    public void setParent(Configuration parent) {
        this.parent = parent;
    }

    @Override
    public Object getProperty(String key) {
        check();
        return XPathUtils.getValue(root, xpathOf(key));
    }

    @Override
    public void setProperty(String key, Object value) {
        //check();

        if (parent != null && key.startsWith("@")) {
            parent.setProperty(key, value);
            return;
        }

        String svalue = value != null ? value.toString() : null;
        XPathUtils.setValue(root, xpathOf(key), svalue, properties);
        updated = true;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private String xpathOf(String key) {
        // - node.node                 node/node
        // - node(index)               node[index]
        // - node[@attribute]          node/@attribute

        if (!key.contains("."))
            return key;

        int state = 0; // 0 -> normal, 1 -> in String

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<key.length(); ++i) {
            char ch = key.charAt(i);
            if (ch == '.') {
                if (state == 0)
                    sb.append('/');
                else
                    sb.append(ch);
            }
            else if (ch == '\'' || ch == '"') {
                if (state == 0)
                    state = 1;
                else
                    state = 0;
                sb.append(ch);
            }
            else
                sb.append(ch);
        }

        return sb.toString();
    }

    private void check()  {
        if (isChanged())
            load();
    }
}

