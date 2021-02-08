package jext.data.kv.rocksdb;

import jext.data.kv.KVStorage;
import jext.data.kv.KVStorageProvider;
import jext.data.kv.OpenMode;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class RocketsDBStorageProvider implements KVStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
    throws IOException {
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
}