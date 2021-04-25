package jext.configuration;

import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.Properties;

public class XMLConfiguration implements Configuration {

    private static Logger logger = Logger.getLogger(XMLConfiguration.class);

    private File configurationFile;
    private long timestamp;
    private Document doc;
    private Element root;
    private Properties properties;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public XMLConfiguration(File configurationFile) {
        this.configurationFile = configurationFile;
        this.timestamp = -1;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public Configuration configurationAt(String key) {
        return new InnerConfiguration(key, this);
    }

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

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private String xpathOf(String key) {
        return key.replace(".", "/");
    }

    private void check()  {
        try {
            if (!configurationFile.exists() && this.timestamp != 0) {
                this.doc = XPathUtils.parse("<configuration/>");
                this.root = this.doc.getDocumentElement();
                this.timestamp = 0;
            }
            else if (timestamp != configurationFile.lastModified()) {
                this.doc = XPathUtils.parse(configurationFile);
                this.root = this.doc.getDocumentElement();
                this.timestamp = configurationFile.lastModified();
            }
            this.properties = XPathUtils.getProperties(this.root);
        } catch (Exception e) {
            logger.errorf("Unable to parse configuration file %s", FileUtils.getAbsolutePath(configurationFile));
        }
    }
}

