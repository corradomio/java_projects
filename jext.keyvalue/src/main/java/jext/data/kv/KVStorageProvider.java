package jext.data.kv;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public interface KVStorageProvider {

    <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException;

}
