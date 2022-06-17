package jext.data.kv.berkleydb;

import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class BerkleyDBStorageProvider extends AbstractStorageProvider {

    private static final String CLASS_CATALOG = "java_class_catalog";

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        storageFile = toStorage(storageFile);

        if (!storageFile.exists() && !storageFile.mkdirs())
            throw new IOException("Unable to create directory " + storageFile);

        try {
            BerkleyDB berkleydb = new BerkleyDB();

            berkleydb.envconfig.setAllowCreate(true);
            berkleydb.env = new Environment(storageFile, berkleydb.envconfig);

            berkleydb.catconfig.setAllowCreate(true);

            berkleydb.catalogdb = berkleydb.env.openDatabase(null, CLASS_CATALOG, berkleydb.catconfig);
            berkleydb.catalog = new StoredClassCatalog(berkleydb.catalogdb);

            berkleydb.keyBinding   =  new SerialBinding(berkleydb.catalog, kclass);
            berkleydb.valueBinding =  new SerialBinding(berkleydb.catalog, vclass);

            // StoredContainer
            //      StoredMap
            //          StoredSortedMap
            //      StoredCollection
            //          StoredEntrySet
            //              StoredSortedEntrySet
            //          StoredList
            //          StoredValueSet
            //              StoredSortedValueSet
            //          StoredKeySet
            //              StoredSortedKeySet.

            berkleydb.dbconfig.setAllowCreate(true);
            berkleydb.db = berkleydb.env.openDatabase(null, nameOf(storageFile), berkleydb.dbconfig);
            berkleydb.smap = new StoredMap(berkleydb.db, berkleydb.keyBinding, berkleydb.valueBinding, true);

            return new BerkleyDBStorage(storageFile, berkleydb);
        } catch (DatabaseException e) {
            throw new IOException(e);
        }

    }

    @Override
    public String getFileExtension() {
        return ".berkleydb";
    }
}
