package jext.data.kv;

import java.io.File;
import java.util.concurrent.ConcurrentMap;

public interface KVStorage<K, V> extends ConcurrentMap<K, V> {

    File getStorage();

    void close();

}