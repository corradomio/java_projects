package jext.data.kv.berkleydb;

import jext.data.kv.util.AbstractStorage;

import java.io.File;
import java.io.Serializable;
import java.util.Set;

public class BerkleyDBStorage<K extends Serializable, V extends Serializable> extends AbstractStorage<K, V> {

    private BerkleyDB berkleydb;

    public BerkleyDBStorage(File storageFile, BerkleyDB berkleydb) {
        super(storageFile);
        this.berkleydb = berkleydb;

    }

    @Override
    public void close() {
        this.berkleydb.close();
    }

    @Override
    public Set<K> keySet() {
        return this.berkleydb.smap.keySet();
    }

    @Override
    public V put(K key, V value) {
        return (V) this.berkleydb.smap.put(key, value);
    }

    @Override
    public V get(Object key) {
        return (V) this.berkleydb.smap.get(key);
    }
}
