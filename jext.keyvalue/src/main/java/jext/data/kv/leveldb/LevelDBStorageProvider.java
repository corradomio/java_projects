package jext.data.kv.leveldb;

import jext.data.kv.KVStorage;
import jext.data.kv.KVStorageProvider;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class LevelDBStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        storageFile = toStorage(storageFile);

        Options options = new Options();
        options.createIfMissing(true);

        DB db = Iq80DBFactory.factory.open(storageFile, options);

        return new LevelDBStorage(storageFile, db);
    }

    @Override
    protected String getFileExtension() {
        return ".leveldb";
    }
}
