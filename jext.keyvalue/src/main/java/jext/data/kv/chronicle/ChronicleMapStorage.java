package jext.data.kv.chronicle;

import jext.data.kv.util.AbstractStorage;
import net.openhft.chronicle.map.ChronicleMap;

import java.io.File;
import java.util.Set;

public class ChronicleMapStorage<K, V> extends AbstractStorage<K, V> {

    private ChronicleMap<K, V> cmap;

    protected ChronicleMapStorage(File storageFile, ChronicleMap<K, V> cmap) {
        super(storageFile);
        this.cmap = cmap;
    }

    @Override
    public void close() {
        this.cmap.close();
    }

    @Override
    public Set<K> keySet() {
        return cmap.keySet();
    }

    @Override
    public V put(K key, V value) {
        return cmap.put(key, value);
    }

    @Override
    public V get(Object key) {
        return cmap.get(key);
    }
}
