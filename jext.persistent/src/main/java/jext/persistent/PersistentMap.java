package jext.persistent;

import java.util.Map;

public interface PersistentMap<K, V> extends Map<K, V> {

    void close();
}
