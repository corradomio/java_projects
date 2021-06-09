package jext.persistent;

import org.apache.commons.lang3.SerializationUtils;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MapStore {

    private static class RocketsDBStorage<K extends Serializable, V extends Serializable> extends AbstractStore<K, V> {

        private RocksDB rocksdb;

        public RocketsDBStorage(File storageFile, RocksDB rocksdb) {
            super(storageFile);
            this.rocksdb = rocksdb;
        }

        public void close() {
            this.rocksdb.close();
        }

        public Set<K> keySet() {
            Set<K> kset = new HashSet<>();
            RocksIterator it = this.rocksdb.newIterator();
            while(it.isValid()) {
                byte[] value = it.key();
                K key = SerializationUtils.deserialize(value);
                kset.add(key);
            }
            return kset;
        }

        public V put(K key, V value) {
            try {
                this.rocksdb.put(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
                return null;
            } catch (RocksDBException e) {
                throw new RuntimeException(e);
            }
        }

        public V get(Object key) {
            try {
                byte[] value = this.rocksdb.get(SerializationUtils.serialize((Serializable) key));
                return SerializationUtils.deserialize(value);
            } catch (RocksDBException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <K, V> Map<K, V> open(File storageFile, Properties properties) throws IOException {
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
