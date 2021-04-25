package jext.configuration;

import jext.logging.Logger;
import jext.util.FileUtils;
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
    // IO Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean isChanged() {
        if (this.timestamp != 0 && !configurationFile.exists())
            return true;
        else if (this.timestamp == 0 && !configurationFile.exists())
            return false;
        else
            return this.timestamp != configurationFile.lastModified();
    }

    @Override
    public void load() throws IOException {
        try {
            if (configurationFile.exists()) {
                this.doc = XPathUtils.parse(configurationFile);
                this.root = this.doc.getDocumentElement();
                this.timestamp = configurationFile.lastModified();
            } else {
                this.doc = XPathUtils.parse("<configuration/>");
                this.root = this.doc.getDocumentElement();
                this.timestamp = 0;
            }
            this.properties = XPathUtils.getProperties(this.root);
            this.updated = false;
        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void save() throws IOException {
        if (updated)
        try {
            XPathUtils.serialize(doc, configurationFile);
            this.updated = false;
        } catch (TransformerException e) {
            throw new IOException(e);
        }
    }

    // ----------------------------------------------------------------------
    // Read Operations
    // ----------------------------------------------------------------------

    @Override
    public HierarchicalConfiguration configurationAt(String key) {
        return new InnerConfiguration(key, this);
    }

    @Override
    public List<HierarchicalConfiguration> configurationsAt(String key) {
        int n = XPathUtils.selectNodes(root, xpathOf(key)).size();
        List<HierarchicalConfiguration> configList = new ArrayList<>();
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
        return XPathUtils.getValue(root, xpathOf(key), defaultValue);
    }

    @Override
    public Properties getProperties(String key) {
        check();
        return XPathUtils.getProperties(root, xpathOf(key));
    }

    // ----------------------------------------------------------------------
    // Write Operations
    // ----------------------------------------------------------------------

    @Override
    public void setProperty(String key, Object value) {
        check();
        String svalue = value != null ? value.toString() : null;
        XPathUtils.setValue(root, xpathOf(key), svalue, properties);
        updated = true;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private String xpathOf(String key) {
        return key.replace(".", "/");
    }

    private void check()  {
        try {
            if (isChanged())
                load();
        } catch (IOException e) {
            logger.errorf("Unable to parse configuration file %s", FileUtils.getAbsolutePath(configurationFile));
        }
    }
}

