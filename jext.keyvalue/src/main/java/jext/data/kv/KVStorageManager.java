package jext.data.kv;

import jext.data.kv.mapdb.MapDBStorageProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilderFactory;

public class KVStorageManager {

    private static Logger logger = LogManager.getLogger(KVStorageManager.class);

    private static final KVStorageManager instance = new KVStorageManager();

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static void configure() {
        instance.defaultConfiguration();
    }

    /** Configure the cache manager using a '.xml' or a '.properties' file */
    public static void configure(File configurationFile) {
        instance.configureUsing(configurationFile);
    }

    /** Configure the cache manager using the list of properties */
    public static void configure(Properties properties) throws Exception {
        instance.configureUsing(properties);
    }

    public static KVStorageProvider provider() {
        return instance.provider;
    }

    public static <K, V> KVStorage<K, V> open(File storageFile, Class<K> kclass, Class<V> vclass)
        throws IOException {
        return instance.openStorage(OpenMode.READ, storageFile, kclass, vclass);
    }

    public static <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass)
        throws IOException {
        return instance.openStorage(mode, storageFile, kclass, vclass);
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private Properties properties;
    private KVStorageProvider provider = new MapDBStorageProvider();

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private static final String DEFAULT_STORAGE_PROVIDER = "jext.data.kv.mapdb.MapDBStorageProvider";
    private static final String STORAGE_PROVIDER = "jext.data.kv.kvstorageProvider";

    private void defaultConfiguration() {
        String filename = System.getProperties().getProperty("kvstorage.config", "kvstorage4j.xml");
        File configurations = new File(filename);
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }
        configurations = new File("kvstorage4j.properties");
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }
        configurations = new File("config/kvstorage4j.xml");
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }
        configurations = new File("config/kvstorage4j.properties");
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }

        logger.warn("KS storage provider not defined. It will be used " + DEFAULT_STORAGE_PROVIDER);

        //throw new KVStorageException("Unable to configure KVStorageManager: no 'kvstorage4j.xml' found");
    }

    private void configureUsing(File configurationsFile) {
        String name = configurationsFile.getName();
        try {
            if (name.endsWith(".xml")) {
                Element configuration = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(configurationsFile)
                    .getDocumentElement();
                configureUsing(configuration);
            } else if (name.endsWith(".properties")) {
                Properties properties = new Properties();
                try(InputStream stream = new FileInputStream(configurationsFile)) {
                    properties.load(stream);
                }
                configureUsing(properties);
            } else {
                Properties properties = new Properties();
                properties.put(STORAGE_PROVIDER, DEFAULT_STORAGE_PROVIDER);
                configureUsing(properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureUsing(Element configuration) throws Exception {
        Properties properties = new Properties();
        // String providerClass = XPathUtils.getValue(configuration, "/configuration/provider/@value", DEFAULT_STORAGE_PROVIDER);
        String providerClass = ((Element)configuration.getElementsByTagName("provider").item(0))
            .getAttribute("value");
        if (providerClass.isEmpty())
            providerClass = DEFAULT_STORAGE_PROVIDER;
        properties.setProperty(STORAGE_PROVIDER, providerClass);
        configureUsing(properties);
    }

    private void configureUsing(Properties properties) throws Exception {
        this.properties = properties;
        String providerClass = properties.getProperty(STORAGE_PROVIDER, DEFAULT_STORAGE_PROVIDER);
        this.provider = (KVStorageProvider) Class.forName(providerClass).getConstructor().newInstance();
    }

    private <K, V> KVStorage<K, V> openStorage(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass)
        throws IOException {
        return provider.open(mode, storageFile, kclass, vclass, properties);
    }

}

