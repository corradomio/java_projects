package jext.data.kv.swaydb;

import jext.data.kv.KVStorage;
import jext.data.kv.KVStorageProvider;
import jext.data.kv.OpenMode;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class SwayDBStorageProvider implements KVStorageProvider {
    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {

        return null;
    }
}
