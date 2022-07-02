package jext.data.kv.unqlite;

import jext.data.kv.util.AbstractStorage;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UnQLiteStorage<K, V> extends AbstractStorage<K, V> {

    private UnQLiteDB pDB;

    protected UnQLiteStorage(File storageFile, UnQLiteDB pDB) {
        super(storageFile);
        this.pDB = pDB;
    }

    @Override
    public void close() {
        pDB.close();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        pDB.keys().forEach(kdata -> {
            K key = SerializationUtils.deserialize(kdata);
            keys.add(key);
        });

        return keys;
    }

    @Override
    public V put(K key, V value) {
        byte[] kdata = SerializationUtils.serialize((Serializable) key);
        byte[] vdata = SerializationUtils.serialize((Serializable) value);
        pDB.store(kdata, vdata);
        return null;
    }

    @Override
    public V get(Object key) {
        byte[] kdata = SerializationUtils.serialize((Serializable) key);
        byte[] vdata = pDB.fetch(kdata);
        return SerializationUtils.deserialize(vdata);
    }
}
