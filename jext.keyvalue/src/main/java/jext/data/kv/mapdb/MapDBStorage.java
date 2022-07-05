package jext.data.kv.mapdb;

import jext.data.kv.util.AbstractStorage;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DB;

import java.io.File;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class MapDBStorage<K, V> extends AbstractStorage<K, V> {

    private DB db;
    private ConcurrentMap map;

    public MapDBStorage(File storageFile, DB db, ConcurrentMap map) {
        super(storageFile);
        this.db = db;
        this.map = map;
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public void close() {
        db.close();
    }

    @Override
    public V get(Object key) {
        return (V) map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return (V) map.put(key, value);
    }

    @Override
    public V putIfAbsent(@NotNull K key, V value) {
        return (V) map.putIfAbsent(key, value);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return (V) map.getOrDefault(key, defaultValue);
    }

    @Override
    public boolean remove(@NotNull Object key, Object value) {
        return map.remove(key, value);
    }

    @Override
    public V replace(@NotNull K key, @NotNull V value) {
        return (V) map.replace(key, value);
    }

    @Override
    public boolean replace(@NotNull K key, @NotNull V oldValue, @NotNull V newValue) {
        return map.replace(key, oldValue, newValue);
    }

}
