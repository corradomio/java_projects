package jext.data.kv.whippetdb;

import io.github.whippetdb.db.api.DbBuilder;
import jext.data.kv.util.AbstractStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class WhippetDBStorage<K, V> extends AbstractStorage<K, V> {

    private DbBuilder<K, V> builder;
    private Map<K, V> db;

    protected WhippetDBStorage(File storageFile, DbBuilder<K, V> builder) {
        super(storageFile);
        this.builder = builder;
        this.db = builder.asMap();
    }

    @Override
    public void close() {
        builder.db().close();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return db.keySet();
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return db.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return db.entrySet();
    }

    @Override
    public V get(Object key) {
        return db.get(key);
    }

    @Override
    public V put(K key, V value) {
        return db.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return db.remove(key);
    }
}
