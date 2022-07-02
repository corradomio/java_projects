package jext.data.kv.swaydb;

import jext.data.kv.util.AbstractStorage;
import org.jetbrains.annotations.NotNull;
import swaydb.java.Map;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SwayDBStorage<K, V> extends AbstractStorage<K, V> {

    private Map<K, V, Void> pmap;

    public SwayDBStorage(File storageFile, Map<K, V, Void> pmap) {
        super(storageFile);
        this.pmap = pmap;
    }

    @Override
    public void close() {
        pmap.close();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        pmap.forEach(kv -> {
            keys.add(kv.key());
        });
        return keys;
    }

    @Override
    public V get(Object key) {
        return pmap.get((K)key).get();
    }

    @Override
    public V put(K key, V value) {
        // Optional<V> old = pmap.get(key);
        pmap.put(key, value);
        return null;
    }

    @Override
    public boolean remove(@NotNull Object key, Object value) {
        Optional<V> old = pmap.get((K)key);
        pmap.remove((K)key);
        return old.isPresent();
    }

}
