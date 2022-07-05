package jext.data.kv;

import java.io.File;
import java.util.concurrent.ConcurrentMap;

public interface KVStorage<K, V> extends ConcurrentMap<K, V>, AutoCloseable {

    File getStorage();

    void close();

    // V put(K key, V value);

    // V get(K key, V defval);
    // void add(K key, V value);

}
