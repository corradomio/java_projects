package jext.data.kv.leveldb;

import jext.data.kv.util.AbstractStorage;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LevelDBStorage<K extends Serializable, V extends Serializable> extends AbstractStorage<K, V> {

    private DB leveldb;

    public LevelDBStorage(File storageFile, DB leveldb) {
        super(storageFile);
        this.leveldb = leveldb;
    }

    @Override
    public void close() {
        try {
            this.leveldb.close();
        } catch (IOException e) { }
    }

    @Override
    public Set<K> keySet() {
        Set<K> kset = new HashSet<>();
        DBIterator it = this.leveldb.iterator();
        while(it.hasNext()) {
            byte[] value = it.next().getKey();
            K key = SerializationUtils.deserialize(value);
            kset.add(key);
        }
        return kset;
    }

    @Override
    public V put(K key, V value) {
        leveldb.put(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
        return null;
    }

    @Override
    public V get(Object key) {
        byte[] value = leveldb.get(SerializationUtils.serialize((Serializable) key));
        return SerializationUtils.deserialize(value);
    }
}
