package jext.data.kv.rocksdb;

import jext.data.kv.KVStorageError;
import jext.data.kv.util.AbstractStorage;
import org.apache.commons.lang3.SerializationUtils;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RocketsDBStorage<K extends Serializable, V extends Serializable> extends AbstractStorage<K, V> {

    private RocksDB rocksdb;

    public RocketsDBStorage(File storageFile, RocksDB rocksdb) {
        super(storageFile);
        this.rocksdb = rocksdb;
    }

    @Override
    public void close() {
        this.rocksdb.close();
    }

    @Override
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

    @Override
    public V put(K key, V value) {
        try {
            this.rocksdb.put(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
            return null;
        } catch (RocksDBException e) {
            throw new KVStorageError(e);
        }
    }

    @Override
    public V get(Object key) {
        try {
            byte[] value = this.rocksdb.get(SerializationUtils.serialize((Serializable) key));
            return SerializationUtils.deserialize(value);
        } catch (RocksDBException e) {
            throw new KVStorageError(e);
        }
    }
}
