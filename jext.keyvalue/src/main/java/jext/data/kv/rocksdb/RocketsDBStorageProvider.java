package jext.data.kv.rocksdb;

import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class RocketsDBStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
    throws IOException {
        storageFile = toStorage(storageFile);

        RocksDB.loadLibrary();
        Options options = new Options();
        options.setCreateIfMissing(true);
        storageFile.mkdirs();

        try {
            RocksDB db = RocksDB.open(options, storageFile.getAbsolutePath());

            return new RocketsDBStorage(storageFile, db);
        } catch (RocksDBException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected String getFileExtension() {
        return ".rocksdb";
    }
}