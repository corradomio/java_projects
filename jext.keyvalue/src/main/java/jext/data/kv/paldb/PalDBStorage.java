package jext.data.kv.paldb;

import com.linkedin.paldb.api.StoreReader;
import com.linkedin.paldb.api.StoreWriter;
import jext.data.kv.util.AbstractStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class PalDBStorage<K, V> extends AbstractStorage<K, V> {

    private StoreReader reader;
    private StoreWriter writer;

    public PalDBStorage(File storageFile, StoreReader reader) {
        super(storageFile);
        this.reader = reader;
    }

    public PalDBStorage(File storageFile, StoreWriter writer) {
        super(storageFile);
        this.writer = writer;
    }

    @Override
    public Set<K> keySet() {
        Set<K> kset = new HashSet<>();
        reader.iterable().forEach(entry -> {
            kset.add((K) entry.getKey());
        });
        return kset;
    }

    @Override
    public void close() {
        if (reader != null)
            reader.close();
        if (writer != null)
            writer.close();
    }

    @Override
    public V get(Object key) {
        return reader.get(key);
    }

    @Override
    public V put(K key, V value) {
        writer.put(key, value);
        return null;
    }

    @Override
    public V putIfAbsent(@NotNull K key, V value) {
        writer.put(key, value);
        return null;
    }

}
