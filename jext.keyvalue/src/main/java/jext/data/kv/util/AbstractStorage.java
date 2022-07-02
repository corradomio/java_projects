package jext.data.kv.util;

import jext.data.kv.KVStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.AbstractMap;
import java.util.Set;

public class AbstractStorage<K, V> extends AbstractMap<K, V> implements KVStorage<K, V> {

    protected File storageFile;

    protected AbstractStorage(File storageFile) {
        this.storageFile = storageFile;
    }

    public File getStorageFile() {
        return storageFile;
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getStorage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(K key, V value) {
        put(key, value);
    }

    @Override
    public V putIfAbsent(@NotNull K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(@NotNull Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V replace(@NotNull K key, @NotNull V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(@NotNull K key, @NotNull V oldValue, @NotNull V newValue) {
        throw new UnsupportedOperationException();
    }
}
